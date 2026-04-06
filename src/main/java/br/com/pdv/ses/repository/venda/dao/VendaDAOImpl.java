package br.com.pdv.ses.repository.venda.dao;

import br.com.pdv.ses.common.audit.UsuarioContexto;
import br.com.pdv.ses.config.database.DatabaseConfig;
import br.com.pdv.ses.domain.venda.Venda;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class VendaDAOImpl implements VendaDAO {

    /**
     * consulta o próximo valor da sequence no banco. nextval() avança a sequence permanentemente
     * mesmo quando transações são revertidas. design intencional, gaps evitam deadlocks em inserções concorrentes
     */
    @Override
    public Integer proximoIdVenda() {
        try (EntityManager em = DatabaseConfig.getEntityManager()) {
            return ((Number) em
                    .createNativeQuery("SELECT nextval('pdv_venda_seq')")
                    .getSingleResult()
            ).intValue();
        }
    }

    @Override
    public void adicionar(Venda venda) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            venda.setNmUsuarioCriacao(UsuarioContexto.get());
            venda.setNmUsuarioAlteracao(UsuarioContexto.get());
            em.persist(venda);
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao criar a venda", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Venda> buscar(Integer id) {
        try (EntityManager em = DatabaseConfig.getEntityManager()) {
            return Optional.ofNullable(em.find(Venda.class, id));
        }
    }

    @Override
    public void atualizar(Venda venda) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            venda.setNmUsuarioAlteracao(UsuarioContexto.get());
            em.merge(venda); // não valida existencia do registro
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar a venda");
        } finally {
            em.close();
        }
    }

    @Override
    public void remover(Integer id) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            Venda venda = em.find(Venda.class, id);
            if (venda != null) em.remove(venda);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao remover a venda");
        } finally {
            em.close();
        }
    }

}

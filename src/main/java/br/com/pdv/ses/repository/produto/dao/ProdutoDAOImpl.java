package br.com.pdv.ses.repository.produto.dao;

import br.com.pdv.ses.common.audit.UsuarioContexto;
import br.com.pdv.ses.config.database.DatabaseConfig;
import br.com.pdv.ses.domain.produto.Produto;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class ProdutoDAOImpl implements ProdutoDAO {

    /**
     * consulta o próximo valor da sequence no banco. nextval() avança a sequence permanentemente
     * mesmo quando transações são revertidas. design intencional, gaps evitam deadlocks em inserções concorrentes
     */
    @Override
    public Integer proximoIdProduto() {
        try (EntityManager em = DatabaseConfig.getEntityManager()) {
            return ((Number) em
                    .createNativeQuery("SELECT nextval('pdv_produto_seq')")
                    .getSingleResult()
            ).intValue();
        }
    }

    @Override
    public void adicionar(Produto produto) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            produto.setNmUsuarioCriacao(UsuarioContexto.get());
            produto.setNmUsuarioAlteracao(UsuarioContexto.get());
            em.persist(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao criar o produto ", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Produto> buscar(Integer id) {
        try (EntityManager em = DatabaseConfig.getEntityManager()) {
            return Optional.ofNullable(em.find(Produto.class, id));
        }
    }

    @Override
    public void atualizar(Produto produto) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            produto.setNmUsuarioAlteracao(UsuarioContexto.get());
            em.merge(produto); // não lança exceção se o registro não existir.
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar o produto ", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void remover(Integer id) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            Produto produto = em.find(Produto.class, id);
            if (produto != null) em.remove(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao remover o produto ", e);
        } finally {
            em.close();
        }
    }
}
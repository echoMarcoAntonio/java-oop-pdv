package br.com.pdv.ses.domain.venda;

import br.com.pdv.ses.common.exception.itemvenda.ItemVendaInvalidoException;
import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.com.pdv.ses.common.exception.MensagensExcecao.VENDA_FECHADA;

/**
 * Venda. entidade de domínio enriquecida
 * <p>
 * A entidade é um Aggregate Root.
 * agrupa o conjunto de objetos de domínio (ItemVenda + Produto)
 */
@Entity
@Table(name = "venda_tb")
@Audited
@AuditTable(value = "venda_audit_tb")
public class Venda {


    // identidade: id da venda
    @Id
    @Column(name = "venda_id", nullable = false)
    private Integer id;

    // itens da venda
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVenda> itens = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "venda_status", nullable = false)
    private Status status;

    /**
     * Campos de auditoria: quem gravou e quando. Preenchido pelo DAO no INSERT/UPDATE, jamais pelo construtor.
     * Criação: nmUsuarioCriacao + dtHrCriacao. Alteração: nmUsuarioAlteracao + dtHrAlteracao
     */
    @Column(name = "nm_usuario_criacao", length = 15)
    private String nmUsuarioCriacao;

    @CreationTimestamp
    @Column(name = "dt_hr_criacao", nullable = false, updatable = false)
    private LocalDateTime dtHrCriacao;

    @Column(name = "nm_usuario_alteracao", length = 15)
    private String nmUsuarioAlteracao;

    @UpdateTimestamp
    @Column(name = "dt_hr_alteracao", nullable = false)
    private LocalDateTime dtHrAlteracao;

    // construtores

    // vazio, exigido por JPA
    public Venda() {
    }

    // construtor de venda
    public Venda(
            Integer id,
            List<ItemVenda> itens
    ) {
        this.id = id;
        this.itens = itens;
    }

    // comportamentos do domínio

    /**
     * remove um item da venda e recalcula o total automaticamente.
     * caso o produto não seja encontrado retorna exceção
     */
    public void adicionarItemVenda(Integer idItemVenda, Integer quantidade) {
        if (this.status == Status.APROVADO) throw new ItemVendaInvalidoException(VENDA_FECHADA);

        ItemVenda item = new ItemVenda();
        item.setId(idItemVenda);
        item.setQuantidade(quantidade);
        item.setVenda(this);

        this.itens.add(item);
        this.recalcularTotal();
    }

    /**
     * remove um item da venda e recalcula o total automaticamente.
     * caso o produto não seja encontrado retorna exceção
     */
    public void removerItemVenda(Integer idItemVenda) {
        if (this.status == Status.APROVADO) throw new ItemVendaInvalidoException(VENDA_FECHADA);

        // remove da lista interna da memória
        this.itens.removeIf(item -> item.getId().equals(idItemVenda));

        // recalcula o total
        this.recalcularTotal();
    }

    /**
     * re-calcula o total da venda
     */
    private void recalcularTotal() {
    }

    // getters & setters
    public Integer getId() {
        return id;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // audit
    public String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }

    public String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }

    public LocalDateTime getDtHrCriacao() {
        return dtHrCriacao;
    }

    public LocalDateTime getDtHrAlteracao() {
        return dtHrAlteracao;
    }

    public void setNmUsuarioCriacao(String nmUsuarioCriacao) {

        // preenchido pelo DAO. nasce nulo no construtor do dominio. trim so roda quando tem valor
        this.nmUsuarioCriacao = nmUsuarioCriacao != null ? nmUsuarioCriacao.trim() : null;
    }

    public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao != null ? nmUsuarioAlteracao.trim() : null;
    }

    public void setDtHrCriacao(LocalDateTime dtHrCriacao) {
        this.dtHrCriacao = dtHrCriacao;
    }

    public void setDtHrAlteracao(LocalDateTime dtHrAlteracao) {
        this.dtHrAlteracao = dtHrAlteracao;
    }

    // métodos de identidade
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venda other)) return false;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Venda{id=" + id + "itens=" + "}";
    }
}

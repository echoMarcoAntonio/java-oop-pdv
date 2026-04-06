package br.com.pdv.ses.domain.venda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import br.com.pdv.ses.common.exception.itemvenda.ProdutoInvalidoException;
import br.com.pdv.ses.common.exception.itemvenda.QuantidadeInvalidaException;
import br.com.pdv.ses.domain.produto.Produto;
import br.com.pdv.ses.common.exception.MensagensExcecao;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 * ItemVenda. entidade de domínio enriquecida
 * <p>
 * a entidade representa um item no carrinho de compras.
 * O domínio nao conhece camadas como o mapper, dtos...
 */
@Entity
@Table(name = "itemvenda_tb")
@Audited
@AuditTable(value = "itemvenda_audit_tb")
public class ItemVenda {

    // identidade: id do item dentro da venda
    @Id
    @Column(name = "itemvenda_id", nullable = false)
    private Integer id;

    //campos essenciais

    @ManyToOne
    @JoinColumn(name = "venda_id")  //  cria a fk
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "item_preco_unitario")
    private BigDecimal precoUnitario;

    @Column(name = "item_preco_unitario")
    private BigDecimal subtotal;

    @Column(name = "itemvenda_quantidade", nullable = false)
    private int quantidade;

    /**
     * Campos de auditoria: quem gravou e quando. preenchido pelo DAO no INSERT/UPDATE
     * nao precisa, nem deve, ir no construtor ou receber validacoes.
     */
    @Column(name = "nm_usuario_criacao", length = 15)
    private String nmUsuarioCriacao;

    @CreationTimestamp
    @Column(name = "dt_hr_criacao", nullable = false, updatable = false)
    private LocalDateTime dtHrCriacao;

    @Column(name = "nm_usuario_alteracao", length = 15)
    private String nmUsuarioAlteracao;

    @UpdateTimestamp
    @Column(name = "dt_hr_alteracao")
    private LocalDateTime dtHrAlteracao;

    protected ItemVenda() {
    }

    public ItemVenda(
            Produto produto,
            int quantidade
    ) {
        validarProduto(produto);
        validarQuantidade(quantidade);

        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
    }

    // validações
    private static void validarProduto(Produto idProduto) {
        if (idProduto == null)
            throw new ProdutoInvalidoException(MensagensExcecao.PRODUTO_OBRIGATORIO);
    }

    private static void validarQuantidade(int quantidade) {
        if (quantidade <= 0)
            throw new QuantidadeInvalidaException(MensagensExcecao.QUANTIDADE_MINIMA);
    }

    // comportamentos do domínio

    /**
     * regra de negocio: ItemVenda sabe calcular seu valor total (produto + quantidade)
     */
    public BigDecimal getSubtotal() {
        return precoUnitario.multiply(new BigDecimal(quantidade));
    }

    //getters & setters

    public Integer getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // audit
    public String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }

    public LocalDateTime getDtHrCriacao() {
        return dtHrCriacao;
    }

    public String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
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


    // metodos de identidade
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemVenda other)) return false;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ItemVenda{id" + id + "produto=" + produto + ", quantidade=" + quantidade + "}";
    }
}

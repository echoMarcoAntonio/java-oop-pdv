package br.com.pdv.ses.domain.produto;

import br.com.pdv.ses.common.exception.produto.DescontoInvalidoException;
import jakarta.persistence.*;
import br.com.pdv.ses.common.exception.MensagensExcecao;
import br.com.pdv.ses.common.exception.produto.CodigoInvalidoException;
import br.com.pdv.ses.common.exception.produto.NomeInvalidoException;
import br.com.pdv.ses.common.exception.produto.PrecoInvalidoException;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Produto. entidade de domínio enriquecida
 * <p>
 * A entidade representa um item / produto no catalogo do supermercado.
 * O domínio nao conhece camadas como o mapper, dtos...
 */
@Entity
@Table(name = "produto_tb")
@Audited
@AuditTable(value = "produto_audit_tb")
public class Produto {

    // regras do dominio

    private static final int TAMANHO_MINIMO_NOME = 3;
    private static final int TAMANHO_MAXIMO_NOME = 100;

    // identidade: id do produto
    @Id
    @Column(name = "produto_id", nullable = false)
    private Integer id;

    // campos essenciais

    @Embedded
    private CodigoBarra cdBarra;

    @Column(name = "produto_nm", nullable = false, length = 100)
    private String nome;

    @Column(name = "produto_preco", nullable = false)
    private BigDecimal preco;

    @Column(name = "produto_desconto")
    private BigDecimal desconto;

    @Enumerated(EnumType.STRING)
    @Column(name = "produto_setor", nullable = false)
    private Setor setor;

    @Enumerated(EnumType.STRING)
    @Column(name = "produto_categoria", nullable = false)
    private Categoria categoria;

    @Column(name = "produto_fl_inativo", nullable = false)
    private boolean inativo;

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

    /**
     * construtor privado exigido pelo JPA. protected impede criacao fora do ORM
     * usado apenas por factory methods e reconstituicao.
     */
    protected Produto() {
    }

    public Produto(
            int id,
            CodigoBarra cdBarra,
            String nome,
            BigDecimal preco,
            Setor setor,
            Categoria categoria
    ) {
        this.id = validarIdProduto(id);
        this.nome = validarNome(nome);
        this.preco = validarPreco(preco);

        this.cdBarra = cdBarra;
        this.setor = setor;
        this.categoria = categoria;
        this.desconto = BigDecimal.ZERO;
        this.inativo = false;  // produto sempre nasce ativo
    }

    // validações
    private static Integer validarIdProduto(Integer id) {
        if (id == null || id <= 0)
            throw new CodigoInvalidoException(MensagensExcecao.CODIGO_PRODUTO_OBRIGATORIO);

        return id;
    }

    private static String validarNome(String nome) {
        if (nome == null || nome.isBlank())
            throw new NomeInvalidoException(MensagensExcecao.NOME_PRODUTO_OBRIGATORIO);

        String nomeTrimado = nome.trim();
        int tamanho = nomeTrimado.length();

        if (tamanho < TAMANHO_MINIMO_NOME)
            throw new NomeInvalidoException(MensagensExcecao.NOME_PRODUTO_CURTO_DEMAIS);

        if (tamanho > TAMANHO_MAXIMO_NOME)
            throw new NomeInvalidoException(MensagensExcecao.NOME_PRODUTO_LONGO_DEMAIS);

        return nomeTrimado; // persiste normalizado
    }

    private static BigDecimal validarPreco(BigDecimal preco) {
        if (preco == null)
            throw new PrecoInvalidoException(MensagensExcecao.PRECO_PRODUTO_OBRIGATORIO);

        if (preco.compareTo(BigDecimal.ZERO) <= 0)
            throw new PrecoInvalidoException(MensagensExcecao.PRECO_PRODUTO_NEGATIVO);

        return preco;
    }

    private BigDecimal validarDesconto(BigDecimal desconto) {
        if (desconto.compareTo(BigDecimal.ZERO) <= 0)
            throw new DescontoInvalidoException(MensagensExcecao.DESCONTO_PRODUTO_NEGATIVO);

        return desconto;
    }

    // comportamentos do domínio

    /**
     * altera a partir de um novo nome de produto ja validado e normalizado
     */
    public void alterarNome(String novoNome) {
        this.nome = validarNome(novoNome);
    }

    /**
     * aplica um desconto validado
     */
    public void aplicarDesconto(BigDecimal desconto) {
        this.desconto = validarDesconto(desconto);
    }

    /**
     * permite inativar o produto. as regras mais complexas pertencem ao service
     */
    public void inativar() {
        this.inativo = true;
    }

    /**
     * reativa o produto
     */
    public void reativar() {
        this.inativo = false;
    }

    // getters

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public CodigoBarra getCdBarra() {
        return cdBarra;
    }

    public Setor getSetor() {
        return setor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public boolean isInativo() {
        return inativo;
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

    // metodos de identidade
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto other)) return false;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Produto{id='" + id + "', nome='" + nome + "', preco=" + preco + "}";
    }
}

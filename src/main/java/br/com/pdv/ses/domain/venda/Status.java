package br.com.pdv.ses.domain.venda;

public enum Status {
    CANCELADO, // venda interrompida por falta de pagamento ou desistência
    PENDENTE, // pedido realizado, pagamento ainda não confirmado
    REJEITADO, // o pagamento foi negado pela operadora do cartão
    APROVADO, // pagamento aprovado
}

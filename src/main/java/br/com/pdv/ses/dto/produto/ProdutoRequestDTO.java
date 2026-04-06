package br.com.pdv.ses.dto.produto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProdutoRequestDTO(

        @NotBlank(message = "O código de barras do produto não pode ser informado em branco!")
        String cdBarra,

        @NotBlank(message = "O nome do produto não pode ser informado em branco!")
        @Size(min = 3, max = 100, message = "O nome do produto deve ter entre 3 e 100 caracteres!")
        String nome,

        @NotNull(message = "O preço do produto não pode ser nulo!")
        @DecimalMin(value = "0.0", inclusive = false, message = "O preço informado no produto deve ser positivo")
        BigDecimal preco,

        @DecimalMin(value = "0.0", message = "O desconto informado no produto deve ser positivo")
        BigDecimal desconto,

        @NotBlank(message = "A categoria do produto não pode ser informada em branco!")
        String categoria,

        @NotBlank(message = "O setor do produto não pode ser informado em branco!")
        String setor
) {
}

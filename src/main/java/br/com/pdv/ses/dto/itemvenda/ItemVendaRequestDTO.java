package br.com.pdv.ses.dto.itemvenda;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemVendaRequestDTO(

        @NotBlank(message = "O identificador do produto não pode ser passado em branco!")
        Integer idProduto,

        @NotNull(message = "O preço unitário do produto não pode ser nulo!")
        @DecimalMin(value = "0.0", inclusive = false, message = "O preço unitário informado deve ser positivo")
        BigDecimal precoUnitario,

        @NotNull
        @Min(value = 1)
        int quantidade
) {
}

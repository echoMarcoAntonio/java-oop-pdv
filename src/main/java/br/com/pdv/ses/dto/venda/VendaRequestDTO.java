package br.com.pdv.ses.dto.venda;

import br.com.pdv.ses.dto.itemvenda.ItemVendaRequestDTO;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record VendaRequestDTO(

        @NotEmpty(message = "O nome do produto não pode ser informado em branco!")
        List<ItemVendaRequestDTO> itens
) {
}

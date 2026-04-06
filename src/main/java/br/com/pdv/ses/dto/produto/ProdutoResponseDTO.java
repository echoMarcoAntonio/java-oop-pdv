package br.com.pdv.ses.dto.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoResponseDTO(

        Integer id,

        String cdBarras,

        String nome,

        BigDecimal preco,

        BigDecimal desconto,

        String setor,

        String categoria,

        String nmUsuarioCriacao,

        String nmUsuarioAlteracao,

        LocalDateTime dtHrCriacao,

        LocalDateTime dtHrAlteracao
) {
}

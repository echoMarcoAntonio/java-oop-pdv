package br.com.pdv.ses.controller.venda;

import br.com.pdv.ses.domain.venda.Venda;
import br.com.pdv.ses.dto.venda.VendaRequestDTO;
import br.com.pdv.ses.dto.venda.VendaResponseDTO;
import br.com.pdv.ses.mapper.venda.VendaMapper;
import br.com.pdv.ses.service.venda.VendaService;

import java.util.Optional;

public class VendaController {

    private final VendaService service;

    public VendaController(
            VendaService service
    ) {
        this.service = service;
    }

    public VendaResponseDTO adicionar(VendaRequestDTO dto) {
        Integer id = service.proximoId();
        Venda venda = VendaMapper.toEntity(dto, id);
        Venda salva = service.adicionar(venda);
        return VendaMapper.toResponse(salva);
    }

    public Optional<VendaResponseDTO> buscar(Integer id) {
        return service.buscar(id)
                .map(VendaMapper::toResponse);
    }

    public VendaResponseDTO atualizar(Integer id, VendaRequestDTO dto) {
        Venda venda = VendaMapper.toEntity(dto, id);
        Venda atualizada = service.atualizar(venda);
        return VendaMapper.toResponse(atualizada);
    }

    public void remover(Integer id) {
        service.buscar(id);
        service.remover(id);
    }
}

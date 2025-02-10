package com.lucasgusmao.gestao_de_estoque_api.controller;

import com.lucasgusmao.gestao_de_estoque_api.dto.FornecedorDTO;
import com.lucasgusmao.gestao_de_estoque_api.dto.ProdutoDTO;
import com.lucasgusmao.gestao_de_estoque_api.model.Fornecedor;
import com.lucasgusmao.gestao_de_estoque_api.service.FornecedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fornecedores")
public class FornecedorController {

    private FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping
    public ResponseEntity<List<FornecedorDTO>> listarTodos() {
        List<FornecedorDTO> fornecedores = fornecedorService.listarTodos()
                .stream()
                .map(this::toDTO)
                .collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<FornecedorDTO> buscarPorId(@PathVariable Long id) {
        Fornecedor fornecedor = fornecedorService.buscarPorId(id);
        return ResponseEntity.ok(toDTO(fornecedor));
    }

    @PostMapping
    public ResponseEntity<Fornecedor> salvar(@RequestBody FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = fornecedorService.salvar(toEntity(fornecedorDTO));
        return ResponseEntity.ok(fornecedor);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        fornecedorService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private FornecedorDTO toDTO(Fornecedor fornecedor) {
        FornecedorDTO dto = new FornecedorDTO();
        dto.setId(fornecedor.getId());
        dto.setNome(fornecedor.getNome());
        dto.setContato(fornecedor.getContato());
        return dto;
    }

    private Fornecedor toEntity(FornecedorDTO dto) {
        Fornecedor entity = new Fornecedor();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setContato(dto.getContato());
        return entity;
    }
}

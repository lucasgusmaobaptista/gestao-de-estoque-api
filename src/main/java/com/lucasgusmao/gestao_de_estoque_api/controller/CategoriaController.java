package com.lucasgusmao.gestao_de_estoque_api.controller;

import com.lucasgusmao.gestao_de_estoque_api.dto.CategoriaDTO;
import com.lucasgusmao.gestao_de_estoque_api.dto.ProdutoDTO;
import com.lucasgusmao.gestao_de_estoque_api.model.Categoria;
import com.lucasgusmao.gestao_de_estoque_api.model.Produto;
import com.lucasgusmao.gestao_de_estoque_api.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarTodos() {
        List<CategoriaDTO> categorias = categoriaService.listarTodos()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categorias);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(toDTO(categoria));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> salvar(@RequestBody CategoriaDTO dto) {
        Categoria categoria = categoriaService.salvar(toEntity(dto));
        return ResponseEntity.ok(toDTO(categoria));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private Categoria toEntity(CategoriaDTO dto) {
        Categoria entity = new Categoria();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        return entity;
    }

    private CategoriaDTO toDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());
        return dto;
    }

}

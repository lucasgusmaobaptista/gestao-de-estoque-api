package com.lucasgusmao.gestao_de_estoque_api.controller;

import com.lucasgusmao.gestao_de_estoque_api.dto.ProdutoDTO;
import com.lucasgusmao.gestao_de_estoque_api.model.Produto;
import com.lucasgusmao.gestao_de_estoque_api.service.ProdutoSevice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {

    private ProdutoSevice produtoSevice;

    public ProdutoController(ProdutoSevice produtoSevice) {
        this.produtoSevice = produtoSevice;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos() {
        List<ProdutoDTO> produtos = produtoSevice.listarTodos()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(produtos);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(toDTO(produtoSevice.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> salvar(@RequestBody ProdutoDTO dto) {
        Produto produto = produtoSevice.salvar(toEntity(dto));
        return ResponseEntity.ok(toDTO(produto));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        Produto produto = produtoSevice.atualizar(id, toEntity(dto));
        return ResponseEntity.ok(toDTO(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoSevice.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        dto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        dto.setFornecedorId(produto.getFornecedor().getId());
        dto.setCategoriaId(produto.getCategoria().getId());
        return dto;
    }

    private Produto toEntity(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        return produto;
    }
}

package com.lucasgusmao.gestao_de_estoque_api.controller;

import com.lucasgusmao.gestao_de_estoque_api.dto.ProdutoDTO;
import com.lucasgusmao.gestao_de_estoque_api.model.Categoria;
import com.lucasgusmao.gestao_de_estoque_api.model.Fornecedor;
import com.lucasgusmao.gestao_de_estoque_api.model.Produto;
import com.lucasgusmao.gestao_de_estoque_api.repository.CategoriaRepository;
import com.lucasgusmao.gestao_de_estoque_api.repository.FornecedorRepository;
import com.lucasgusmao.gestao_de_estoque_api.service.ProdutoSevice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {

    private ProdutoSevice produtoSevice;
    private CategoriaRepository categoriaRepository;
    private FornecedorRepository fornecedorRepository;

    public ProdutoController(ProdutoSevice produtoSevice, CategoriaRepository categoriaRepository, FornecedorRepository fornecedorRepository) {
        this.produtoSevice = produtoSevice;
        this.categoriaRepository = categoriaRepository;
        this.fornecedorRepository = fornecedorRepository;
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

        if (dto.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
            produto.setCategoria(categoria);
        }

        if (dto.getFornecedorId() != null) {
            Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado!"));
            produto.setFornecedor(fornecedor);
        }

        return produto;
    }
}

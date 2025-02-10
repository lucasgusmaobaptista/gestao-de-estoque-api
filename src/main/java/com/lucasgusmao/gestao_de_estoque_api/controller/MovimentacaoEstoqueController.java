package com.lucasgusmao.gestao_de_estoque_api.controller;

import com.lucasgusmao.gestao_de_estoque_api.dto.MovimentacaoEstoqueDTO;
import com.lucasgusmao.gestao_de_estoque_api.model.MovimentacaoEstoque;
import com.lucasgusmao.gestao_de_estoque_api.model.TipoMovimentacao;
import com.lucasgusmao.gestao_de_estoque_api.service.MovimentacaoEstoqueService;
import com.lucasgusmao.gestao_de_estoque_api.service.ProdutoSevice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movimentacoes")
public class MovimentacaoEstoqueController {

    private final ProdutoSevice produtoSevice;
    private MovimentacaoEstoqueService movimentacaoEstoqueService;

    public MovimentacaoEstoqueController(MovimentacaoEstoqueService movimentacaoEstoqueService, ProdutoSevice produtoSevice) {
        this.movimentacaoEstoqueService = movimentacaoEstoqueService;
        this.produtoSevice = produtoSevice;
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoEstoqueDTO>> listarTodas() {
        List<MovimentacaoEstoqueDTO> movimentacoes = movimentacaoEstoqueService.listarTodos().
                stream()
                .map(this::toDTO)
                .collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoEstoqueDTO> buscarPorId(@PathVariable Long id) {
        MovimentacaoEstoque movimentacaoEstoque = movimentacaoEstoqueService.buscarPorId(id);
        return ResponseEntity.ok(toDTO(movimentacaoEstoque));
    }

    @PostMapping("{produtoId}/entrada/{quantidade}")
    public ResponseEntity<MovimentacaoEstoqueDTO> registrarEntrada(@PathVariable Long produtoId, @PathVariable Integer quantidade) {
        MovimentacaoEstoque movimentacaoEstoque = movimentacaoEstoqueService.registrarMovimentacao(produtoId, quantidade, TipoMovimentacao.ENTRADA);
        return ResponseEntity.ok(toDTO(movimentacaoEstoque));
    }

    @PostMapping("{produtoId}/saida/{quantidade}")
    public ResponseEntity<MovimentacaoEstoqueDTO> registrarSaida(@PathVariable Long produtoId, @PathVariable Integer quantidade) {
        MovimentacaoEstoque movimentacaoEstoque = movimentacaoEstoqueService.registrarMovimentacao(produtoId, quantidade, TipoMovimentacao.SAIDA);
        return ResponseEntity.ok(toDTO(movimentacaoEstoque));
    }

    private MovimentacaoEstoqueDTO toDTO(MovimentacaoEstoque movimentacaoEstoque) {
        MovimentacaoEstoqueDTO dto = new MovimentacaoEstoqueDTO();
        dto.setId(movimentacaoEstoque.getId());
        dto.setProdutoId(movimentacaoEstoque.getProduto().getId());
        dto.setQuantidade(movimentacaoEstoque.getQuantidade());
        dto.setTipoMovimentacao(movimentacaoEstoque.getTipoMovimentacao());
        dto.setDataMovimentacao(movimentacaoEstoque.getData());
        dto.setValorTotal(movimentacaoEstoque.getValorTotal());
        return dto;
    }

    private MovimentacaoEstoque movimentacaoEstoque(MovimentacaoEstoqueDTO dto) {
        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
        movimentacaoEstoque.setId(dto.getId());
        movimentacaoEstoque.setProduto(produtoSevice.buscarPorId(dto.getProdutoId()));
        movimentacaoEstoque.setQuantidade(dto.getQuantidade());
        movimentacaoEstoque.setTipoMovimentacao(dto.getTipoMovimentacao());
        movimentacaoEstoque.setData(dto.getDataMovimentacao());
        movimentacaoEstoque.setValorTotal(dto.getValorTotal());
        return movimentacaoEstoque;
    }
}

package com.lucasgusmao.gestao_de_estoque_api.service;

import com.lucasgusmao.gestao_de_estoque_api.execption.ResourceNotFoundException;
import com.lucasgusmao.gestao_de_estoque_api.model.MovimentacaoEstoque;
import com.lucasgusmao.gestao_de_estoque_api.model.Produto;
import com.lucasgusmao.gestao_de_estoque_api.model.TipoMovimentacao;
import com.lucasgusmao.gestao_de_estoque_api.repository.MovimentacaoEstoqueRepository;
import com.lucasgusmao.gestao_de_estoque_api.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoEstoqueService {

    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    private ProdutoRepository produtoRepository;

    public MovimentacaoEstoqueService(MovimentacaoEstoqueRepository movimentacaoEstoqueRepository, ProdutoRepository produtoRepository) {
        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<MovimentacaoEstoque> listarTodos() {
        return movimentacaoEstoqueRepository.findAll();
    }

    public MovimentacaoEstoque buscarPorId(Long id) {
        return movimentacaoEstoqueRepository.findById(id).orElse(null);
    }

    @Transactional
    public MovimentacaoEstoque registrarMovimentacao(Long produtoId, Integer quantidade, TipoMovimentacao tipo) {
        Produto produto = produtoRepository.findById(produtoId).
                orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        if (tipo == TipoMovimentacao.SAIDA && produto.getQuantidadeEstoque() < quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente");
        }

        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque.Builder()
                .produto(produto)
                .quantidade(quantidade)
                .tipoMovimentacao(tipo)
                .data(LocalDateTime.now())
                .valorTotal(produto.getPreco().multiply(BigDecimal.valueOf(quantidade)))
                .build();

        if (tipo == TipoMovimentacao.ENTRADA) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
        } else {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        }
        produtoRepository.save(produto);
        return movimentacaoEstoqueRepository.save(movimentacaoEstoque);
    }
}

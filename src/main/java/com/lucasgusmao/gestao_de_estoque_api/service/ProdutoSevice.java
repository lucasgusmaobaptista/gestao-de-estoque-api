package com.lucasgusmao.gestao_de_estoque_api.service;

import com.lucasgusmao.gestao_de_estoque_api.execption.ResourceNotFoundException;
import com.lucasgusmao.gestao_de_estoque_api.model.Produto;
import com.lucasgusmao.gestao_de_estoque_api.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoSevice {

    private ProdutoRepository produtoRepository;

    public ProdutoSevice(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    }

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produto = buscarPorId(id);
        produto.setNome(produtoAtualizado.getNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
        produto.setCategoria(produtoAtualizado.getCategoria());
        produto.setFornecedor(produtoAtualizado.getFornecedor());
        return produtoRepository.save(produto);
    }

    @Transactional
    public void deletar(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }
}

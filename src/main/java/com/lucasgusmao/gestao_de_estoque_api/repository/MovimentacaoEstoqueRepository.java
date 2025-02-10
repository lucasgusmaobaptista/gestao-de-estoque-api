package com.lucasgusmao.gestao_de_estoque_api.repository;

import com.lucasgusmao.gestao_de_estoque_api.model.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {

    List<MovimentacaoEstoque> findByProdutoId(Long produtoId);
}

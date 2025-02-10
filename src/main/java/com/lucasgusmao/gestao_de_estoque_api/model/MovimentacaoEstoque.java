package com.lucasgusmao.gestao_de_estoque_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "movimentacoes_estoque")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipoMovimentacao;

    private LocalDateTime data;

    private BigDecimal valorTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }


    public static class Builder {
        private Long id;
        private Produto produto;
        private Integer quantidade;
        private TipoMovimentacao tipoMovimentacao;
        private LocalDateTime data;
        private BigDecimal valorTotal;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder produto(Produto produto) {
            this.produto = produto;
            return this;
        }

        public Builder quantidade(Integer quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public Builder tipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
            this.tipoMovimentacao = tipoMovimentacao;
            return this;
        }

        public Builder data(LocalDateTime data) {
            this.data = data;
            return this;
        }

        public Builder valorTotal(BigDecimal valorTotal) {
            this.valorTotal = valorTotal;
            return this;
        }

        public MovimentacaoEstoque build() {
            MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
            movimentacaoEstoque.id = this.id;
            movimentacaoEstoque.produto = this.produto;
            movimentacaoEstoque.quantidade = this.quantidade;
            movimentacaoEstoque.tipoMovimentacao = this.tipoMovimentacao;
            movimentacaoEstoque.data = this.data;
            movimentacaoEstoque.valorTotal = this.valorTotal;
            return movimentacaoEstoque;
        }
    }

}


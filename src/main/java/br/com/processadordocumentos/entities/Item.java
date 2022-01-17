package br.com.processadordocumentos.entities;

import java.math.BigDecimal;

public class Item extends Entity {
    
    private Long id;
    private Long quantidade;
    private BigDecimal preco;

    public Item(Long id, Long quantidade, BigDecimal preco) {
        this.id = id;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
}

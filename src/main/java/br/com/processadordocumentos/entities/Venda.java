package br.com.processadordocumentos.entities;

import java.util.List;

public class Venda extends Entity {
    
    private Long id;
    private List<Item> items;
    private String vendedorNome;

    public Venda(Long id, List<Item> items, String vendedorNome) {
        this.id = id;
        this.items = items;
        this.setVendedorNome(vendedorNome);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItens(Item item) {
        this.items.add(item);
    }

	public String getVendedorNome() {
		return vendedorNome;
	}

	public void setVendedorNome(String vendedorNome) {
		this.vendedorNome = vendedorNome;
	}
}

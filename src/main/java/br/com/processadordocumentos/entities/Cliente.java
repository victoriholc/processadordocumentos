package br.com.processadordocumentos.entities;

public class Cliente extends Entity {

    private String cnpj;
    private String nome;
    private String cargo;

    public Cliente(String cnpj, String nome, String cargo) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.setCargo(cargo);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}

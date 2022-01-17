package br.com.processadordocumentos.entities;

import java.math.BigDecimal;

public class Vendedor extends Entity {

    private String cpf;
    private String nome;
    private BigDecimal salario;

    public Vendedor(String cpf, String nome, BigDecimal salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public BigDecimal getSalary() {
        return salario;
    }

    public void setSalary(BigDecimal salary) {
        this.salario = salary;
    }

    
    
}

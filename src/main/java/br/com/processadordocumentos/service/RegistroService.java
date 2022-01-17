package br.com.processadordocumentos.service;

import br.com.processadordocumentos.entities.Cliente;
import br.com.processadordocumentos.entities.Entity;
import br.com.processadordocumentos.entities.Venda;
import br.com.processadordocumentos.entities.Item;
import br.com.processadordocumentos.entities.Vendedor;
import br.com.processadordocumentos.factories.ClienteFactory;
import br.com.processadordocumentos.factories.EntityFactory;
import br.com.processadordocumentos.factories.VendaFactory;
import br.com.processadordocumentos.factories.VendedorFactory;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegistroService {

    private List<Entity> datas;

    public RegistroService() {
    	datas = new ArrayList<Entity>();
    }

    public void registrarTudo(List<String> linhas){
    	linhas.stream().forEach(linha -> registrar(linha));
    }
    public void registrar(String linha) {
        EntityFactory factory = getCorrespondentFactory(linha);
        datas.add(factory.create(linha));
    }

    public void clear(){
    	datas.clear();
    }
    
    public List<Cliente> getClientes() {
        return datas.stream().
        		filter(data -> data instanceof Cliente).map(data -> (Cliente) data).
        		collect(Collectors.toList());
    }
    
    public List<Vendedor> getVendedores() {
        return datas.stream()
                .filter(data -> data instanceof Vendedor).map(data -> (Vendedor) data)
                .collect(Collectors.toList());
    }
    
    public List<Venda> getVendas() {
        return datas.stream().filter(data -> data instanceof Venda).map(data -> (Venda) data)
                .collect(Collectors.toList());
    }

    public Long getVendaMaisCaraId() {
        BigDecimal precoMaisCaro = BigDecimal.ZERO;
        Long vendaMaisCaraId = 0L;
        List<Venda> vendas = getVendas();
        for (Venda venda : vendas) {
            BigDecimal compraTotal = compraTotal(venda);
            if (precoMaisCaro.compareTo(compraTotal) <= 0) {
            	vendaMaisCaraId = venda.getId();
            	precoMaisCaro = compraTotal;
            }
        }
        return vendaMaisCaraId;
    }
    
    public String getPiorVendedor(){
        List<Venda> vendas = getVendas();
        BigDecimal vendaPiorPreco = compraTotal(vendas.get(0));
        Venda piorVenda = vendas.get(0);
        for(Venda venda : vendas) {
        	BigDecimal compraTotal = compraTotal(venda);
            if(vendaPiorPreco.compareTo(compraTotal) < 0){
            } else {
            	vendaPiorPreco = compraTotal;
                piorVenda = venda;
            }
        }
        
        return piorVenda.getVendedorNome();
    }
    
    public String getRelatrio(){
        return String.format("%dç%dç%02dç%s", getClientes().size(), getVendedores().size(), getVendaMaisCaraId(), getPiorVendedor());
    }

    private EntityFactory getCorrespondentFactory(String line) {
        String type = line.substring(0, 3);
        EntityFactory factory;

        switch (type) {
            case "001":
                factory = new VendedorFactory();
                break;
            case "002":
                factory = new ClienteFactory();
                break;
            case "003":
                factory = new VendaFactory();
                break;
            default:
                throw new InvalidParameterException("Registro inválido");
        }
        return factory;
    }

    private BigDecimal compraTotal(Venda venda) {
        BigDecimal compraTotal = BigDecimal.ZERO;
        for (Item item : venda.getItems()) {
            compraTotal = compraTotal.add(item.getPreco());
        }
        return compraTotal;
    }
}

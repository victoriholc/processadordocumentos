package br.com.processadordocumentos.factories;

import br.com.processadordocumentos.entities.Cliente;
import br.com.processadordocumentos.entities.Entity;

public class ClienteFactory implements EntityFactory{

	@Override
    public Entity create(String clienteData) {
        String[] splitData = clienteData.split("ç");
        return new Cliente(splitData[1], splitData[2], splitData[3]);
    }
    
}

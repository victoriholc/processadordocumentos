package br.com.processadordocumentos.factories;

import br.com.processadordocumentos.entities.Entity;
import br.com.processadordocumentos.entities.Vendedor;
import java.math.BigDecimal;

public class VendedorFactory implements EntityFactory {

	@Override
    public Entity create(String vendedorData) {
        String[] splitData = vendedorData.split("ç");
        return new Vendedor(splitData[1], splitData[2], new BigDecimal(splitData[3]));
    }

}

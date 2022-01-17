package br.com.processadordocumentos.factories;

import br.com.processadordocumentos.entities.Entity;
import br.com.processadordocumentos.entities.Item;
import java.math.BigDecimal;

public class ItemFactory implements EntityFactory {

    public ItemFactory() {
    }

    @Override
    public Entity create(String itemData) {
        String[] splitData = itemData.split("-");
        return new Item(Long.valueOf(splitData[0]), Long.valueOf(splitData[1]), new BigDecimal(splitData[2]));
    }
}

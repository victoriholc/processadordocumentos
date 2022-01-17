package br.com.processadordocumentos.factories;

import br.com.processadordocumentos.entities.Venda;
import br.com.processadordocumentos.entities.Entity;
import br.com.processadordocumentos.entities.Item;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendaFactory implements EntityFactory {

	@Override
    public Entity create(String vendaData) {
        String[] splitData = vendaData.split("ç");
        Venda venda = new Venda(Long.valueOf(splitData[1]), createItem(splitData[2]), splitData[3]);        
        return venda;
    }
    
    private List<Item> createItem(String itemData){
        List<Item> items = new ArrayList<Item>();
        List<String> itemsData = Arrays.asList(itemData.replace("[", "").replace("]", "").split(","));
        ItemFactory itemFactory = new ItemFactory();
        for(String item : itemsData) {
            items.add((Item) itemFactory.create(item));
        }
        return items;
    }
    
}

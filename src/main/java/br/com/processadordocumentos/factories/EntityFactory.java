package br.com.processadordocumentos.factories;

import br.com.processadordocumentos.entities.Entity;


public interface EntityFactory {
    
    /**
     *
     * @param data
     * @return Entity
     * 
     */
    abstract Entity create(String data);
    
}

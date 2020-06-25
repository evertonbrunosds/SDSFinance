/*
 * This file is part of the SDSFinance Open Source Project.
 * SDSFinance is licensed under the GNU GPLv3.
 *
 * Copyright (c) 2020. Everton Bruno Silva dos Santos <evertonbrunogithub@yahoo.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package model.territories;

import exceptions.NullObjectException;
import util.Filter;

/**
 * Classe responsável por comportar-se como uma rua.
 * @author Everton Bruno Silva dos Santos.
 */
public class Street extends TerritoryEditable implements IStreet {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = 4632318692274167268L;
    /**
     * Refere-se ao nome da rua.
     */
    private String name;

    /**
     * Construtor responsável pelo instanciamento da rua.
     * @param name Refere-se ao nome da rua.
     * @throws NullObjectException Exceção lançada no caso da string ser nula.
     */
    public Street(final String name) throws NullObjectException {
        Filter.nullObject(name);
        this.name = name;
    }

    /**
     * Método responsável por alterar o nome da rua.
     * @param key Refere-se ao novo nome da rua.
     * @throws NullObjectException Exceção lançada em caso de nome nulo.
     */
    @Override
    public void setKey(final String key) throws NullObjectException {
        Filter.nullObject(key);
        name = key;
    }

    /**
     * Método responsável por pré-visualizar nome da rua pós-alterações.
     * @param key Refere-se ao novo nome.
     * @return Retorna pré-visualização de novo nome.
     * @throws NullObjectException Exceção lançada em caso de nome nulo.
     */
    @Override
    public Comparable<String> previewKey(final String key) throws NullObjectException {
        Filter.nullObject(key);
        return key.toLowerCase();
    }

    /**
     * Método responsável por retornar nome da rua.
     * @return Retorna nome da rua.
     */
    @Override
    public Comparable<String> getKey() {
        return name.toLowerCase();
    }
    
    /**
     * Método responsável por retornar o nome da rua.
     * @return Retorna nome da rua.
     */
    @Override
    public String toString() {
        return name;
    }
    
}

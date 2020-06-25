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
package model.organization;

import exceptions.NullObjectException;
import model.territories.ICity;
import model.territories.INeighborhood;
import model.territories.IStreet;
import util.Filter;

/**
 * Classe responsável por comportar-se como fornecedor.
 * @author Everton Bruno Silva dos Santos.
 */
public class Provider extends OrganizationEditable implements IProvider {
    /**
     * Refere-se ao nome do fornecedor.
     */
    private String name;
    /**
     * Refere-se a rua do fornecedor.
     */
    private IStreet street;
    /**
     * Refere-se ao bairro do forencedor.
     */
    private INeighborhood neighborhood;
    /**
     * Refere-se a cidade do fornecedor.
     */
    private ICity city;

    /**
     * Construtor responsável pelo instanciamento do fornecedor.
     * @param name Refere-se ao nome do fornecedor.
     * @param street Refere-se a rua do fornecedor.
     * @param neighborhood Refere-se ao bairro do fornecedor.
     * @param city Refere-se a cidade do fornecedor.
     * @throws NullObjectException Exceção lançada no caso do nome ser nulo.
     */
    public Provider(String name, IStreet street, INeighborhood neighborhood, ICity city) throws NullObjectException {
        Filter.nullObject(name);
        this.name = name;
        this.street = getCorrectStreet(street);
        this.neighborhood = getCorrectNeighborhood(neighborhood);
        this.city = getCorrectCity(city);
    }

    /**
     * Método responsável por alterar o nome do fornecedor.
     * @param key Refere-se ao novo nome do fornecedor.
     * @throws NullObjectException Exceção lançada em caso de nome nulo.
     */
    @Override
    public void setKey(String key) throws NullObjectException {
        Filter.nullObject(key);
        this.name = key;
    }

    /**
     * Método responsável por alterar a rua do fornecedor.
     * @param street Refere-se a nova rua do fornecedor.
     */
    @Override
    public void setStreet(IStreet street) {
        this.street = getCorrectStreet(street);
    }
    
    /**
     * Método responsável por alterar o bairro do fornecedor.
     * @param neighborhood Refere-se ao nov bairro do fornecedor.
     */
    @Override
    public void setNeighborhood(INeighborhood neighborhood) {
        this.neighborhood = getCorrectNeighborhood(neighborhood);
    }

    /**
     * Método responsável por alterar a cidade do fornecedor.
     * @param city Refere-se a nova cidade do fornecedor.
     */
    @Override
    public void setCity(ICity city) {
        this.city = getCorrectCity(city);
    }

    /**
     * Método responsável por pré-visualizar nome do fornecedor pós-alterações.
     * @param key Refere-se ao novo nome.
     * @return Retorna pré-visualização de novo nome.
     * @throws NullObjectException Exceção lançada em caso de nome nulo.
     */
    @Override
    public Comparable<String> previewKey(String key) throws NullObjectException {
        Filter.nullObject(key);
        key = city.toString() + neighborhood.toString() + street.toString() + key;
        return key.toLowerCase();
    }

    /**
     * Método responsável por retornar nome do fornecedor.
     * @return Retorna nome da renda.
     */
    @Override
    public Comparable<String> getKey() {
        String key = city.toString() + neighborhood.toString() + street.toString() + name;
        return key.toLowerCase();
    }

    /**
     * Método responsável por retornar o nome do fornecedor.
     * @return Retorna nome do fornecedor.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Método responsável por retornar rua do fornecedor.
     * @return Retorna rua do fornecedor.
     */
    @Override
    public IStreet getStreet() {
        return street;
    }

    /**
     * Método responsável por retornar bairro do fornecedor.
     * @return Retorna bairro do fornecedor.
     */
    @Override
    public INeighborhood getNeighborhood() {
        return neighborhood;
    }

    /**
     * Método responsável por retornar cidade do fornecedor.
     * @return Retorna cidade do fornecedor.
     */
    @Override
    public ICity getCity() {
        return city;
    }
    
}

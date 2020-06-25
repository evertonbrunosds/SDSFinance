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
import control.GetCorrect;

/**
 * Classe responsável por comportar-se como fornecedor.
 * @author Everton Bruno Silva dos Santos.
 */
public class Provider extends OrganizationEditable implements IProvider {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = 5553043695336369639L;
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
     * @throws NullObjectException Exceção lançada no caso do nome de fornecedor nulo.
     */
    public Provider(final String name, final IStreet street, final INeighborhood neighborhood, final ICity city)
            throws NullObjectException {
        Filter.nullObject(name);
        this.name = name;
        this.street = GetCorrect.street(street);
        this.neighborhood = GetCorrect.neighborhood(neighborhood);
        this.city = GetCorrect.city(city);
    }

    /**
     * Método responsável por alterar chave do fornecedor.
     * @param key Refere-se ao novo nome do fornecedor.
     * @throws NullObjectException Exceção lançada em caso de nome de fornecedor nulo.
     */
    @Override
    public void setKey(final String key) throws NullObjectException {
        Filter.nullObject(key);
        this.name = key;
    }

    /**
     * Método responsável por alterar a rua do fornecedor.
     * @param street Refere-se a nova rua do fornecedor.
     */
    @Override
    public void setStreet(final IStreet street) {
        this.street = GetCorrect.street(street);
    }

    /**
     * Método responsável por alterar o bairro do fornecedor.
     * @param neighborhood Refere-se ao novo bairro do fornecedor.
     */
    @Override
    public void setNeighborhood(final INeighborhood neighborhood) {
        this.neighborhood = GetCorrect.neighborhood(neighborhood);
    }

    /**
     * Método responsável por alterar a cidade do fornecedor.
     * @param city Refere-se a nova cidade do fornecedor.
     */
    @Override
    public void setCity(final ICity city) {
        this.city = GetCorrect.city(city);
    }

    /**
     * Método responsável por pré-visualizar chave do fornecedor pós-alterações.
     * @param key Refere-se ao novo nome.
     * @return Retorna pré-visualização de nova chave.
     * @throws NullObjectException Exceção lançada em caso de nome de fornecedor nulo.
     */
    @Override
    public Comparable<String> previewKey(String key) throws NullObjectException {
        Filter.nullObject(key);
        key = city.toString() + neighborhood.toString() + street.toString() + key;
        return key.toLowerCase();
    }

    /**
     * Método responsável por pré-visualizar chave do fornecedor pós-alterações.
     * @param street Refere-se a nova cidade.
     * @return Retorna pré-visualização de nova chave.
     */
    @Override
    public Comparable<String> previewKey(final IStreet street) {
        final String key = city.toString() + neighborhood.toString() + street.toString() + name;
        return key.toLowerCase();
    }

    /**
     * Método responsável por pré-visualizar chave do fornecedor pós-alterações.
     * @param neighborhood Refere-se ao novo bairro.
     * @return Retorna pré-visualização da nova chave.
     */
    @Override
    public Comparable<String> previewKey(final INeighborhood neighborhood) {
        final String key = city.toString() + neighborhood.toString() + street.toString() + name;
        return key.toLowerCase();
    }

    /**
     * Método responsável por pré-visualizar chave do fornecedor pós-alterações.
     * @param city Refere-se a nova cidade.
     * @return Retorna pré-visualização da nova chave.
     */
    @Override
    public Comparable<String> previewKey(final ICity city) {
        final String key = city.toString() + neighborhood.toString() + street.toString() + name;
        return key.toLowerCase();
    }

    /**
     * Método responsável por retornar chave do fornecedor.
     * @return Retorna chave do fornecedor.
     */
    @Override
    public Comparable<String> getKey() {
        final String key = city.toString() + neighborhood.toString() + street.toString() + name;
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

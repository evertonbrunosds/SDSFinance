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

import control.Controller;
import exceptions.KeyUsedException;
import exceptions.NullObjectException;
import model.territories.ICity;
import model.territories.INeighborhood;
import model.territories.IStreet;
import util.IElement;

/**
 * Classe abstrata responsável por fornecer os métodos de organização editável.
 * @author Everton Bruno Silva dos Santos.
 */
public abstract class OrganizationEditable implements IOrganizationVisible, IElement<String> {
    
    /**
     * Método responsável por alterar o nome da oferta.
     * @param key Refere-se ao novo nome da oferta.
     * @throws NullObjectException Exceção lançada em caso de nome nulo.
     */
    @Override
    public abstract void setKey(String key) throws NullObjectException;
    
    /**
     * Método responsável por alterar a rua da organização.
     * @param street Refere-se a nova rua da organização.
     */
    public abstract void setStreet(IStreet street);
    
    /**
     * Método responsável por alterar o bairro da organização.
     * @param neighborhood Refere-se ao novo bairro da organização.
     */
    public abstract void setNeighborhood(INeighborhood neighborhood);
    
    /**
     * Método responsável por alterar a cidade da organização.
     * @param city Refere-se a nova cidade da organização.
     */
    public abstract void setCity(ICity city);
    
    /**
     * Método responsável por pré-visualizar nome da ofeta pós-alterações.
     * @param key Refere-se ao novo nome.
     * @return Retorna pré-visualização de novo nome.
     * @throws NullObjectException Exceção lançada em caso de nome nulo.
     */
    @Override
    public abstract Comparable<String> previewKey(String key) throws NullObjectException;
    
    /**
     * Método responsável por retornar a instância de rua correta.
     * @param street Refere-se a instância de rua possívelmente correta.
     * @return Retorna instância de rua correta.
     */
    protected static IStreet getCorrectStreet(final IStreet street) {
        try {
            Controller.getInstance().getStreetCollection().insert(street);
            return street;
        } catch (KeyUsedException ex) {
            return (IStreet) ex.getElement();
        }
    }
    
    /**
     * Método responsável por retornar a instância de bairro correto.
     * @param neighborhood Refere-se a instância de bairo possívelmente correto.
     * @return Retorna instância de bairro correta.
     */
    protected static INeighborhood getCorrectNeighborhood(final INeighborhood neighborhood) {
        try {
            Controller.getInstance().getNeighborhoodCollection().insert(neighborhood);
            return neighborhood;
        } catch (KeyUsedException ex) {
            return (INeighborhood) ex.getElement();
        }
    }
    
    /**
     * Método responsável por retornar a instância de cidade correta.
     * @param city Refere-se a instância de cidade possívelmente correta.
     * @return Retorna instância de cidade correta.
     */
    protected static ICity getCorrectCity(final ICity city) {
        try {
            Controller.getInstance().getCityCollection().insert(city);
            return city;
        } catch (KeyUsedException ex) {
            return (ICity) ex.getElement();
        }
    }
    
}

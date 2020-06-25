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
package util;

import control.Controller;
import exceptions.KeyUsedException;
import exceptions.NullObjectException;
import model.territories.City;
import model.territories.ICity;
import model.territories.INeighborhood;
import model.territories.IStreet;
import model.territories.Neighborhood;
import model.territories.Street;

/**
 * Classe responsável por comportar-se como fábrica.
 * @author Everton Bruno Silva dos Santos.
 */
public abstract class Factory {
    
    /**
     * Método responsável por gerar instância de rua.
     * @param name Refere-se ao nome da rua.
     * @return Retorna instância de rua.
     * @throws NullObjectException Exceção lançada em caso de nome de rua nulo.
     */
    public static IStreet street(final String name) throws NullObjectException {
        final IStreet street = new Street(name);
        try {
            Controller.getInstance().getStreetCollection().insert(street);
            return street;
        } catch (final KeyUsedException ex) {
            return (IStreet) ex.getElement();
        }
    }

    /**
     * Método responsável por gerar instância de bairro.
     * @param name Refere-se ao nome do bairro.
     * @return Retorna instância de bairro.
     * @throws NullObjectException Exceção lançada em caso de nome de bairro nulo.
     */
    public static INeighborhood neighborhood(final String name) throws NullObjectException {
        final INeighborhood neighborhood = new Neighborhood(name);
        try {
            Controller.getInstance().getNeighborhoodCollection().insert(neighborhood);
            return neighborhood;
        } catch (final KeyUsedException ex) {
            return (INeighborhood) ex.getElement();
        }
    }

    /**
     * Método responsável por gerar instância de cidade.
     * @param name Refere-se ao nome da cidade.
     * @return Retorna instância de cidade.
     * @throws NullObjectException Exceção lançada em caso de nome de cidade nulo.
     */
    public static ICity city(final String name) throws NullObjectException {
        final ICity city = new City(name);
        try {
            Controller.getInstance().getCityCollection().insert(city);
            return city;
        } catch (final KeyUsedException ex) {
            return (ICity) ex.getElement();
        }
    }
    
}

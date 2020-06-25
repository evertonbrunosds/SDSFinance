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
package control;

import exceptions.KeyUsedException;
import model.territories.ICity;
import model.territories.INeighborhood;
import model.territories.IStreet;

/**
 * Classe responsável por retornar as referências corretas de determinados objetos.
 * @author Everton Bruno Silva dos Santos.
 */
public abstract class GetCorrect {
    
    /**
     * Método responsável por retornar a instância de rua correta.
     * @param street Refere-se a instância de rua possívelmente correta.
     * @return Retorna instância de rua correta.
     */
    public static IStreet street(final IStreet street) {
        try {
            Controller.getInstance().getStreetCollection().insert(street);
            return street;
        } catch (final KeyUsedException ex) {
            return (IStreet) ex.getElement();
        }
    }

    /**
     * Método responsável por retornar a instância de bairro correto.
     * @param neighborhood Refere-se a instância de bairo possívelmente correto.
     * @return Retorna instância de bairro correto.
     */
    public static INeighborhood neighborhood(final INeighborhood neighborhood) {
        try {
            Controller.getInstance().getNeighborhoodCollection().insert(neighborhood);
            return neighborhood;
        } catch (final KeyUsedException ex) {
            return (INeighborhood) ex.getElement();
        }
    }

    /**
     * Método responsável por retornar a instância de cidade correta.
     * @param city Refere-se a instância de cidade possívelmente correta.
     * @return Retorna instância de cidade correta.
     */
    public static ICity city(final ICity city) {
        try {
            Controller.getInstance().getCityCollection().insert(city);
            return city;
        } catch (final KeyUsedException ex) {
            return (ICity) ex.getElement();
        }
    }
    
}

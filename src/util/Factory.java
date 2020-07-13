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
import exceptions.DateInvalidException;
import exceptions.IntegerValueInvalidException;
import exceptions.KeyUsedException;
import exceptions.NullObjectException;
import model.business.Acquisition;
import model.business.IAcquisition;
import model.offers.Expense;
import model.offers.IExpense;
import model.offers.IIncome;
import model.offers.IOfferVisible;
import model.offers.Income;
import model.organizations.IProvider;
import model.organizations.Provider;
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

    /**
     * Método responsável por gerar instância de fornecedor.
     * @param name         Refere-se ao nome do fornecedor.
     * @param street       Refere-se ao nome da rua do fornecedor.
     * @param neighborhood Refere-se ao nome do bairro do fornecedor.
     * @param city         Refere-se ao nome da cidade do fornecedor.
     * @return Retorna instância de fornecedor.
     * @throws NullObjectException Exceção lançada em caso de nome de fornecedor nulo.
     */
    public static IProvider provider(final String name, final IStreet street, final INeighborhood neighborhood,
            final ICity city) throws NullObjectException {
        return new Provider(name, street, neighborhood, city);
    }

    /**
     * Método responsável por gerar instância de despesa.
     * @param name  Refere-se ao nome da despesa.
     * @param value Refere-se ao valor da despesa.
     * @return Retorna instância de despesa.
     * @throws NullObjectException Exceção lançada em caso de nome de fornecedor nulo.
     */
    public static IExpense expense(final String name, final double value) throws NullObjectException {
        return new Expense(name, value);
    }

    /**
     * Método responsável por gerar instância de renda.
     * @param name  Refere-se ao nome da renda.
     * @param value Refere-se ao valor da renda.
     * @return Retorna instância de renda.
     * @throws NullObjectException Exceção lançada em caso de nome de fornecedor nulo.
     */
    public static IIncome income(final String name, final double value) throws NullObjectException {
        return new Income(name, value);
    }

    /**
     * Método responsável por gerar instância da data atual.
     * @return Retorna instância de data.
     */
    public static Date date() {
        return new Date();
    }

    /**
     * Método responsável por gerar instância de data especificada.
     * @param date Refere-se a data especificada.
     * @return Retorna instância de data.
     * @throws NullObjectException  Exceção lançada no caso da data não ser especificada.
     * @throws DateInvalidException Exceção lançada no caso da data especificada ser inválida.
     */
    public static Date date(final String date) throws NullObjectException, DateInvalidException {
        return new Date(date);
    }

    /**
     * Método responsável por gerar instância de aquisição.
     * @param provider Refere-se ao fornecedor da oferta.
     * @param offer    Refere-se a oferta adquirida.
     * @param amount   Refere-se a quantidade em que foi adquirida.
     * @param date     Refere-se a data em que foi adquirida.
     * @return Retorna instância de aquisição.
     * @throws NullObjectException          Exceção lançada no caso de haver o uso de strings nulas.
     * @throws IntegerValueInvalidException Exceção lançada no caso do valor quantitativo ser inválido.
     */
    public static IAcquisition acquisition(final IProvider provider, final IOfferVisible offer, final String amount,
            final Date date) throws NullObjectException, IntegerValueInvalidException {
        return new Acquisition(provider, offer, Converter.toInteger(amount), date);
    }

}
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
package model.organizations;

import java.io.Serializable;
import model.offers.IExpense;
import model.offers.IIncome;
import model.sets.IOfferCollection;
import model.territories.ICity;
import model.territories.INeighborhood;
import model.territories.IStreet;

/**
 * Interface responsável por fornecer os métodos de organização visível.
 * @author Everton Bruno Silva dos Sanots.
 */
public interface IOrganizationVisible extends Serializable {

    /**
     * Método responsável por retornar o nome da organização.
     * @return Retorna nome da organização.
     */
    @Override
    public String toString();

    /**
     * Método responsável por retornar rua da organização.
     * @return Retorna rua da organização.
     */
    public IStreet getStreet();

    /**
     * Método responsável por retornar bairro da organização.
     * @return Retorna bairro da organização.
     */
    public INeighborhood getNeighborhood();

    /**
     * Método responsável por retornar cidade da organização.
     * @return Retorna cidade da organização.
     */
    public ICity getCity();

    /**
     * Método responsável por retornar coleção de ofertas de despesa.
     * @return Retorna coleção de ofertas de despesa.
     */
    public IOfferCollection<IExpense> getExpenseCollection();

    /**
     * Método responsável por retornar coleção de ofertas de renda.
     * @return Retorna coleção de ofertas de renda.
     */
    public IOfferCollection<IIncome> getIncomeCollection();

    /**
     * Método responsável por retornar a chave comparável.
     * @return Retorna chave comparável.
     */
    public Comparable<String> getKey();
    
    /**
     * Método responsável por esvaziar as ofertas contidas na organização.
     */
    public void clear();

}
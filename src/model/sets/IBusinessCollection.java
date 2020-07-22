/*
 * This file is part of the SDSFinance Open Source Project.
 * SDSFinance is licensed under the GNU GPLv3.
 *
 * Copyright © 2020. Everton Bruno Silva dos Santos <evertonbrunogithub@yahoo.com>
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
package model.sets;

import exceptions.ElementNotFoundException;
import model.offers.IOfferVisible;
import model.organizations.IProvider;
import util.Date;

/**
 * Interface responsável por fornecer os métodos de uma coleção de negócios.
 * @author Everton Bruno Silva dos Santos.
 * @param <T> Refere-se ao tipo de negócio.
 */
public interface IBusinessCollection<T> extends ICollection<String, T> {

    /**
     * Método responsável por alterar o fornecedor do negócio.
     * @param key      Refere-se a chave do negócio.
     * @param provider Refere-se ao novo fornecedor.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     */
    public void setProvider(Comparable<String> key, IProvider provider) throws ElementNotFoundException;

    /**
     * Método responsável por alterar a oferta negociada.
     * @param key   Refere-se a chave do negócio.
     * @param offer Refere-se a nova oferta.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     */
    public void setOffer(Comparable<String> key, IOfferVisible offer) throws ElementNotFoundException;

    /**
     * Método responsável por alterar o valor da oferta negociada.
     * @param key          Refere-se a chave do negócio.
     * @param unitaryValue Refere-se ao novo valor da oferta.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     */
    public void setUnitaryValue(Comparable<String> key, double unitaryValue) throws ElementNotFoundException;

    /**
     * Método responsável por alterar a quantidade da oferta negociada.
     * @param key    Refere-se a chave do negócio.
     * @param amount Refere-se a nova quantidade da oferta.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     */
    public void setAmount(Comparable<String> key, int amount) throws ElementNotFoundException;

    /**
     * Método responsável por alterar a data do negócio.
     * @param key  Refere-se a chave do negócio.
     * @param date Refere-se a nova data do negócio.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     */
    public void setDate(Comparable<String> key, Date date) throws ElementNotFoundException;

    /**
     * Método responsável por inserir dado negócio na coleção.
     * @param newElement Refere-se ao elemento.
     */
    @Override
    public void insert(T newElement);

}
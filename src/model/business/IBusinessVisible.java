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
package model.business;

import java.io.Serializable;
import model.offers.IOfferVisible;
import model.organizations.IProvider;
import util.IDateVisible;

/**
 * Interface responsável por fornecer os métodos de um negócio visível.
 * @author Everton Bruno Silva dos Santos.
 */
public interface IBusinessVisible extends Serializable {

    /**
     * Método responsável por retornar o nome do negócio.
     * @return Retorna nome do negócio.
     */
    @Override
    public String toString();

    /**
     * Método responsável por retornar o fornecedor do negócio.
     * @return Retorna fornecedor do negócio.
     */
    public IProvider getProvider();

    /**
     * Método responsável por retornar a oferta do negócio.
     * @return Retorna oferta do negócio.
     */
    public IOfferVisible getOffer();

    /**
     * Método responsável por retornar a quantidade do negócios.
     * @return Retorna quantidade de negócios.
     */
    public int getAmount();

    /**
     * Método responsável por retornar o valor total do negócio.
     * @return Retorna valor total do negócio.
     */
    public double getTotalValue();

    /**
     * Método responsável por retornar o data do negócio.
     * @return Retorna data do negócio.
     */
    public IDateVisible getDate();

    /**
     * Método responsável por retornar chave comparável.
     * @return Retorna chave comparável.
     */
    public Comparable<String> getKey();

}
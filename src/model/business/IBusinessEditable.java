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

import exceptions.NullObjectException;
import model.offers.IOfferVisible;
import model.organizations.IProvider;
import util.Date;
import util.IElement;

/**
 * Interface responsável por fornecer os métodos de um negócio editável.
 * @author Everton Bruno Silva dos Santos.
 */
public interface IBusinessEditable extends IBusinessVisible, IElement<String> {

    /**
     * Método responsável por pré-visualizar chave do negócio pós-alterações.
     * @param provider Refere-se ao novo fornecedor.
     * @return Retorna pré-visualização de nova chave.
     */
    public Comparable<String> previewKey(IProvider provider);
    
    /**
     * Método responsável por pré-visualizar chave do negócio pós-alterações.
     * @param offer Refere-se a nova oferta.
     * @return Retorna pré-visualização de nova chave.
     */
    public Comparable<String> previewKey(IOfferVisible offer);

    /**
     * Método responsável por pré-visualizar chave do negócio pós-alterações.
     * @param key Refere-se ao novo nome.
     * @return Retorna pré-visualização de nova chave.
     * @throws NullObjectException Exceção lançada em caso de nome de negócio nulo.
     */
    @Override
    public Comparable<String> previewKey(String key) throws NullObjectException;

    /**
     * Método responsável por pré-visualizar chave do negócio pós-alterações.
     * @param unitaryValue Refere-se ao novo valor unitário.
     * @return Retorna pré-visualização de nova chave.
     */
    public Comparable<String> previewKey(double unitaryValue);

    /**
     * Método responsável por pré-visualizar chave do negócio pós-alterações.
     * @param date Refere-se ao nova data.
     * @return Retorna pré-visualização de nova chave.
     */
    public Comparable<String> previewKey(Date date);

    /**
     * Método responsável por alterar o fornecedor do negócio.
     * @param provider Refere-se ao novo fornecedor.
     */
    public void setProvider(IProvider provider);

    /**
     * Método responsável por alterar a oferta do negócio.
     * @param offer Refere-se a nova oferta.
     */
    public void setOffer(IOfferVisible offer);

    /**
     * Método responsável por alterar o nome da oferta do negócio.
     * @param key Refere-se ao novo nome de oferta.
     * @throws NullObjectException Exceção lançada no caso do nome ser nula.
     */
    @Override
    public void setKey(String key) throws NullObjectException;

    /**
     * Método responsável por alterar o valor unitário do negócio.
     * @param unitaryValue Refere-se ao novo valor unitário.
     */
    public void setUnitaryValue(double unitaryValue);

    /**
     * Método responsável por alterar a quantidade de ofertas do negócio.
     * @param amount Refere-se a nova quantidade de ofertas.
     */
    public void setAmount(int amount);

    /**
     * Método responsável por alterar a data do negócio.
     * @param date Refere-se a data do negócio.
     */
    public void setDate(Date date);

}
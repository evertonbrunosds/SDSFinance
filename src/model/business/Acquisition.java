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
import model.offers.IOfferEditable;
import model.offers.IOfferVisible;
import model.organizations.IProvider;
import util.Converter;
import util.Date;
import util.Filter;
import util.IDateVisible;

/**
 * Classe responsável por comportar-se como aquisição.
 * @author Everton Bruno Silva dos Santos.
 */
public class Acquisition implements IAcquisition, IBusinessEditable {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -6430448773162381185L;
    /**
     * Refere-se ao fornecedor.
     */
    private IProvider provider;
    /**
     * Refere-se a oferta.
     */
    private IOfferEditable offer;
    /**
     * Refere-se a quantidade de aquisição.
     */
    private int amount;
    /**
     * Refere-se a data de aquisição.
     */
    private Date date;

    /**
     * Construtor responsável pelo instanciamento da aquisição.
     * @param provider Refere-se ao fornecedor da aquisição.
     * @param offer    Refere-se a oferta adiquirida.
     * @param amount   Refere-se a quantidade em que foi adiquirida.
     * @param date     Refere-se a data em que foi adiquirida.
     */
    public Acquisition(final IProvider provider, final IOfferVisible offer, final int amount, final Date date) {
        this.provider = provider;
        this.offer = (IOfferEditable) offer;
        this.amount = Converter.toPositive(amount);
        this.date = date;
    }

    /**
     * Método responsável por retornar o fornecedor da aquisição.
     * @return Retorna fornecedor da aquisição.
     */
    @Override
    public IProvider getProvider() {
        return provider;
    }

    /**
     * Método responsável por retornar a oferta da aquisição.
     * @return Retorna oferta da aquisição.
     */
    @Override
    public IOfferVisible getOffer() {
        return offer;
    }

    /**
     * Método responsável por retornar a quantidade da aquisição.
     * @return Retorna quantidade de aquisição.
     */
    @Override
    public int getAmount() {
        return amount;
    }

    /**
     * Método responsável por retornar o valor total da aquisição.
     * @return Retorna valor total da aquisição.
     */
    @Override
    public double getTotalValue() {
        return amount * offer.getValue();
    }

    /**
     * Método responsável por retornar o data da aquisição.
     * @return Retorna data da aquisição.
     */
    @Override
    public IDateVisible getDate() {
        return date;
    }

    /**
     * Método responsável por retornar chave comparável.
     * @return Retorna chave comparável.
     */
    @Override
    public Comparable<String> getKey() {
        final String dateString = toString(date.getYear()) + toString(date.getMonth()) + toString(date.getDay());
        final String key = dateString + provider.getKey() + offer.toString() + offer.getValue();
        return key.toLowerCase();
    }

    /**
     * Método responsável por pré-visualizar chave da aquisição pós-alterações.
     * @param provider Refere-se ao novo fornecedor.
     * @return Retorna pré-visualização de nova chave.
     */
    @Override
    public Comparable<String> previewKey(final IProvider provider) {
        final String dateString = toString(date.getYear()) + toString(date.getMonth()) + toString(date.getDay());
        final String key = dateString + provider.getKey() + offer.toString() + offer.getValue();
        return key.toLowerCase();
    }
    
    /**
     * Método responsável por pré-visualizar chave da aquisição pós-alterações.
     * @param offer Refere-se a nova oferta.
     * @return Retorna pré-visualização de nova chave.
     */
    @Override
    public Comparable<String> previewKey(final IOfferVisible offer) {
        final String dateString = toString(date.getYear()) + toString(date.getMonth()) + toString(date.getDay());
        final String key = dateString + provider.getKey() + offer.toString() + offer.getValue();
        return key.toLowerCase();
    }

    /**
     * Método responsável por pré-visualizar chave da aquisição pós-alterações.
     * @param key Refere-se ao novo nome.
     * @return Retorna pré-visualização de nova chave.
     * @throws NullObjectException Exceção lançada em caso de nome de aquisição nula.
     */
    @Override
    public Comparable<String> previewKey(String key) throws NullObjectException {
        final String dateString = toString(date.getYear()) + toString(date.getMonth()) + toString(date.getDay());
        key = dateString + provider.getKey() + key + offer.getValue();
        return key.toLowerCase();
    }

    /**
     * Método responsável por pré-visualizar chave da aquisição pós-alterações.
     * @param unitaryValue Refere-se ao novo valor unitário.
     * @return Retorna pré-visualização de nova chave.
     */
    @Override
    public Comparable<String> previewKey(double unitaryValue) {
        if (offer.getValue() < 0) {
            unitaryValue = unitaryValue * -1;
        }
        final String dateString = toString(date.getYear()) + toString(date.getMonth()) + toString(date.getDay());
        final String key = dateString + provider.getKey() + offer.toString() + unitaryValue;
        return key.toLowerCase();
    }

    /**
     * Método responsável por pré-visualizar chave da aquisição pós-alterações.
     * @param date Refere-se ao nova data.
     * @return Retorna pré-visualização de nova chave.
     */
    @Override
    public Comparable<String> previewKey(final Date date) {
        final String dateString = toString(date.getYear()) + toString(date.getMonth()) + toString(date.getDay());
        final String key = dateString + provider.getKey() + offer.toString() + offer.getValue();
        return key.toLowerCase();
    }

    /**
     * Método responsável por alterar o fornecedor da aquisição.
     * @param provider Refere-se ao novo fornecedor.
     */
    @Override
    public void setProvider(final IProvider provider) {
        this.provider = provider;
    }

    /**
     * Método responsável por alterar a oferta da aquisição.
     * @param offer Refere-se a nova oferta.
     */
    @Override
    public void setOffer(final IOfferVisible offer) {
        this.offer = (IOfferEditable) offer;
    }

    /**
     * Método responsável por alterar o nome da oferta da aquisição.
     * @param key Refere-se ao novo nome de oferta.
     * @throws NullObjectException Exceção lançada no caso do nome ser nula.
     */
    @Override
    public void setKey(final String key) throws NullObjectException {
        Filter.nullObject(key);
        this.offer.setKey(key);
    }

    /**
     * Método responsável por alterar o valor unitário da aquisição.
     * @param unitaryValue Refere-se ao novo valor unitário.
     */
    @Override
    public void setUnitaryValue(final double unitaryValue) {
        this.offer.setValue(unitaryValue);
    }

    /**
     * Método responsável por alterar a quantidade de ofertas da aquisição.
     * @param amount Refere-se a nova quantidade de ofertas.
     */
    @Override
    public void setAmount(final int amount) {
        this.amount = Converter.toPositive(amount);
    }

    /**
     * Método responsável por alterar a data da aquisição.
     * @param date Refere-se a data da aquisição.
     */
    @Override
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Método responsável por converter para string de dois caracteres, números que
     * expressam dia e mês.
     * @param number Refere-se ao número que será convertido.
     * @return Retorna string de dois caracteres que expressam dia e mês.
     */
    private String toString(final int number) {
        final String numberStr = Converter.toString(number);
        if (numberStr.length() < 2) {
            return "0" + numberStr;
        } else {
            return numberStr;
        }
    }

    /**
     * Método responsável por retornar o nome da aquisição.
     * @return Retorna nome da aquisição.
     */
    @Override
    public String toString() {
        return this.offer.toString();
    }

}
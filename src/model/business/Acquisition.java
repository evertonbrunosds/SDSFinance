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
public class Acquisition {

    private IProvider provider;
    private IOfferEditable offer;
    private int amount;
    private Date date;

    public Acquisition(IProvider provider, IOfferVisible offer, int amount, Date date) {
        this.provider = provider;
        this.offer = (IOfferEditable) offer;
        this.amount = Converter.toPositive(amount);
        this.date = date;
    }

    public void setKey(String key) throws NullObjectException {
        Filter.nullObject(key);
        this.offer.setKey(key);
    }

    public Comparable<String> getKey() {
        final String dateString = toString(date.getYear()) + toString(date.getMonth()) + toString(date.getDay());
        final String key = dateString + provider.toString() + offer.toString() + offer.getValue();
        return key.toLowerCase();
    }

    public Comparable<String> previewKey(String key) throws NullObjectException {
        final String dateString = toString(date.getYear()) + toString(date.getMonth()) + toString(date.getDay());
        key = dateString + provider.toString() + key + offer.getValue();
        return key.toLowerCase();
    }

    public Comparable<String> previewKey(final double value) {
        final String dateString = toString(date.getYear()) + toString(date.getMonth()) + toString(date.getDay());
        final String key = dateString + provider.toString() + offer.toString() + value;
        return key.toLowerCase();
    }

    public Comparable<String> previewKey(final IProvider provider) {
        final String dateString = toString(date.getYear()) + toString(date.getMonth()) + toString(date.getDay());
        final String key = dateString + provider.toString() + offer.toString() + offer.getValue();
        return key.toLowerCase();
    }

    public Comparable<String> previewKey(final Date date) {
        final String dateString = toString(date.getYear()) + toString(date.getMonth()) + toString(date.getDay());
        final String key = dateString + provider.toString() + offer.toString() + offer.getValue();
        return key.toLowerCase();
    }
    
    public void setAmount(int amount) {
        this.amount = Converter.toPositive(amount);
    }
    
    public void setValue(double value) {
        this.offer.setValue(value);
    }
    
    @Override
    public String toString() {
        return this.offer.toString();
    }

    private String toString(final int number) {
        final String numberStr = Converter.toString(number);
        if (numberStr.length() < 2) {
            return "0" + numberStr;
        } else {
            return numberStr;
        }
    }

    public IProvider getProvider() {
        return provider;
    }

    public IOfferVisible getOffer() {
        return offer;
    }

    public int getAmount() {
        return amount;
    }

    public IDateVisible getDate() {
        return date;
    }

}

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
package model.sets;

import exceptions.ElementNotFoundException;
import exceptions.KeyUsedException;
import model.business.IBusinessEditable;
import model.offers.IOfferVisible;
import model.organizations.IProvider;
import util.Date;

/**
 * Classe responsável por comportar-se como coleção de negócios.
 * @author Everton Bruno Silva dos Santos
 * @param <T> Refere-se ao tipo de negócio.
 */
public class BusinessCollection<T> extends Collection<String, T> implements IBusinessCollection<T> {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -4742459143127158906L;

    /**
     * Método responsável por alterar o fornecedor do negócio.
     * @param key      Refere-se a chave do negócio.
     * @param provider Refere-se ao novo fornecedor.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     */
    @Override
    public void setProvider(final Comparable<String> key, final IProvider provider) throws ElementNotFoundException {
        try {
            this.redefineKey(key, provider);
        } catch (final KeyUsedException ex) {
            ((IBusinessEditable) ex.getElement()).setAmount(
                    ((IBusinessEditable) ex.getElement()).getAmount() + ((IBusinessEditable) search(key)).getAmount());
        }
    }

    /**
     * Método responsável por alterar o fornecedor do negócio.
     * @param key      Refere-se a chave do negócio.
     * @param provider Refere-se ao novo fornecedor.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outro negócio.
     */
    private void redefineKey(final Comparable<String> key, final IProvider provider)
            throws ElementNotFoundException, KeyUsedException {
        final IBusinessEditable businessInCurrentState = (IBusinessEditable) super.search(key);
        try {
            final IBusinessEditable businessInNewState = (IBusinessEditable) super.search(
                    businessInCurrentState.previewKey(provider));
            if (!businessInCurrentState.equals(businessInNewState)) {
                throw new KeyUsedException(businessInNewState);
            } else {
                redefineKey(businessInCurrentState, provider);
            }
        } catch (final ElementNotFoundException ex) {
            redefineKey(businessInCurrentState, provider);
        }
    }

    /**
     * Método responsável por efetuar a redefinição de fornecedor de um negócio.
     * @param businessInCurrentState Refere-se ao negócio em seu atual estado.
     * @param newProvider            Refere-se ao novo fornecedor do negócio.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outro negócio.
     */
    private void redefineKey(final IBusinessEditable businessInCurrentState, final IProvider newProvider)
            throws ElementNotFoundException, KeyUsedException {
        super.remove(businessInCurrentState.getKey());
        businessInCurrentState.setProvider(newProvider);
        super.insert((T) businessInCurrentState);
    }

    /**
     * Método responsável por alterar a oferta negociada.
     * @param key   Refere-se a chave do negócio.
     * @param offer Refere-se a nova oferta.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     */
    @Override
    public void setOffer(final Comparable<String> key, final IOfferVisible offer) throws ElementNotFoundException {
        try {
            this.redefineKey(key, offer);
        } catch (final KeyUsedException ex) {
            ((IBusinessEditable) ex.getElement()).setAmount(
                    ((IBusinessEditable) ex.getElement()).getAmount() + ((IBusinessEditable) search(key)).getAmount());
        }
    }

    /**
     * Método responsável por alterar a oferta negociada.
     * @param key   Refere-se a chave do negócio.
     * @param offer Refere-se a nova oferta.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outro negócio.
     */
    private void redefineKey(final Comparable<String> key, final IOfferVisible offer)
            throws ElementNotFoundException, KeyUsedException {
        final IBusinessEditable businessInCurrentState = (IBusinessEditable) super.search(key);
        try {
            final IBusinessEditable businessInNewState = (IBusinessEditable) super.search(
                    businessInCurrentState.previewKey(offer));
            if (!businessInCurrentState.equals(businessInNewState)) {
                throw new KeyUsedException(businessInNewState);
            } else {
                redefineKey(businessInCurrentState, offer);
            }
        } catch (final ElementNotFoundException ex) {
            redefineKey(businessInCurrentState, offer);
        }
    }

    /**
     * Método responsável por efetuar a redefinição de oferta de um negócio.
     * @param businessInCurrentState Refere-se ao negócio em seu atual estado.
     * @param newOffer               Refere-se a nova oferta do negócio.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outro negócio.
     */
    private void redefineKey(final IBusinessEditable businessInCurrentState, final IOfferVisible newOffer)
            throws ElementNotFoundException, KeyUsedException {
        super.remove(businessInCurrentState.getKey());
        businessInCurrentState.setOffer(newOffer);
        super.insert((T) businessInCurrentState);
    }

    /**
     * Método responsável por alterar o valor da oferta negociada.
     * @param key          Refere-se a chave do negócio.
     * @param unitaryValue Refere-se ao novo valor da oferta.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     */
    @Override
    public void setUnitaryValue(final Comparable<String> key, final double unitaryValue) throws ElementNotFoundException {
        try {
            this.redefineKey(key, unitaryValue);
        } catch (final KeyUsedException ex) {
            ((IBusinessEditable) ex.getElement()).setAmount(
                    ((IBusinessEditable) ex.getElement()).getAmount() + ((IBusinessEditable) search(key)).getAmount());
        }
    }

    /**
     * Método responsável por alterar o valor da oferta negociada.
     * @param key          Refere-se a chave do negócio.
     * @param unitaryValue Refere-se ao novo valor unitário do negócio.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outro negócio.
     */
    private void redefineKey(final Comparable<String> key, final double unitaryValue)
            throws ElementNotFoundException, KeyUsedException {
        final IBusinessEditable businessInCurrentState = (IBusinessEditable) super.search(key);
        try {
            final IBusinessEditable businessInNewState = (IBusinessEditable) super.search(
                    businessInCurrentState.previewKey(unitaryValue));
            if (!businessInCurrentState.equals(businessInNewState)) {
                throw new KeyUsedException(businessInNewState);
            } else {
                redefineKey(businessInCurrentState, unitaryValue);
            }
        } catch (final ElementNotFoundException ex) {
            redefineKey(businessInCurrentState, unitaryValue);
        }
    }

    /**
     * Método responsável por alterar o valor da oferta negociada.
     * @param businessInCurrentState Refere-se ao negócio em seu atual estado.
     * @param unitaryValue           Refere-se ao novo valor unitário do negócio.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outro negócio.
     */
    private void redefineKey(final IBusinessEditable businessInCurrentState, final double unitaryValue)
            throws ElementNotFoundException, KeyUsedException {
        super.remove(businessInCurrentState.getKey());
        businessInCurrentState.setUnitaryValue(unitaryValue);
        super.insert((T) businessInCurrentState);
    }

    /**
     * Método responsável por alterar a quantidade da oferta negociada.
     * @param key    Refere-se a chave do negócio.
     * @param amount Refere-se a nova quantidade da oferta.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     */
    @Override
    public void setAmount(final Comparable<String> key, final int amount) throws ElementNotFoundException {
        ((IBusinessEditable) super.search(key)).setAmount(amount);
    }

    /**
     * Método responsável por alterar a data do necócio.
     * @param key  Refere-se a chave do negócio.
     * @param date Refere-se a nova data do negócio.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     */
    @Override
    public void setDate(final Comparable<String> key, final Date date) throws ElementNotFoundException {
        try {
            this.redefineKey(key, date);
        } catch (final KeyUsedException ex) {
            ((IBusinessEditable) ex.getElement()).setAmount(
                    ((IBusinessEditable) ex.getElement()).getAmount() + ((IBusinessEditable) search(key)).getAmount());
        }
    }

    /**
     * Método responsável por alterar a data do necócio.
     * @param key  Refere-se a chave do negócio.
     * @param date Refere-se a nova data do negócio.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outro negócio.
     */
    private void redefineKey(final Comparable<String> key, final Date date) throws ElementNotFoundException, KeyUsedException {
        final IBusinessEditable businessInCurrentState = (IBusinessEditable) super.search(key);
        try {
            final IBusinessEditable businessInNewState = (IBusinessEditable) super.search(
                    businessInCurrentState.previewKey(date));
            if (!businessInCurrentState.equals(businessInNewState)) {
                throw new KeyUsedException(businessInNewState);
            } else {
                redefineKey(businessInCurrentState, date);
            }
        } catch (final ElementNotFoundException ex) {
            redefineKey(businessInCurrentState, date);
        }
    }

    /**
     * Método responsável por alterar a data do necócio.
     * @param key  Refere-se a chave do negócio.
     * @param date Refere-se a nova data do negócio.
     * @throws ElementNotFoundException Exceção lançada no caso do negócio não ser encontrado.
     * @throws KeyUsedException         Exceção lançada no caso da chave estar em uso por outro negócio.
     */
    private void redefineKey(final IBusinessEditable businessInCurrentState, final Date date)
            throws ElementNotFoundException, KeyUsedException {
        super.remove(businessInCurrentState.getKey());
        businessInCurrentState.setDate(date);
        super.insert((T) businessInCurrentState);
    }

    /**
     * Método responsável por insetir dado negócio na coleção.
     * @param newElement Refere-se ao elemento.
     */
    @Override
    public void insert(final T newElement) {
        try {
            super.insert(newElement);
        } catch (final KeyUsedException ex) {
            final IBusinessEditable myElement = (IBusinessEditable) ex.getElement();
            myElement.setAmount(myElement.getAmount() + ((IBusinessEditable) newElement).getAmount());

        }
    }

}
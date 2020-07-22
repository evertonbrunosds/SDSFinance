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
import model.offers.IOfferEditable;

/**
 * Classe responsável por comportar-se como coleção de ofertas.
 * @author Everton Bruno Silva dos Santos.
 * @param <T> Refere-se ao tipo de oferta.
 */
public class OfferCollection<T> extends Collection<String, T> implements IOfferCollection<T> {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = 3858720943074218736L;

    /**
     * Método responsável por alterar o valor de uma oferta.
     * @param key   Refere-se a chave da oferta.
     * @param value Refere-se ao novo valor da oferta.
     * @throws ElementNotFoundException Exceção lançada no caso da oferta não ser encontrada.
     */
    @Override
    public void setValue(final Comparable<String> key, final double value) throws ElementNotFoundException {
        ((IOfferEditable) super.search(key)).setValue(value);
    }

}
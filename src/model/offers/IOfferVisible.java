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
package model.offers;

import java.io.Serializable;
import util.IDuplicable;

/**
 * Interface responsável por fornecer os métodos de oferta visível.
 * @author Everton Bruno Silva dos Santos.
 */
public interface IOfferVisible extends Serializable, IDuplicable {

    /**
     * Método responsável por retornar o nome da oferta.
     * @return Retorna nome da oferta.
     */
    @Override
    public String toString();

    /**
     * Método responsável por duplicar oferta.
     * @return Retorna oferta duplicata.
     */
    @Override
    public IOfferVisible duplicate();

    /**
     * Método responsável por retornar valor de oferta.
     * @return Retorna valor de oferta.
     */
    public double getValue();

    /**
     * Método responsável por retornar chave comparável.
     * @return Retorna chave comparável.
     */
    public Comparable<String> getKey();

}
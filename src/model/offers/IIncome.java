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
package model.offers;

/**
 * Interface responsável por fornecer os métodos de renda.
 * @author Everton Bruno Silva dos Santos.
 */
public interface IIncome extends IOfferVisible {

    /**
     * Método responsável por duplicar renda.
     * @return Retorna renda duplicata.
     */
    @Override
    public IIncome duplicate();

    /**
     * Método responsável por retornar o nome da renda.
     * @return Retorna nome da renda.
     */
    @Override
    public String toString();

}
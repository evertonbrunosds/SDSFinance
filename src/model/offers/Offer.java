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

import exceptions.NullObjectException;
import util.IElement;

/**
 * Classe abstrata responsável por fornecer os métodos de uma oferta.
 * @author Everton Bruno Silva dos Santos.
 */
public abstract class Offer implements IOffer, IElement<String> {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -3453059105283424072L;

    /**
     * Método responsável por alterar o valor da oferta.
     * @param value Refere-se ao novo valor da oferta.
     */
    public abstract void setValue(double value);
    
    /**
     * Método responsável por alterar o nome da oferta.
     * @param key Refere-se ao novo nome da oferta.
     * @throws NullObjectException Exceção lançada em caso de nome nulo.
     */
    @Override
    public abstract void setKey(String key) throws NullObjectException;
    
    /**
     * Método responsável por pré-visualizar nome da ofeta pós-alterações.
     * @param key Refere-se ao novo nome.
     * @return Retorna pré-visualização de novo nome.
     * @throws NullObjectException Exceção lançada em caso de nome nulo.
     */
    @Override
    public abstract Comparable<String> previewKey(String key) throws NullObjectException;
    
}
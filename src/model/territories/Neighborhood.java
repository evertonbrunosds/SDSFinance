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
package model.territories;

import exceptions.NullObjectException;
import java.io.Serializable;
import util.Filter;

/**
 * Classe responsável por comportar-se como um bairro.
 * @author Everton Bruno Silva dos Santos.
 */
public class Neighborhood implements Serializable {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -1970407300868458193L;
    /**
     * Refere-se ao nome do bairro.
     */
    private final String name;

    /**
     * Construtor responsável pelo instanciamento do bairro.
     * @param name Refere-se ao nome do bairro.
     * @throws NullObjectException Exceção lançada em caso do nome do bairro nulo.
     */
    public Neighborhood(final String name) throws NullObjectException {
        Filter.nullObject(name);
        this.name = name;
    }

    /**
     * Método responsável por retornar o nome do bairro.
     * @return Retorna nome do bairro.
     */
    @Override
    public String toString() {
        return name;
    }

}
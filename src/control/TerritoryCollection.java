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
package control;

import model.sets.Collection;

/**
 * Classe responsável por comportar-se como coleção de territórios.
 * @author Everton Bruno Silva dos Santos.
 * @param <T> Refere-se ao tipo de território.
 */
public class TerritoryCollection<T> extends Collection<String,T> implements ITerritoryCollection<T> {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = 2225591261298026065L;
    
}

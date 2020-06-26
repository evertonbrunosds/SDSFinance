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
package util;

/**
 * Interface responsável por fornecer os métodos visíveis de data.
 * @author Everton Bruno Silva dos Santos.
 */
public interface IDateVisible {
    
    /**
     * Método responsável por retornar o dia da data.
     * @return Retorna dia da data.
     */
    public int getDay();
    
    /**
     * Método responsável por retornar o mês da data.
     * @return Retorna mês da data.
     */
    public int getMonth();
    
    /**
     * Método responsável por retornar o ano da data.
     * @return Retorna ano da data.
     */
    public int getYear();
    
    /**
     * Método responsável por retornar data.
     * @return Retorna data.
     */
    @Override
    public String toString();
    
}

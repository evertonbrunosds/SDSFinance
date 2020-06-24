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
package model.bytes;

import exceptions.FileStreamInvalidException;
import exceptions.IncompatibleTypeException;
import java.io.Serializable;

/**
 * Interface responsável por fornecer os métodos de uma estrutura capaz de carregar
 * e gravar dados em outras estruturas.
 * @author Everton Bruno Silva dos Santos.
 */
public interface IStream extends Serializable {
    
    /**
     * Método responsável pelo carregamento de dados de uma estrutura em outra estrutura.
     * @param fileStream Refere-se a estrutura que será carregada.
     * @throws FileStreamInvalidException Exceção lançada em caso de arquivo em fluxo inválido.
     * @throws IncompatibleTypeException Exceção lançada em caso do tipo ser incompatível.
     */
    public void loadFromStream(IFileStream fileStream) throws FileStreamInvalidException, IncompatibleTypeException;
    
    /**
     * Método responsável pela gravação de dados de uma estrutura em outra estrutura.
     * @param fileStream Refere-se a estrutura que será gravada.
     * @throws FileStreamInvalidException Exceção lançada em caso de arquivo em fluxo inválido.
     * @throws IncompatibleTypeException Exceção lançada em caso do tipo ser incompatível.
     */
    public void saveFromStream(IFileStream fileStream) throws FileStreamInvalidException, IncompatibleTypeException;
    
    /**
     * Método responsável pelo retorno de dados contidos na estrutura.
     * @return Retorna dados contidos na estrutura.
     */
    public Object getObject();
    
    /**
     * Método responsável pelo esvaziamento da estrutura de fluxos.
     */
    public void clear();
    
}
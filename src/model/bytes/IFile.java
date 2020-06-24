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
import exceptions.NullObjectException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface responsável por fornecer os métodos de uma estrutura capazes de 
 * carregar e gravar dados em arquivos.
 * @author Everton Bruno Silva dos Santos.
 */
public interface IFile {
    
    /**
     * Método responsável pelo carregamento de dados de uma estrutura em arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws NullObjectException Exceção lançada em caso de string nula.
     * @throws FileNotFoundException Exceção lançada em caso do arquivo não ser encontrado.
     * @throws IOException Exceção lançada em caso de problemas no acesso ao arquivo.
     * @throws ClassNotFoundException Exceção lançada em caso de não haver uma classe contida no arquivo.
     * @throws FileStreamInvalidException Exceção lançada em caso de arquivo em fluxo inválido.
     * @throws IncompatibleTypeException Exceção lançada em caso do tipo ser incompatível.
     */
    public void loadFromFile(String fileName) 
            throws NullObjectException, FileNotFoundException, IOException, 
            ClassNotFoundException, FileStreamInvalidException, IncompatibleTypeException;
    
    /**
     * Método responsável pela gravação de dados de uma estrutura em arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws NullObjectException Exceção lançada em caso de string nula.
     * @throws FileNotFoundException Exceção lançada em caso do arquivo não ser encontrado.
     * @throws IOException Exceção lançada em caso de problemas no acesso ao arquivo.
     * @throws FileStreamInvalidException Exceção lançada em caso de arquivo em fluxo inválido.
     * @throws IncompatibleTypeException Exceção lançada em caso do tipo ser incompatível.
     */
    public void saveFromFile(String fileName) 
            throws NullObjectException, FileNotFoundException, IOException, 
            FileStreamInvalidException, IncompatibleTypeException;
    
}

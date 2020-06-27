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

import exceptions.NullObjectException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Classe responsável por comportar-se como um arquivo em fluxo.
 * @author Everton Bruno Silva dos Santos.
 */
public class FileStream implements Serializable {
    /**
     * Refere-se ao número de série do objeto.
     */
    private static final long serialVersionUID = 5276767284452659555L;
    /**
     * Refere-se ao objeto que armazena as estruturas.
     */
    private Object object;

    /**
     * Construtor responsável pelo instanciamento do arquivo em fluxo.
     */
    public FileStream() {
        object = null;
    }

    /**
     * Método responsável pelo carregamento de dados de uma estrutura contido em arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws NullObjectException    Exceção lançada em caso de nome de arquivo nulo.
     * @throws FileNotFoundException  Exceção lançada em caso do arquivo não ser encontrado.
     * @throws IOException            Exceção lançada em caso de problemas no acesso ao arquivo.
     * @throws ClassNotFoundException Exceção lançada em caso de não haver uma classe contida no arquivo.
     */
    public void loadFromFile(final String fileName)
            throws NullObjectException, FileNotFoundException, IOException, ClassNotFoundException {
        Filter.nullObject(fileName);
        try (ObjectInputStream fileStream = new ObjectInputStream(new FileInputStream(fileName))) {
            this.object = fileStream.readObject();
            fileStream.close();
        }
    }

    /**
     * Método responsável pela gravação de dados de uma estrutura em arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws NullObjectException   Exceção lançada em caso de nome de arquivo nulo.
     * @throws FileNotFoundException Exceção lançada em caso do arquivo não ser encontrado.
     * @throws IOException           Exceção lançada em caso de problemas no acesso ao arquivo.
     */
    public void saveFromFile(final String fileName) throws NullObjectException, FileNotFoundException, IOException {
        Filter.nullObject(fileName);
        try (ObjectOutputStream fileStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            fileStream.writeObject(object);
            fileStream.close();
        }
    }

    /**
     * Método responsável por retornar o objeto contido na estrutura.
     * @return Retorna objeto contido na estrutura.
     */
    public Object getObject() {
        return object;
    }

    /**
     * Método responsável por alterar o objeto contido na estrutura.
     * @param object Refere-se ao novo objeto.
     */
    public void setObject(final Serializable object) {
        this.object = object;
    }

    /**
     * Método responsável pelo esvaziamento da estrutura.
     */
    public void clear() {
        object = null;
    }

}
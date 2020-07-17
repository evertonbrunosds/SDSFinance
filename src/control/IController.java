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

import exceptions.IncompatibleTypeException;
import exceptions.NullObjectException;
import java.io.IOException;
import java.io.Serializable;
import model.business.IAcquisition;
import model.sets.IOrganizationCollection;
import model.organizations.IProvider;
import model.sets.IBusinessCollection;

/**
 * Interface responsável por fornecer os métodos de controlador.
 * @author Everton Bruno Silva dos Santos.
 */
public interface IController extends Serializable {

    /**
     * Método responsável por retornar coleção de fornecedores.
     * @return Retorna coleção de fornecedores.
     */
    public IOrganizationCollection<IProvider> getProviderCollection();

    /**
     * Método responsável por retornar coleção de aquisições.
     * @return Retorna coleção de aquisições.
     */
    public IBusinessCollection<IAcquisition> getAcquisitionCollection();

    /**
     * Método responsável por esvaziar os dados contidos no controlador.
     */
    public void clear();

    /**
     * Método responsável por carregar dados de arquivo para o controlador.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws NullObjectException       Exceção lançada em caso de nome de arquivo nulo.
     * @throws IOException               Exceção lançada em caso de problemas no acesso ao arquivo.
     * @throws ClassNotFoundException    Exceção lançada em caso de não haver uma classe contida no arquivo.
     * @throws IncompatibleTypeException Exceção lançada em caso de no arquivo haver os dados de um controlador incompatível.
     */
    public void loadFromFile(String fileName) throws NullObjectException, IOException, ClassNotFoundException, IncompatibleTypeException;

    /**
     * Método responsável por gravar dados do controlador em arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws NullObjectException Exceção lançada em caso de nome de arquivo nulo.
     * @throws IOException         Exceção lançada em caso de problemas no acesso ao arquivo.
     */
    public void saveFromFile(String fileName) throws NullObjectException, IOException;

    /**
     * Método responsável por gravar dados do controlador em arquivo.
     * @throws IOException Exceção lançada em caso de problemas no acesso ao arquivo.
     */
    public void saveFromFile() throws IOException;
    
    /**
     * Método responsáve por alterar o nome do arquivo.
     * @param fileName Refere-se ao novo nome de arquivo.
     */
    public void setFileName(final String fileName);
    
    /**
     * Método responsáve por retornar o nome do arquivo.
     * @return Retorna o nome do arquivo.
     */
    public String getFileName();

}
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
import model.business.IAcquisition;
import model.sets.OrganizationCollection;
import model.sets.IOrganizationCollection;
import model.organizations.IProvider;
import model.sets.BusinessCollection;
import model.sets.IBusinessCollection;
import util.FileStream;
import util.Filter;

/**
 * Classe responsável por comportar-se como registro.
 * @author Everton Bruno Silva dos Santos.
 */
public class Record implements IRecord {
    /**
     * Refere-se a instância do registro.
     */
    private static Record instance;
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = 3154611275482929630L;
    /**
     * Refere-se a versão da classe.
     */
    private final long version = 14072020;
    /**
     * Refere-se ao arquivo de registro.
     */
    private String fileName;
    /**
     * Refere-se a coleção de fornecedores.
     */
    private IOrganizationCollection<IProvider> providerCollection;
    /**
     * Refere-se a coleção de aquisições realizadas.
     */
    private IBusinessCollection<IAcquisition> acquisitionCollection;

    /**
     * Construtor responsável pelo instanciamento do registro.
     */
    private Record() {
        this.fileName = null;
        providerCollection = new OrganizationCollection<>();
        acquisitionCollection = new BusinessCollection<>();
    }

    /**
     * Método responsável por retornar instância do registro.
     * @return Retorna instância do registro.
     */
    public static IRecord getInstance() {
        if (instance == null) {
            instance = new Record();
        }
        return instance;
    }

    /**
     * Método responsável por retornar coleção de fornecedores.
     * @return Retorna coleção de fornecedores.
     */
    @Override
    public IOrganizationCollection<IProvider> getProviderCollection() {
        return providerCollection;
    }

    /**
     * Método responsável por retornar coleção de aquisições.
     * @return Retorna coleção de aquisições.
     */
    @Override
    public IBusinessCollection<IAcquisition> getAcquisitionCollection() {
        return acquisitionCollection;
    }

    /**
     * Método responsável por esvaziar os dados contidos no registro.
     */
    @Override
    public void clear() {
        providerCollection = new OrganizationCollection<>();
        acquisitionCollection = new BusinessCollection<>();
        fileName = null;
    }

    /**
     * Método responsável por carregar arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws NullObjectException       Exceção lançada em caso de nome de arquivo nulo.
     * @throws IOException               Exceção lançada no caso de haverem problemas de acesso no arquivo.
     * @throws ClassNotFoundException    Exceção lançada em caso de não haver uma classe contida no arquivo.
     * @throws IncompatibleTypeException Exceção lançada em caso de no arquivo haverem dados incompatíveis.
     */
    @Override
    public void loadFromFile(final String fileName) throws NullObjectException, IOException, ClassNotFoundException, IncompatibleTypeException {
        Filter.nullObject(fileName);
        auxLoadFromFile(fileName);
        this.fileName = fileName;
    }

    /**
     * Método responsável por carregar arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws IOException               Exceção lançada no caso de haverem problemas de acesso no arquivo.
     * @throws ClassNotFoundException    Exceção lançada em caso de não haver uma classe contida no arquivo.
     * @throws IncompatibleTypeException Exceção lançada em caso de no arquivo haverem dados incompatíveis.
     */
    private void auxLoadFromFile(final String fileName) throws IOException, ClassNotFoundException, IncompatibleTypeException {
        final FileStream fileStream = new FileStream();
        fileStream.loadFromFile(fileName);
        if (fileStream.getObject() instanceof Record) {
            final Record controller = (Record) fileStream.getObject();
            if (controller.version == version) {
                providerCollection = controller.providerCollection;
                acquisitionCollection = controller.acquisitionCollection;
            } else {
                throw new IncompatibleTypeException();
            }
        } else {
            throw new IncompatibleTypeException();
        }
    }

    /**
     * Método responsável por salvar em arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws NullObjectException Exceção lançada em caso de nome de arquivo nulo.
     * @throws IOException         Exceção lançada no caso de haverem problemas de acesso no arquivo.
     */
    @Override
    public void saveToFile(final String fileName) throws NullObjectException, IOException {
        Filter.nullObject(fileName);
        auxSaveToFile(fileName);
        this.fileName = fileName;
    }

    /**
     * Método responsável por salvar em arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws IOException Exceção lançada no caso de haverem problemas de acesso no arquivo.
     */
    private void auxSaveToFile(final String fileName) throws IOException {
        final FileStream fileStream = new FileStream();
        fileStream.setObject(this);
        fileStream.saveToFile(fileName);
    }

    /**
     * Método responsável por salvar em arquivo.
     * @throws IOException Exceção lançada no caso de haverem problemas de acesso no arquivo.
     */
    @Override
    public void saveToFile() throws IOException {
        auxSaveToFile(fileName);
    }

    /**
     * Método responsável por retornar o nome do arquivo.
     * @return Retorna o nome do arquivo.
     */
    @Override
    public String getFileName() {
        if(fileName != null) {
            String[] strings;
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                strings = fileName.split("\\\\");
            } else {
                strings = fileName.split("/");
            }
            String string = strings[strings.length - 1];
            if(string.contains(".sdsf")) {
                strings = string.split(".sdsf");
                string = strings[0];
            }
            return "[" + string + "]";
        } else {
            return "[Sem Título]";
        }
    }
    
    /**
     * Método responsável por retornar indicativo de que nunca foi salvo em arquivo.
     * @return Retorna indicativo de que nunca foi salvo em arquivo.
     */
    @Override
    public boolean neverBeenSavedInFile() {
        return fileName == null;
    }
}
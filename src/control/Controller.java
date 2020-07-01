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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import model.business.IAcquisition;
import model.sets.ITerritoryCollection;
import model.sets.OrganizationCollection;
import model.sets.IOrganizationCollection;
import model.sets.TerritoryCollection;
import model.organizations.IProvider;
import model.sets.BusinessCollection;
import model.sets.IBusinessCollection;
import model.territories.ICity;
import model.territories.INeighborhood;
import model.territories.IStreet;
import util.FileStream;
import util.Filter;

/**
 * Classe responsável por comportar-se como controlador.
 * @author Everton Bruno Silva dos Santos.
 */
public class Controller implements IController {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = 3154611275482929630L;
    /**
     * Refere-se a versão da classe.
     */
    private final long version = 03062020;
    /**
     * Refere-se ao arquivo de registro.
     */
    private final String pathFile;
    /**
     * Refere-se a instância do controlador.
     */
    private static Controller instance;
    /**
     * Refere-se a coleção de ruas.
     */
    private ITerritoryCollection<IStreet> streetCollection;
    /**
     * Refere-se a coleção de bairros.
     */
    private ITerritoryCollection<INeighborhood> neighborhoodCollection;
    /**
     * Refere-se a coleção de cidades.
     */
    private ITerritoryCollection<ICity> cityCollection;
    /**
     * Refere-se a coleção de fornecedores.
     */
    private IOrganizationCollection<IProvider> providerCollection;
    /**
     * Refere-se a coleção de aquisições realisadas.
     */
    private IBusinessCollection<IAcquisition> acquisitionCollection;

    /**
     * Construtor responsável pelo instanciamento do controlador.
     */
    private Controller() {
        this.pathFile = System.getProperty("user.home") + "/.SDSFinance";
        final File file = new File(pathFile);
        if (!file.exists()) {
            file.mkdirs();
        }
        streetCollection = new TerritoryCollection<>();
        neighborhoodCollection = new TerritoryCollection<>();
        cityCollection = new TerritoryCollection<>();
        providerCollection = new OrganizationCollection<>();
        acquisitionCollection = new BusinessCollection<>();
    }

    /**
     * Método responsável por retornar instância do controlador.
     * @return Retorna instância do controlador.
     */
    public static IController getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    /**
     * Método responsável por retornar coleção de ruas.
     * @return Retorna coleção de ruas.
     */
    @Override
    public ITerritoryCollection<IStreet> getStreetCollection() {
        return streetCollection;
    }

    /**
     * Método responsável por retornar coleção de bairros.
     * @return Retorna coleção de bairros.
     */
    @Override
    public ITerritoryCollection<INeighborhood> getNeighborhoodCollection() {
        return neighborhoodCollection;
    }

    /**
     * Método responsável por retornar coleção de cidades.
     * @return Retorna coleção de cidades.
     */
    @Override
    public ITerritoryCollection<ICity> getCityCollection() {
        return cityCollection;
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
     * Método responsável por esvaziar os dados contidos no controlador.
     */
    @Override
    public void clear() {
        streetCollection = new TerritoryCollection<>();
        neighborhoodCollection = new TerritoryCollection<>();
        cityCollection = new TerritoryCollection<>();
        providerCollection = new OrganizationCollection<>();
        acquisitionCollection = new BusinessCollection<>();
    }

    /**
     * Método responsável por carregar dados de arquivo para o controlador.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws NullObjectException       Exceção lançada em caso de nome de arquivo nulo.
     * @throws IOException               Exceção lançada em caso de problemas no acesso ao arquivo.
     * @throws ClassNotFoundException    Exceção lançada em caso de não haver uma classe contida no arquivo.
     * @throws IncompatibleTypeException Exceção lançada em caso de no arquivo haver os dados de um controlador incompatível.
     */
    @Override
    public void loadFromFile(final String fileName) throws NullObjectException, IOException,
            ClassNotFoundException, IncompatibleTypeException {
        Filter.nullObject(fileName);
        fileInternalLoader(fileName);
    }

    /**
     * Método responsável por carregar dados de arquivo para o controlador.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws IOException               Exceção lançada em caso de problemas no acesso ao arquivo.
     * @throws ClassNotFoundException    Exceção lançada em caso de não haver uma classe contida no arquivo.
     * @throws IncompatibleTypeException Exceção lançada em caso de no arquivo haver os dados de um controlador incompatível.
     */
    private void fileInternalLoader(final String fileName)
            throws IOException, ClassNotFoundException, IncompatibleTypeException {
        final FileStream fileStream = new FileStream();
        fileStream.loadFromFile(fileName);
        if (fileStream.getObject() instanceof Controller) {
            final Controller controller = (Controller) fileStream.getObject();
            if (controller.version == version) {
                streetCollection = controller.streetCollection;
                neighborhoodCollection = controller.neighborhoodCollection;
                cityCollection = controller.cityCollection;
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
     * Método responsável por gravar dados do controlador em arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws NullObjectException   Exceção lançada em caso de nome de arquivo nulo.
     * @throws IOException           Exceção lançada em caso de problemas no acesso ao arquivo.
     */
    @Override
    public void saveFromFile(final String fileName) throws NullObjectException, IOException {
        Filter.nullObject(fileName);
        fileInternalRecorder(fileName);
    }

    /**
     * Método responsável por gravar dados do controlador em arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws IOException Exceção lançada em caso de problemas no acesso ao arquivo.
     */
    private void fileInternalRecorder(final String fileName) throws IOException {
        final FileStream fileStream = new FileStream();
        fileStream.setObject(this);
        fileStream.saveFromFile(fileName);
    }

    /**
     * Método responsável por carregar dados de arquivo para o controlador.
     * @throws IOException            Exceção lançada em caso de problemas no acesso ao arquivo.
     */
    @Override
    public void loadFromFile() throws IOException {
        try {
            fileInternalLoader(pathFile + "/history.sdsf");
        } catch (FileNotFoundException | ClassNotFoundException | IncompatibleTypeException ex) {
            saveFromFile();
        }
    }

    /**
     * Método responsável por gravar dados do controlador em arquivo.
     * @throws IOException Exceção lançada em caso de problemas no acesso ao arquivo.
     */
    @Override
    public void saveFromFile() throws IOException {
        fileInternalRecorder(pathFile + "/history.sdsf");
    }

}
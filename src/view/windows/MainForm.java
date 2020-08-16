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
package view.windows;

import control.Record;
import exceptions.ElementNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import model.business.IAcquisition;
import model.organizations.IProvider;
import util.Converter;
import view.managers.Show;
import view.managers.ViewControl;

/**
 * Classe responsável por comportar-se como janela principal da aplicação.
 * @author Everton Bruno Silva dos Santos.
 */
public class MainForm extends javax.swing.JFrame {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = 3549108067875363853L;
    /**
     * Refere-se a instância da janela principal.
     */
    private static MainForm instance;

    /**
     * Construtor responsável por inicializar a janela principal da aplicação.
     * @param fileName Refere-se ao nome do arquivo que será aberto.
     */
    private MainForm(final String fileName) {
        instance = this;
        initComponents();
        ViewControl.alignTo(table, SwingConstants.CENTER);
        if (fileName != null) {
            ViewControl.loadRecordFromFile(fileName);
        }
    }

    /**
     * Método responsável por fechar a aplicação.
     */
    @Override
    public void dispose() {
        if (!ViewControl.getWasChanged()) {
            if (!Show.questionMessage("Se você não salvar o registro, todas as alterações\n"
                    + "serão perdidas. Deseja salvar antes de fechar?", "Sim", "Não")) {
                ViewControl.saveRecordToFile(this);
                if (ViewControl.getWasChanged()) {
                    System.exit(0);
                }
            } else {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }

    /**
     * Método responsável por alterar o título da janela.
     * @param newTitle Refere-se ao novo titulo.
     */
    public static void setTitleWindow(final String newTitle) {
        if (instance != null) {
            instance.setTitle("SDS Finance - " + newTitle);
        }
    }

    /**
     * Método responsável por atualizar a janela principal.
     */
    public static void updateWindow() {
        if (instance != null) {
            ViewControl.clear(instance.table);
            final DefaultTableModel model = (DefaultTableModel) instance.table.getModel();
            Record.getInstance().getAcquisitionCollection().forEach(true, acquisition -> {
                model.addRow(Converter.toVector(acquisition));
            });
        }
    }

    /**
     * Método responsável por excluir uma lista de aquisições.
     * @throws ElementNotFoundException Exceção lançada no caso das aquisições não terem sido encontradas.
     */
    private void removeAcquisitions() throws ElementNotFoundException {
        final int[] selectedRows = table.getSelectedRows();
        if (selectedRows.length > 0) {
            if (Show.questionMessage(
                    "Essa ação excluirá permanentemente todas as\n" + "aquisições selecionadas. Deseja prosseguir?", "Não", "Sim")) {
                IAcquisition acquisition;
                for (final int row : selectedRows) {
                    acquisition = (IAcquisition) table.getModel().getValueAt(row, 0);
                    Record.getInstance().getAcquisitionCollection().remove(acquisition.getKey());
                }
                ViewControl.setWasChanged(false);
                updateWindow();
            }
        }
    }

    /**
     * Método responsável por atualizar as opções.
     */
    private void updateOptions() {
        if (table.getRowCount() == 0 || table.getSelectedRow() == -1) {
            optRemoveAcquisition.setEnabled(false);
        } else {
            optRemoveAcquisition.setEnabled(true);
        }
        if (table.getRowCount() == 0 || table.getSelectedRow() == -1 || table.getSelectedRows().length > 1) {
            optAccessProvider.setEnabled(false);
            optEditAcquisition.setEnabled(false);
        } else {
            optAccessProvider.setEnabled(true);
            optEditAcquisition.setEnabled(true);
        }
    }

    /**
     * Método responsável por retornar indicativo de que há atualizações.
     * @return Retorna indicativo de que há atualizações.
     * @throws Exception Exceção lançada caso hajam falhas no processo.
     */
    public boolean thereAreUpdates() throws Exception {
        final StringBuilder output = new StringBuilder();
        final URL url = new URL("https://evertonbrunosds.github.io/sdsfinance/version");
        final URLConnection uCon = url.openConnection();
        try (InputStream input = uCon.getInputStream()) {
            final byte[] buffer = new byte[2048];
            while (input.read(buffer) != -1) {
                output.append(new String(buffer));
            }
        }
        return Converter.toDouble(output.toString()) > 1.0;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        optAccessProvider = new javax.swing.JMenuItem();
        optEditAcquisition = new javax.swing.JMenuItem();
        optRemoveAcquisition = new javax.swing.JMenuItem();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        toolBar = new javax.swing.JMenuBar();
        optFile = new javax.swing.JMenu();
        optNewFile = new javax.swing.JMenuItem();
        optOpenFile = new javax.swing.JMenuItem();
        optSaveFile = new javax.swing.JMenuItem();
        optSaveAsFile = new javax.swing.JMenuItem();
        optShow = new javax.swing.JMenu();
        optProvider = new javax.swing.JMenuItem();
        optExtracts = new javax.swing.JMenuItem();
        optAbout = new javax.swing.JMenu();
        optUpdateChecker = new javax.swing.JMenuItem();
        optLicense = new javax.swing.JMenuItem();
        optAuthor = new javax.swing.JMenuItem();

        optAccessProvider.setText("Acessar Fornecedor");
        optAccessProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optAccessProviderActionPerformed(evt);
            }
        });
        popupMenu.add(optAccessProvider);

        optEditAcquisition.setText("Editar Aquisição");
        optEditAcquisition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optEditAcquisitionActionPerformed(evt);
            }
        });
        popupMenu.add(optEditAcquisition);

        optRemoveAcquisition.setText("Excluir Aquisições");
        optRemoveAcquisition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optRemoveAcquisitionActionPerformed(evt);
            }
        });
        popupMenu.add(optRemoveAcquisition);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SDS Finance - [Sem Título]");
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/x48/SDSFinance.png")));

        scrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(final java.awt.event.MouseEvent evt) {
                scrollPaneMouseReleased(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
                new String[] { "Aquisição", "Fornecedor", "Quantidade", "Valor Unitário", "Valor Total", "Data" }) {
            private static final long serialVersionUID = 1239724359305259903L;
            boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(final java.awt.event.MouseEvent evt) {
                tableMouseReleased(evt);
            }
        });
        scrollPane.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(2).setMinWidth(90);
            table.getColumnModel().getColumn(2).setPreferredWidth(90);
            table.getColumnModel().getColumn(2).setMaxWidth(90);
            table.getColumnModel().getColumn(3).setMinWidth(110);
            table.getColumnModel().getColumn(3).setPreferredWidth(110);
            table.getColumnModel().getColumn(3).setMaxWidth(110);
            table.getColumnModel().getColumn(4).setMinWidth(110);
            table.getColumnModel().getColumn(4).setPreferredWidth(110);
            table.getColumnModel().getColumn(4).setMaxWidth(110);
            table.getColumnModel().getColumn(5).setMinWidth(90);
            table.getColumnModel().getColumn(5).setPreferredWidth(90);
            table.getColumnModel().getColumn(5).setMaxWidth(90);
        }

        optFile.setText("Arquivo");
        optFile.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(final java.awt.event.ItemEvent evt) {
                optFileItemStateChanged(evt);
            }
        });

        optNewFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        optNewFile.setText("Novo");
        optNewFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optNewFileActionPerformed(evt);
            }
        });
        optFile.add(optNewFile);

        optOpenFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        optOpenFile.setText("Abrir");
        optOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optOpenFileActionPerformed(evt);
            }
        });
        optFile.add(optOpenFile);

        optSaveFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        optSaveFile.setText("Salvar");
        optSaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optSaveFileActionPerformed(evt);
            }
        });
        optFile.add(optSaveFile);

        optSaveAsFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
                java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        optSaveAsFile.setText("Salvar Como");
        optSaveAsFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optSaveAsFileActionPerformed(evt);
            }
        });
        optFile.add(optSaveAsFile);

        toolBar.add(optFile);

        optShow.setText("Exibir");
        optShow.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(final java.awt.event.ItemEvent evt) {
                optShowItemStateChanged(evt);
            }
        });

        optProvider.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        optProvider.setText("Fornecedores");
        optProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optProviderActionPerformed(evt);
            }
        });
        optShow.add(optProvider);

        optExtracts.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        optExtracts.setText("Extratos");
        optExtracts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optExtractsActionPerformed(evt);
            }
        });
        optShow.add(optExtracts);

        toolBar.add(optShow);

        optAbout.setText("Sobre");

        optUpdateChecker.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A,
                java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        optUpdateChecker.setText("Atualizações");
        optUpdateChecker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optUpdateCheckerActionPerformed(evt);
            }
        });
        optAbout.add(optUpdateChecker);

        optLicense.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        optLicense.setText("Licença");
        optLicense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optLicenseActionPerformed(evt);
            }
        });
        optAbout.add(optLicense);

        optAuthor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        optAuthor.setText("Autoria");
        optAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                optAuthorActionPerformed(evt);
            }
        });
        optAbout.add(optAuthor);

        toolBar.add(optAbout);

        setJMenuBar(toolBar);

        final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void optAuthorActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAuthorActionPerformed
        AuthorAbout.showModal();
    }//GEN-LAST:event_optAuthorActionPerformed

    private void tableMouseReleased(final java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseReleased
        if (evt.isMetaDown()) {
            updateOptions();
            popupMenu.show(this, getMousePosition().x, getMousePosition().y);
        }
    }//GEN-LAST:event_tableMouseReleased

    private void optProviderActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optProviderActionPerformed
        ProviderWindow.showModal();
    }//GEN-LAST:event_optProviderActionPerformed

    private void optShowItemStateChanged(final java.awt.event.ItemEvent evt) {//GEN-FIRST:event_optShowItemStateChanged
        if (table.getRowCount() == 0) {
            optExtracts.setEnabled(false);
        } else {
            optExtracts.setEnabled(true);
        }
    }//GEN-LAST:event_optShowItemStateChanged

    private void optSaveAsFileActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optSaveAsFileActionPerformed
        ViewControl.saveAsRecordToFile(this);
    }//GEN-LAST:event_optSaveAsFileActionPerformed

    private void optOpenFileActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optOpenFileActionPerformed
        ViewControl.loadRecordFromFile(this);
    }//GEN-LAST:event_optOpenFileActionPerformed

    private void optExtractsActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optExtractsActionPerformed
        if (table.getRowCount() > 0) {
            ExtractsWindow.showModal();
        }
    }//GEN-LAST:event_optExtractsActionPerformed

    private void optNewFileActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optNewFileActionPerformed
        if (ViewControl.getWasChanged() && !Record.getInstance().neverBeenSavedInFile()) {
            ViewControl.newRecord(this);
        } else if (!ViewControl.getWasChanged()) {
            ViewControl.newRecord(this);
        }
    }//GEN-LAST:event_optNewFileActionPerformed

    private void optRemoveAcquisitionActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optRemoveAcquisitionActionPerformed
        try {
            removeAcquisitions();
        } catch (final ElementNotFoundException ex) {
            Show.errorMessage("Falha no sistema, informe o desenvolvedor.");
        }
    }//GEN-LAST:event_optRemoveAcquisitionActionPerformed

    private void scrollPaneMouseReleased(final java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollPaneMouseReleased
        if (evt.isMetaDown()) {
            updateOptions();
            popupMenu.show(this, getMousePosition().x, getMousePosition().y);
        }
    }//GEN-LAST:event_scrollPaneMouseReleased

    private void optEditAcquisitionActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optEditAcquisitionActionPerformed
        AcquisitionManager.showModal((IAcquisition) table.getValueAt(table.getSelectedRow(), 0));
    }//GEN-LAST:event_optEditAcquisitionActionPerformed

    private void optAccessProviderActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAccessProviderActionPerformed
        OfferWindow.showModal((IProvider) table.getValueAt(table.getSelectedRow(), 1));
    }//GEN-LAST:event_optAccessProviderActionPerformed

    private void optFileItemStateChanged(final java.awt.event.ItemEvent evt) {//GEN-FIRST:event_optFileItemStateChanged
        if (ViewControl.getWasChanged()) {
            if (Record.getInstance().neverBeenSavedInFile()) {
                optNewFile.setEnabled(false);
                optSaveFile.setEnabled(true);
            } else {
                optNewFile.setEnabled(true);
                optSaveFile.setEnabled(false);
            }
        } else {
            optNewFile.setEnabled(true);
            optSaveFile.setEnabled(true);
        }
    }//GEN-LAST:event_optFileItemStateChanged

    private void optSaveFileActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optSaveFileActionPerformed
        ViewControl.saveRecordToFile(this);
    }//GEN-LAST:event_optSaveFileActionPerformed

    private void optLicenseActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optLicenseActionPerformed
        LicenseWindow.showModal();
    }//GEN-LAST:event_optLicenseActionPerformed

    private void optUpdateCheckerActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optUpdateCheckerActionPerformed
        final Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    if (thereAreUpdates()) {
                        if (!Show.questionMessage("Há atualizações disponíveis. Deseja baixar as atualizações?", "Sim",
                                "Não")) {
                            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://evertonbrunosds.github.io/sdsfinance/"));
                        }
                    } else {
                        Show.informationMessage("O SDS Finance está atualizado.");
                    }
                } catch (final Exception ex) {
                    Show.warningMessage("Não foi possível determinar o estado de atualização.");
                }
            }
        };
        thread.start();
    }//GEN-LAST:event_optUpdateCheckerActionPerformed

    /**
     * Método responsável por comportar-se como o principal da classe.
     * @param args Refere-se a linha de comando de argumentos.
     */
    public static void main(final String args[]) {
        try {
            for (final javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            if (args.length > 0) {
                new MainForm(args[0]).setVisible(true);
            } else {
                new MainForm(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu optAbout;
    private javax.swing.JMenuItem optAccessProvider;
    private javax.swing.JMenuItem optAuthor;
    private javax.swing.JMenuItem optEditAcquisition;
    private javax.swing.JMenuItem optExtracts;
    private javax.swing.JMenu optFile;
    private javax.swing.JMenuItem optLicense;
    private javax.swing.JMenuItem optNewFile;
    private javax.swing.JMenuItem optOpenFile;
    private javax.swing.JMenuItem optProvider;
    private javax.swing.JMenuItem optRemoveAcquisition;
    private javax.swing.JMenuItem optSaveAsFile;
    private javax.swing.JMenuItem optSaveFile;
    private javax.swing.JMenu optShow;
    private javax.swing.JMenuItem optUpdateChecker;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    private javax.swing.JMenuBar toolBar;
    // End of variables declaration//GEN-END:variables
}
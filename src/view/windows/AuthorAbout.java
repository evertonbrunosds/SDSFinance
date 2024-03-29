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

import java.io.IOException;
import java.net.URISyntaxException;
import view.managers.Show;

/**
 * Classe responsável por comportar-se como janela de autoria.
 * @author Everton Bruno Silva dos Santos.
 */
public class AuthorAbout extends javax.swing.JDialog {
    /**
     * Refere-se ao número de série do objeto.
     */
    private static final long serialVersionUID = -7292939132647268588L;
    /**
     * Refere-se a instância da classe.
     */
    private static AuthorAbout instance;

    /**
     * Método responsável por gerar instância da janela.
     */
    private static void createInstance() {
        instance = new AuthorAbout(null, true) {
            private static final long serialVersionUID = -1727959699038950953L;
            @Override
            public void dispose() {
                instance = null;
                super.dispose();
            }
        };
    }

    /**
     * Método responsável por exibir janela de autoria.
     */
    public static void showModal() {
        createInstance();
        instance.setVisible(true);
    }

    /**
     * Construtor responsável pelo instanciamento da janela de autoria.
     * @param parent Refere-se ao invocador da janela.
     * @param modal  Refere-se ao modo de exibição.
     */
    private AuthorAbout(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelVersion = new javax.swing.JLabel();
        separatorOne = new javax.swing.JSeparator();
        labelProductNameAndVersion = new javax.swing.JLabel();
        labelNameDevAuthor = new javax.swing.JLabel();
        labelEmailDevAuthor = new javax.swing.JLabel();
        labelWebSite = new javax.swing.JLabel();
        separatorTwo = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sobre Autor");
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/x48/SDSFinance.png")));
        setResizable(false);

        labelVersion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x128/SDSFinance.png"))); // NOI18N
        labelVersion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelVersion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        labelProductNameAndVersion.setText("SDS Finance (v1.0)");

        labelNameDevAuthor.setText("Copyright © 2020. Everton Bruno Silva dos Santos.");

        labelEmailDevAuthor.setText("E-mail: evertonbrunogithub@yahoo.com");

        labelWebSite.setForeground(new java.awt.Color(0, 0, 255));
        labelWebSite.setText("<html><u>Web Site</u>");
        labelWebSite.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelWebSite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(final java.awt.event.MouseEvent evt) {
                labelWebSiteMouseReleased(evt);
            }
        });

        final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(15, 15, 15).addGroup(layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER).addComponent(labelVersion)
                        .addComponent(separatorOne, javax.swing.GroupLayout.PREFERRED_SIZE, 400,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelProductNameAndVersion).addComponent(labelNameDevAuthor)
                        .addComponent(labelEmailDevAuthor).addComponent(separatorTwo).addComponent(labelWebSite,
                                javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(20, 20, 20).addComponent(labelVersion)
                        .addGap(18, 18, 18).addComponent(labelProductNameAndVersion).addGap(7, 7, 7)
                        .addComponent(separatorOne, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0).addComponent(labelNameDevAuthor).addGap(2, 2, 2)
                        .addComponent(labelEmailDevAuthor).addGap(2, 2, 2)
                        .addComponent(labelWebSite, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2).addComponent(separatorTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void labelWebSiteMouseReleased(final java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelWebSiteMouseReleased
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://evertonbrunosds.github.io/sdsfinance/"));
        } catch (IOException | URISyntaxException ex) {
            Show.errorMessage("Acesso indisponível.");
        }
    }//GEN-LAST:event_labelWebSiteMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelEmailDevAuthor;
    private javax.swing.JLabel labelNameDevAuthor;
    private javax.swing.JLabel labelProductNameAndVersion;
    private javax.swing.JLabel labelVersion;
    private javax.swing.JLabel labelWebSite;
    private javax.swing.JSeparator separatorOne;
    private javax.swing.JSeparator separatorTwo;
    // End of variables declaration//GEN-END:variables
}
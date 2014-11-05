/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Favorito
 */
public class MenuMaster extends javax.swing.JFrame {

    /**
     * Creates new form MenuMaster
     */
    public MenuMaster() {
        initComponents();
        Fondo fondo = new Fondo();
        Dimension size = this.getSize();
        fondo.setSize(size);
        add(fondo);
    }
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("imagenes/logo.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        __ALTAPRODUCTO = new javax.swing.JButton();
        __CONSULTAS = new javax.swing.JButton();
        __MOVIMIENTOS = new javax.swing.JButton();
        __REPORTES = new javax.swing.JButton();
        __CANCELAR = new javax.swing.JButton();
        __etqFechaMenuMaster = new javax.swing.JLabel();
        __etqUsuarioMenuMaster = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú de Usuario");
        setIconImage(getIconImage());
        setUndecorated(true);
        setResizable(false);

        __ALTAPRODUCTO.setFont(new java.awt.Font("Elephant", 0, 12)); // NOI18N
        __ALTAPRODUCTO.setText("NUEVO PRODUCTO");

        __CONSULTAS.setFont(new java.awt.Font("Elephant", 0, 12)); // NOI18N
        __CONSULTAS.setText("CONSULTAS");

        __MOVIMIENTOS.setFont(new java.awt.Font("Elephant", 0, 12)); // NOI18N
        __MOVIMIENTOS.setText("MOVIMIENTOS");

        __REPORTES.setFont(new java.awt.Font("Elephant", 0, 12)); // NOI18N
        __REPORTES.setText("REPORTES");
        __REPORTES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __REPORTESActionPerformed(evt);
            }
        });

        __CANCELAR.setFont(new java.awt.Font("Elephant", 0, 12)); // NOI18N
        __CANCELAR.setText("CANCELAR");

        __etqFechaMenuMaster.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        __etqFechaMenuMaster.setForeground(new java.awt.Color(255, 255, 255));
        __etqFechaMenuMaster.setText("DD/MM/AAAA");

        __etqUsuarioMenuMaster.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        __etqUsuarioMenuMaster.setForeground(new java.awt.Color(255, 255, 255));
        __etqUsuarioMenuMaster.setText("USUARIO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(__CANCELAR, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(__etqUsuarioMenuMaster)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(__etqFechaMenuMaster))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(__REPORTES, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(__CONSULTAS, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(__ALTAPRODUCTO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(__MOVIMIENTOS, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(__etqFechaMenuMaster)
                    .addComponent(__etqUsuarioMenuMaster))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(__ALTAPRODUCTO, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(__MOVIMIENTOS, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(__CONSULTAS, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(__REPORTES, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(__CANCELAR, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void __REPORTESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___REPORTESActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___REPORTESActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuMaster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuMaster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuMaster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuMaster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuMaster().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton __ALTAPRODUCTO;
    public javax.swing.JButton __CANCELAR;
    public javax.swing.JButton __CONSULTAS;
    public javax.swing.JButton __MOVIMIENTOS;
    public javax.swing.JButton __REPORTES;
    public javax.swing.JLabel __etqFechaMenuMaster;
    public javax.swing.JLabel __etqUsuarioMenuMaster;
    // End of variables declaration//GEN-END:variables
}

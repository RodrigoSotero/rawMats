/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Rodrigo
 */
public class Reportes extends javax.swing.JFrame {

    /**
     * Creates new form Reportes
     */
    public Reportes() {
        initComponents();
        Fondo fondo = new Fondo();
        this.setLocationRelativeTo(null);
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

        jLabel2 = new javax.swing.JLabel();
        __etqTotal = new javax.swing.JLabel();
        __RInventario = new javax.swing.JButton();
        __SALIR = new javax.swing.JButton();
        __etqFecha = new javax.swing.JLabel();
        __etqTotal1 = new javax.swing.JLabel();
        __Entrada = new javax.swing.JButton();
        __etqTotal2 = new javax.swing.JLabel();
        __Salida = new javax.swing.JButton();
        __etqTotal3 = new javax.swing.JLabel();
        __StockArriba = new javax.swing.JButton();
        __etqTotal4 = new javax.swing.JLabel();
        __StockAbajo = new javax.swing.JButton();
        __etqTotal5 = new javax.swing.JLabel();
        __Finanzas = new javax.swing.JButton();
        __RInventario1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        __Archivo = new javax.swing.JMenu();
        __menuAltaProducto = new javax.swing.JMenuItem();
        __menuAnterior = new javax.swing.JMenuItem();
        __menuConsultas = new javax.swing.JMenuItem();
        __menuMovimientos = new javax.swing.JMenuItem();
        __MenuAbrirArchivo = new javax.swing.JMenuItem();
        __menuCerrarSesion = new javax.swing.JMenuItem();
        __menuSalir = new javax.swing.JMenuItem();
        __Edicion = new javax.swing.JMenu();
        __menuCambiarFecha = new javax.swing.JMenuItem();
        __menuNuevoUsuario = new javax.swing.JMenuItem();
        __menuCambiarContraseña = new javax.swing.JMenuItem();
        __menuReporte = new javax.swing.JMenuItem();
        __menu_datos = new javax.swing.JMenuItem();
        __menuBackup = new javax.swing.JMenuItem();
        __menuAcerca = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(getIconImage());

        jLabel2.setFont(new java.awt.Font("Papyrus", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Reportes Generales del Almacén ");

        __etqTotal.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __etqTotal.setForeground(new java.awt.Color(255, 255, 255));
        __etqTotal.setText("Generar Reporte de Inventario");

        __RInventario.setText("Inventario Materias Primas");
        __RInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __RInventarioActionPerformed(evt);
            }
        });

        __SALIR.setText("Salir");

        __etqFecha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        __etqFecha.setForeground(new java.awt.Color(255, 255, 255));
        __etqFecha.setText("DD/MM/AAAA");

        __etqTotal1.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __etqTotal1.setForeground(new java.awt.Color(255, 255, 255));
        __etqTotal1.setText("Detalles de Entrada");

        __Entrada.setText("Entrada");
        __Entrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __EntradaActionPerformed(evt);
            }
        });

        __etqTotal2.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __etqTotal2.setForeground(new java.awt.Color(255, 255, 255));
        __etqTotal2.setText("Detalles de Salida");

        __Salida.setText("Salida");
        __Salida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __SalidaActionPerformed(evt);
            }
        });

        __etqTotal3.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __etqTotal3.setForeground(new java.awt.Color(255, 255, 255));
        __etqTotal3.setText("Productos por Arriba de Stock:");

        __StockArriba.setText("Arriba de Stock");
        __StockArriba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __StockArribaActionPerformed(evt);
            }
        });

        __etqTotal4.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __etqTotal4.setForeground(new java.awt.Color(255, 255, 255));
        __etqTotal4.setText("Productos por Abajo de Stock:");

        __StockAbajo.setText("Abajo de Stock");
        __StockAbajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __StockAbajoActionPerformed(evt);
            }
        });

        __etqTotal5.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __etqTotal5.setForeground(new java.awt.Color(255, 255, 255));
        __etqTotal5.setText("Reporte Finanzas");

        __Finanzas.setText("Finanzas");
        __Finanzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __FinanzasActionPerformed(evt);
            }
        });

        __RInventario1.setText("Inventario Refacciones");
        __RInventario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __RInventario1ActionPerformed(evt);
            }
        });

        __Archivo.setText("Archivo");

        __menuAltaProducto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        __menuAltaProducto.setText("Alta Producto");
        __menuAltaProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __menuAltaProductoActionPerformed(evt);
            }
        });
        __Archivo.add(__menuAltaProducto);

        __menuAnterior.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        __menuAnterior.setText("Regresar al Menú Anterior");
        __menuAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __menuAnteriorActionPerformed(evt);
            }
        });
        __Archivo.add(__menuAnterior);

        __menuConsultas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        __menuConsultas.setText("Cosultas");
        __menuConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __menuConsultasActionPerformed(evt);
            }
        });
        __Archivo.add(__menuConsultas);

        __menuMovimientos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        __menuMovimientos.setText("Movimientos");
        __Archivo.add(__menuMovimientos);

        __MenuAbrirArchivo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        __MenuAbrirArchivo.setText("Abrir Archivo...");
        __MenuAbrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __MenuAbrirArchivoActionPerformed(evt);
            }
        });
        __Archivo.add(__MenuAbrirArchivo);

        __menuCerrarSesion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        __menuCerrarSesion.setText("Cerrar Sesion");
        __Archivo.add(__menuCerrarSesion);

        __menuSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        __menuSalir.setText("Salir del Sistema");
        __menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __menuSalirActionPerformed(evt);
            }
        });
        __Archivo.add(__menuSalir);

        jMenuBar1.add(__Archivo);

        __Edicion.setText("Edición");

        __menuCambiarFecha.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        __menuCambiarFecha.setText("Cambiar Fecha del Sistema");
        __menuCambiarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __menuCambiarFechaActionPerformed(evt);
            }
        });
        __Edicion.add(__menuCambiarFecha);

        __menuNuevoUsuario.setText("Nuevo Usuario...");
        __Edicion.add(__menuNuevoUsuario);

        __menuCambiarContraseña.setText("Cambiar mi Contraseña");
        __Edicion.add(__menuCambiarContraseña);

        __menuReporte.setText("Reporte de Usuarios");
        __Edicion.add(__menuReporte);

        __menu_datos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        __menu_datos.setText("Listar Clientes,Proveedores, Propietarios");
        __Edicion.add(__menu_datos);

        __menuBackup.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        __menuBackup.setText("Backup");
        __menuBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __menuBackupActionPerformed(evt);
            }
        });
        __Edicion.add(__menuBackup);

        __menuAcerca.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, java.awt.event.InputEvent.CTRL_MASK));
        __menuAcerca.setText("Acerca de...");
        __Edicion.add(__menuAcerca);

        jMenuBar1.add(__Edicion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(147, 147, 147)
                .addComponent(__etqFecha))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(__etqTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(__RInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(__etqTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(__Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(__etqTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(__Salida, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(__RInventario1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(__etqTotal4)
                .addGap(71, 71, 71)
                .addComponent(__etqTotal5))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(__etqTotal3)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(__StockArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(67, 67, 67)
                .addComponent(__StockAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(__Finanzas, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(560, 560, 560)
                .addComponent(__SALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(__etqFecha)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(__etqTotal)
                        .addGap(5, 5, 5)
                        .addComponent(__RInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(__etqTotal1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(__Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(__etqTotal2)
                        .addGap(7, 7, 7)
                        .addComponent(__Salida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(__RInventario1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(__etqTotal4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(__etqTotal5)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(__etqTotal3)
                        .addGap(6, 6, 6)
                        .addComponent(__StockArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(__StockAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(__Finanzas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(__SALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void __menuAltaProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___menuAltaProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___menuAltaProductoActionPerformed

    private void __menuAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___menuAnteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___menuAnteriorActionPerformed

    private void __MenuAbrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___MenuAbrirArchivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___MenuAbrirArchivoActionPerformed

    private void __menuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___menuSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___menuSalirActionPerformed

    private void __menuCambiarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___menuCambiarFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___menuCambiarFechaActionPerformed

    private void __menuBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___menuBackupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___menuBackupActionPerformed

    private void __menuConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___menuConsultasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___menuConsultasActionPerformed

    private void __RInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___RInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___RInventarioActionPerformed

    private void __EntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___EntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___EntradaActionPerformed

    private void __SalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___SalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___SalidaActionPerformed

    private void __StockArribaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___StockArribaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___StockArribaActionPerformed

    private void __StockAbajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___StockAbajoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___StockAbajoActionPerformed

    private void __FinanzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___FinanzasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___FinanzasActionPerformed

    private void __RInventario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___RInventario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___RInventario1ActionPerformed

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
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenu __Archivo;
    public javax.swing.JMenu __Edicion;
    public javax.swing.JButton __Entrada;
    public javax.swing.JButton __Finanzas;
    public javax.swing.JMenuItem __MenuAbrirArchivo;
    public javax.swing.JButton __RInventario;
    public javax.swing.JButton __RInventario1;
    public javax.swing.JButton __SALIR;
    public javax.swing.JButton __Salida;
    public javax.swing.JButton __StockAbajo;
    public javax.swing.JButton __StockArriba;
    public javax.swing.JLabel __etqFecha;
    public javax.swing.JLabel __etqTotal;
    public javax.swing.JLabel __etqTotal1;
    public javax.swing.JLabel __etqTotal2;
    public javax.swing.JLabel __etqTotal3;
    public javax.swing.JLabel __etqTotal4;
    public javax.swing.JLabel __etqTotal5;
    public javax.swing.JMenuItem __menuAcerca;
    public javax.swing.JMenuItem __menuAltaProducto;
    public javax.swing.JMenuItem __menuAnterior;
    public javax.swing.JMenuItem __menuBackup;
    public javax.swing.JMenuItem __menuCambiarContraseña;
    public javax.swing.JMenuItem __menuCambiarFecha;
    public javax.swing.JMenuItem __menuCerrarSesion;
    public javax.swing.JMenuItem __menuConsultas;
    public javax.swing.JMenuItem __menuMovimientos;
    public javax.swing.JMenuItem __menuNuevoUsuario;
    public javax.swing.JMenuItem __menuReporte;
    public javax.swing.JMenuItem __menuSalir;
    public javax.swing.JMenuItem __menu_datos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}

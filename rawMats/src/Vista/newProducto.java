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
public class newProducto extends javax.swing.JFrame {

    /**
     * Creates new form newProducto
     */
    public newProducto() {
        
        initComponents();
        this.setSize(500,720);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel10 = new javax.swing.JLabel();
        __cmbArea = new javax.swing.JComboBox();
        __etqNewEliminarMaquina = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        __cmbMaquina = new javax.swing.JComboBox();
        __etqNewMaquina = new javax.swing.JLabel();
        __etqAlto_ = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        __descripcion = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        __etqFech = new javax.swing.JLabel();
        __GUARDAR = new javax.swing.JButton();
        __etqClave = new javax.swing.JLabel();
        __BORRAR = new javax.swing.JButton();
        __SALIR = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        __SMin_ = new javax.swing.JTextField();
        __SMax_ = new javax.swing.JTextField();
        __etqNewArea = new javax.swing.JLabel();
        __etqNewEliminarArea = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        __Ubicacion = new javax.swing.JTextField();
        __cmbum = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        __MateriaPrima = new javax.swing.JRadioButton();
        __Refacciones = new javax.swing.JRadioButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        __Archivo = new javax.swing.JMenu();
        __menuMovimientos = new javax.swing.JMenuItem();
        __menuAnterior = new javax.swing.JMenuItem();
        __menuConsultas = new javax.swing.JMenuItem();
        __menuReportes = new javax.swing.JMenuItem();
        __MenuAbrirArchivo = new javax.swing.JMenuItem();
        __menuCerrarSesion = new javax.swing.JMenuItem();
        __menuSalir = new javax.swing.JMenuItem();
        __Edicion = new javax.swing.JMenu();
        __menuCambiarFecha = new javax.swing.JMenuItem();
        __menuNuevoUsuario = new javax.swing.JMenuItem();
        __menuCambiarContraseña = new javax.swing.JMenuItem();
        __menuReporte = new javax.swing.JMenuItem();
        __menuBajaProducto = new javax.swing.JMenuItem();
        __menuAcerca = new javax.swing.JMenuItem();
        __menuBackup = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(getIconImage());
        setResizable(false);

        jLabel10.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Area:");

        __cmbArea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona....." }));
        __cmbArea.setEnabled(false);
        __cmbArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __cmbAreaActionPerformed(evt);
            }
        });

        __etqNewEliminarMaquina.setFont(new java.awt.Font("Papyrus", 0, 12)); // NOI18N
        __etqNewEliminarMaquina.setForeground(new java.awt.Color(255, 255, 255));
        __etqNewEliminarMaquina.setText("X");

        jLabel11.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Sección:");

        __cmbMaquina.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona....." }));
        __cmbMaquina.setEnabled(false);
        __cmbMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __cmbMaquinaActionPerformed(evt);
            }
        });

        __etqNewMaquina.setFont(new java.awt.Font("Papyrus", 0, 12)); // NOI18N
        __etqNewMaquina.setForeground(new java.awt.Color(255, 255, 255));
        __etqNewMaquina.setText("Nueva Seccion...");
        __etqNewMaquina.setEnabled(false);

        __etqAlto_.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        __etqAlto_.setForeground(new java.awt.Color(255, 255, 255));
        __etqAlto_.setText("Descripcion del Producto:");

        __descripcion.setColumns(20);
        __descripcion.setLineWrap(true);
        __descripcion.setRows(5);
        jScrollPane1.setViewportView(__descripcion);

        jLabel2.setFont(new java.awt.Font("Papyrus", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Alta del Producto");

        __etqFech.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        __etqFech.setForeground(new java.awt.Color(255, 255, 255));
        __etqFech.setText("DD/MM/AAAA");

        __GUARDAR.setText("Guardar");

        __etqClave.setBackground(new java.awt.Color(255, 255, 0));
        __etqClave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        __etqClave.setForeground(new java.awt.Color(255, 255, 255));
        __etqClave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        __etqClave.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        __etqClave.setEnabled(false);
        __etqClave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        __BORRAR.setText("Borrar Formulario");

        __SALIR.setText("Regresar");

        jLabel16.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Clave de Producto:");

        jLabel17.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Stock.....");

        jLabel18.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Minimo:");

        jLabel19.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Maximo:");

        __SMin_.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        __SMin_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __SMin_ActionPerformed(evt);
            }
        });

        __SMax_.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        __etqNewArea.setFont(new java.awt.Font("Papyrus", 0, 12)); // NOI18N
        __etqNewArea.setForeground(new java.awt.Color(255, 255, 255));
        __etqNewArea.setText("Nueva Area...");
        __etqNewArea.setEnabled(false);

        __etqNewEliminarArea.setFont(new java.awt.Font("Papyrus", 0, 12)); // NOI18N
        __etqNewEliminarArea.setForeground(new java.awt.Color(255, 255, 255));
        __etqNewEliminarArea.setText("X");

        jLabel20.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Ubicación:");

        __Ubicacion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        __cmbum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona...", "Pz", "Lts", "Kgs", "Mts" }));

        jLabel21.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Unidad de Medida:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 255, 255)));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        buttonGroup1.add(__MateriaPrima);
        __MateriaPrima.setForeground(new java.awt.Color(255, 255, 255));
        __MateriaPrima.setText("Materia Prima");
        __MateriaPrima.setOpaque(false);
        __MateriaPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __MateriaPrimaActionPerformed(evt);
            }
        });

        buttonGroup1.add(__Refacciones);
        __Refacciones.setForeground(new java.awt.Color(255, 255, 255));
        __Refacciones.setText("Refacciones");
        __Refacciones.setOpaque(false);
        __Refacciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __RefaccionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(__MateriaPrima)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(__Refacciones)
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(__MateriaPrima)
                    .addComponent(__Refacciones))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        __Archivo.setText("Archivo");

        __menuMovimientos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        __menuMovimientos.setText("Movimientos");
        __menuMovimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __menuMovimientosActionPerformed(evt);
            }
        });
        __Archivo.add(__menuMovimientos);

        __menuAnterior.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        __menuAnterior.setText("Regresar al Menú Anterior");
        __menuAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __menuAnteriorActionPerformed(evt);
            }
        });
        __Archivo.add(__menuAnterior);

        __menuConsultas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        __menuConsultas.setText("Consultas");
        __Archivo.add(__menuConsultas);

        __menuReportes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        __menuReportes.setText("Generar un reporte...");
        __menuReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __menuReportesActionPerformed(evt);
            }
        });
        __Archivo.add(__menuReportes);

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

        __menuBajaProducto.setText("Baja de Producto");
        __Edicion.add(__menuBajaProducto);

        __menuAcerca.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, java.awt.event.InputEvent.CTRL_MASK));
        __menuAcerca.setText("Acerca de...");
        __Edicion.add(__menuAcerca);

        __menuBackup.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        __menuBackup.setText("Backup");
        __menuBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                __menuBackupActionPerformed(evt);
            }
        });
        __Edicion.add(__menuBackup);

        jMenuBar1.add(__Edicion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel11)
                        .addGap(3, 3, 3)
                        .addComponent(__cmbMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(__etqNewEliminarMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(__GUARDAR, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(__BORRAR, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(__SALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(__etqNewMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel10)
                                .addGap(3, 3, 3)
                                .addComponent(__cmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(__etqNewArea, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(__etqNewEliminarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(__etqClave, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(__etqAlto_))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel18)
                        .addGap(2, 2, 2)
                        .addComponent(__SMin_, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel19)
                        .addGap(9, 9, 9)
                        .addComponent(__SMax_, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(__Ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(__cmbum, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(__etqFech)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(__etqFech))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(__cmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(__etqNewEliminarArea, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(__etqNewArea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(__cmbMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(__etqNewEliminarMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(__etqNewMaquina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(__etqAlto_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(__SMin_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(__SMax_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(__Ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(__cmbum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(__etqClave, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(__GUARDAR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(__BORRAR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(__SALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void __cmbAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___cmbAreaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___cmbAreaActionPerformed

    private void __cmbMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___cmbMaquinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___cmbMaquinaActionPerformed

    private void __menuMovimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___menuMovimientosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___menuMovimientosActionPerformed

    private void __menuAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___menuAnteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___menuAnteriorActionPerformed

    private void __menuReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___menuReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___menuReportesActionPerformed

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

    private void __MateriaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___MateriaPrimaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___MateriaPrimaActionPerformed

    private void __RefaccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___RefaccionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___RefaccionesActionPerformed

    private void __SMin_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event___SMin_ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event___SMin_ActionPerformed

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
            java.util.logging.Logger.getLogger(newProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(newProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(newProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(newProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new newProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenu __Archivo;
    public javax.swing.JButton __BORRAR;
    public javax.swing.JMenu __Edicion;
    public javax.swing.JButton __GUARDAR;
    public javax.swing.JRadioButton __MateriaPrima;
    public javax.swing.JMenuItem __MenuAbrirArchivo;
    public javax.swing.JRadioButton __Refacciones;
    public javax.swing.JButton __SALIR;
    public javax.swing.JTextField __SMax_;
    public javax.swing.JTextField __SMin_;
    public javax.swing.JTextField __Ubicacion;
    public javax.swing.JComboBox __cmbArea;
    public javax.swing.JComboBox __cmbMaquina;
    public javax.swing.JComboBox __cmbum;
    public javax.swing.JTextArea __descripcion;
    public javax.swing.JLabel __etqAlto_;
    public javax.swing.JLabel __etqClave;
    public javax.swing.JLabel __etqFech;
    public javax.swing.JLabel __etqNewArea;
    public javax.swing.JLabel __etqNewEliminarArea;
    public javax.swing.JLabel __etqNewEliminarMaquina;
    public javax.swing.JLabel __etqNewMaquina;
    public javax.swing.JMenuItem __menuAcerca;
    public javax.swing.JMenuItem __menuAnterior;
    public javax.swing.JMenuItem __menuBackup;
    public javax.swing.JMenuItem __menuBajaProducto;
    public javax.swing.JMenuItem __menuCambiarContraseña;
    public javax.swing.JMenuItem __menuCambiarFecha;
    public javax.swing.JMenuItem __menuCerrarSesion;
    public javax.swing.JMenuItem __menuConsultas;
    public javax.swing.JMenuItem __menuMovimientos;
    public javax.swing.JMenuItem __menuNuevoUsuario;
    public javax.swing.JMenuItem __menuReporte;
    public javax.swing.JMenuItem __menuReportes;
    public javax.swing.JMenuItem __menuSalir;
    public javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

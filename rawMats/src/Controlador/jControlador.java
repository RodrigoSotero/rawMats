/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.modelo;
import Vista.CambioP;
import Vista.Consulta1;
import Vista.Fecha;
import Vista.HiloProgreso;
import Vista.Login;
import Vista.MenuMaster;
import Vista.Movimientos;
import Vista.NuevoUsu;
import Vista.ReporteU;
import Vista.Splash;
import Vista.newProducto;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Irving & Rodrigo
 */
public class jControlador implements ActionListener {
    HiloProgreso hilo;
    private static  Vista.Splash splash = new Splash();  
    private final  Login login = new Login();
    private final  modelo mimodelo = new modelo();
    private final  Fecha fecha = new Fecha(); 
    private final  MenuMaster menumaster = new MenuMaster();
    private final  newProducto newP = new newProducto();
    private final  NuevoUsu newU = new NuevoUsu();
    private final  CambioP pass = new CambioP();
    private final Movimientos movimientos = new Movimientos();
    private final Consulta1 conEP = new Consulta1();
    private final  ReporteU reporteu = new ReporteU();
    int a=1,id_responsable,cargo,pedirfecha,confir,filas,columnas,se,act,Min,Max;
    String fec,user="",contra,pswd,fech,horaentrada,horasalida,modificaruser;
    private int tipoalta;
    public jControlador( JFrame padre ){
        //this.frmprincipal = (frmPrincipal) padre;
        this.splash = (Splash) padre;
    }
    public void iniciar(){
        Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
        hilo = new HiloProgreso(this.splash.progreso);
        hilo.start();
        hilo = null;
        this.splash.setLocationRelativeTo(null);
        this.splash.setVisible(true);
        this.splash.progreso.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                if (splash.progreso.getValue()==100){
                    splash.dispose();
                    login.setLocationRelativeTo(null);
                    login.setVisible(true);
                    login.setDefaultCloseOperation(0);
                }
            }
        });
        this.login.__etqBloqMayus.setVisible(true);
        this.login.__INICIA_SESION.setActionCommand("__INICIA_SESION");
        this.login.__INICIA_SESION.addActionListener(this);
        this.login.__INICIA_SESION.setMnemonic('A');
        this.login.__INICIA_SESION.addKeyListener(new java.awt.event.KeyAdapter() {
                     public void keyPressed(java.awt.event.KeyEvent evt){
                         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                            iniciasesion();
                        } 
                     }
                });
        this.login.__SALIR_SISTEMA.setMnemonic('C');
        this.login.__SALIR_SISTEMA.setActionCommand("__SALIR");
        this.login.__SALIR_SISTEMA.addActionListener(this);
        this.login.__Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        login.__etqBloqMayus.setVisible(true);
                        a=0;
                    }
                    else {
                        login.__etqBloqMayus.setVisible(false);
                        a=1;
                    }
                }
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    login.__Pswd.requestFocus();
                }
            }
        });
        this.login.__Pswd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        login.__etqBloqMayus.setVisible(true);
                        a=0;
                    }
                    else {
                        login.__etqBloqMayus.setVisible(false);
                        a=1;
                    }
                }
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    iniciasesion();
                } 
            }
        });
        this.login.__Pswd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                String contra = login.__Pswd.getText().toString();
                login.__Pswd.setToolTipText("<html><body bgcolor=\"red\"><font color=\"white\">"+contra+"</font></body></html>");
            }
        });
        this.fecha.__ACEPTAR.setActionCommand("__ACEPTAR_FECHA");
        this.fecha.__ACEPTAR.addActionListener(this);
        fecha.date.getDateEditor().getUiComponent().addKeyListener(new java.awt.event.KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    fechaini();
                } 
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        
        });       
        this.fecha.__CANCELAR.setActionCommand("__CANCELAR_FECHA");
        this.fecha.__CANCELAR.addActionListener(this);
        //FORMULARIO MENU MASTER
        this.menumaster.__ALTAPRODUCTO.setActionCommand("__MENU_MASTER_ALTAPRODUCTO");
        this.menumaster.__ALTAPRODUCTO.setMnemonic('N');
        this.menumaster.__ALTAPRODUCTO.addActionListener(this);
        this.menumaster.__MOVIMIENTOS.setActionCommand("__MENU_MASTER_MOVIMIENTOS");
        this.menumaster.__MOVIMIENTOS.setMnemonic('M');
        this.menumaster.__MOVIMIENTOS.addActionListener(this);
        this.menumaster.__REPORTES.setActionCommand("__MENU_MASTER_REPORTES");
        this.menumaster.__REPORTES.setMnemonic('R');
        this.menumaster.__REPORTES.addActionListener(this);
        this.menumaster.__CONSULTAS.setActionCommand("__MENU_MASTER_CONSULTAS");
        this.menumaster.__CONSULTAS.setMnemonic('C');
        this.menumaster.__CONSULTAS.addActionListener(this);
        this.menumaster.__SALIR.setActionCommand("__MENU_MASTER_CANCELAR");
        this.menumaster.__SALIR.setMnemonic('A');
        this.menumaster.__SALIR.addActionListener(this);
        //FORMULARIO NEW PRODUCTO
        this.newP.__GUARDAR.setActionCommand("__GUARDAR_PRODUCTO");
        this.newP.__GUARDAR.setMnemonic('G');
        this.newP.__GUARDAR.addActionListener(this);
        this.newP.__BORRAR.setActionCommand("__BORRAR_PRODUCTO");
        this.newP.__BORRAR.setMnemonic('B');
        this.newP.__BORRAR.addActionListener(this);
        this.newP.__SALIR.setActionCommand("__SALIR_PRODUCTO");
        this.newP.__SALIR.setMnemonic('R');
        this.newP.__SALIR.addActionListener(this);
        this.newP.__etqNewArea.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                nuevaArea(newP.__cmbArea);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
               newP.__etqNewArea.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                newP.__etqNewArea.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        this.newP.__etqNewMaquina.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                nuevaMaquina(newP.__cmbMaquina);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
               newP.__etqNewMaquina.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                newP.__etqNewMaquina.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        this.newP.__cmbArea.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(newP.__cmbArea.getSelectedIndex()<=0){
                    return;
                }else{
                    int idarea = busquedaid("area");
                    System.out.println(newP.__cmbArea.getSelectedIndex());
                    try {
                        ResultSet buscarMaquina = mimodelo.buscarMaquina(idarea);
                        newP.__cmbMaquina.removeAllItems();
                        newP.__cmbMaquina.addItem("Selecciona...");
                        while(buscarMaquina.next()){
                            newP.__cmbMaquina.addItem(buscarMaquina.getString(1)); 
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        this.newP.__descripcion.addKeyListener(new java.awt.event.KeyListener() {
            public void keyTyped(KeyEvent e) {
                KeyTipedLetrasNumCar(e);
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });
        this.newP.__SMax_.addKeyListener(new java.awt.event.KeyListener() {
            public void keyTyped(KeyEvent e) {                
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });               
        //--NuevoUsuario                
        this.newU.__ACEPTAR.setActionCommand("__ACEPTARUSER"); 
        this.newU.__ACEPTAR.setMnemonic('A');
        this.newU.__ACEPTAR.addActionListener(this);
        this.newU.__ACEPTAR.addKeyListener(new java.awt.event.KeyAdapter() {            
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        });
        this.newU.__MODIFICARUSER.setActionCommand("__MODIFICARUSER");
        this.newU.__MODIFICARUSER.setMnemonic('M');
        this.newU.__MODIFICARUSER.addActionListener(this);        
        this.newU.__BLOQUEARUSUARIO.setActionCommand("__BLOQUEARUSER");
        this.newU.__BLOQUEARUSUARIO.setMnemonic('B');
        this.newU.__BLOQUEARUSUARIO.addActionListener(this);
        this.newU.__CANCELAR.setActionCommand("__CANCELARUSER");
        this.newU.__CANCELAR.setMnemonic('C');
        this.newU.__CANCELAR.addKeyListener(new java.awt.event.KeyAdapter() {           
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    cancelarnewU();
                } 
            }

        });
        this.newU.__CANCELAR.addActionListener(this);
        this.newU.__NEWUSER.setActionCommand("__NUEVOUSER");        
        this.newU.__NEWUSER.setMnemonic('N');
        this.newU.__NEWUSER.addKeyListener(new java.awt.event.KeyAdapter() {            
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    borrarFormularioNewUser();
                } 
            }
        });
        this.newU.__NEWUSER.addActionListener(this);    
        this.newU.__nombreUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                 //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        newU.__etqBloqMayus.setVisible(true);
                        a=0;
                    }
                    else {
                        newU.__etqBloqMayus.setVisible(false);
                        a=1;
                    }
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        }); 
        this.newU.__User.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        newU.__etqBloqMayus.setVisible(true);
                        a=0;
                    }
                    else {
                        newU.__etqBloqMayus.setVisible(false);
                        a=1;
                    }
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        }); 
        this.newU.__NewPswd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        newU.__etqBloqMayus.setVisible(true);
                        a=0;
                    }
                    else {
                        newU.__etqBloqMayus.setVisible(false);
                        a=1;
                    }
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        }); 
        this.newU.__NewPswd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                String contra = newU.__NewPswd.getText().toString();
                newU.__NewPswd.setToolTipText("<html><body bgcolor=\"red\"><font color=\"white\">"+contra+"</font></body></html>");
            }
        });
        this.newU.__NewPswd2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        newU.__etqBloqMayus.setVisible(true);
                        a=0;
                    }
                    else {
                        newU.__etqBloqMayus.setVisible(false);
                        a=1;
                    }
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        });
        this.newU.__NewPswd2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                String contra = newU.__NewPswd2.getText().toString();
                newU.__NewPswd2.setToolTipText("<html><body bgcolor=\"red\"><font color=\"white\">"+contra+"</font></body></html>");
            }
        });
        this.newU.__optActivo.addKeyListener(new java.awt.event.KeyAdapter() {           
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        });
        this.newU.__optInactivo.addKeyListener(new java.awt.event.KeyAdapter() {           
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        });
        this.newU.__optMaster.addKeyListener(new java.awt.event.KeyAdapter() {           
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        });
        this.newU.__optJunior.addKeyListener(new java.awt.event.KeyAdapter() {           
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        });
        this.newU.__optKid.addKeyListener(new java.awt.event.KeyAdapter() {           
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();                
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altamodificacion();
                } 
            }
        });
        //nuevo usuarios finaliza y comienza el cambio de contraseña
        this.pass.__GUARDAR.setActionCommand("__GUARDARPASSWORD");
        this.pass.__GUARDAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        pass.__etqBloqMayus.setVisible(true);
                        a=0;
                    }else{
                        pass.__etqBloqMayus.setVisible(false);
                        a=1;
                    }                   
                } 
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    cambiopswd();
                } 
            }            
        });
        this.pass.__GUARDAR.setMnemonic('G');
        this.pass.__GUARDAR.addActionListener(this);
        this.pass.__CANCELAR.setActionCommand("__CANCELARPASSWORD");
        this.pass.__CANCELAR.setMnemonic('C');
        this.pass.__CANCELAR.addActionListener(this);
        this.pass.__Pswd.addKeyListener(new java.awt.event.KeyAdapter() {//Evaluacion del las Caja de texto __Pswd del formulario Password
            public void keyTyped(java.awt.event.KeyEvent evt){//y evento de la etiqueta bloq mayus del mismo formulario Pasword
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        pass.__etqBloqMayus.setVisible(true);
                        a=0;
                    }else{
                        pass.__etqBloqMayus.setVisible(false);
                        a=1;
                    }                   
                } 
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    pass.__NewPswd.requestFocus(true);
                } 
            }            
        });
        this.pass.__Pswd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                String contra = pass.__Pswd.getText().toString();
                pass.__Pswd.setToolTipText("<html><body bgcolor=\"red\"><font color=\"white\">"+contra+"</font></body></html>");
            }
        });
        this.pass.__NewPswd.addKeyListener(new java.awt.event.KeyAdapter() {//Evaluacion del las Caja de texto __Pswd del formulario Password
            public void keyTyped(java.awt.event.KeyEvent evt){//y evento de la etiqueta bloq mayus del mismo formulario Pasword
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        pass.__etqBloqMayus.setVisible(true);
                        a=0;
                    }else{
                        pass.__etqBloqMayus.setVisible(false);
                        a=1;
                    }                   
                } 
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    pass.__NewPswd1.requestFocus(true);
                } 
            }            
        });
        this.pass.__NewPswd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                String contra = pass.__NewPswd.getText().toString();
                pass.__NewPswd.setToolTipText("<html><body bgcolor=\"red\"><font color=\"white\">"+contra+"</font></body></html>");
            }
        });
        this.pass.__NewPswd1.addKeyListener(new java.awt.event.KeyAdapter() {//Evaluacion del las Caja de texto __Pswd del formulario Password
            public void keyTyped(java.awt.event.KeyEvent evt){//y evento de la etiqueta bloq mayus del mismo formulario Pasword
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_CAPS_LOCK){
                    //_ Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        pass.__etqBloqMayus.setVisible(true);
                        a=0;
                    }else{
                        pass.__etqBloqMayus.setVisible(false);
                        a=1;
                    }                   
                } 
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    pass.__GUARDAR.requestFocus(true);
                } 
            }            
        });
        this.pass.__NewPswd1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                String contra = pass.__NewPswd1.getText().toString();
                pass.__NewPswd1.setToolTipText("<html><body bgcolor=\"red\"><font color=\"white\">"+contra+"</font></body></html>");
            }
        });
        //FIN DEL CAMBIO DE CONTRASEÑA Y INICIO DEL REPORTE DE USUARIOS
        this.reporteu.__ACEPTAR.setActionCommand("__ACEPTAR_REPORTE_USUARIO");
        this.reporteu.__ACEPTAR.setMnemonic('A');
        this.reporteu.__ACEPTAR.addActionListener(this);
        this.reporteu.__REGRESAR.setActionCommand("__REGRESAR_REPORTE_USUARIO");
        this.reporteu.__REGRESAR.setMnemonic('R');
        this.reporteu.__NUEVABUSQUEDA.setActionCommand("__NUEVABUSQUEDA_REPORTE_USUARIO");
        this.reporteu.__NUEVABUSQUEDA.setMnemonic('N');
        this.reporteu.__NUEVABUSQUEDA.addActionListener(this);
        this.reporteu.__REGRESAR.addActionListener(this);
        this.reporteu.__ReporteNombreUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
             public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                        buscarreporteusuario(2);
                    //eSTO ES UNA MODIFICACION 
                } 
            }
            
        });
        this.reporteu.reportedate.getDateEditor().getUiComponent().addKeyListener(new java.awt.event.KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {                
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){                     
                        buscarreporteusuario(2);                    
                } 
            }
            @Override
            public void keyReleased(KeyEvent e) {                
            }        
        });
        //FIN CAMBIO DE CONTRASEÑA Y INICIA LA CONSULTA DEL PRODUCTO
        this.conEP.__ACEPTARNP.setActionCommand("__ACEPTARNP");
        this.conEP.__ACEPTARNP.setMnemonic('A');
        this.conEP.__ACEPTARNP.addActionListener(this);
        this.conEP.__MODIFICAR.setActionCommand("__MODIFICARCONSULTAEP");
        this.conEP.__MODIFICAR.setMnemonic('M'); 
        this.conEP.__MODIFICAR.addActionListener(this);
        this.conEP.__ELIMINAR.setActionCommand("__ELIMINARCONSULTAEP");
        this.conEP.__ELIMINAR.setMnemonic('E');         
        this.conEP.__ELIMINAR.addActionListener(this);
        this.conEP.__REGRESAR.setActionCommand("__REGRESARCONSULTAEP");
        this.conEP.__REGRESAR.setMnemonic('R');         
        this.conEP.__REGRESAR.addActionListener(this);
        this.conEP.__descripcionP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
             public void keyPressed(java.awt.event.KeyEvent evt){
                 
            }
             public void keyReleased(java.awt.event.KeyEvent evt){
                 bucarPorducto();
             }                      
        });        
        //MENUS
        //Menu New Producto 
        this.newP.__menuMovimientos.setActionCommand("__MENU_MOV_PAPEL");
        this.newP.__menuMovimientos.addActionListener(this);
        this.newP.__menuMovimientos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,InputEvent.CTRL_MASK));
        this.newP.__menuAnterior.setActionCommand("__MENU_ANTERIOR");
        this.newP.__menuAnterior.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        this.newP.__menuAnterior.addActionListener(this);
        this.newP.__menuConsultas.setActionCommand("__MENU_CONSULTAS");
        this.newP.__menuConsultas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
        this.newP.__menuConsultas.addActionListener(this);
        this.newP.__menuReportes.setActionCommand("__MENU_REPORTES");
        this.newP.__menuConsultas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK));
        this.newP.__menuReportes.addActionListener(this);
        this.newP.__MenuAbrirArchivo.setActionCommand("__MENU_ABRIR_ARCHIVO");
        this.newP.__MenuAbrirArchivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
        this.newP.__MenuAbrirArchivo.addActionListener(this);
        this.newP.__menuCerrarSesion.setActionCommand("__MENU_CERRAR_SESION");
        this.newP.__menuCerrarSesion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK));
        this.newP.__menuCerrarSesion.addActionListener(this);
        this.newP.__menuSalir.setActionCommand("__MENU_SALIR");
        this.newP.__menuSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.SHIFT_MASK |InputEvent.CTRL_MASK));
        this.newP.__menuSalir.addActionListener(this);
        this.newP.__menuCambiarFecha.setActionCommand("__MENU_FECHA");
        this.newP.__menuCambiarFecha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
        this.newP.__menuCambiarFecha.addActionListener(this);
        this.newP.__menuNuevoUsuario.setActionCommand("__MENU_NEWUSER");
        this.newP.__menuNuevoUsuario.addActionListener(this);
        this.newP.__menuCambiarContraseña.setActionCommand("__MENU_CONTRASEÑA");
        this.newP.__menuCambiarContraseña.addActionListener(this);        
        this.newP.__menuReporte.setActionCommand("__MENU_REPORTE_USUARIOS");
        this.newP.__menuReporte.addActionListener(this);
        this.newP.__menucerrarsesiones.setActionCommand("__MENU_CERRAR_SESIONES");
        this.newP.__menucerrarsesiones.addActionListener(this);
        this.newP.__menuBajaProducto.setActionCommand("__MENU_BAJA_PRODUCTO");
        this.newP.__menuBajaProducto.addActionListener(this);
        this.newP.__menuAcerca.setActionCommand("__MENU_ACERCADE");
        this.newP.__menuAcerca.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10,InputEvent.CTRL_MASK));
        this.newP.__menuAcerca.addActionListener(this);  
        this.newP.__menuBackup.setActionCommand("__MENU_BACKUP");
        this.newP.__menuBackup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK));
        this.newP.__menuBackup.addActionListener(this); 
        //MENUS MOVIMIENTOS
        this.movimientos.__menuMovimientos.setActionCommand("__MENU_MOV_PAPEL");
        this.movimientos.__menuMovimientos.addActionListener(this);
        this.movimientos.__menuMovimientos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,InputEvent.CTRL_MASK));
        this.movimientos.__menuAnterior.setActionCommand("__MENU_ANTERIOR");
        this.movimientos.__menuAnterior.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        this.movimientos.__menuAnterior.addActionListener(this);
        this.movimientos.__menuConsultas.setActionCommand("__MENU_CONSULTAS");
        this.movimientos.__menuConsultas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
        this.movimientos.__menuConsultas.addActionListener(this);
        this.movimientos.__menuReportes.setActionCommand("__MENU_REPORTES");
        this.movimientos.__menuConsultas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK));
        this.movimientos.__menuReportes.addActionListener(this);
        this.movimientos.__MenuAbrirArchivo.setActionCommand("__MENU_ABRIR_ARCHIVO");
        this.movimientos.__MenuAbrirArchivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
        this.movimientos.__MenuAbrirArchivo.addActionListener(this);
        this.movimientos.__menuCerrarSesion.setActionCommand("__MENU_CERRAR_SESION");
        this.movimientos.__menuCerrarSesion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK));
        this.movimientos.__menuCerrarSesion.addActionListener(this);
        this.movimientos.__menuSalir.setActionCommand("__MENU_SALIR");
        this.movimientos.__menuSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.SHIFT_MASK |InputEvent.CTRL_MASK));
        this.movimientos.__menuSalir.addActionListener(this);
        this.movimientos.__menuCambiarFecha.setActionCommand("__MENU_FECHA");
        this.movimientos.__menuCambiarFecha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
        this.movimientos.__menuCambiarFecha.addActionListener(this);
        this.movimientos.__menuNuevoUsuario.setActionCommand("__MENU_NEWUSER");
        this.movimientos.__menuNuevoUsuario.addActionListener(this);
        this.movimientos.__menuCambiarContraseña.setActionCommand("__MENU_CONTRASEÑA");
        this.movimientos.__menuCambiarContraseña.addActionListener(this);        
        this.movimientos.__menuReporte.setActionCommand("__MENU_REPORTE_USUARIOS");
        this.movimientos.__menuReporte.addActionListener(this);
        this.movimientos.__menucerrarsesiones.setActionCommand("__MENU_CERRAR_SESIONES");
        this.movimientos.__menucerrarsesiones.addActionListener(this);        
        this.movimientos.__menuAcerca.setActionCommand("__MENU_ACERCADE");
        this.movimientos.__menuAcerca.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10,InputEvent.CTRL_MASK));
        this.movimientos.__menuAcerca.addActionListener(this);  
        this.movimientos.__menuBackup.setActionCommand("__MENU_BACKUP");
        this.movimientos.__menuBackup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK));
        this.movimientos.__menuBackup.addActionListener(this);
        //FIN MENU
    }
    
    public enum Accion{
        __INICIA_SESION,
        __SALIR,
        __CANCELAR_FECHA,
        __ACEPTAR_FECHA,
        __MENU_MASTER_ALTAPRODUCTO,
        __MENU_MASTER_MOVIMIENTOS,
        __MENU_MASTER_REPORTES,
        __MENU_MASTER_CONSULTAS,
        __MENU_MASTER_CANCELAR,
        //MENUS DE LOS FORMULARIOS
        __MENU_ANTERIOR,
        __MENU_ALTA,
        __MENU_MOV_PAPEL,        
        __MENU_CONSULTAS,
        __MENU_REPORTES,
        __MENU_CERRAR_SESION,
        __MENU_SALIR,
        __MENU_FECHA,
        __MENU_NEWUSER,
        __MENU_CONTRASEÑA,
        __MENU_REPORTE_USUARIOS,
        __MENU_ACERCADE,
        __MENU_BACKUP,
        __MENU_DATOS,
        __MENU_MOV_TRASPASO,
        __MENU_ABRIR_ARCHIVO,
        __MENU_CERRAR_SESIONES,
        __MENU_BAJA_PRODUCTO,
        //Nuevo Usuario ABC
        __MODIFICARUSER,
        __BLOQUEARUSER,
        __CANCELARUSER,
        __NUEVOUSER,
        __ACEPTARUSER,
        //cambio de contraseña
        __GUARDARPASSWORD,
        __CANCELARPASSWORD,
        //REPORTE DE USUARIOS
        __ACEPTAR_REPORTE_USUARIO,
        __REGRESAR_REPORTE_USUARIO,
        __NUEVABUSQUEDA_REPORTE_USUARIO,
        //CONSULTA INICIAL DEL PRODUCTO
        __ACEPTARNP,       
        __MODIFICARCONSULTAEP,        
        __ELIMINARCONSULTAEP,        
        __NUEVABUSQUEDACONSULTAEP,        
        __REGRESARCONSULTAEP,
        //NUEVO PRODUCTO
        __GUARDAR_PRODUCTO,                
        __BORRAR_PRODUCTO,                
        __SALIR_PRODUCTO        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(Accion.valueOf(e.getActionCommand())){
            case __ACEPTAR_FECHA:
                fechaini();
                break;
            case __INICIA_SESION:
                break;
            case __SALIR:
                confir = mensajeConfirmacion("¿Desea Salir?","Salida");
                if (confir==JOptionPane.OK_OPTION){
                    this.SalirSistema();
                }
                break;
            case __CANCELAR_FECHA:
                confir = this.mensajeConfirmacion("¿Estas seguro que deseas salir?","ALERTA");
                if (confir==JOptionPane.OK_OPTION){
                    login.setEnabled(true);
                    newP.setEnabled(true);
                    fecha.setVisible(false);
                     Calendar Cal= Calendar.getInstance();                                                  
                               String hora=Cal.get(Cal.HOUR_OF_DAY)<10 ? "0"+Cal.get(Cal.HOUR_OF_DAY) : ""+Cal.get(Cal.HOUR_OF_DAY);
                               String minute=Cal.get(Cal.MINUTE)<10 ? "0"+Cal.get(Cal.MINUTE) : ""+Cal.get(Cal.MINUTE);
                               horasalida = hora+":"+minute;                
                    boolean registrasalida=mimodelo.horasalida(horasalida,user);
                    if(!user.equals("ROOT")){
                                try {
                                    mimodelo.cerrarsesion(user);
                                } catch (SQLException ex) {
                                    Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                                }
                    }
                            login.__Usuario.requestFocus();
                        }
                break;
            case __MENU_MASTER_ALTAPRODUCTO:
                menumaster.dispose();
                conEP.setVisible(true);
                conEP.setName("Consulta del Producto");
                break;
            case __MENU_MASTER_MOVIMIENTOS:
                movimientos.setName("Consulta del Producto");
                menumaster.dispose();
                movimientos.setVisible(true);
                movimientos.setLocationRelativeTo(null);
                movimientos.setDefaultCloseOperation(0);
                ponerfecha();
                addItems("movimientos");                
                break;
              //--MENUS DE TOODOS LOS FORMULARIOS
            case __MENU_ANTERIOR:                
                    regresar();
                break;            
            case __MENU_ALTA:
                switch(cargo){
                    case 1:
                        confir = mensajeConfirmacion("¿Realmente Deseas ir a Alta de Papel?","Salida");
                            if (confir == JOptionPane.OK_OPTION){
                            this.newP.setVisible(true);
                            this.newP.setLocationRelativeTo(null);
                            movimientos.dispose();
//                            consultas.dispose();
//                            reportes.dispose();
//                            reporteuser.dispose();
//                            verconsulta.dispose();
                            this.addItems("newP");
                            }
                    break;                        
                    case 2:
                        mensaje(2,"No Hay Acceso a esta Información");
                    break;
                    case 3:
                        mensaje(2,"No Hay Acceso a esta Información");
                        break;
                }
                break;
            case __MENU_MOV_PAPEL:
                 if(cargo!=3){
                      confir = mensajeConfirmacion("¿Realmente Deseas ir a Movimientos?","Salida");
                        if (confir == JOptionPane.OK_OPTION){
                            movimientos.setVisible(true);
                            movimientos.setLocationRelativeTo(null);
//                            consultas.dispose();                        
                            this.newP.dispose();
//                            reportes.dispose();
                            ponerfecha();
//                            movimientos.__TipoEntrada.requestFocus();
//                            addItems("movimientos");
//                            maximoentrada();
//                            maximosalida();
//                            maximosalidah();
//                            maximosalidab();
                        }
                    }else{
                        mensaje(2,"No Hay Acceso a esta Información");
                        break;
                    }
                break;
            case __MENU_CONSULTAS:
                 switch(cargo){
                    case 1:
                        confir = mensajeConfirmacion("¿Realmente Deseas ir a Consultas?","Salida");
                        if (confir == JOptionPane.OK_OPTION){
                            this.addItems("consultas");
//                            consultas.setVisible(true);
//                            consultas.setLocationRelativeTo(null);
                            this.newP.dispose();
//                            reportes.dispose();
                            movimientos.dispose();
//                            reporteuser.dispose();
//                            verconsulta.dispose();
                        }                    
                        break;
                    case 2:
                         confir = mensajeConfirmacion("¿Realmente Deseas ir a Consultas?","Salida");
                        if (confir == JOptionPane.OK_OPTION){
                            this.addItems("consultas");
//                            consultas.setVisible(true);
//                            consultas.setLocationRelativeTo(null);
                            this.newP.dispose();
//                            reportes.dispose();
                            movimientos.dispose();
//                            reporteuser.dispose();
//                            verconsulta.dispose();
                        }
                        break;
                    case 3:
                        mensaje(2,"No Hay Acceso a esta Información");
                        break;               
                }
                break;
            case __MENU_REPORTES:
                switch(cargo){
                    case 1:
                        confir = mensajeConfirmacion("¿Realmente Deseas ir a Generar un Reporte?","Salida");
                            if (confir == JOptionPane.OK_OPTION){
//                            reportes.setVisible(true);
//                            reortes.setLocationRelativeTo(null);
                            movimientos.dispose();
                            this.newP.dispose();
//                            consultas.dispose();
//                            reporteuser.dispose();
//                            verconsulta.dispose();
                            }
                        break;
                    case 2:
                         confir = mensajeConfirmacion("¿Realmente Deseas ir a Generar un Reporte?","Salida");
                            if (confir == JOptionPane.OK_OPTION){
//                            reportes.setVisible(true);
//                            reportes.setLocationRelativeTo(null);
                            movimientos.dispose();
                            this.newP.dispose();
//                            consultas.dispose();
//                            reporteuser.dispose();
//                            verconsulta.dispose();
                            }
                        break;
                     case 3:
                        mensaje(2,"No Hay Acceso a esta Información");
                        break; 
                }                      
                break;
            case __MENU_CERRAR_SESION:
                confir = mensajeConfirmacion("¿Realmente Desea Cerrar la Sesión?","Salida");
                    if (confir == JOptionPane.OK_OPTION){
                        try {
                            login.setVisible(true);
                            login.setLocationRelativeTo(null);
                            this.newP.dispose();
                            movimientos.dispose();
//                            consultas.dispose();
//                            reportes.dispose();
//                            reporteuser.dispose();
                            mimodelo.cerrarsesion(user);
//                            borrarFormularioNewUser();
//                            borrarFormularioAltaPapel();
//                            borrarFormularioMovimientosPapel();
//                            borrarFormularioProveedor();
//                            borrarFormularioConsultas();
//                            borrarFormularioEmergente();
                        } catch (SQLException ex) {
                            mensaje(3,ex.getMessage());
                        }
                    }
                break;
            case __MENU_SALIR:
                confir = mensajeConfirmacion("¿Realmente Desea Salir del Sistema?","Salida");
                            if (confir == JOptionPane.OK_OPTION){
                            SalirSistema();
                            }
                break;
            case __MENU_FECHA:
                    pedirfecha=1;
                    newP.setEnabled(false);
                    movimientos.setEnabled(false);
//                    reportes.setEnabled(false);
//                    consultas.setEnabled(false);
                    fecha.setVisible(true);
                    fecha.setLocationRelativeTo(null);
                break;
            case __MENU_NEWUSER:
                Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);  
                    newU.__etqBloqMayus.setVisible(true);
                        if(cargo!=3 || cargo!=2){
                            newP.setEnabled(false);
                            movimientos.setEnabled(false);
//                            reportes.setEnabled(false);
//                            consultas.setEnabled(false);
                            newU.setVisible(true);
                            newU.setLocationRelativeTo(null);
                         }else{
                            mensaje(2,"No Hay Acceso a esta Información");
                        }
                break;
            case __MENU_CONTRASEÑA:
                if(menumaster.__etqUsuarioMenuMaster.getText().equals("ROOT")){
                        mensaje(2,"No Puedes Cambiar la Contraseña a Root pero Gracias por Intentarlo");
                    }else{
                    Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    newP.setEnabled(false);
                    movimientos.setEnabled(false);
//                    reportes.setEnabled(false);
//                    consultas.setEnabled(false);
                    pass.__etqBloqMayus.setVisible(true);
                    pass.show();
                    pass.setLocationRelativeTo(null);
                    }
                break;
            case __MENU_REPORTE_USUARIOS:
                switch(cargo){
                        case 1:                                                       
                                reporteu.setVisible(true);
                                reporteu.setLocationRelativeTo(null);
                                this.buscarreporteusuario(1);
                                newP.setEnabled(false);
                                movimientos.setEnabled(false);
//                                reportes.setEnabled(false);
//                                consultas.setEnabled(false);
                            break;
                        case 2:
                            mensaje(2,"No Hay Acceso a esta Información");
                            break;
                        case 3:
                            mensaje(2,"No Hay Acceso a esta Información");
                            break;
                    }            
                break;
            case __MENU_ACERCADE:
                mensaje(1,"Dispaper \nBy: =F@vo0R!to0= && yo0po0");
                break;
            case __MENU_BACKUP:
                 if(cargo==1){
                        mimodelo.bp(fech+"desdemenu");
                        this.enviaarchivo("C:\\iexsa\\backups\\dump"+fech+"desdemenu.sql","dispaper.iexsa@gmail.com" ,"Backup de la base de datos");
                        File fichero = new File("C:\\iexsa\\backups\\dump"+fech+"desdemenu.sql");
                        fichero.delete();
                        mensaje(1,"Backup de la Base de Datos Correcta");
                    }else{
                        mensaje(2,"No Hay Acceso a esta Información");
                    }
                break;
            case __MENU_DATOS:
                //this.mimodelo.abrirReporte("Datos.jrxml",new HashMap());
                break;           
            case __MENU_ABRIR_ARCHIVO:
                try {
                    File archivo = this.archivo(2);
                    if(archivo==null){
                        break;
                    }
                    String fil = archivo.getAbsolutePath();
                    if(!fil.isEmpty()){
                        File f = new File(fil);
                        Desktop.getDesktop().open(f);
                    }else{
                        mensaje(3,"Debes Seleccionar el archivo para abrir");
                    }
                } catch (IOException ex) {
                    mensaje(3,ex.getMessage());
                }
                break;
            case __MENU_CERRAR_SESIONES:
                switch(cargo){
                        case 1: 
                            int SesionCerrada=0;                            
                            confir= mensajeConfirmacion("Realmente Desea Cerrar Todas las Sesiones ","Cerrar Sesiones");
                            if(confir==JOptionPane.OK_OPTION){
                                boolean SC=mimodelo.cerrarsesiones(SesionCerrada);
                                    if(SC==true){
                                        mensaje(1,"Sesiones Cerradas con Exito");
                                        tipoalta=0;
                                    }else{
                                        mensaje(3,"Ocurrio un Error al Cerrar las Sesiones");
                                    }
                            }
                            break;
                        case 2:
                            mensaje(2,"No Hay Acceso a esta Información");
                            break;
                        case 3:
                            mensaje(2,"No Hay Acceso a esta Información");
                            break;
                    }
                break;
            case __MENU_BAJA_PRODUCTO:
                switch(cargo){
                        case 1:  
                                newP.setEnabled(false);
                                movimientos.setEnabled(false);
//                                reportes.setEnabled(false);
//                                consultas.setEnabled(false);
//                                bajaP.setVisible(true);
//                                bajaP.setLocationRelativeTo(null);
                            break;
                        case 2:
                            mensaje(2,"No Hay Acceso a esta Información");
                            break;
                        case 3:
                            mensaje(2,"No Hay Acceso a esta Información");
                            break;
                    } 
                break;
            //Casos de los botones del formulario de nuevo usuario           
            case __ACEPTARUSER:
                altamodificacion();
                break;
            case __CANCELARUSER:
                cancelarnewU();
                break;
            case __NUEVOUSER:
                borrarFormularioNewUser();
                break;
            case __MODIFICARUSER://ingresar un nombre de usuario para modificar algunos de sus valores  
                modificaruser = JOptionPane.showInputDialog(null,"Escribe el Nombre de Usuario a Modificar","Modificar Usuario",JOptionPane.PLAIN_MESSAGE);
                modificaruser = modificaruser.toUpperCase();
                    if(modificaruser==null){
                        tipoalta=1;
                        break;
                    }
                    if(menumaster.__etqUsuarioMenuMaster.getText().equals(modificaruser)){
                        mensaje(2,"No Puedes Modificar la Sesion Activa");
                    }else{
                        try {                                               
                            ResultSet buscarUser = mimodelo.buscarUser(modificaruser);  
                                if(buscarUser.next()){
                                 buscarUser.beforeFirst();
                                    while (buscarUser.next()){
                                        newU.__nombreUser.setText(buscarUser.getString(4));
                                        newU.__User.setText(buscarUser.getString(5));
                                        newU.__NewPswd.setText(buscarUser.getString("_cntr"));
                                        newU.__NewPswd2.setText(buscarUser.getString("_cntr"));
                                            if(buscarUser.getInt(3)==1){
                                                newU.__optMaster.setSelected(true);
                                            }               
                                            if(buscarUser.getInt(3)==2){
                                                newU.__optJunior.setSelected(true);
                                            }
                                            if(buscarUser.getInt(3)==3){
                                                newU.__optKid.setSelected(true);
                                            }
                                            if(buscarUser.getInt(6)==1){
                                                newU.__optActivo.setSelected(true);
                                            }
                                            if(buscarUser.getInt(6)==0){
                                                newU.__optInactivo.setSelected(true);
                                            }
                                            tipoalta=1;
                                    } 
                                }else{
                                    mensaje(2,"No Existe El Usuario");
                                    tipoalta=0;
                                    break;
                                }   
                            }catch (SQLException ex) {
                                mensaje(3,ex.getMessage());
                            }
                    }
            break;
            case __BLOQUEARUSER:
                String bloquearuser = JOptionPane.showInputDialog(null,"Escribe el Nombre de Usuario a Bloquear","Bloquear Usuario",JOptionPane.PLAIN_MESSAGE);
                bloquearuser = bloquearuser.toUpperCase();
                if(bloquearuser==null){
                    break;
                }  
                if(menumaster.__etqUsuarioMenuMaster.getText().equals(bloquearuser)){
                    mensaje(2,"No Puedes Bloquear la Sesion Activa");
                }else{
                    try {
                        ResultSet bu = mimodelo.buscarUser(bloquearuser);
                            if(!bu.next()){
                                mensaje(2,"El usuario no existe");
                            }else{
                                confir= mensajeConfirmacion("Realmente Desea Bloquear el Usuario "+bloquearuser,"Modificación");
                                if(confir==JOptionPane.OK_OPTION){
                                    boolean busuario=mimodelo.bloquearusuario(bloquearuser);
                                    if(busuario==true){
                                        mensaje(1,"Usuario Bloqueado");
                                    }
                                }
                            }      
                    }catch (SQLException ex) {
                        mensaje(3,ex.getMessage());
                    }          
                }                      
                break; 
            case __GUARDARPASSWORD:
                cambiopswd();
                break;
            case __CANCELARPASSWORD:
                newP.setEnabled(true);
                movimientos.setEnabled(true);
//                reportes.setEnabled(true);
//                consultas.setEnabled(true);
                pass.__Pswd.setText("");
                pass.__NewPswd1.setText("");
                pass.__NewPswd.setText("");
                pass.setVisible(false);
                break;
            case __ACEPTAR_REPORTE_USUARIO:
                buscarreporteusuario(2);
                buscarreporteusuario(1);
                break;
            case __REGRESAR_REPORTE_USUARIO:
                String dt=this.aceptarFecha(reporteu.reportedate, 1);
                if(reporteu.__ReporteNombreUsuario.getText().isEmpty() && dt==null ){
                    this.reporteu.__ReporteNombreUsuario.setText("");
                    this.reporteu.reportedate.setDate(null);
                    limpiarTabla(reporteu.__tablaReporteUsuario);
                    newP.setEnabled(true);
                    movimientos.setEnabled(true);
//                    reportes.setEnabled(true);
//                    consultas.setEnabled(true);
                    reporteu.setVisible(false);
                }else{
                confir = mensajeConfirmacion("¿Realmente Deseas Salir Y Borrar?","Salida");
                                if (confir == JOptionPane.OK_OPTION){ 
                                    this.reporteu.__ReporteNombreUsuario.setText("");
                                    this.reporteu.reportedate.setDate(null);
                                    limpiarTabla(reporteu.__tablaReporteUsuario);
                                    newP.setEnabled(true);
                                    movimientos.setEnabled(true);
//                                    reportes.setEnabled(true);
//                                    consultas.setEnabled(true);
                                    reporteu.setVisible(false);
                                }
                }
                break;
            case __NUEVABUSQUEDA_REPORTE_USUARIO:
                confir = mensajeConfirmacion("¿Realmente Deseas Realizar una Nueva Busqueda?","Salida");
                                if (confir == JOptionPane.OK_OPTION){ 
                                    this.reporteu.__ReporteNombreUsuario.setText("");
                                    this.reporteu.reportedate.setDate(null);
                                    limpiarTabla(reporteu.__tablaReporteUsuario);
                                }
                break;
                //BOTONES DE LA CONSULTA INICIAL DE LA EXISTENCIA DEL PRODUCTO
            case __ACEPTARNP:                
                addItems("newP");
                menumaster.dispose();
                conEP.dispose();
                newP.setVisible(true);
                ponerfecha();                
                newP.setName("Alta de Producto");
                break;
            case __MODIFICARCONSULTAEP:
                break;
            case __ELIMINARCONSULTAEP:
                break;            
            case __REGRESARCONSULTAEP:
                regresar();
                break;
                //FIN DE LOS BOTONES DE LA CONSULTA INICIAL DE LA EXISTENCIA DEL PRODUCTO
                //ALTA DE PRODUCTOS
            case __GUARDAR_PRODUCTO:
                int areaid = this.busquedaid("area");
                int maquinaid = this.busquedaid("maquina");
                if(areaid==0){
                    mensaje(3,"No Dejes el Area Vacia");
                    this.newP.__cmbArea.requestFocus();
                    return;
                }
                if(maquinaid==0){
                    mensaje(3,"No Dejes la Maquina Vacia");
                    this.newP.__cmbMaquina.requestFocus();
                    return;
                }
                String DesP = this.newP.__descripcion.getText();
                if(DesP.equals("")){
                    mensaje(3,"Debes Colocar una Descripcion al Producto");
                    this.newP.__descripcion.requestFocus();
                    return;
                }
                if(!this.newP.__SMin_.getText().isEmpty()){
                    Min = Integer.parseInt(this.newP.__SMin_.getText());
                }else{
                    mensaje(3,"Debes Poner Un Minimo Mayor a 0");
                    this.newP.__SMin_.requestFocus();
                    return;
                }
                if(!this.newP.__SMax_.getText().isEmpty()){
                    Max = Integer.parseInt(this.newP.__SMax_.getText());
                }else{
                     mensaje(3,"Debes Poner Un Maximo Mayor a 0");
                     this.newP.__SMax_.requestFocus();
                     return;
                }
                if(Min>Max){
                    mensaje(3,"El Maximo No Puede ser Menor que el Minimo");
                     this.newP.__SMax_.requestFocus();
                }
                break;
            case __BORRAR_PRODUCTO:
                borrarFormularioAltaProducto();
                break;
            case __SALIR_PRODUCTO:
                regresar();
                break;
        }
    }
    public void iniciasesion() {
        user=this.login.__Usuario.getText();
        contra=this.login.__Pswd.getText();
        contra = this.encriptaContraseña(contra);
        if(user.isEmpty()){
            mensaje(2,"Escribe el Nombre de Usuario");
            this.login.__Usuario.requestFocus();
        }else 
            if(contra.isEmpty()){
                mensaje(2,"Escribe la Contraseña");
                this.login.__Pswd.requestFocus();
            }else{
                try {
                      ResultSet buscarUser=this.mimodelo.buscarUser1(user);
                      if(!buscarUser.next()){
                          mensaje(3,"Error,  Usuario Erroneo");
                          login.__Pswd.setText("");
                          login.__Usuario.setText("");
                          login.__Usuario.requestFocus();
                          return;
                      }
                      buscarUser.beforeFirst();
                      while(buscarUser.next()){
                           pswd = buscarUser.getString(1);
                           id_responsable = buscarUser.getInt(2);
                           cargo = buscarUser.getInt(3);     
                           se=buscarUser.getInt(7); 
                           act=buscarUser.getInt(6); 
                      }
                       if(contra.equals(pswd)){
                            if(act==1){
                                if(se==0){
                                    login.__Usuario.setText("");
                                    login.__Pswd.setText("");
                                    if(!user.equals("ROOT")){
                                         mimodelo.abrirsesion(user);
                                    }
                                    fecha.__etqUser.setText(user);
                                    Calendar Cal= Calendar.getInstance();
                                    String anio=Cal.get(Cal.YEAR)<10 ? "0"+Cal.get(Cal.YEAR) : ""+Cal.get(Cal.YEAR);
                                    String mess=(Cal.get(Cal.MONTH)+1)<10 ? "0"+(Cal.get(Cal.MONTH)+1) : ""+(Cal.get(Cal.MONTH)+1);
                                    String day=Cal.get(Cal.DATE)<10 ? "0"+Cal.get(Cal.DATE) : ""+Cal.get(Cal.DATE);
                                    fech=anio+"-"+mess+"-"+day;
                                    String hora=Cal.get(Cal.HOUR_OF_DAY)<10 ? "0"+Cal.get(Cal.HOUR_OF_DAY) : ""+Cal.get(Cal.HOUR_OF_DAY);
                                    String minute=Cal.get(Cal.MINUTE)<10 ? "0"+Cal.get(Cal.MINUTE) : ""+Cal.get(Cal.MINUTE);
                                    horaentrada = hora+":"+minute;
                                    boolean registraentrada = mimodelo.registraracceso(user,fech,horaentrada);
                                    login.setEnabled(false);
                                    fecha.setLocationRelativeTo(null);
                                    fecha.setVisible(true);
                                }else{
                                    mensaje(3,"Error, La sesion esta activa");
                                    login.__Pswd.setText("");
                                    login.__Usuario.setText("");
                                    login.__Usuario.requestFocus();
                                }
                            }else{
                                mensaje(3,"Error, el usuario esta Bloqueado");
                                login.__Pswd.setText("");
                                login.__Usuario.setText("");
                                login.__Usuario.requestFocus();
                            }
                       }else{
                           mensaje(3,"Error,  Contraseña Erroneaa");
                           login.__Pswd.selectAll();
                           login.__Pswd.requestFocus();
                       }
                }catch (SQLException ex) {
                       mensaje(3,ex.getMessage());
                }
            }
    }
    public String encriptaContraseña(String contraseña) {
        char[] CONSTS_HEX = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
        try{
           MessageDigest msgd = MessageDigest.getInstance("MD5");
           byte[] bytes = msgd.digest(contraseña.getBytes());
           StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
           for (int i = 0; i < bytes.length; i++){
               int bajo = (int)(bytes[i] & 0x0f);
               int alto = (int)((bytes[i] & 0xf0) >> 4);
               strbCadenaMD5.append(CONSTS_HEX[alto]);
               strbCadenaMD5.append(CONSTS_HEX[bajo]);
           }
           return strbCadenaMD5.toString();
        }catch (NoSuchAlgorithmException e) {
           return null;
        }
    }
    public void mensaje(int tipo,String mensaje){
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        label.setText(mensaje);
        label.setVisible(true);
        panel.add(label);
        switch (tipo){
            case 1:
                panel.setBackground(Color.green);
                JOptionPane.showMessageDialog(null,panel,"OK", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                panel.setBackground(Color.yellow);
                JOptionPane.showMessageDialog(null,panel,"INFORMA", JOptionPane.QUESTION_MESSAGE);
                break;
            case 3:
                panel.setBackground(Color.RED);
                JOptionPane.showMessageDialog(null,panel,"ERROR", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
    private void KeyTipedLetras(KeyEvent evt) {
        mayusculas();
        char caracter = evt.getKeyChar();
        if(((caracter < 'A') || (caracter > 'Z')) && caracter != 'Ñ' && caracter !=' '){
            evt.consume();
        }
    }
    private void KeyTipedLetrasNum(KeyEvent evt) {
        mayusculas();
        char caracter = evt.getKeyChar();
        if(((caracter < 'A') || (caracter > 'Z'))  && ((caracter < '0') || (caracter > '9')) && caracter !=' ' && caracter != 'Ñ' ){
            evt.consume();
        }
    }
    private void KeyTipedLetrasNumCar(KeyEvent evt) {
        mayusculas();
        char caracter = evt.getKeyChar();
        if(((caracter < 'A') || (caracter > 'Z'))  && ((caracter < '0') || (caracter > '9')) && caracter != '-' && caracter != ',' && caracter != '/'  && caracter != ' ' && caracter != '.' && caracter != 'Ñ' ){
            evt.consume();
        }
    }
    public void mayusculas(){
        Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
    }
    public void fechaini() {
        if (aceptarfecha()==true){
            if(pedirfecha==0){
                    switch(cargo){
                        case 1:
                            menumaster.__etqUsuarioMenuMaster.setText(fecha.__etqUser.getText());
                            menumaster.__ALTAPRODUCTO.setEnabled(true);                            
                            menumaster.setLocationRelativeTo(null);
                            menumaster.show();
                            break;
                        case 2:
                            menumaster.__etqUsuarioMenuMaster.setText(fecha.__etqUser.getText());
                            menumaster.__ALTAPRODUCTO.setEnabled(false);
                            menumaster.setLocationRelativeTo(null);
                            menumaster.show();
                            break;
                        case 3:
                            /*this.addItems("consultas");
                            consultas.setLocationRelativeTo(null);
                            consultas.show();*/
                            mensaje(3,"formulario consultas");
                            break;
                    }
                }
                ponerfecha();
                fecha.date.setDate(null);
                login.dispose();
                login.setEnabled(true);
                newP.setEnabled(true);                   
                /*movimientos.setEnabled(true);                
                reportes.setEnabled(true);                    
                consultas.setEnabled(true);*/
                fecha.dispose();
            }
    }
    public boolean aceptarfecha() {
        fec = this.aceptarFecha(fecha.date,0);
                if(fec==null){
                    //mensaje="Ingresa la fecha a capturar";
                    fecha.date.getDateEditor().getUiComponent().requestFocus();
                    return false;
                }
                confir = this.mensajeConfirmacion("La fecha es: "+fec+"\n¿Esta correcta?", "FECHA");
                if (confir==JOptionPane.OK_OPTION){
                    if(this.compara(fec)==true){
                        return true;
                    }else{
                        mensaje(3,"No Puedes Capturar la Fecha: "+fec);
                        return false;
                    }
                }
        return false;
    }
    public boolean compara(String fecha){
        fecha = fecha +" 00:00:00";
        Calendar Cal= Calendar.getInstance();
        String anio=Cal.get(Cal.YEAR)+"";
        String mes=(Cal.get(Cal.MONTH)+1)<10?"0"+(Cal.get(Cal.MONTH)+1):(Cal.get(Cal.MONTH)+1)+"";
        String dia=Cal.get(Cal.DATE)<10?"0"+Cal.get(Cal.DATE):(Cal.get(Cal.DATE))+"";
        String fechaactual=anio+"-"+mes+"-"+dia+ " 00:00:00";
        int max = Integer.parseInt(calcularHoras(fecha, fechaactual,2));
        if(max<16&&max>-1){
               return true;                                     
        }
        return false;
    }
    public String calcularHoras(String fechaInicial, String fechaFinal, int parametro ) {
        java.util.GregorianCalendar jCal = new java.util.GregorianCalendar();
        java.util.GregorianCalendar jCal2 = new java.util.GregorianCalendar();
                //2014-07-20 00:00:00:00
            //jCal.set(year, month, date, hourOfDay, minute)
        jCal.set(Integer.parseInt(fechaInicial.substring(0,4)), 
                Integer.parseInt(fechaInicial.substring(5,7))-1, 
                Integer.parseInt(fechaInicial.substring(8,10)), 
                Integer.parseInt(fechaInicial.substring(11,13)),
                Integer.parseInt(fechaInicial.substring(14,16)), 
                Integer.parseInt(fechaInicial.substring(17,19)));
        jCal2.set(Integer.parseInt(fechaFinal.substring(0,4)), 
                Integer.parseInt(fechaFinal.substring(5,7))-1, 
                Integer.parseInt(fechaFinal.substring(8,10)), 
                Integer.parseInt(fechaFinal.substring(11,13)),
                Integer.parseInt(fechaFinal.substring(14,16)), 
                Integer.parseInt(fechaFinal.substring(17,19)));     
        long diferencia = jCal2.getTime().getTime()-jCal.getTime().getTime();
        
        double minutos = diferencia / (1000 * 60);
        long horas = (long) (minutos / 60);
        long minuto = (long) (minutos%60);
        long dias = horas/24;
        
        //Calcular meses...
        //Crear vector para almacenar los diferentes dias maximos segun correponda
        String[] mesesAnio = new String[12];
        mesesAnio[0] = "31";
        //validacion de los años bisiestos
        if (jCal.isLeapYear(jCal.YEAR)){mesesAnio[1] = "29";}else{mesesAnio[1] = "28";}
        mesesAnio[2] = "31";
        mesesAnio[3] = "30";
        mesesAnio[4] = "31";
        mesesAnio[5] = "30";
        mesesAnio[6] = "31";
        mesesAnio[7] = "31";
        mesesAnio[8] = "30";
        mesesAnio[9] = "31";
        mesesAnio[10] = "30";
        mesesAnio[11] = "31";
        int diasRestantes = (int) dias;
        //variable almacenará el total de meses que hay en esos dias
        int totalMeses = 0;
        int mesActual = jCal.MONTH;
        //Restar los dias de cada mes desde la fecha de ingreso hasta que ya no queden sufcientes dias para 
        // completar un mes.
        for (int i=0; i<=11; i++ ){
            //Validar año, si sumando 1 al mes actual supera el fin de año, 
            // setea la variable a principio de año 
            if ((mesActual+1)>=12){
                mesActual = i;
            }
            //Validar que el numero de dias resultantes de la resta de las 2 fechas, menos los dias
            //del mes correspondiente sea mayor a cero, de ser asi totalMeses aumenta,continuar hasta 
            //que ya nos se cumpla.
            if ((diasRestantes -Integer.parseInt(mesesAnio[mesActual]))>=0){
                totalMeses ++;
                diasRestantes = diasRestantes- Integer.parseInt(mesesAnio[mesActual]);
                mesActual ++;
            }else{
                break;
            }
        }
        //Resto de horas despues de sacar los dias
        horas = horas % 24;
        if(horas<0){
            mensaje(3,"La Hora Final es Incorrecta");
            return null;
        }
        String salida ="";
        if (totalMeses > 0){
            if (totalMeses > 1)
                salida = salida+  String.valueOf(totalMeses)+" Meses,  ";
            else
                salida = salida+  String.valueOf(totalMeses)+" Mes, ";
        }
        if (diasRestantes > 0){
            if (diasRestantes > 1)
                salida = salida+  String.valueOf(diasRestantes)+" Dias, ";
            else
                salida = salida+  String.valueOf(diasRestantes)+" Dia, ";
        }               
         salida = salida + String.valueOf(horas)+":"+String.valueOf(minuto);
         if(parametro==1){
             return salida;
         }
         if(parametro ==2){
             return dias+"";
         }
        return null;
         
    }
    public String aceptarFecha(JDateChooser fech,int a){
        //metodo para extraer la fecha del jdatechooser
        try{
            Date fechaa = fech.getCalendar().getTime();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
            return formatoDeFecha.format(fechaa); 
        }catch(Exception ex){
             if(ex.getMessage()==null){
                 if(a ==1){
                     
                 }else{
                     fech.requestFocus();
                     this.mensaje(2,"Ingresa la Fecha a Capturar");
                 }
             }
        }
        return null;
    }
    private void ponerfecha() {
        //metodo para poner la fecha en todos los formularios
        menumaster.__etqFechaMenuMaster.setText(fec);
        newP.__etqFech.setText(fec);
        
    }
    private int mensajeConfirmacion(String mensaje, String titulo) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        label.setText(mensaje);
        label.setVisible(true);
        panel.add(label);
        panel.setBackground(Color.yellow);
        return JOptionPane.showConfirmDialog(null,panel,titulo,JOptionPane.OK_CANCEL_OPTION);
    }
    public void SalirSistema(){
        try {
            //meter un update 
            if(cargo==1){
                mimodelo.bp(fech);
                this.enviaarchivo("C:\\iexsa\\backups\\dump"+fech+".sql","rawmats2014@gmail.com" ,"Backup de la base de datos");
                File fichero = new File("C:\\iexsa\\backups\\dump"+fech+".sql");
                fichero.delete();
            }
             Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, false);
              Calendar Cal= Calendar.getInstance();                                                  
                               String hora=Cal.get(Cal.HOUR_OF_DAY)<10 ? "0"+Cal.get(Cal.HOUR_OF_DAY) : ""+Cal.get(Cal.HOUR_OF_DAY);
                               String minute=Cal.get(Cal.MINUTE)<10 ? "0"+Cal.get(Cal.MINUTE) : ""+Cal.get(Cal.MINUTE);
                               horasalida = hora+":"+minute;                
            boolean registrasalida=mimodelo.horasalida(horasalida,user);
            if(!user.equals("ROOT")){
                mimodelo.cerrarsesion(user);
            }
            System.exit(0);
        } catch (SQLException ex) {
            mensaje(3,ex.getMessage());
        }
    }
    public boolean enviaarchivo(String urlarchivo, String destinatario,String msg){
        try{
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "dispaper.iexsa@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, null);
            // session.setDebug(true);

            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setText(msg);

            
               
                   //Se compone el adjunto con la imagen
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(urlarchivo)));
           adjunto.setFileName(urlarchivo);       
            
            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);

            // Se compone el correo, dando to, from, subject y el
            // contenido.
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("prueba@prueba.com"));
            message.addRecipient(
            Message.RecipientType.TO,
            new InternetAddress(destinatario));
            message.setSubject("Correo de Dis-Paper "+ fec);
            message.setContent(multiParte);

            // Se envia el correo.
            Transport t = session.getTransport("smtp");
            String micorreo="rawmats2014@gmail.com";
            String mipswd="2014rawmats";
            t.connect(micorreo, mipswd);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            return true;
        }
        catch (Exception e){
            mensaje(3,e.getMessage());
            return false;
        }
        
    }    
    public void nuevaArea(JComboBox combo){
        String Nombre = (String)JOptionPane.showInputDialog(null,"Escribe la nueva Area: ","NUEVO NOMBRE",JOptionPane.PLAIN_MESSAGE);
                if ((Nombre != null) && (Nombre.length() > 0)) {
                    try {
                        Nombre=Nombre.toUpperCase();
                        int conf=JOptionPane.showConfirmDialog(null,"Se agregara el Area:, " +Nombre + ".",Nombre,JOptionPane.OK_CANCEL_OPTION);
                        if (conf==JOptionPane.OK_OPTION){
                            boolean altaNombre=mimodelo.newArea(Nombre);
                            if(altaNombre==true){
                                JOptionPane.showMessageDialog(null,"Area "+Nombre+" Agregada Correctamente.","Correcto",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        ResultSet buscarNombrePapel = mimodelo.buscarArea();
                            combo.removeAllItems();
                            combo.addItem("Selecciona...");
                            while(buscarNombrePapel.next()){
                                    combo.addItem(buscarNombrePapel.getString(1));
                            }
                        return;
                    } catch (SQLException ex) {
                        mensaje(3,ex.getMessage());
                    }
                }
                mensaje(3,"No agregaste Area.");
            }
    public void nuevaMaquina(JComboBox combo){
        int idarea = this.busquedaid("area");
        if(idarea!=0){
            String Nombre = (String)JOptionPane.showInputDialog(null,"Escribe la nueva Maquina: ","NUEVO NOMBRE",JOptionPane.PLAIN_MESSAGE);
                if ((Nombre != null) && (Nombre.length() > 0)) {
                    try {
                        Nombre=Nombre.toUpperCase();
                        int conf=JOptionPane.showConfirmDialog(null,"Se agregara la Maquina:, " +Nombre + ".",Nombre,JOptionPane.OK_CANCEL_OPTION);
                        if (conf==JOptionPane.OK_OPTION){
                            boolean altaNombre=mimodelo.newMaquina(Nombre,idarea);
                            if(altaNombre==true){
                                JOptionPane.showMessageDialog(null,"Maquina "+Nombre+" Agregada Correctamente.","Correcto",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        ResultSet buscarNombrePapel = mimodelo.buscarMaquina(idarea);
                            combo.removeAllItems();
                            combo.addItem("Selecciona...");
                            while(buscarNombrePapel.next()){
                                    combo.addItem(buscarNombrePapel.getString(1));
                            }
                        return;
                    } catch (SQLException ex) {
                        mensaje(3,ex.getMessage());
                    }
                }
                mensaje(3,"No agregaste Maquina.");
        }else{
            mensaje(3,"Selecciona el Area.");
        }
    }
    public void addItems(String formulario){
        try {
            ResultSet buscarArea = mimodelo.buscarArea();
            if(formulario.equals("newP")){
                newP.__cmbArea.removeAllItems();
                newP.__cmbArea.addItem("Selecciona...");
                while(buscarArea.next()){
                    newP.__cmbArea.addItem(buscarArea.getString(1));
                }
            }
        }catch(Exception e){
        }
            
            /*
            if(formulario.equals("consultas")){
                consultas.__cmbColor.removeAllItems();
                consultas.__cmbColor.addItem("");
                consultas.__cmbMarca.removeAllItems();
                consultas.__cmbMarca.addItem("");
                consultas.__cmbPropi.removeAllItems();
                consultas.__cmbPropi.addItem("");
                consultas.__cmbProveedor.removeAllItems();
                consultas.__cmbProveedor.addItem("");          
                consultas.__cmbNombrePapel.removeAllItems();
                consultas.__cmbNombrePapel.addItem("");
                consultas.__cmbTipoEntrada.removeAllItems();
                consultas.__cmbTipoEntrada.addItem("");
                consultas.__cmbTipoSalida.removeAllItems();
                consultas.__cmbTipoSalida.addItem("");
                while(buscarPropiedad.next()){
                    consultas.__cmbPropi.addItem(buscarPropiedad.getString(2));
                }
                while(buscarTipoEntrada.next()){
                    consultas.__cmbTipoEntrada.addItem(buscarTipoEntrada.getString(2));
                }   
                while(buscarTipoSalida.next()){
                    consultas.__cmbTipoSalida.addItem(buscarTipoSalida.getString(2));
                }
                while(buscarProveedores.next()){
                    consultas.__cmbProveedor.addItem(buscarProveedores.getString(2));
                }
                while(buscarMarca.next()){
                    consultas.__cmbMarca.addItem(buscarMarca.getString(2));
                }
                while(buscarColor.next()){
                    consultas.__cmbColor.addItem(buscarColor.getString(1));
                }
                while(buscarNombrePapel.next()){
                    consultas.__cmbNombrePapel.addItem(buscarNombrePapel.getString(1));
                }
            }
            */
        //} catch (SQLException ex) {
         //           mensaje(3,ex.getMessage());
       // }//
    }
    public int busquedaid(String nombre){
        String texto="";
        if(nombre.isEmpty()){
            return 0;
        }else{
            ResultSet id = null;
            switch (nombre){
                case "area":
                    texto = newP.__cmbArea.getSelectedItem().toString();
                    id=mimodelo.buscaridArea(texto);
                    break;
                case "maquina":
                    texto = newP.__cmbMaquina.getSelectedItem().toString();
                    id=mimodelo.buscaridMaquina(texto);
                    break;
            }
            try{
                while(id.next()){
                    return id.getInt("id");
                }
            }catch(Exception a){
                return 0;
            }
        }
        return 0;
    }
    public void regresar(){
        switch(cargo){
                        case 1:
                            confir = mensajeConfirmacion("¿Realmente Deseas Regresar al \n Menú Principal?","Salida");
                                 if (confir == JOptionPane.OK_OPTION){
                                    menumaster.setVisible(true);
                                    menumaster.setLocationRelativeTo(null);
                                    //consultas.dispose();
                                    movimientos.dispose();
                                    this.newP.dispose();
                                    conEP.dispose();
//                                    reportes.dispose();                                    
                                    borrarFormularioNewUser();
                                    borrarFormularioConEP();
                                    borrarFormularioAltaProducto();
//                                    borrarFormularioMovimientosPapel();
//                                    borrarFormularioProveedor();
//                                    borrarFormularioConsultas();
//                                    borrarFormularioEmergente();
                                }
                            break;
                        case 2:
                            confir = mensajeConfirmacion("¿Realmente Deseas Regresar al \n Menú Principal?","Salida");
                                if (confir == JOptionPane.OK_OPTION){
                                menumaster.setVisible(true);
                                menumaster.setLocationRelativeTo(null);
//                                consultas.dispose();
                                movimientos.dispose();
                                this.newP.dispose();
                                conEP.dispose();
//                                reportes.dispose();
                                borrarFormularioNewUser();
                                borrarFormularioAltaProducto();
//                                borrarFormularioMovimientosPapel();
//                                borrarFormularioProveedor();
//                                borrarFormularioConsultas();
//                                borrarFormularioEmergente();
                                }
                            break;
                        case 3:                                
                                mensaje(2,"No Hay Acceso a esta Información");                                                                                                  
                            break;                    
                    }
    }
    public File archivo(int file){
        File archivo;
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filtro = new FileNameExtensionFilter("Archivos Excel (.xls)", "xls");
        fileChooser.setFileFilter(filtro);
        fileChooser.setAcceptAllFileFilterUsed(false);
        File folder = new File("C:\\IEXSA\\");
        if (!folder.exists()) { 
            folder.mkdir();
        }
        fileChooser.setCurrentDirectory(folder);
        //int resultado = fileChooser.showSaveDialog(null);
        //String anio=Cal.get(Cal.YEAR)<10 ? "0"+Cal.get(Cal.YEAR) : ""+Cal.get(Cal.YEAR);
        int resultado = file ==1 ? fileChooser.showSaveDialog(null) : fileChooser.showOpenDialog(null);
        if(resultado==JFileChooser.CANCEL_OPTION){
            return null;
        }
        return archivo = fileChooser.getSelectedFile();
    }
    public void altamodificacion(){
        String nombreus=this.newU.__nombreUser.getText();
                if(nombreus.isEmpty()){
                    mensaje(3,"Debe Especificar el Nombre del Personal");
                    this.newU.__nombreUser.requestFocus();
                    return;
                }
                String usuario=this.newU.__User.getText();
                if(usuario.isEmpty()){
                    mensaje(3,"Debe Colocar un Nombre de Usuario");
                    this.newU.__User.requestFocus();
                    return;                  
                }
                String pwsd1=this.newU.__NewPswd.getText();
                if(pwsd1.isEmpty()){                    
                    mensaje(3,"Debe Colocar una Contraseña para Continuar");
                    this.newU.__NewPswd.requestFocus();
                    return;
                }
                String pwsd2=this.newU.__NewPswd2.getText();
                if(pwsd2.isEmpty()){
                    mensaje(3,"Debe Repetir la Contraseña");
                    this.newU.__NewPswd2.requestFocus();
                    return;
                }
                String contraseña="";
                if(pwsd1.equals(pwsd2)){
                    contraseña=pwsd1;
                }else{
                    mensaje(3,"Las Contraseñas no Coinciden");
                    this.newU.__NewPswd.requestFocus();
                    return;
                }
                int bloqueo;
                if(this.newU.__optActivo.isSelected()){
                    bloqueo=1;
                }else if(this.newU.__optInactivo.isSelected()){
                    bloqueo=0;
                }else{
                    mensaje(3,"Debe Colocar el Usuario en Activo O Inactivo");
                    return;
                }
                int nivel=0;
                if(this.newU.__optMaster.isSelected()){
                    nivel = 1;
                }else if(this.newU.__optJunior.isSelected()){
                   nivel = 2; 
                }else if(this.newU.__optKid.isSelected()){
                    nivel = 3;
                }else{
                    mensaje(3,"Debe Seleccionar un Nivel Para el Usuario");
                    return;
                }                
                switch(tipoalta){//diferenciacion de que es una alta nueva o es una modificacion caso 0 nueva alta caso 1 modificacion de usuario 
                    case 0:
                        confir= mensajeConfirmacion("Realmente Desea dar de Alta el Usuario "+usuario,"Alta");
                            if(confir==JOptionPane.OK_OPTION){
                                boolean altausurio=mimodelo.newaltausuario(nombreus,usuario,contraseña,nivel,bloqueo);                
                                    if(altausurio==true){
                                        mensaje(1,"Alta Correcta de Nuevo Usuario "+usuario+"");                                        
                                    }else{
                                        mensaje(3,"Ocurrio un Error al Dar de Alta el Nuevo Usuario");                          
                                    } 
                        confir= mensajeConfirmacion("¿Desea Dar una Nueva Alta?","Alta");
                                if(confir==JOptionPane.OK_OPTION){
                                        this.borrarFormularioNewUser();
                                       newU.setVisible(true);
                                }else{
                                    borrarFormularioNewUser();                                    
                                    movimientos.setEnabled(true);
                                    newP.setEnabled(true);                   
                                    //reportes.setEnabled(true);                    
                                    //consultas.setEnabled(true);
                                    newU.setVisible(false);
                                } 
                                break;
                            }   
                        break;
                    case 1:
                        confir= mensajeConfirmacion("Realmente Desea Modificar el Usuario "+usuario,"Modificación");
                            if(confir==JOptionPane.OK_OPTION){
                                boolean modificarusuario=mimodelo.modificaruser(nombreus,usuario,contraseña,nivel,bloqueo,modificaruser);
                                    if(modificarusuario==true){
                                        mensaje(1,"Modificacion del Usuario "+nombreus+" Realizada con Exito");
                                        tipoalta=0;
                                    }else{
                                        mensaje(3,"Ocurrio un Error al Modificar el Usuario");
                                    }
                                    borrarFormularioNewUser();                                    
                                    movimientos.setEnabled(true);
                                    newP.setEnabled(true);                   
                                    //reportes.setEnabled(true);                    
                                    //consultas.setEnabled(true);
                                    newU.setVisible(true);
                                }
                        break;
                    
                }
    }
    public void cancelarnewU(){
        if(newU.__nombreUser.getText().isEmpty() && newU.__User.getText().isEmpty() && newU.__NewPswd.getText().isEmpty() && newU.__NewPswd2.getText().isEmpty() && newU.__grpNivelUser.getSelection() == null && newU.__grpActivoInactivo.getSelection()== null){
                    borrarFormularioNewUser();
                    movimientos.setEnabled(true);
                    newP.setEnabled(true);                   
                    //reportes.setEnabled(true);                    
                    //consultas.setEnabled(true);
                     newU.setVisible(false); 
                     return;
        }
        switch(tipoalta){
            case 0:
               confir = mensajeConfirmacion("¿Desea Cancelar la Nueva Alta?","Salida");
                    if (confir==JOptionPane.OK_OPTION){
                        borrarFormularioNewUser();
                        newP.setEnabled(true);
                        movimientos.setEnabled(true);
                        //reportes.setEnabled(true);                    
                        //consultas.setEnabled(true);
                        newU.setVisible(false);
                    }
                break;
            case 1:
                 confir = mensajeConfirmacion("¿Desea Cancelar la Modificación?","Salida");
                    if (confir==JOptionPane.OK_OPTION){
                        borrarFormularioNewUser();
                        newP.setEnabled(true);
                        movimientos.setEnabled(true);
                        //reportes.setEnabled(true);                    
                        //consultas.setEnabled(true);
                        newU.setVisible(false);
                    }
                break;
        }
        
    }
    public void borrarFormularioAltaProducto() {        
        this.newP.__cmbArea.removeAllItems();
        this.newP.__cmbArea.addItem("Selecciona...");
        this.newP.__cmbMaquina.removeAllItems();
        this.newP.__cmbMaquina.addItem("Selecciona...");        
        this.newP.__descripcion.setText("");
        this.newP.__SMin_.setText("");
        this.newP.__SMax_.setText("");        
        this.addItems("newP");
    }
    public void borrarFormularioConEP(){
        conEP.__descripcionP.setText("");
        limpiarTabla(conEP.__tablaProductos);
    }
    public void borrarFormularioNewUser(){
            this.newU.__NewPswd.setText("");
            this.newU.__NewPswd2.setText("");
            this.newU.__User.setText("");
            this.newU.__nombreUser.setText("");
            this.newU.__grpActivoInactivo.clearSelection();
            this.newU.__grpNivelUser.clearSelection();
            this.newU.__nombreUser.requestFocus();
            tipoalta=0;
        }
    public void cambiopswd(){
        String pswdactual=encriptaContraseña(this.pass.__Pswd.getText());
                if(pswdactual.isEmpty()){
                    mensaje(3,"No dejes campos vacios");
                    this.pass.__Pswd.requestFocus();
                    return;
                }
                String newpswd=this.pass.__NewPswd.getText();
                if(newpswd.isEmpty()){
                    mensaje(3,"No dejes campos vacios");
                    this.pass.__NewPswd.requestFocus();
                    return;
                }
                String newpswd1=this.pass.__NewPswd1.getText();
                if(newpswd1.isEmpty()){
                    mensaje(3,"No dejes campos vacios");
                    this.pass.__NewPswd1.requestFocus();
                    return;
                }
                if(pswdactual.equals(contra)){
                    if(newpswd.equals(newpswd1)){
                        confir=this.mensajeConfirmacion("Estas Seguro de Cambiar tu Contraseña","CONTRASEÑA");
                        if (confir==JOptionPane.OK_OPTION){
                            String newpswd2=newpswd1;
                            boolean cambiocontrasena=mimodelo.cambiocontrasena(encriptaContraseña(newpswd1),newpswd2,id_responsable);
                            if(cambiocontrasena==true){
                                mensaje(1,"Cambio de contraseña correcto");
                                pass.setVisible(false);
                                contra=encriptaContraseña(newpswd1);
                                pass.__Pswd.setText("");
                                pass.__NewPswd1.setText("");
                                pass.__NewPswd.setText("");                                
                                newP.setEnabled(true);
                                movimientos.setEnabled(true);
                                //reportes.setEnabled(true);
                                //consultas.setEnabled(true);
                                return;
                            }
                        }
                    }else{
                        mensaje(3,"Las contraseñas no coinciden");
                        pass.__NewPswd.requestFocus();
                        return;
                    }                    
                }else{
                    mensaje(3,"La Contraseña Actual No Es La Correcta");
                    pass.__Pswd.selectAll();
                    pass.__Pswd.requestFocus();
                }
    }
    public void buscarreporteusuario(int accion){
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            this.reporteu.__tablaReporteUsuario.setModel(modelo);
            ResultSet buscarReporteUser =null;
            if(accion ==1){
                buscarReporteUser = mimodelo. buscartodoreporte();
            }
            if(accion==2){
                String nombre = this.reporteu.__ReporteNombreUsuario.getText();
                String datee = this.aceptarFecha(this.reporteu.reportedate,1);

                if(datee==null){
                    buscarReporteUser = mimodelo.buscarReporteUser(nombre);
                }
                if(nombre.isEmpty()){
                    buscarReporteUser = mimodelo.buscarReporteDate(datee);
                }
                if(datee!=null &&  !nombre.isEmpty()){
                    buscarReporteUser = mimodelo.buscarReporteUserDate(nombre,datee);
                }
                
            }
            ResultSetMetaData md = buscarReporteUser.getMetaData();
            String datos []={"Nombre Empleado","Fecha & Hora Entrada","Fecha & Hora Salida"};
            int col = md.getColumnCount();
           for(int i=0;i<col;i++){
                modelo.addColumn(datos[i]);
            } 
            
            while(buscarReporteUser.next()){
                Object [] fila = new Object[col];
                for(int i=0;i<col;i++){
                    fila[i]=buscarReporteUser.getObject(i+1);
                }
                modelo.addRow(fila);
            }
            this.reporteu.__tablaReporteUsuario.setEnabled(false);
        } catch (SQLException ex) {
            mensaje(3,ex.getMessage());
        }
    }
    public void limpiarTabla(JTable tabla){
        try {
            String datos []={};
            DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
            filas=tabla.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
            for (int i = 0;i<10; i++) {
                modelo.addRow(datos);
            }
        } catch (Exception e) {}        
    }    
    public void bucarPorducto() {
        limpiarTabla(conEP.__tablaProductos);
        ResultSet resp = null;
        String des = this.conEP.__descripcionP.getText();
        resp = mimodelo.buscardor(des);
         try {
             if(resp.next()){
                resp.beforeFirst();
                ResultSetMetaData metaData =resp.getMetaData();
                int numcolumnas = metaData.getColumnCount();
                String datosT[] =  new String[numcolumnas];
                String datosC[] =  new String[numcolumnas];
                for(int i=0;i<numcolumnas;i++){
                    datosT[i]=metaData.getColumnLabel(i+1).toUpperCase();
                }
                DefaultTableModel modelo = new DefaultTableModel();
                conEP.__tablaProductos.setModel(modelo);
                for(int i=0;i<numcolumnas;i++){
                    modelo.addColumn(datosT[i]);
                }
                while(resp.next()){
                    for(int i=0;i<numcolumnas;i++){
                        datosC[i]=resp.getString(i+1);
                    }
                    modelo.addRow(datosC);
                }
            }else{
               limpiarTabla(conEP.__tablaProductos);
            }
        } catch (SQLException ex) {
            mensaje(3,ex.getMessage());

        }
    }
}

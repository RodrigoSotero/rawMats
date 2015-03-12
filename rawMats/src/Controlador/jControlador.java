/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.modelo;
import Vista.CSesion;
import Vista.CambioP;
import Vista.Consulta;
import Vista.Consulta1;
import Vista.Consultas;
import Vista.Correo;
import Vista.Fecha;
import Vista.HiloProgreso;
import Vista.Login;
import Vista.MenuMaster;
import Vista.Movimientos;
import Vista.NuevoPC;
import Vista.NuevoUsu;
import Vista.RFinanzas;
import Vista.ReporteU;
import Vista.Reportes;
import Vista.Splash;
import Vista.newProducto;
import static com.lowagie.text.xml.simpleparser.EntitiesToSymbol.map;
import com.mxrck.autocompleter.TextAutoCompleter;
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
import java.io.FileOutputStream;
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
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Irving & Rodrigo  RAWMATS
 */
public class jControlador implements ActionListener {
    HiloProgreso hilo;
    private static  Vista.Splash splash = new Splash();  
    private final  Login login = new Login();
    modelo mimodelo = new modelo();
    private final  Fecha fecha = new Fecha(); 
    private final  MenuMaster menumaster = new MenuMaster();
    private final  newProducto newP = new newProducto();
    private final  NuevoUsu newU = new NuevoUsu();
    private final  CambioP pass = new CambioP();
    private final Movimientos movimientos = new Movimientos();
    private final Consulta1 conEP = new Consulta1();
    private final  ReporteU reporteu = new ReporteU();
    private final NuevoPC NewPC = new NuevoPC();
    private final Consultas consulta = new Consultas();
    private final Reportes reporte = new Reportes();
    private final Consulta verconsulta = new Consulta();
    private final  Vista.Correo correo = new Correo();
    private final Vista.RFinanzas Finanzas = new RFinanzas();
    private final Vista.CSesion csesion = new CSesion();
    int a=1,id_responsable,cargo,pedirfecha,confir,filas,columnas,se,act,clienteprovedor=0,EntradaMovimientos=0,SalidaMovimientos=0,SesionCerrada=0,saber=0;
    Double Min,Max;
    String fec,user="",contra,pswd,fech,horaentrada,horasalida,modificaruser,t1="",t2="",t3="",etiqueta,identradas_;
    private int tipoalta;
    String buscarfolio;
    TextAutoCompleter Com_propietarioE,Com_TipoE,Com_proveedorE,Com_clienteE,com_prodcuto,com_descripcion,com_prodcutoSalida,com_descripcionSalida,Com_TipoS,Com_AreaS,Solicitante,Com_ClaveRFin,Com_DescripcionRFin;
    TextAutoCompleter Com_DescrpcionCon,Com_proveedorCon,Com_propietarioCon,Com_ClienteCon,Com_DocumentoCon,Com_OrdenProduccionCon,Com_OrdenCompraCon,Com_UbicacionCon,Com_ClaveCon,Com_AreaCon,Com_MaquinaCon,Com_TipoEntradaCon,Com_TipoSalidaCon,foloini,foliofin,SolicitanteCon,ubicacioncom,ubicacionsal,ubicacionent;
    int modificarentrada=0;
    double restarcantidad,cantidadbd;
    String identradas[]=new String [1000];
    String idsalidas[]=new String [1000];
    String nombrecolumnas[];
    int modificaproducto;
    
    public jControlador( JFrame padre ){
        //this.frmprincipal = (frmPrincipal) padre;
        this.splash = (Splash) padre;
    }
    String traia;
    public void iniciar(){
        
        Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
        hilo = new HiloProgreso(this.splash.progreso);
        hilo.start();
        hilo = null;
        
        this.csesion.__GUARDAR.setActionCommand("__ACEPTAR_SESION");
        this.csesion.__GUARDAR.addActionListener(this);
        this.csesion.__GUARDAR.setMnemonic('S');
        this.csesion.__NewPswd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                boolean lockingKeyState = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                    if(lockingKeyState == true){
                        csesion.__etqBloqMayus.setVisible(true);
                        a=0;
                    }else{
                        csesion.__etqBloqMayus.setVisible(false);
                        a=1;
                    }
                    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                        sesionactiva();
                    } 
            }
        });
        this.csesion.__CANCELAR.setActionCommand("__CANCELAR_SESION");
        this.csesion.__CANCELAR.addActionListener(this);
        this.csesion.__CANCELAR.setMnemonic('C');
        
        
        
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
        this.newP.__MateriaPrima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                etiqueta="";
                traia="";
                newP.__etqClave.setText("");
                String clave= newP.__etqClave.getText();
                if(!clave.isEmpty()||!clave.equals("")){
                    clave=clave.replace("RF-", "MP-");
                    newP.__etqClave.setText(clave);
                    etiqueta=clave;
                }else{
                    newP.__etqClave.setText("MP-");
                    etiqueta="MP-";
                }
                newP.__cmbArea.setSelectedIndex(0);
                newP.__cmbArea.setEnabled(true);
                newP.__etqNewArea.setEnabled(true);
                newP.__cmbMaquina.setSelectedIndex(0);
                newP.__etqNewMaquina.setEnabled(true);
                newP.__cmbMaquina.setEnabled(true);
            }    
            public void mouseEntered(java.awt.event.MouseEvent evt){
               
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                
            }
        });
        this.newP.__Refacciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                etiqueta="";
                traia="";
                newP.__etqClave.setText("");
                String clave= newP.__etqClave.getText();
                if(!clave.isEmpty()||!clave.equals("")){
                    clave=clave.replace("MP-", "RF-");
                    newP.__etqClave.setText(clave);
                    etiqueta=clave;
                }else{
                    newP.__etqClave.setText("RF-");
                    etiqueta="RF-";
                }
                newP.__cmbArea.setSelectedIndex(0);
                newP.__cmbArea.setEnabled(true);
                newP.__etqNewArea.setEnabled(true);
                newP.__cmbMaquina.setSelectedIndex(0);
                newP.__etqNewMaquina.setEnabled(true);
                newP.__cmbMaquina.setEnabled(true);
                
            }    
            public void mouseEntered(java.awt.event.MouseEvent evt){
               
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                
            }
        });
        
        this.newP.__etqNewEliminarArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                String altanombre=newP.__cmbArea.getSelectedItem().toString();
                if(newP.__cmbArea.getSelectedIndex()==0){
                    mensaje(3,"Debe Seleccionar una Area");
                }else{
                    confir = mensajeConfirmacion("¿Deseas eliminar el area "+altanombre+"?","Confirma"); 
                    if(confir!=JOptionPane.OK_OPTION){
                        return;
                    }else{
                    int idarea = busquedaid("area");
                    mimodelo.bajaArea(idarea);
                    mensaje(1,"Nombre "+ altanombre +" eliminado correctamente");
                    newP.__cmbArea.removeItem(altanombre);
                    }
                }
                
            }    
            public void mouseEntered(java.awt.event.MouseEvent evt){
               
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                
            }
        });
        this.newP.__etqNewEliminarMaquina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                String altanombre=newP.__cmbMaquina.getSelectedItem().toString();
                if(newP.__cmbMaquina.getSelectedIndex()==0){
                    mensaje(3,"Debe Seleccionar una Maquina");
                }else{
                    confir = mensajeConfirmacion("¿Deseas eliminar la maquina "+altanombre+"?","Confirma"); 
                    if(confir!=JOptionPane.OK_OPTION){
                        return;
                    }else{
                    int idarea = busquedaid("maquina");
                    mimodelo.bajaMaquina(idarea);
                    mensaje(1,"Nombre "+ altanombre +" eliminado correctamente");
                    newP.__cmbMaquina.removeItem(altanombre);
                    }
                }
                
            }    
            public void mouseEntered(java.awt.event.MouseEvent evt){
               
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                
            }
        });
        this.newP.__GUARDAR.setActionCommand("__GUARDAR_PRODUCTO");
        this.newP.__GUARDAR.setMnemonic('G');
        this.newP.__GUARDAR.addActionListener(this);
        this.newP.__BORRAR.setActionCommand("__BORRAR_PRODUCTO");
        this.newP.__BORRAR.setMnemonic('B');
        this.newP.__BORRAR.addActionListener(this);
        this.newP.__SALIR.setActionCommand("__SALIR_PRODUCTO");
        this.newP.__SALIR.setMnemonic('R');
        this.newP.__SALIR.addActionListener(this);
        this.newP.__etqNewEliminarMaquina.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                nuevaArea(newP.__cmbArea);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
               newP.__etqNewEliminarMaquina.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                newP.__etqNewEliminarMaquina.setFont(new java.awt.Font("Papyrus", 0, 12));
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
        
        this.newP.__cmbArea.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int idarea;
                if(newP.__cmbArea.getSelectedIndex()<=0){
                    return;
                }else{
                    idarea = busquedaid("area");
                    try {
                        ResultSet buscarMaquina = mimodelo.buscarMaquina(idarea);
                        newP.__cmbMaquina.removeAllItems();
                        newP.__cmbMaquina.addItem("Selecciona...");
                        while(buscarMaquina.next()){
                            newP.__cmbMaquina.addItem(buscarMaquina.getString(1)); 
                        }
                        newP.__etqClave.setText("");
                        String area = idarea < 10 ? "0"+idarea+"-": idarea+"-";
                        newP.__etqClave.setText("");
                        newP.__etqClave.setText(etiqueta+area);
                        traia= etiqueta+area;
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        });
        this.newP.__cmbMaquina.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int idmaquina;
                if(newP.__cmbMaquina.getSelectedIndex()<=0){
                    return;
                }else{
                    idmaquina = busquedaid("maquina");
                    String ponerid = idmaquina <10 ? "0"+idmaquina:idmaquina+"";
                    newP.__etqClave.setText(traia+ponerid);
                    String likeclave = newP.__etqClave.getText();
                    try{
                        ResultSet clave = mimodelo.buscarProductos(likeclave);
                        if(clave.next()){
                            String poneridclave= "";
                            System.out.println(likeclave+"like");
                            System.out.println( clave.getString("clave")+"regresa");
                            String[] partido = clave.getString("clave").split("-");
                            int idclave = Integer.parseInt(partido[3]);
                            idclave++;
                            if(idclave<10) poneridclave = "000"+idclave;
                            else if(idclave<100) poneridclave = "00"+idclave;
                            else if(idclave<1000) poneridclave = "0"+idclave;
                            else if(idclave<10000) poneridclave = ""+idclave;
                            newP.__etqClave.setText(traia+ponerid+"-"+poneridclave);
                        }else{
                            newP.__etqClave.setText(likeclave+"-0001");
                        }
                    }catch(Exception ex){
                  
                    }
                }
                
            }
        });
        this.newP.__descripcion.addKeyListener(new java.awt.event.KeyListener() {
            public void keyTyped(KeyEvent e) {
                KeyTipedLetrasNumCar(e);
            }
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_TAB){
                    newP.__SMin_.requestFocus();
                } 
            }
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
        //MOVIMIENTOS PAPEL
        this.movimientos.__etqNewEliminarAreaSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String area = movimientos.__AreaSalida.getText().toString();
                if(area.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar el area "+area+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__AreaSalida);
                    if(id>0){
                        boolean tru =mimodelo.bajaArea(id);
                        if(tru) {mensaje(1,"Baja de cliente "+ cliente +" correcta.");
                                         movimientos.__ClientEntr.setText("");}
                        else mensaje(3,"Baja de cliente "+ cliente +" incorrecta.");
                    }else mensaje(2,"Verifica el cliente");
                }
            }
        });
        this.movimientos.__etqNewEliminarClienteE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String cliente = movimientos.__ClientEntr.getText().toString();
                if(cliente.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar al cliente "+cliente+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__ClientEntr);
                    if(id>0){
                        boolean tru =mimodelo.bajaClientes(id);
                        if(tru) {mensaje(1,"Baja de cliente "+ cliente +" correcta.");
                                         movimientos.__ClientEntr.setText("");}
                        else mensaje(3,"Baja de cliente "+ cliente +" incorrecta.");
                    }else mensaje(2,"Verifica el cliente");
                }
            }
        });
        this.movimientos.__etqNewEliminarProveedorEntr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String prove = movimientos.__ProvEntr.getText().toString();
                if(prove.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar el proveedor "+prove+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__ProvEntr);
                    if(id>0){
                        boolean tru =mimodelo.bajaProveedor(id);
                        if(tru) {mensaje(1,"Baja de proveedor"+ prove +" correcta.");
                                         movimientos.__ProvEntr.setText("");}
                        else mensaje(3,"Baja de proveedor "+ prove +" incorrecta.");
                    }else mensaje(2,"Verifica el proveedor");
                }
            }
        });
         this.movimientos.__etqNewEliminarPropietarioEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String propietario = movimientos.__PropietarioEntr.getText().toString();
                if(propietario.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar al propietario "+propietario+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__PropietarioEntr);
                    if(id>0){
                        boolean tru =mimodelo.bajaPropietarios(id);
                        if(tru) {mensaje(1,"Baja de propietario "+ propietario +" correcta.");
                                         movimientos.__PropietarioEntr.setText("");}
                        else mensaje(3,"Baja de propietario "+ propietario +" incorrecta.");
                    }else mensaje(2,"Verifica el propietario");
                }
            }
        });
        this.movimientos.__etqNewEliminarTipoEntr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){}
            public void mouseExited(java.awt.event.MouseEvent evt){}
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String ent = movimientos.__TipoEntrada.getText().toString();
                if(ent.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar el tipo de entrada "+ent+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__TipoEntrada);
                    if(id>0){
                        boolean tru =mimodelo.bajaTipoEntrada(id);
                        if(tru) {mensaje(1,"Baja de tipo entrada "+ ent +" correcta.");
                                         movimientos.__TipoEntrada.setText("");}
                        else mensaje(3,"Baja de tipo entrada "+ ent +" incorrecta.");
                    }else mensaje(2,"Verifica el tipo de entrada");
                }
            }
        });
        this.movimientos.__etqNewEliminarTipoSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                System.out.println("borrar");
                String sal = movimientos.__TipoSalida.getText().toString();
                if(sal.isEmpty())return;
                confir = mensajeConfirmacion("¿Deseas eliminar el tipo de salida "+sal+"?","Confirma");  
                if(confir==JOptionPane.YES_OPTION){
                    int id = busquedaid(movimientos.__TipoSalida);
                    if(id>0){
                        boolean tru =mimodelo.bajaTipoSalida(id);
                        if(tru) {mensaje(1,"Baja de tipo salida "+ sal +" correcta.");
                                         movimientos.__TipoSalida.setText("");}
                        else mensaje(3,"Baja de tipo salidaa "+ sal +" incorrecta.");
                    }else mensaje(2,"Verifica el tipo de salida");
                }
            }    
            public void mouseEntered(java.awt.event.MouseEvent evt){
               
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                
            }
        });
        this.movimientos.__ACEPTARENTRADA.setActionCommand("__ACEPTAR_ENTRADA");
        this.movimientos.__ACEPTARENTRADA.setMnemonic('A');
        this.movimientos.__ACEPTARENTRADA.addActionListener(this);
        this.movimientos.__MODIFICACIONENTRADA.setActionCommand("__MODIFICACION_ENTRADA");
        this.movimientos.__MODIFICACIONENTRADA.setMnemonic('M');
        this.movimientos.__MODIFICACIONENTRADA.addActionListener(this);
        this.movimientos.__ACEPTARSALIDA.setActionCommand("__ACEPTAR_SALIDA");
        this.movimientos.__ACEPTARSALIDA.setMnemonic('C');
        this.movimientos.__ACEPTARSALIDA.addActionListener(this);
        this.movimientos.__MODIFICACIONSALIDA.setActionCommand("__MODIFICACION_SALIDA");
        this.movimientos.__MODIFICACIONSALIDA.setMnemonic('O');
        this.movimientos.__MODIFICACIONSALIDA.addActionListener(this);          
        //autocomplentables entrada 
        Com_propietarioE = new TextAutoCompleter(movimientos.__PropietarioEntr);
        Com_propietarioE.setMode(0);//infijo
        Com_TipoE = new TextAutoCompleter(movimientos.__TipoEntrada);
        Com_TipoE.setMode(0);//infijo
        Com_proveedorE= new TextAutoCompleter(movimientos.__ProvEntr);
        Com_proveedorE.setMode(0);//infijo
        Com_clienteE= new TextAutoCompleter(movimientos.__ClientEntr);
        Com_clienteE.setMode(0);//infijo
        com_prodcuto= new TextAutoCompleter(movimientos._claveProducto);
        com_prodcuto.setMode(0);//infijo
        com_descripcion= new TextAutoCompleter(movimientos._descripcionProducto);
        com_descripcion.setMode(0);//infijo    
       
        Solicitante = new TextAutoCompleter(movimientos.__SolicitanteSalida);
        Solicitante.setMode(0);//infijo
        
        this.movimientos.__SolicitanteSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    String sol = movimientos.__SolicitanteSalida.getText();
                    ResultSet soli = mimodelo.buscaSolicitante(sol);
                    Solicitante.removeAll();
                    while(soli.next()){
                        Solicitante.addItem(soli.getString(1));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        /*com_prodcutoSalida= new TextAutoCompleter(movimientos._claveProductoSalida);
        com_prodcuto.setMode(0);//infijo*/
        
        final TextAutoCompleter prodSalida = new TextAutoCompleter(movimientos.__claveProductoSalida);
        prodSalida.setMode(0);
        
        final TextAutoCompleter descSalida = new TextAutoCompleter(movimientos.__descripcionProductoSalida);
        descSalida.setMode(0);//infijo 
        
        this.movimientos.__claveProductoSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    try {
                        String parametro = movimientos.__claveProductoSalida.getText();
                        ResultSet prod = mimodelo.buscarProductoByClave(parametro);
                        int fila =movimientos.__tablaSalida.getSelectedRow();
                        while(prod.next()){
                            movimientos.__tablaSalida.setValueAt(prod.getString("clave"), fila, 0);
                            movimientos.__tablaSalida.setValueAt(prod.getString("descripcion"), fila, 1);
                            movimientos.__tablaSalida.setValueAt(prod.getString("ubicacion"), fila, 2);
                            movimientos.__tablaSalida.setValueAt(prod.getString("unidadmedida"), fila, 4);
                        }
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    String prod = movimientos.__claveProductoSalida.getText();
                    System.out.println(prod);
                    ResultSet bcP = mimodelo.buscaClaveProductoSalir(prod);
                    prodSalida.removeAll();
                    while(bcP.next()){
                        prodSalida.addItem(bcP.getString(1));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        });        
        this.movimientos._claveProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
             public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    try {
                        String parametro = movimientos._claveProducto.getText();
                        ResultSet prod = mimodelo.buscarProductoByClave(parametro);
                        int fila =movimientos.__tablaEntrada.getSelectedRow();
                        while(prod.next()){
                            movimientos.__tablaEntrada.setValueAt(prod.getString("clave"), fila, 0);
                            movimientos.__tablaEntrada.setValueAt(prod.getString("descripcion"), fila, 1);
                            movimientos.__tablaEntrada.setValueAt(prod.getString("ubicacion"), fila, 2);
                            movimientos.__tablaEntrada.setValueAt(prod.getString("unidadmedida"), fila, 4);
                            ResultSet costo = mimodelo.ultimocosto(prod.getString("clave"));
                            while(costo.next()){
                                movimientos.__tablaEntrada.setValueAt(Double.parseDouble(costo.getString("costo")), fila, 5);
                            }
                        }
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
                try {
                    String parametro = movimientos._claveProducto.getText();
                    ResultSet buscaClaveProducto = mimodelo.buscaClaveProducto(parametro);
                    com_prodcuto.removeAll();
                    while(buscaClaveProducto.next()){
                        com_prodcuto.addItem(buscaClaveProducto.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
            }
        });
        this.movimientos.__tablaSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                if(evt.getButton()==3){
                    int fila= movimientos.__tablaSalida.getSelectedRow();
                    if(fila==-1){
                        return;
                    }
                    confir = mensajeConfirmacion("¿Deseas eliminar la salida " +(fila+1),"ALERTA");
                    if (confir==JOptionPane.OK_OPTION){
                        DefaultTableModel dtm = (DefaultTableModel) movimientos.__tablaSalida.getModel();
                        dtm.removeRow(fila);
                        String datos []={};
                        dtm.addRow(datos);
                    }
                }
            }    
            public void mouseEntered(java.awt.event.MouseEvent evt){
               
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                
            }
        });
        this.movimientos.__tablaEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                if(evt.getButton()==3){
                    int fila= movimientos.__tablaEntrada.getSelectedRow();
                    if(fila==-1){
                        return;
                    }
                    confir = mensajeConfirmacion("¿Deseas eliminar la entrada " +(fila+1),"ALERTA");
                    if (confir==JOptionPane.OK_OPTION){
                        DefaultTableModel dtm = (DefaultTableModel) movimientos.__tablaEntrada.getModel();
                        dtm.removeRow(fila);
                        String datos []={};
                        dtm.addRow(datos);
                    }
                }
            }    
            public void mouseEntered(java.awt.event.MouseEvent evt){
               
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                
            }
        });
        this.movimientos.__tablaEntrada.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                int columna = movimientos.__tablaEntrada.getSelectedColumn();
                int fila = movimientos.__tablaEntrada.getSelectedRow();
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    try{
                        if(columna==3||columna==5){
                            Double cant=Double.parseDouble(movimientos.__tablaEntrada.getValueAt(fila, 3)+"");
                            Double cos=Double.parseDouble(movimientos.__tablaEntrada.getValueAt(fila, 5)+"");
                            Double totcos= cant*cos;
                            movimientos.__tablaEntrada.setValueAt(totcos, fila, 6);
                        }
                    }catch(Exception e){
                    }
                }
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                
            }
        });          
        this.movimientos.__descripcionProductoSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
             public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    try {
                        
                        String parametro = movimientos.__descripcionProductoSalida.getText();
                        System.out.print(parametro);
                        ResultSet prod = mimodelo.buscarProductoByDescripcion(parametro);
                        int fila =movimientos.__tablaSalida.getSelectedRow();
                        while(prod.next()){
                            movimientos.__tablaSalida.setValueAt(prod.getString("clave"), fila, 0);
                            movimientos.__tablaSalida.setValueAt(prod.getString("ubicacion"), fila, 2);
                            movimientos.__tablaSalida.setValueAt(prod.getString("unidadmedida"), fila, 4);
                            
                            
                            
                        }
                        
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
                try {
                    String parametro = movimientos.__descripcionProductoSalida.getText();
                    ResultSet buscaDescripcionProducto = mimodelo.buscaDescripcionProducto(parametro);
                    descSalida.removeAll();
                    while(buscaDescripcionProducto.next()){
                        descSalida.addItem(buscaDescripcionProducto.getString(1));
                        
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
            }
        });                
        this.movimientos._descripcionProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                
            }
             public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    try {
                        String parametro = movimientos._descripcionProducto.getText();
                        
                        ResultSet prod = mimodelo.buscarProductoByDescripcion(parametro);
                        int fila =movimientos.__tablaEntrada.getSelectedRow();
                        while(prod.next()){
                            movimientos.__tablaEntrada.setValueAt(prod.getString("clave"), fila, 0);
                            movimientos.__tablaEntrada.setValueAt(prod.getString("ubicacion"), fila, 2);
                            movimientos.__tablaEntrada.setValueAt(prod.getString("unidadmedida"), fila, 4);
                            ResultSet costo = mimodelo.ultimocosto(prod.getString("clave"));
                            while(costo.next()){
                                movimientos.__tablaEntrada.setValueAt(Double.parseDouble(costo.getString("costo")), fila, 5);
                            }
                        }
                        
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
                try {
                    String parametro = movimientos._descripcionProducto.getText();
                    ResultSet buscaDescripcionProducto = mimodelo.buscaDescripcionProducto(parametro);
                    com_descripcion.removeAll();
                    while(buscaDescripcionProducto.next()){
                        com_descripcion.addItem(buscaDescripcionProducto.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
            }
        });    
        
        
        
        
        
        this.movimientos.__documentoEntr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
             public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                   movimientos.__TipoEntrada.requestFocus();
                } 
            }
             
            
        });
        this.movimientos.__documentoEntr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                docentrada();
            }
        });
        this.movimientos.__TipoEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
             public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                                            
                    movimientos.__PropietarioEntr.requestFocus();
                } 
            }
             public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__TipoEntrada.getText();
                    ResultSet buscarTipoEntrada = mimodelo.buscaTipoEntrada(parametro,true);
                    Com_TipoE.removeAll();
                    while(buscarTipoEntrada.next()){
                        Com_TipoE.addItem(buscarTipoEntrada.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
             
            
        });
        this.movimientos.__PropietarioEntr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);                
            }
             public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     movimientos.__ProvEntr.requestFocus();
                } 
            }
              public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__PropietarioEntr.getText();
                    //ResultSet buscarTipoEntrada = mimodelo.buscaTipoEntrada(parametro);
                    ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro,true);
                    Com_propietarioE.removeAll();
                    while(buscarPropiedad.next()){
                        Com_propietarioE.addItem(buscarPropiedad.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
             
            
        });
        this.movimientos.__ProvEntr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     movimientos.__OrdenProduccionEntr.requestFocus();
                } 
            }
           
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__ProvEntr.getText();
                    ResultSet buscarProveedores = mimodelo.buscaProveedor(parametro,true);
                    //ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro);
                    Com_proveedorE.removeAll();
                    while(buscarProveedores.next()){
                        Com_proveedorE.addItem(buscarProveedores.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
            
        });
        this.movimientos.__OrdenProduccionEntr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNumCar(evt);                 
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     movimientos.__OrdenCompraEntr.requestFocus();
                } 
            }
            
        });
        this.movimientos.__OrdenCompraEntr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     movimientos.__ClientEntr.requestFocus();
                } 
            }
            
            
            
        });
        
        this.movimientos.__ClientEntr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNum(evt);  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     movimientos.__chkTurno1Entr.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    
                    String parametro = movimientos.__ClientEntr.getText();
                    //ResultSet buscarProveedores = mimodelo.buscaProveedor(parametro);
                    ResultSet buscarClientes = mimodelo.buscarCliente(parametro,true);
                    //ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro);
                    Com_clienteE.removeAll();
                    while(buscarClientes.next()){
                        Com_clienteE.addItem(buscarClientes.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
            
            
            
        });
        this.movimientos.__chkTurno1Entr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     movimientos.__chkTurno2Entr.requestFocus();
                } 
            }
            
        });
        this.movimientos.__chkTurno2Entr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     movimientos.__chkTurno3Entr.requestFocus();
                } 
            }
            
        });
        this.movimientos.__chkTurno3Entr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     movimientos.__tablaEntrada.requestFocus();
                } 
            }
            
        });
        this.movimientos.__etqLimpiarTablaEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                limpiarTabla(movimientos.__tablaEntrada);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqLimpiarTablaEntrada.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqLimpiarTablaEntrada.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        this.movimientos.__etqLimpiarTablaSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                limpiarTabla(movimientos.__tablaSalida);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqLimpiarTablaSalida.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqLimpiarTablaSalida.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        this.movimientos.__etqNewTipoEntr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewTipoEntr.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewTipoEntr.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevaTipoEntrada();
            }
        });
        this.movimientos.__etqNewPropietarioEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewPropietarioEntrada.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewPropietarioEntrada.setFont(new java.awt.Font("Papyrus", 0, 12));
            }   
            public void mouseClicked(java.awt.event.MouseEvent evt){
                nuevoPropietario();
            }
        });
        this.movimientos.__etqNewProveedorEntr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clienteprovedor=1;
                NewPC.ProveCli.setText("Nuevo Proveedor");
                NewPC.setLocationRelativeTo(null);
                NewPC.setVisible(true);
                movimientos.setEnabled(false);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewProveedorEntr.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewProveedorEntr.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        }); 
        this.movimientos.__etqNewClienteE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clienteprovedor=0;
                NewPC.ProveCli.setText("Nuevo Cliente");
                NewPC.setVisible(true);
                NewPC.setLocationRelativeTo(null);
                movimientos.setEnabled(false);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewClienteE.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewClienteE.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
        });
        //Salidas AutoCompletables        
        Com_TipoS = new TextAutoCompleter(movimientos.__TipoSalida);
        Com_TipoS.setMode(0);//infijo
        Com_AreaS= new TextAutoCompleter(movimientos.__AreaSalida);
        Com_AreaS.setMode(0);//infijo        
        
//        this.movimientos._claveProductoSalida
//        this.movimientos._descripcionProductoSalida        
        this.movimientos.__TipoSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
             public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                                            
                    movimientos.__documentoSalida.requestFocus();
                } 
            }
             public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__TipoSalida.getText();
                    ResultSet buscarTipoSalida = mimodelo.buscaTipoSalida(parametro,true);
                    Com_TipoS.removeAll();
                    while(buscarTipoSalida.next()){
                        Com_TipoS.addItem(buscarTipoSalida.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
             
            
        });
        this.movimientos.__documentoSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
             public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                   movimientos.__OrdenProduccionSalida.requestFocus();
                } 
            }                         
        });        
        this.movimientos.__OrdenProduccionSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNumCar(evt);                 
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     movimientos.__SolicitanteSalida.requestFocus();
                } 
            }
            
        });
        this.movimientos.__SolicitanteSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNumCar(evt);                 
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     movimientos.__AreaSalida.requestFocus();
                } 
            }            
        });
        this.movimientos.__AreaSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
             public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                                            
                    movimientos.__chkTurno1Salida.requestFocus();
                } 
            }
             public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String parametro = movimientos.__AreaSalida.getText();
                    ResultSet buscarArea = mimodelo.buscaArea(parametro,true);
                    Com_AreaS.removeAll();
                    while(buscarArea.next()){
                        Com_AreaS.addItem(buscarArea.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }
             
            
        });
        this.movimientos.__chkTurno1Salida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     movimientos.__chkTurno2Salida.requestFocus();
                } 
            }
            
        });
        this.movimientos.__chkTurno2Salida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     movimientos.__chkTurno3Salida.requestFocus();
                } 
            }
            
        });
        this.movimientos.__chkTurno3Salida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetras(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     movimientos.__tablaSalida.requestFocus();
                } 
            }
            
        });
        this.movimientos.__etqNewTipoSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewTipoSalida.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewTipoSalida.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {                
                nuevoTipoSalida();                
            }
        });
        this.movimientos.__etqNewAreaSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){
                movimientos.__etqNewAreaSalida.setFont(new java.awt.Font("Papyrus", 3, 12));
            }
            public void mouseExited(java.awt.event.MouseEvent evt){
                movimientos.__etqNewAreaSalida.setFont(new java.awt.Font("Papyrus", 0, 12));
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {                                
                nuevaArea();                
            }
        });
        //NUEVO PROVEEDOR CLIENTE
        this.consulta.__SALIRCONSULTA.setActionCommand("__SALIR_CONSULTA");
        this.consulta.__SALIRCONSULTA.setMnemonic('C');
        this.consulta.__SALIRCONSULTA.addActionListener(this);
        this.NewPC.__ACEPTARPROVEEDOR.setActionCommand("__ACEPTAR_PROVEEDOR");
        this.NewPC.__ACEPTARPROVEEDOR.setMnemonic('A');
        this.NewPC.__ACEPTARPROVEEDOR.addActionListener(this);
        this.NewPC.__CANCELARPROVEEDOR.setActionCommand("__CANCELAR_PROVEEDOR");
        this.NewPC.__CANCELARPROVEEDOR.setMnemonic('C');
        this.NewPC.__CANCELARPROVEEDOR.addActionListener(this);
        this.NewPC.__NombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
              }
            public void keyPressed(java.awt.event.KeyEvent evt){
                   if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altaproveedor();
                } 
            }
            });
        this.NewPC.__DireccionProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
              }
             public void keyPressed(java.awt.event.KeyEvent evt){
                   if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altaproveedor();
                } 
            }
            });
        this.NewPC.__TelefonoNProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);                
              }
             public void keyPressed(java.awt.event.KeyEvent evt){
                   if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altaproveedor();
                } 
            }
            });
        this.NewPC.__RFC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);
              }
             public void keyPressed(java.awt.event.KeyEvent evt){
                   if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                    altaproveedor();
                } 
            }
            });
        ubicacioncom = new TextAutoCompleter(newP.__Ubicacion);
        ubicacioncom.setMode(0);//infijo
        this.newP.__Ubicacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                                            
                    //consulta.__proveedor.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String ubicacion = newP.__Ubicacion.getText();
                    ResultSet buscarDescripcion = mimodelo.buscarubicacion(ubicacion);
                    ubicacioncom.removeAll();
                    while(buscarDescripcion.next()){
                        ubicacioncom.addItem(buscarDescripcion.getString("ubicacion"));
                    }
              }catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
               }
            }                         
        });
        ubicacionent = new TextAutoCompleter(movimientos._ubicacion);
        ubicacionent.setMode(0);//infijo
        this.movimientos._ubicacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                                            
                    //consulta.__proveedor.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String ubicacion = movimientos._ubicacion.getText();
                    ResultSet buscarDescripcion = mimodelo.buscarubicacion(ubicacion);
                    ubicacionent.removeAll();
                    while(buscarDescripcion.next()){
                        ubicacionent.addItem(buscarDescripcion.getString("ubicacion"));
                    }
              }catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
               }
            }                         
        });
        ubicacionsal = new TextAutoCompleter(movimientos._ubicacionsalida);
        ubicacionsal.setMode(0);//infijo
        this.movimientos._ubicacionsalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                                            
                    //consulta.__proveedor.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String ubicacion = movimientos._ubicacionsalida.getText();
                    ResultSet buscarDescripcion = mimodelo.buscarubicacion(ubicacion);
                    ubicacionsal.removeAll();
                    while(buscarDescripcion.next()){
                        ubicacionsal.addItem(buscarDescripcion.getString("ubicacion"));
                    }
              }catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
               }
            }                         
        });
        //cnsita
        this.verconsulta.__SALIR.setActionCommand("__REGRESAR");
        this.verconsulta.__SALIR.addKeyListener(new java.awt.event.KeyAdapter(){
            public void keyPressed(java.awt.event.KeyEvent evt){
                if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
                    limpiarTabla(verconsulta.__tConsulta);
                    verconsulta.setVisible(false);
                }                    
            }
        });             
        this.verconsulta.__SALIR.addActionListener(this); 
        this.verconsulta.__EXCEL.setActionCommand("__EXCEL");
        this.verconsulta.__EXCEL.addActionListener(this);
        this.verconsulta.__CORREO.setActionCommand("__CORREO");
        this.verconsulta.__CORREO.addActionListener(this);
        
        //Consultas autocompletables        
        //DESCRIPCION
        Com_DescrpcionCon = new TextAutoCompleter(consulta.__Descripcion);
        Com_DescrpcionCon.setMode(0);//infijo
        this.consulta.__Descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNum(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                                            
                    consulta.__proveedor.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String descripcion = consulta.__Descripcion.getText();
                    ResultSet buscarDescripcion = mimodelo.buscarProductoByDescripcion(descripcion);
                    Com_DescrpcionCon.removeAll();
                    while(buscarDescripcion.next()){
                        Com_DescrpcionCon.addItem(buscarDescripcion.getString("descripcion"));
                    }
              }catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
               }
            }                         
        });
        //documento
        SolicitanteCon = new TextAutoCompleter(consulta.__Solicitante);
        SolicitanteCon.setMode(0);//infijo
        this.consulta.__Solicitante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNum(evt);  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     //consulta.__Propietario.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String sol = consulta.__Solicitante.getText();
                    ResultSet soli = mimodelo.buscaSolicitante(sol);
                    SolicitanteCon.removeAll();
                    while(soli.next()){
                        SolicitanteCon.addItem(soli.getString(1));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
             }                                    
        });
        //PROVEEDOR
        Com_proveedorCon = new TextAutoCompleter(consulta.__proveedor);
        Com_proveedorCon.setMode(0);//infijo
        this.consulta.__proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNum(evt);  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     consulta.__Propietario.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {                    
                    String parametro = consulta.__proveedor.getText();
                    ResultSet buscarProveedores = mimodelo.buscaProveedor(parametro,true);
                    //ResultSet buscarClientes = mimodelo.buscarCliente(parametro,true);
                    //ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro,true);
                    Com_proveedorCon.removeAll();
                    while(buscarProveedores.next()){
                        Com_proveedorCon.addItem(buscarProveedores.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }                                    
        });
        //PROPIETARIO
        Com_propietarioCon = new TextAutoCompleter(consulta.__Propietario);
        Com_propietarioCon.setMode(0);//infijo
        this.consulta.__Propietario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNum(evt);  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     consulta.__Cliente.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {                    
                    String parametro = consulta.__Propietario.getText();
                    //ResultSet buscarProveedores = mimodelo.buscaProveedor(parametro);
                    //ResultSet buscarClientes = mimodelo.buscarCliente(parametro,true);
                    ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro,true);
                    Com_propietarioCon.removeAll();
                    while(buscarPropiedad.next()){
                        Com_propietarioCon.addItem(buscarPropiedad.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }                                    
        });
        //CLIENTE
        Com_ClienteCon = new TextAutoCompleter(consulta.__Cliente);
        Com_ClienteCon.setMode(0);//infijo
        this.consulta.__Cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNum(evt);  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     consulta.__documento.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {                    
                    String parametro = consulta.__Cliente.getText();
                    //ResultSet buscarProveedores = mimodelo.buscaProveedor(parametro);
                    ResultSet buscarClientes = mimodelo.buscarCliente(parametro,true);
                    //ResultSet buscarPropiedad = mimodelo.buscarPropiedad(parametro);
                    Com_ClienteCon.removeAll();
                    while(buscarClientes.next()){
                        Com_ClienteCon.addItem(buscarClientes.getString(2));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }                                    
        });
        //DOCUMENTO
        Com_DocumentoCon = new TextAutoCompleter(consulta.__documento);
        Com_DocumentoCon.setMode(0);//infijo
        this.consulta.__documento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNum(evt);                  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     //consulta.__OrdenC.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {                    
                    String parametro = consulta.__OrdenP.getText();
                    ResultSet buscarDocu = null;
                    if(consulta.__optEntrada.isSelected()){
                        buscarDocu= mimodelo.documentoe(parametro);
                    }
                    if(consulta.__optSalida.isSelected()){
                        buscarDocu= mimodelo.documentos(parametro);
                    }
                    Com_DocumentoCon.removeAll();
                    while(buscarDocu.next()){
                        Com_DocumentoCon.addItem(buscarDocu.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }                                    
        });
        //OP
        Com_OrdenProduccionCon = new TextAutoCompleter(consulta.__OrdenP);
        Com_OrdenProduccionCon.setMode(0);//infijo
        this.consulta.__OrdenP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNum(evt);                  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     consulta.__OrdenC.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {                    
                    String parametro = consulta.__OrdenP.getText();                    
                    ResultSet buscarOP = mimodelo.buscarop(parametro);
                    System.out.println(buscarOP);
                    Com_OrdenProduccionCon.removeAll();
                    while(buscarOP.next()){
                        Com_OrdenProduccionCon.addItem(buscarOP.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }                                    
        });
        //OC
        Com_OrdenCompraCon = new TextAutoCompleter(consulta.__OrdenC);
        Com_OrdenCompraCon.setMode(0);//infijo
        this.consulta.__OrdenC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNum(evt);                  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     consulta.__Ubicacion.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {                    
                    String parametro = consulta.__OrdenC.getText();                    
                    ResultSet buscarOC = mimodelo.buscaroc(parametro);                   
                    Com_OrdenCompraCon.removeAll();
                    while(buscarOC.next()){
                        Com_OrdenCompraCon.addItem(buscarOC.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }                                    
        });
        
        //UBICACION
        Com_UbicacionCon = new TextAutoCompleter(consulta.__Ubicacion);
        Com_UbicacionCon.setMode(0);//infijo
        this.consulta.__Ubicacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNum(evt);                  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     consulta.__clave.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {                    
                    String parametro = consulta.__Ubicacion.getText();                    
                    ResultSet buscarUbicacion = mimodelo.buscarubicacion(parametro);                   
                    Com_UbicacionCon.removeAll();
                    while(buscarUbicacion.next()){
                        Com_UbicacionCon.addItem(buscarUbicacion.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }                                    
        });
        //CLAVE
        Com_ClaveCon = new TextAutoCompleter(consulta.__clave);
        Com_ClaveCon.setMode(0);//infijo
        this.consulta.__clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNumCar(evt);                  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     consulta.__Area.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {                    
                    String parametro = consulta.__clave.getText();                    
                    ResultSet buscarClave = mimodelo.buscarclave(parametro);                   
                    Com_ClaveCon.removeAll();
                    while(buscarClave.next()){
                        Com_ClaveCon.addItem(buscarClave.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }                                    
        });
        //AREA
        Com_AreaCon = new TextAutoCompleter(consulta.__Area);
        Com_AreaCon.setMode(0);//infijo
        this.consulta.__Area.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNum(evt);                  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     consulta.__Secion.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {                    
                    String parametro = consulta.__Area.getText();                    
                    ResultSet buscarArea = mimodelo.buscararea(parametro);                   
                    Com_AreaCon.removeAll();
                    while(buscarArea.next()){
                        Com_AreaCon.addItem(buscarArea.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }                                    
        });
        //MAQUINA
        Com_MaquinaCon = new TextAutoCompleter(consulta.__Secion);
        Com_MaquinaCon.setMode(0);//infijo
        this.consulta.__Secion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNum(evt);                  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     consulta.__TipoEntrada.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {                    
                    String parametro = consulta.__Secion.getText();                    
                    ResultSet buscarMaquina = mimodelo.buscarmaquina(parametro);                   
                    Com_MaquinaCon.removeAll();
                    while(buscarMaquina.next()){
                        Com_MaquinaCon.addItem(buscarMaquina.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }                                    
        });
        //TIPO DE ENTRADA
        Com_TipoEntradaCon = new TextAutoCompleter(consulta.__TipoEntrada);
        Com_TipoEntradaCon.setMode(0);//infijo
        this.consulta.__TipoEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNum(evt);                  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     consulta.__TipoSalida.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {                    
                    String parametro = consulta.__TipoEntrada.getText();                    
                    ResultSet buscarTEntrada = mimodelo.buscartentrada(parametro);                   
                    Com_TipoEntradaCon.removeAll();
                    while(buscarTEntrada.next()){
                        Com_TipoEntradaCon.addItem(buscarTEntrada.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }                                    
        });
        //TIPO DE SALIDA
        Com_TipoSalidaCon = new TextAutoCompleter(consulta.__TipoSalida);
        Com_TipoSalidaCon.setMode(0);//infijo
        this.consulta.__TipoSalida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNum(evt);                  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
//                     consulta.__Area.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {                    
                    String parametro = consulta.__TipoSalida.getText();                    
                    ResultSet buscarTSalida = mimodelo.buscartsalida(parametro);                   
                    Com_TipoSalidaCon.removeAll();
                    while(buscarTSalida.next()){
                        Com_TipoSalidaCon.addItem(buscarTSalida.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }                                    
        });
        foloini = new TextAutoCompleter(consulta.__folio);
        foloini.setMode(0);
        this.consulta.__folio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNumCar(evt);                  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
//                     consulta.__Area.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {                    
                    String parametro = consulta.__folio.getText();                    
                    ResultSet buscarfolio= null;
                    if(consulta.__optEntrada.isSelected()){
                        buscarfolio= mimodelo.folioentrada(parametro);  
                    }
                    if(consulta.__optSalida.isSelected()){
                        buscarfolio= mimodelo.foliosalida(parametro);  
                    }             
                    foloini.removeAll();
                    while(buscarfolio.next()){
                        foloini.addItem(buscarfolio.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }                                    
        });
        foliofin = new TextAutoCompleter(consulta.__foliohasta);
        foliofin.setMode(0);
        this.consulta.__foliohasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNumCar(evt);                  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
//                     consulta.__Area.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {                    
                    String parametro = consulta.__foliohasta.getText();                    
                    ResultSet buscarfolio= null;
                    if(consulta.__optEntrada.isSelected()){
                        buscarfolio= mimodelo.folioentrada(parametro);  
                    }
                    if(consulta.__optSalida.isSelected()){
                        buscarfolio= mimodelo.foliosalida(parametro);  
                    }             
                    foliofin.removeAll();
                    while(buscarfolio.next()){
                        foliofin.addItem(buscarfolio.getString(1));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }                                    
        });
        //autocompletables Finanzas          
        this.Finanzas.__ACEPTAR.setActionCommand("__ACEPTAR_FINANZAS");
        this.Finanzas.__ACEPTAR.setMnemonic('A');
        this.Finanzas.__ACEPTAR.addActionListener(this);
        this.Finanzas.__SALIR2.setActionCommand("__CANCELAR_FINANZAS");
        this.Finanzas.__SALIR2.setMnemonic('C');
        this.Finanzas.__SALIR2.addActionListener(this);
        Com_ClaveRFin = new TextAutoCompleter(Finanzas.__clave);
        Com_ClaveRFin.setMode(0);//infijo
        this.Finanzas.__clave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                 KeyTipedLetrasNumCar(evt);                  
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                    
                     Finanzas.__descripcion.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {                    
                    String parametro = Finanzas.__clave.getText();                    
                    ResultSet buscarClave = mimodelo.buscarclave(parametro);                   
                    Com_ClaveRFin.removeAll();
                    while(buscarClave.next()){
                        Com_ClaveRFin.addItem(buscarClave.getString("claveProducto"));
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                }
             }                                    
        });
        Com_DescripcionRFin = new TextAutoCompleter(Finanzas.__descripcion);
        Com_DescripcionRFin.setMode(0);//infijo
        this.Finanzas.__descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KeyTipedLetrasNumCar(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt){
                int evento=evt.getKeyCode();               
                 if(evt.getKeyCode()==KeyEvent.VK_ENTER){                                            
                    //consulta.__proveedor.requestFocus();
                } 
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
              try {
                    String descripcion = Finanzas.__descripcion.getText();
                    ResultSet buscarDescripcion = mimodelo.buscarProductoByDescripcion(descripcion);
                    Com_DescripcionRFin.removeAll();
                    while(buscarDescripcion.next()){
                        Com_DescripcionRFin.addItem(buscarDescripcion.getString("descripcion"));
                    }
              }catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
               }
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
        this.newP.__menuReportes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK));
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
        this.newP.__menuBajaProducto.setActionCommand("__MENU_BAJA_PRODUCTO");
        this.newP.__menuBajaProducto.addActionListener(this);
        this.newP.__menuAcerca.setActionCommand("__MENU_ACERCADE");
        this.newP.__menuAcerca.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10,InputEvent.CTRL_MASK));
        this.newP.__menuAcerca.addActionListener(this);  
        this.newP.__menuBackup.setActionCommand("__MENU_BACKUP");
        this.newP.__menuBackup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK));
        this.newP.__menuBackup.addActionListener(this); 
        //MENUS MOVIMIENTOS
        this.movimientos.__menuAltaProducto.setActionCommand("__MENU_ALTA");
        this.movimientos.__menuAltaProducto.addActionListener(this);
        this.movimientos.__menuAltaProducto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
        this.movimientos.__menuAnterior.setActionCommand("__MENU_ANTERIOR");
        this.movimientos.__menuAnterior.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        this.movimientos.__menuAnterior.addActionListener(this);
        this.movimientos.__menuConsultas.setActionCommand("__MENU_CONSULTAS");
        this.movimientos.__menuConsultas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
        this.movimientos.__menuConsultas.addActionListener(this);
        this.movimientos.__menuReportes.setActionCommand("__MENU_REPORTES");
        this.movimientos.__menuReportes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK));
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
        this.movimientos.__menuconsultarexistencia.setActionCommand("__MENU_CONSULTA_EXISTENCIA");
        this.movimientos.__menuconsultarexistencia.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,InputEvent.CTRL_MASK));
        this.movimientos.__menuconsultarexistencia.addActionListener(this);  
        this.movimientos.__menuAcerca.setActionCommand("__MENU_ACERCADE");
        this.movimientos.__menuAcerca.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10,InputEvent.CTRL_MASK));
        this.movimientos.__menuAcerca.addActionListener(this);  
        this.movimientos.__menuBackup.setActionCommand("__MENU_BACKUP");
        this.movimientos.__menuBackup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK));
        this.movimientos.__menuBackup.addActionListener(this);
        //MENU CONSULTAS
        this.consulta.__menuAltaProducto.setActionCommand("__MENU_ALTA");
        this.consulta.__menuAltaProducto.addActionListener(this);
        this.consulta.__menuAltaProducto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
        this.consulta.__menuMovimientos.setActionCommand("__MENU_MOV_PAPEL");
        this.consulta.__menuMovimientos.addActionListener(this);
        this.consulta.__menuMovimientos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,InputEvent.CTRL_MASK));
        this.consulta.__menuAnterior.setActionCommand("__MENU_ANTERIOR");
        this.consulta.__menuAnterior.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        this.consulta.__menuAnterior.addActionListener(this);        
        this.consulta.__menuReportes.setActionCommand("__MENU_REPORTES");
        this.consulta.__menuReportes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK));
        this.consulta.__menuReportes.addActionListener(this);
        this.consulta.__MenuAbrirArchivo.setActionCommand("__MENU_ABRIR_ARCHIVO");
        this.consulta.__MenuAbrirArchivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
        this.consulta.__MenuAbrirArchivo.addActionListener(this);
        this.consulta.__menuCerrarSesion.setActionCommand("__MENU_CERRAR_SESION");
        this.consulta.__menuCerrarSesion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK));
        this.consulta.__menuCerrarSesion.addActionListener(this);
        this.consulta.__menuSalir.setActionCommand("__MENU_SALIR");
        this.consulta.__menuSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.SHIFT_MASK |InputEvent.CTRL_MASK));
        this.consulta.__menuSalir.addActionListener(this);
        this.consulta.__menuCambiarFecha.setActionCommand("__MENU_FECHA");
        this.consulta.__menuCambiarFecha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
        this.consulta.__menuCambiarFecha.addActionListener(this);
        this.consulta.__menuNuevoUsuario.setActionCommand("__MENU_NEWUSER");
        this.consulta.__menuNuevoUsuario.addActionListener(this);
        this.consulta.__menuCambiarContraseña.setActionCommand("__MENU_CONTRASEÑA");
        this.consulta.__menuCambiarContraseña.addActionListener(this);        
        this.consulta.__menuReporte.setActionCommand("__MENU_REPORTE_USUARIOS");
        this.consulta.__menuReporte.addActionListener(this);               
        this.consulta.__menuAcerca.setActionCommand("__MENU_ACERCADE");
        this.consulta.__menuAcerca.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10,InputEvent.CTRL_MASK));
        this.consulta.__menuAcerca.addActionListener(this);  
        this.consulta.__menuBackup.setActionCommand("__MENU_BACKUP");
        this.consulta.__menuBackup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK));
        this.consulta.__menuBackup.addActionListener(this);       
        //MENU REPORTE
        this.reporte.__menuAltaProducto.setActionCommand("__MENU_ALTA");
        this.reporte.__menuAltaProducto.addActionListener(this);
        this.reporte.__menuAltaProducto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
        this.reporte.__menuConsultas.setActionCommand("__MENU_CONSULTAS");
        this.reporte.__menuConsultas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
        this.reporte.__menuConsultas.addActionListener(this);
        this.reporte.__menuMovimientos.setActionCommand("__MENU_MOV_PAPEL");
        this.reporte.__menuMovimientos.addActionListener(this);
        this.reporte.__menuMovimientos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,InputEvent.CTRL_MASK));
        this.reporte.__menuAnterior.setActionCommand("__MENU_ANTERIOR");
        this.reporte.__menuAnterior.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        this.reporte.__menuAnterior.addActionListener(this);        
        this.reporte.__MenuAbrirArchivo.setActionCommand("__MENU_ABRIR_ARCHIVO");
        this.reporte.__MenuAbrirArchivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK));
        this.reporte.__MenuAbrirArchivo.addActionListener(this);
        this.reporte.__menuCerrarSesion.setActionCommand("__MENU_CERRAR_SESION");
        this.reporte.__menuCerrarSesion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.CTRL_MASK));
        this.reporte.__menuCerrarSesion.addActionListener(this);
        this.reporte.__menuSalir.setActionCommand("__MENU_SALIR");
        this.reporte.__menuSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,InputEvent.SHIFT_MASK |InputEvent.CTRL_MASK));
        this.reporte.__menuSalir.addActionListener(this);
        this.reporte.__menuCambiarFecha.setActionCommand("__MENU_FECHA");
        this.reporte.__menuCambiarFecha.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
        this.reporte.__menuCambiarFecha.addActionListener(this);
        this.reporte.__menuNuevoUsuario.setActionCommand("__MENU_NEWUSER");
        this.reporte.__menuNuevoUsuario.addActionListener(this);
        this.reporte.__menuCambiarContraseña.setActionCommand("__MENU_CONTRASEÑA");
        this.reporte.__menuCambiarContraseña.addActionListener(this);        
        this.reporte.__menuReporte.setActionCommand("__MENU_REPORTE_USUARIOS");
        this.reporte.__menuReporte.addActionListener(this);             
        this.reporte.__menuAcerca.setActionCommand("__MENU_ACERCADE");
        this.reporte.__menuAcerca.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10,InputEvent.CTRL_MASK));
        this.reporte.__menuAcerca.addActionListener(this);  
        this.reporte.__menuBackup.setActionCommand("__MENU_BACKUP");
        this.reporte.__menuBackup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK));
        this.reporte.__menuBackup.addActionListener(this);
        this.reporte.__menu_datos.setActionCommand("__MENU_DATOS");
        this.reporte.__menu_datos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,InputEvent.CTRL_MASK));
        this.reporte.__menu_datos.addActionListener(this);       
        this.reporte.__RInventario.setActionCommand("__INVENTARIO");
        this.reporte.__RInventario.setMnemonic('I');
        this.reporte.__RInventario.addActionListener(this);
        this.reporte.__Salida.setActionCommand("__SALIDA");
        this.reporte.__Salida.setMnemonic('S');
        this.reporte.__Salida.addActionListener(this); 
        this.reporte.__Entrada.setActionCommand("__ENTRADA");
        this.reporte.__Entrada.setMnemonic('E');
        this.reporte.__Entrada.addActionListener(this);
        this.reporte.__StockArriba.setActionCommand("__STOCK_ARRIBA");
        this.reporte.__StockArriba.setMnemonic('S');
        this.reporte.__StockArriba.addActionListener(this);        
        this.reporte.__StockAbajo.setActionCommand("__STOCK_ABAJO");
        this.reporte.__StockAbajo.setMnemonic('A');
        this.reporte.__StockAbajo.addActionListener(this);
        this.reporte.__SALIR.setActionCommand("__SALIR_PRODUCTO");
        this.reporte.__SALIR.setMnemonic('R');
        this.reporte.__SALIR.addActionListener(this);
        this.reporte.__Finanzas.setActionCommand("__REPORTE_FINANZAS");
        this.reporte.__Finanzas.setMnemonic('F');
        this.reporte.__Finanzas.addActionListener(this);
        //FINAL REPORTES
        this.correo.__ABRIREXCEL.setActionCommand("__ABRIREXCEL");
        this.correo.__ABRIREXCEL.addActionListener(this);
        this.correo.__BUSCARARCHIVO.setActionCommand("__BUSCARARCHIVO");
        this.correo.__BUSCARARCHIVO.addActionListener(this);
        this.correo.__ACEPTARCORREO.setActionCommand("__ACEPTARCORREO");
        this.correo.__ACEPTARCORREO.addActionListener(this);
        this.correo.__SALIRCORREO.setActionCommand("__SALIRCORREO");
        this.correo.__SALIRCORREO.addActionListener(this);
        //FIN MENU
        
        //ACCIOENES CONSULTAS
        this.consulta.__optNinguno.setActionCommand("__OPTNINGUNO");
        this.consulta.__optNinguno.addActionListener(this);    
        this.consulta.__optEntrada.setActionCommand("__OPTENTRADA");
        this.consulta.__optEntrada.addActionListener(this); 
        this.consulta.__optSalida.setActionCommand("__OPTSALIDA");
        this.consulta.__optSalida.addActionListener(this); 
        
        this.consulta.__ACEPTARCONSULTA.setActionCommand("__ACEPTARCONSULTA");
        this.consulta.__ACEPTARCONSULTA.addActionListener(this); 
    }   

    public void borrarFormularioConsultas() {
        this.consulta.__Descripcion.setText("");
        this.consulta.__clave.setText("");
        this.consulta.__cmbUnidad.setSelectedIndex(0);
        this.consulta.__Cantidad.setText("");
        this.consulta.__Ubicacion.setText("");
        this.consulta.__Area.setText("");
        this.consulta.__Secion.setText("");
        this.consulta.__min.setText("");
        this.consulta.__max.setText("");
        this.consulta.__OrdenP.setText("");
        this.consulta.__proveedor.setText("");//
        this.consulta.__Cliente.setText("");
        this.consulta.__folio.setText("");
        this.consulta.__foliohasta.setText("");
        this.consulta.__documento.setText("");
        this.consulta.__OrdenC.setText("");
        this.consulta.__TipoEntrada.setText("");
        this.consulta.__TipoSalida.setText("");
        this.consulta.__Propietario.setText("");
        this.consulta.__chkTurno1.setSelected(false);
        this.consulta.__chkTurno2.setSelected(false);
        this.consulta.__chkTurno3.setSelected(false);
        this.consulta.__Observaciones.setText("");
        this.consulta.__Solicitante.setText("");
        this.consulta.__dateIni.setDate(null);
        this.consulta.__datefin.setDate(null);
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
        __MENU_CONSULTA_EXISTENCIA,
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
        __SALIR_PRODUCTO,
        //NUEVO PROVEEDOR CLIENTE
        __ACEPTAR_PROVEEDOR,
        __CANCELAR_PROVEEDOR,
        //MOVIMIENTOS
        __ACEPTAR_ENTRADA,                             
        __MODIFICACION_ENTRADA,                
        __ACEPTAR_SALIDA,                
        __MODIFICACION_SALIDA,
        //REPORTES
        __INVENTARIO,
        __SALIDA,
        __ENTRADA,
        __STOCK_ARRIBA,
        __STOCK_ABAJO,
        __REPORTE_FINANZAS,
        __ACEPTAR_FINANZAS,
        __CANCELAR_FINANZAS,
        __OPTNINGUNO,
        __REGRESAR_REPORTES,
        __OPTSALIDA,
        __OPTENTRADA,
        __ACEPTARCONSULTA,
        __SALIR_CONSULTA,
        //VER CONSULTA
        __REGRESAR,
        __EXCEL,
        __IMPRIMIR,
        __CORREO,
        //CORREO
        __BUSCARARCHIVO,
        __ABRIREXCEL,
        __ACEPTARCORREO,
        __SALIRCORREO,
        //cerrar sesion
        __ACEPTAR_SESION,
        __CANCELAR_SESION
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(Accion.valueOf(e.getActionCommand())){
            case __ACEPTAR_FECHA:
                fechaini();
                break;
            case __INICIA_SESION:
                iniciasesion();
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
                    consulta.setEnabled(true);
                    movimientos.setEnabled(true);
                    fecha.setVisible(false);
                    this.reporte.setEnabled(false);
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
                saber=1;
                this.borrarFormularioAltaProducto();
                this.borrarFormularioConEP();
                this.borrarFormularioConsultas();
                this.borrarFormularioMovimientos();
                this.borrarFormularioNewUser();
                this.borrarFormularioProveedor();
                
                conEP.setVisible(true);
                conEP.setName("Consulta del Producto");
                this.bucarPorducto();
                
                break;
            case __MENU_MASTER_MOVIMIENTOS:                
                this.borrarFormularioAltaProducto();
                this.borrarFormularioConEP();
                this.borrarFormularioConsultas();
                this.borrarFormularioMovimientos();
                this.borrarFormularioNewUser();
                this.borrarFormularioProveedor();
                movimientos.setTitle("Movimientos del Producto");
                menumaster.dispose();
                if(cargo !=1 ){
                    this.movimientos.__MODIFICACIONENTRADA.setEnabled(false);
                    this.movimientos.__MODIFICACIONSALIDA.setEnabled(false);
                }else{
                    this.movimientos.__MODIFICACIONENTRADA.setEnabled(true);
                    this.movimientos.__MODIFICACIONSALIDA.setEnabled(true);
                }
                movimientos.setVisible(true);
                movimientos.setLocationRelativeTo(null);
                movimientos.setDefaultCloseOperation(0);                
                maximoentrada();
                maximosalida();
                ponerfecha();
                addItems("movimientos");                
                break;
            case __MENU_MASTER_REPORTES:
                this.borrarFormularioAltaProducto();
                this.borrarFormularioConEP();
                this.borrarFormularioConsultas();
                this.borrarFormularioMovimientos();
                this.borrarFormularioNewUser();
                this.borrarFormularioProveedor();
                menumaster.dispose();
                reporte.setVisible(true);
                reporte.setLocationRelativeTo(null);
                reporte.setTitle("Reportes");
                break;
            case __MENU_MASTER_CONSULTAS:
                this.borrarFormularioAltaProducto();
                this.borrarFormularioConEP();
                this.borrarFormularioConsultas();
                this.borrarFormularioMovimientos();
                this.borrarFormularioNewUser();
                this.borrarFormularioProveedor();
                menumaster.dispose();
                this.addItems("consultas");
                consulta.setVisible(true);
                consulta.__optNinguno.requestFocus();
                consulta.setLocationRelativeTo(null);
                consulta.setTitle("Consultas");
                break;
            case __MENU_MASTER_CANCELAR:
                this.borrarFormularioAltaProducto();
                this.borrarFormularioConEP();
                this.borrarFormularioConsultas();
                this.borrarFormularioMovimientos();
                this.borrarFormularioNewUser();
                this.borrarFormularioProveedor();
                confir = this.mensajeConfirmacion("¿Desea Salir?","Salida");
                if (confir==JOptionPane.OK_OPTION){
                    menumaster.dispose();
                    menumaster.__ALTAPRODUCTO.setEnabled(true);
                    menumaster.__MOVIMIENTOS.setEnabled(true);
                    Calendar Cal= Calendar.getInstance();                                                  
                    String hora=Cal.get(Cal.HOUR_OF_DAY)<10 ? "0"+Cal.get(Cal.HOUR_OF_DAY) : ""+Cal.get(Cal.HOUR_OF_DAY);
                    String minute=Cal.get(Cal.MINUTE)<10 ? "0"+Cal.get(Cal.MINUTE) : ""+Cal.get(Cal.MINUTE);
                    horasalida = hora+":"+minute;                
                    boolean registrasalida=mimodelo.horasalida(horasalida,user);
                    if (registrasalida == true){                        
                        if(!user.equals("ROOT")){                            
                            try {                           
                                mimodelo.cerrarsesion(user);
                            } catch (SQLException ex) {
                                Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }                    
                    login.show();
                }
                break;
              //--MENUS DE TOODOS LOS FORMULARIOS
            case __MENU_ANTERIOR:                
                    regresar();
                break;            
            case __MENU_ALTA:
                switch(cargo){
                    case 1:
                        confir = mensajeConfirmacion("¿Realmente Deseas ir a Alta de Producto?","Salida");
                            if (confir == JOptionPane.OK_OPTION){
                                conEP.setVisible(true);                            
                                conEP.setLocationRelativeTo(null);
                                movimientos.dispose();
                                consulta.dispose();
                                reporte.dispose();
                                reporteu.dispose();
//                            verconsulta.dispose();                                
                                this.bucarPorducto();
                            }                                                                                        
                    break;                        
                    case 2:
                        confir = mensajeConfirmacion("¿Realmente Deseas ir a Alta de Producto?","Salida");
                            if (confir == JOptionPane.OK_OPTION){
                                conEP.setVisible(true);                            
                                conEP.setLocationRelativeTo(null);
                                movimientos.dispose();
                                consulta.dispose();
                                reporte.dispose();
                                reporteu.dispose();
//                            verconsulta.dispose();                                
                                this.bucarPorducto();
                            }
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
                            if(cargo !=1 ){
                                this.movimientos.__MODIFICACIONENTRADA.setEnabled(false);
                                this.movimientos.__MODIFICACIONSALIDA.setEnabled(false);
                            }else{
                                this.movimientos.__MODIFICACIONENTRADA.setEnabled(true);
                                this.movimientos.__MODIFICACIONSALIDA.setEnabled(true);
                            }
                            movimientos.setVisible(true);
                            movimientos.setLocationRelativeTo(null);
                            consulta.dispose();                        
                            newP.dispose();
                            reporte.dispose();
                            ponerfecha();
                            movimientos.__documentoEntr.requestFocus();
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
                        confir = mensajeConfirmacion("¿Realmente Deseas ir a Consultas?","Salida");
                        if (confir == JOptionPane.OK_OPTION){
                            this.addItems("consultas");
                            consulta.setVisible(true);
                            consulta.setLocationRelativeTo(null);
                            newP.dispose();
                            reporte.dispose();
                            movimientos.dispose();
                            reporteu.dispose();
//                            verconsulta.dispose();
                        }                                        
                break;
            case __MENU_REPORTES:             
                        confir = mensajeConfirmacion("¿Realmente Deseas ir a Generar un Reporte?","Salida");
                            if (confir == JOptionPane.OK_OPTION){
                            reporte.setVisible(true);
                            reporte.setLocationRelativeTo(null);
                            movimientos.dispose();
                            newP.dispose();
                            consulta.dispose();
                            reporteu.dispose();
//                            verconsulta.dispose();
                            }                        
                break;
            case __MENU_CERRAR_SESION:
                    confir = mensajeConfirmacion("¿Realmente Desea Cerrar la Sesión?","Salida");
                    if (confir == JOptionPane.OK_OPTION){
                        try {
                            login.setVisible(true);
                            login.setLocationRelativeTo(null);
                            newP.dispose();
                            movimientos.dispose();
                            menumaster.__ALTAPRODUCTO.setEnabled(true);
                            menumaster.__MOVIMIENTOS.setEnabled(true);
                            consulta.dispose();
                            reporte.dispose();
                            reporteu.dispose();                            
                            mimodelo.cerrarsesion(user);
                            borrarFormularioNewUser();
//                            borrarFormularioAltaPapel();
//                            borrarFormularioMovimientosPapel();
                            borrarFormularioProveedor();
                            
//                            borrarFormularioEmergente();
                            if(!user.equals("ROOT")){
                                mimodelo.cerrarsesion(user);
                            }
                        } catch (SQLException ex) {
                            mensaje(3,ex.getMessage());
                        }
                    }
                    this.borrarFormularioAltaProducto();
                    this.borrarFormularioConEP();
                    this.borrarFormularioConsultas();
                    this.borrarFormularioNewUser();
                    this.borrarFormularioMovimientos();
                    this.borrarFormularioProveedor();
                break;
            case __MENU_SALIR:
                confir = mensajeConfirmacion("¿Realmente Desea Salir del Sistema?","Salida");
                            if (confir == JOptionPane.OK_OPTION){
                            if(!user.equals("ROOT")){
                                try {
                                    mimodelo.cerrarsesion(user);
                                } catch (SQLException ex) {
                                    Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            SalirSistema();
                            }
                break;
            case __MENU_FECHA:
                    pedirfecha=1;
                    newP.setEnabled(false);
                    movimientos.setEnabled(false);
                    reporte.setEnabled(false);
                    consulta.setEnabled(false);
                    fecha.setVisible(true);
                    fecha.setLocationRelativeTo(null);
                break;
            case __MENU_NEWUSER:
                Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);  
                    newU.__etqBloqMayus.setVisible(true);
                        if(cargo == 1){
                            newP.setEnabled(false);
                            movimientos.setEnabled(false);
                            reporte.setEnabled(false);
                            consulta.setEnabled(false);
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
                    reporte.setEnabled(false);
                    consulta.setEnabled(false);
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
                                reporte.setEnabled(false);
                                consulta.setEnabled(false);
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
                mensaje(1,"rawMats By: =F@vo0R!to0= && yo0po0");
                break;
            case __MENU_BACKUP:                 
                        mimodelo.bp(fech+"desdemenu");
                        this.enviaarchivo("C:\\rawmats\\backups\\dump"+fech+"desdemenu.sql","rawmats2014@gmail.com" ,"Backup de la base de datos");
                        File fichero = new File("C:\\rawmats\\backups\\dump"+fech+"desdemenu.sql");
                        fichero.delete();
                        mensaje(1,"Backup de la Base de Datos Correcta");                    
                break;
            case __MENU_DATOS:
                this.mimodelo.abrirReporte("Listado.jrxml",new HashMap());
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
            case __MENU_CONSULTA_EXISTENCIA:
                movimientos.setEnabled(false);
                saber=2;
                conEP.setVisible(true);
                conEP.setName("Consulta del Producto");
                if(saber==2){                    
                    conEP.__MODIFICAR.setEnabled(false);
                    conEP.__ACEPTARNP.setEnabled(false);
                }
                this.bucarPorducto();
                break;
//            case __MENU_BAJA_PRODUCTO:
//                switch(cargo){
//                        case 1:  
//                                newP.setEnabled(false);
//                                movimientos.setEnabled(false);
//                                reportes.setEnabled(false);
//                                consultas.setEnabled(false);
//                                bajaP.setVisible(true);
//                                bajaP.setLocationRelativeTo(null);
//                            break;
//                        case 2:
//                            mensaje(2,"No Hay Acceso a esta Información");
//                            break;
//                        case 3:
//                            mensaje(2,"No Hay Acceso a esta Información");
//                            break;
//                    } 
//                break;
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
                reporte.setEnabled(true);
                consulta.setEnabled(true);
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
                    reporte.setEnabled(true);
                    consulta.setEnabled(true);
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
                                    consulta.setEnabled(true);
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
                newP.setTitle("Alta de Producto");
                break;
            case __MODIFICARCONSULTAEP:
                modificaproducto=1;
                int fila = this.conEP.__tablaProductos.getSelectedRow();
                if(fila<0){
                    mensaje(2,"Selecciona un producto");
                    return;
                }
                try{
                    ResultSet buscarArea = mimodelo.buscarArea();
                    newP.__cmbArea.removeAllItems();
                    newP.__cmbArea.addItem("Selecciona...");
                    while(buscarArea.next()){
                        newP.__cmbArea.addItem(buscarArea.getString(1));
                    }
                    ResultSet buscarMaquina = mimodelo.buscarMaquina();
                    newP.__cmbMaquina.removeAllItems();
                    newP.__cmbMaquina.addItem("Selecciona...");
                    while(buscarMaquina.next()){
                        newP.__cmbMaquina.addItem(buscarMaquina.getString(1));
                    } 
                }catch(Exception j){
                            
                }
                String clavemod = this.conEP.__tablaProductos.getValueAt(fila, 0).toString();
                confir = mensajeConfirmacion("¿Realmente Deseas Modificar el Producto "+clavemod+"?","Modificar");
                    if (confir == JOptionPane.OK_OPTION){
                        try {
                            ResultSet producto = mimodelo.buscarProducto(clavemod);
                            while(producto.next()){
                                newP.__cmbArea.setEnabled(true);
                                newP.__cmbMaquina.setEnabled(true);
                                
                                
                                String clavemodi =producto.getString("clave");
                                if(clavemodi.contains("RF-")){
                                    newP.__Refacciones.setSelected(true);
                                }else if(clavemodi.contains("MP-")){
                                    newP.__Refacciones.setSelected(true);
                                }
                                newP.__cmbArea.setSelectedItem(producto.getString("area"));
                                newP.__cmbMaquina.setSelectedItem(producto.getString("maquina"));
                                newP.__descripcion.setText(producto.getString("descripcion"));
                                newP.__SMin_.setText(producto.getString("min"));
                                newP.__SMax_.setText(producto.getString("max"));
                                newP.__etqClave.setText(producto.getString("clave"));
                                newP.__Ubicacion.setText(producto.getString("ubicacion"));
                                newP.__cmbum.setSelectedItem(producto.getString("unidadM"));
                            }
                            conEP.dispose();
                            newP.setVisible(true);        
                        } catch (SQLException ex) {
                            Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    newP.__cmbArea.setEnabled(false);
                    newP.__cmbMaquina.setEnabled(false);
                    newP.__etqNewArea.setEnabled(false);
                    newP.__etqNewMaquina.setEnabled(false);
                    newP.__Refacciones.setEnabled(false);
                    newP.__MateriaPrima.setEnabled(false);
                break;
            case __ELIMINARCONSULTAEP:
                break;            
            case __REGRESARCONSULTAEP:
                if(saber==1){
                    regresar();
                }else{
                    conEP.dispose();
                    movimientos.setEnabled(true);
                    movimientos.setVisible(true);                    
                    conEP.__MODIFICAR.setEnabled(true);
                    conEP.__ACEPTARNP.setEnabled(true);
                    saber=1;
                }
                break;
                //FIN DE LOS BOTONES DE LA CONSULTA INICIAL DE LA EXISTENCIA DEL PRODUCTO
                //ALTA DE PRODUCTOS
            case __GUARDAR_PRODUCTO:
                int areaid = this.busquedaid("area");
                int maquinaid = this.busquedaid("maquina");
                if(!newP.__Refacciones.isSelected()&&!newP.__MateriaPrima.isSelected()){
                    mensaje(3,"Selcciona el tipo de Producto");
                    return;
                }
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
                DesP = DesP.replaceAll("\n", "");
                DesP = DesP.replaceAll("\t", "");
                if(DesP.equals("")){
                    mensaje(3,"Debes Colocar una Descripcion al Producto");
                    this.newP.__descripcion.requestFocus();
                    return;
                }
                if(!this.newP.__SMin_.getText().isEmpty()){
                    Min = Double.parseDouble(this.newP.__SMin_.getText());
                }else{
                    mensaje(3,"Debes Poner Un Minimo Mayor a 0");
                    this.newP.__SMin_.requestFocus();
                    return;
                }
                if(!this.newP.__SMax_.getText().isEmpty()){
                    Max = Double.parseDouble(this.newP.__SMax_.getText());
                }else{
                     mensaje(3,"Debes Poner Un Maximo Mayor a 0");
                     this.newP.__SMax_.requestFocus();
                     return;
                }
                if(Min>Max){
                    mensaje(3,"El Maximo No Puede ser Menor que el Minimo");
                     this.newP.__SMax_.requestFocus();
                     return;
                }
                String ubicacion1 = newP.__Ubicacion.getText();
                if(ubicacion1.isEmpty()){
                    mensaje(3,"Debes Colocar una Ubicacion al Producto");
                    this.newP.__Ubicacion.requestFocus();
                    return;
                }
                String um = newP.__cmbum.getSelectedItem().toString();
                if(um.equals("Selecciona...")){
                    mensaje(3,"Debes Seeccionar una unidad de medida");
                    this.newP.__cmbum.requestFocus();
                    return;
                }
                String clave = newP.__etqClave.getText();
                confir= mensajeConfirmacion("La clave de producto " + clave+" es correcta?" ,"Aceptar");
                
                if(confir==JOptionPane.OK_OPTION){
                    if(modificaproducto==0){
                        if(mimodelo.nuevoProducto(areaid, maquinaid, clave,DesP, Max, Min)){
                            if(mimodelo.nuevaexistencia(clave,ubicacion1,um)){
                                mensaje(1,"Alta de producto correcta");
                                borrarFormularioAltaProducto();
                            }
                        }
                    }else{
                        mimodelo.updateProducto(areaid, maquinaid, clave,DesP, Max, Min,ubicacion1,um);
                        mimodelo.updateProducto(clave, DesP);
                        mensaje(1,"Modificacion de producto correcta");
                        borrarFormularioAltaProducto();
                    }
                }
                break;
            case __BORRAR_PRODUCTO:
                borrarFormularioAltaProducto();
                break;
            case __SALIR_PRODUCTO:               
                regresar();
                break;
            case  __ACEPTAR_PROVEEDOR:
                altaproveedor();                             
                break;
            case __CANCELAR_PROVEEDOR: 
                if(NewPC.__NombreProveedor.getText().isEmpty()&&NewPC.__DireccionProveedor.getText().isEmpty()&&NewPC.__RFC.getText().isEmpty()&&NewPC.__TelefonoNProveedor.getText().isEmpty() ){
                        borrarFormularioProveedor();
                        movimientos.setEnabled(true);
                        NewPC.dispose();
                        movimientos.setVisible(true);
                }else
                confir= mensajeConfirmacion("Realmente Desea Salir","Cancelar");
                    if(confir==JOptionPane.OK_OPTION){
                        borrarFormularioProveedor();
                        movimientos.setEnabled(true);
                        NewPC.dispose(); 
                        movimientos.setVisible(true);
                    }
                break;
            case __ACEPTAR_ENTRADA:
                EntradaAceptarModificar(); 
                break;                
            case __MODIFICACION_ENTRADA:
                buscarfolio = JOptionPane.showInputDialog("Folio","Ingresa el Folio de la Entrada a Modificar");
                if(buscarfolio==null || buscarfolio.length()<3){
                    mensaje(2,"Intenta otra vez");
                    break;
                }
                ResultSet entrada = mimodelo.buscarEntrada(buscarfolio);
                int idfolio=0;
                try {
                    if(entrada.next()){
                        System.out.println(buscarfolio);
                        movimientos.__FolioEntrada.setText(buscarfolio);
                        entrada.beforeFirst();
                        while(entrada.next()){  
                            movimientos.__TipoEntrada.setText(this.busquedaNombre("tipo_entrada", entrada.getInt("tipoE")));
                            movimientos.__PropietarioEntr.setText(this.busquedaNombre("id_propietario", entrada.getInt("propietarioe")));
                            movimientos.__ClientEntr.setText(this.busquedaNombre("cliente", entrada.getInt("cliente")));
                            movimientos.__ProvEntr.setText(this.busquedaNombre("id_proveedores", entrada.getInt("provedorE")));
                            movimientos.__OrdenProduccionEntr.setText(entrada.getString("op"));
                            movimientos.__OrdenCompraEntr.setText(entrada.getString("oc"));
                            movimientos.__documentoEntr.setText(entrada.getString("documentoe"));
                            if(entrada.getString("T1").equals("t1")){
                                movimientos.__chkTurno1Entr.setSelected(true);
                            }
                            if(entrada.getString("T2").equals("t2")){
                                movimientos.__chkTurno2Entr.setSelected(true);
                            }
                            if(entrada.getString("T3").equals("t3")){
                                movimientos.__chkTurno3Entr.setSelected(true);
                            }
                            idfolio= entrada.getInt("identrada");
                        }
                        this.movimientos.__MODIFICACIONENTRADA.setEnabled(false);
                        this.movimientos.__Archivo.setEnabled(false);
                        this.movimientos.__Edicion.setEnabled(false);
                        this.movimientos.JPanel.setEnabledAt(1, false);
                        ResultSet detalleentrada = mimodelo.buscarDetalleEntrada(idfolio);
                        if(detalleentrada.next()){
                            detalleentrada.beforeFirst();
                            int d =0;
                            while(detalleentrada.next()){
                                identradas[d]=detalleentrada.getString("iddetalleentrada");
                                movimientos.__tablaEntrada.setValueAt(detalleentrada.getString("claveProducto"), d, 0);
                                movimientos.__tablaEntrada.setValueAt(detalleentrada.getString("descripcion"), d, 1);
                                movimientos.__tablaEntrada.setValueAt(detalleentrada.getString("ubicacion"), d, 2);
                                movimientos.__tablaEntrada.setValueAt(Double.parseDouble(detalleentrada.getString("cantidad")), d, 3);
                                movimientos.__tablaEntrada.setValueAt(detalleentrada.getString("unidadMedida"), d, 4);
                                movimientos.__tablaEntrada.setValueAt(Double.parseDouble(detalleentrada.getString("costo")), d, 5);
                                movimientos.__tablaEntrada.setValueAt(Double.parseDouble(detalleentrada.getString("totalcosto")), d, 6);
                                //mimodelo.updateteporalde(0,identradas[d]);
                                //mimodelo.sumarexistencia(detalleentrada.getString("claveproducto"));
                                ResultSetMetaData metaData = detalleentrada.getMetaData();
                                int numcol = metaData.getColumnCount();
                                nombrecolumnas = new String[numcol];
                                for(int i=2;i<numcol;i++){
                                    this.nombrecolumnas[i]=metaData.getColumnLabel(i+1);
                                }
                                d++;
                            }
                        }
                        this.modificarentrada=1;
                    }else{
                        mensaje(2,"La Entrada no Existe");
                        break;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case __ACEPTAR_SALIDA:
                SalidaAceptarModificar();
                break;
            case __MODIFICACION_SALIDA:
                buscarfolio = JOptionPane.showInputDialog("Folio","Ingresa el Folio de la Salida a Modificar");
                if(buscarfolio==null || buscarfolio.length()<3){
                    mensaje(2,"Intenta otra vez");
                    SalidaMovimientos=0;
                    break;
                }
                try {
                   ResultSet salida = mimodelo.buscarSalida(buscarfolio); 
                   int iddfolio=0;
                   if(salida.next()){
                       movimientos.__FolioSalida.setText(buscarfolio);
                       salida.beforeFirst();
                       while(salida.next()){
                           movimientos.__TipoSalida.setText(salida.getString("tipo_salida"));
                           movimientos.__documentoSalida.setText(salida.getString("documento"));
                           movimientos.__OrdenProduccionSalida.setText(salida.getString("orden_produccion"));
                           movimientos.__SolicitanteSalida.setText(salida.getString("solicitante"));
                           movimientos.__AreaSalida.setText(salida.getString("area"));
                           if(salida.getString("TURNO1").equals("t1")){
                               movimientos.__chkTurno1Salida.setSelected(true);
                           }
                           if(salida.getString("TURNO2").equals("t2")){
                               movimientos.__chkTurno2Salida.setSelected(true);
                           }
                           if(salida.getString("TURNO3").equals("t3")){
                               movimientos.__chkTurno3Salida.setSelected(true);
                           }
                           iddfolio= salida.getInt("id_salida");
                       }
                       this.movimientos.jMenuBar1.setEnabled(false);
                       this.movimientos.__MODIFICACIONSALIDA.setEnabled(false);
                       this.movimientos.__Archivo.setEnabled(false);
                       this.movimientos.__Edicion.setEnabled(false);
                        this.movimientos.JPanel.setEnabledAt(0, false);
                        this.movimientos.JPanel.setEnabledAt(1, true);
                   }else{
                       mensaje(2,"La Salida no Existe");
                       break;
                   }
                   ResultSet detallesalida = mimodelo.buscarDetalleSalida(iddfolio);
                   if(detallesalida.next()){
                       detallesalida.beforeFirst();
                       int a=0;
                       while(detallesalida.next()){
                           idsalidas[a]=detallesalida.getString("iddetallesalida");
                            movimientos.__tablaSalida.setValueAt(detallesalida.getString("claveproduto"), a, 0);
                            movimientos.__tablaSalida.setValueAt(detallesalida.getString("descripcion"), a, 1);
                            movimientos.__tablaSalida.setValueAt(detallesalida.getString("ubicacion"), a, 2);
                            movimientos.__tablaSalida.setValueAt(Double.parseDouble(detallesalida.getString("cantidad_salida")), a, 3); 
                            String entradas=  detallesalida.getString("entradas");
                            this.antipeps3(entradas);
                            movimientos.__tablaSalida.setValueAt(detallesalida.getString("unidad"), a, 4);
                            mimodelo.sumarexistencia(detallesalida.getString("claveproduto"));
                           a++;
                       }
                   }
                   movimientos.__TipoSalida.requestFocus();
                   SalidaMovimientos=1;
                }catch(Exception ex){
                    //mensaje(3,ex.getMessage());
                    ex.printStackTrace();
                }
                break;
            case __INVENTARIO:
                
                this.mimodelo.abrirReporte("inventario.jrxml",new HashMap());
                break;              
            case __SALIDA:
                String SL = JOptionPane.showInputDialog(null,"Ingresa la Fecha Inicial(aaaa-mm-dd)");
                map.put("ingresa", SL);
                String SLF = JOptionPane.showInputDialog(null,"Ingresa la Fecha Final(aaaa-mm-dd)");
                map.put("final", SLF);
                if(!SL.equals("") && !SLF.equals("")){
                    mensaje(1,"Generando Salidas entre las Fechas "+SL+" A "+SLF );
                    this.mimodelo.abrirReporte("SalidasIguales.jrxml",map);                    
                }else if(!SL.equals("") && SLF.equals("")){
                    mensaje(1,"Generando Salidas Mayores o Iguales a la Fecha "+SL);
                    this.mimodelo.abrirReporte("SalidasMayores.jrxml",map);                    
                }else if(SL.equals("") && !SLF.equals("")){
                    mensaje(1,"Generando Salidas Menores o Iguales a la Fecha "+SLF);
                    this.mimodelo.abrirReporte("SalidasMenores.jrxml",map);                    
                }else{
                    mensaje(2,"No hay Parametros de Busqueda, No se Creara el Reporte");  
                }                                                
                break;        
            case __ENTRADA:
                String EN = JOptionPane.showInputDialog(null,"Ingresa la Fecha Inicial(aaaa-mm-dd)");
                map.put("INICIO", EN);
                String ENF = JOptionPane.showInputDialog(null,"Ingresa la Fecha Final(aaaa-mm-dd)");
                map.put("FIN", ENF);
                if(!EN.equals("") && !ENF.equals("")){
                    mensaje(1,"Generando Entradas Entre las Fechas "+EN+" A "+ENF );
                    this.mimodelo.abrirReporte("EntradaEntre.jrxml",map);                    
                }else if(!EN.equals("") && ENF.equals("")){
                    mensaje(1,"Generando Entradas Mayores o Iguales a la Fecha "+EN);
                    this.mimodelo.abrirReporte("EntradaDesde.jrxml",map);                    
                }else if(EN.equals("") && !ENF.equals("")){
                    mensaje(1,"Generando Entradas Menores o Iguales a la Fecha "+ENF);
                    this.mimodelo.abrirReporte("EntradaHasta.jrxml",map);                    
                }else{
                    mensaje(2,"No hay Parametros de Busqueda, No se Creara el Reporte");  
                }                
                break;
            case __STOCK_ARRIBA:                
                mensaje(1,"Generando Reporte Mayores al Stock Minimo");
                this.mimodelo.abrirReporte("ArribaStock.jrxml",map);                
                break;
            case __STOCK_ABAJO:
                mensaje(1,"Generando Reporte Menores al Stock Maximo");
                this.mimodelo.abrirReporte("AbajoStock.jrxml",map);  
                break; 
            case __REPORTE_FINANZAS:
                this.Finanzas.setVisible(true);                
                this.reporte.setEnabled(false);                
                break;
            case __ACEPTAR_FINANZAS:
                String ClaveFinanzas=this.Finanzas.__clave.getText();
                String Descripcion=this.Finanzas.__descripcion.getText();
                Date fechainii=null,fechafinn=null;
                map.put("clave",ClaveFinanzas);
                map.put("descripcion",Descripcion);
                String RFI,RFF;
                if(aceptarFecha(this.Finanzas.__dateIni,1)==null){
                    RFI="";
                }else{
                    RFI= this.aceptarFecha(this.Finanzas.__dateIni,1).toString();
                    fechainii = Finanzas.__dateIni.getCalendar().getTime();
                    map.put("inicial",RFI);
                }
                if(aceptarFecha(this.Finanzas.__datefin,1)==null){
                    RFF="";
                }else{
                    RFF = this.aceptarFecha(this.Finanzas.__datefin,1).toString();
                    map.put("final",RFF);
                    fechafinn=  Finanzas.__datefin.getCalendar().getTime();
                }
                if(fechafinn!=null&&fechainii!=null&&fechafinn.before(fechainii)){
                    mensaje(3,"La Fecha Inicial No Puede se Mayor a Fecha Final");
                    return;
                }
                if(ClaveFinanzas.isEmpty()){
                    if(Descripcion.isEmpty()){
                        if(RFI.equals("")){
                            if(RFF.equals("")){
                                //No Hay Datos de Busqueda
                                mensaje(2,"No hay Parametros de Busqueda, No se Creara el Reporte");  
                            }else{
                                //Solo Busca Por fecha Final
                                mensaje(1,"Busqueda por: Fecha Final "+RFF);
                                this.mimodelo.abrirReporte("RFinanzasFFinal.jrxml",map);
                            }
                        }else{
                            if(RFF.equals("")){
                                //Solo Tiene Fecha Inicial
                                mensaje(1,"Busqueda por: Fecha Inicial "+RFI);
                                this.mimodelo.abrirReporte("RFinanzasFInicial.jrxml",map);
                            }else{
                             //Tiene Fecha Inicial Y Fecha Final
                                mensaje(1,"Busqueda por: Fecha Inicial "+RFI+", Fecha Final "+RFF);
                                this.mimodelo.abrirReporte("RFinanzasFInicialFFinal.jrxml",map);
                            }                            
                        }
                    }else{
                        if(RFI.equals("")){
                            if(RFF.equals("")){
                                ////Aqui Solo Tenemos Descripcion
                                mensaje(1,"Busqueda por: Descripción "+Descripcion);
                                this.mimodelo.abrirReporte("RFinanzasDescripcion.jrxml",map);
                            }else{
                                mensaje(1,"Busqueda por: Descripción "+Descripcion+" y Fecha Final: "+RFF);
                                this.mimodelo.abrirReporte("RFinanzasDescripcionFFinal.jrxml",map);
                                //Aqui Solo Tengo Descripcion ff
                            }
                        }else{
                            if(RFF.equals("")){
                                mensaje(1,"Busqueda por: Descripción "+Descripcion+" y Fecha Inicial: "+RFI);
                                this.mimodelo.abrirReporte("RFinanzasDescripcionFInicial.jrxml",map);
                                //Aqui Solo Tenemos Descripcion Y Fecha Inicial
                            }else{
                                mensaje(1,"Busqueda por: Descripción "+Descripcion +", Fecha Inicial: "+RFI+", Fecha Final: "+RFF);
                                this.mimodelo.abrirReporte("RFinanzasDescripcionFInicialFFinal.jrxml",map);
                                //Aqui Tenemos Descripcion Fecha Inicial Y Fecha Final
                            }
                        }
                    }
                }else{
                    if(Descripcion.isEmpty()){
                        if(RFI.equals("")){
                            if(RFF.equals("")){
                                mensaje(1,"Busqueda por: Clave "+ClaveFinanzas);
                                this.mimodelo.abrirReporte("RFinanzasClave.jrxml",map);
                                //Aqui Solo Tengo Clave 
                            }else{
                                mensaje(1,"Busqueda por: Clave "+ClaveFinanzas+", Fecha Final: "+RFF);
                                this.mimodelo.abrirReporte("RFinanzasClaveFFinal.jrxml",map);
                                //Aqui Solo Tengo Clave Y fi
                            }
                        }else{
                            if(RFF.equals("")){
                                mensaje(1,"Busqueda por: Clave "+ClaveFinanzas);
                                this.mimodelo.abrirReporte("RFinanzasClave.jrxml",map);
                                //Aqui Solo Tengo Clave 
                            }else{
                                mensaje(1,"Busqueda por: Clave "+ClaveFinanzas+", Fecha Inicial: "+RFI+", Fecha Final: "+RFF);
                                this.mimodelo.abrirReporte("RFinanzasClaveFInicialFFinal.jrxml",map);
                                //Aqui Solo Tengo Clave Y fi ff
                            }
                        }
                    }else{
                        if(RFI.equals("")){
                            if(RFF.equals("")){
                                mensaje(1,"Busqueda por: Descripción "+Descripcion);
                                this.mimodelo.abrirReporte("RFinanzasDescripcion.jrxml",map);
                                //Aqui Solo Tengo Descripcion
                            }else{
                                mensaje(1,"Busqueda por: Descripción "+Descripcion+" y Fecha Final: "+RFF);
                                this.mimodelo.abrirReporte("RFinanzasDescripcionFFinal.jrxml",map);
                                //Aqui Solo Tengo Descripcion ff
                            }
                        }else{
                            if(RFF.equals("")){
                                mensaje(1,"Busqueda por: Descripción "+Descripcion+" y Fecha Inicial: "+RFI);
                                this.mimodelo.abrirReporte("RFinanzasDescripcionFInicial.jrxml",map);
                                //Aqui Solo Tengo Descripcion Fecha Inicial
                            }else{
                                mensaje(1,"Busqueda por: Descripción "+Descripcion+", Fecha Inicial: "+RFI+", Fecha Final: "+RFF);
                                this.mimodelo.abrirReporte("RFinanzasDescripcionFInicialFFinal.jrxml",map);
                               // Aqui Solo Tengo Descripcion Fecha Inicial ff
                            }
                        }
                    }
                }                   
                break;
            case __CANCELAR_FINANZAS:
                Finanzas.__clave.setText("");
                Finanzas.__descripcion.setText("");
                this.Finanzas.__dateIni.setDate(null);
                this.Finanzas.__datefin.setDate(null);                
                reporte.setEnabled(true);
                reporte.setVisible(true);
                Finanzas.setVisible(false);                
                break;
            case __OPTNINGUNO:
                //area, maquina, clave, descripcion, max, min, costopromedio, existencia, ubicacion, op <---
                this.consulta.__clave.setEnabled(true);
                this.consulta.__chkClave.setEnabled(true);
                this.consulta.__cmbUnidad.setEnabled(true);
                this.consulta.__chkUnidad.setEnabled(true);
                this.consulta.__Cantidad.setEnabled(true);
                this.consulta.__chkCantidad.setEnabled(true);
                this.consulta.__costo.setEnabled(true);
                this.consulta.__chkCosto.setEnabled(true);
                this.consulta.__Descripcion.setEnabled(true);
                this.consulta.__chkDescripcion.setEnabled(true);
                this.consulta.__proveedor.setEnabled(false);
                this.consulta.__chkProveedor.setEnabled(false);
                this.consulta.__Propietario.setEnabled(false);
                this.consulta.__chkPropietario.setEnabled(false);
                this.consulta.__Cliente.setEnabled(false);
                this.consulta.__chkCliente.setEnabled(false);
                this.consulta.__folio.setEnabled(false);
                this.consulta.__chkFolio.setEnabled(false);
                this.consulta.__foliohasta.setEnabled(false);
                this.consulta.__documento.setEnabled(false);
                this.consulta.__chkDocumento.setEnabled(false);
                this.consulta.__OrdenP.setEnabled(true);
                this.consulta.__chkOrdenP.setEnabled(true);
                this.consulta.__OrdenC.setEnabled(false);
                this.consulta.__chkOrdenC.setEnabled(false);
                this.consulta.__Ubicacion.setEnabled(true);
                this.consulta.__chkUbicacion.setEnabled(true);
                this.consulta.__dateIni.setEnabled(false);
                this.consulta.__chkFechaIni.setEnabled(false);
                this.consulta.__datefin.setEnabled(false);
                this.consulta.__chkFechaFin.setEnabled(false);
                this.consulta.__Area.setEnabled(true);
                this.consulta.__chkArea.setEnabled(true);
                this.consulta.__Secion.setEnabled(true);
                this.consulta.__chkMaquina.setEnabled(true);
                this.consulta.__TipoEntrada.setEnabled(false);
                this.consulta.__chkTipoEntrada.setEnabled(false);
                this.consulta.__TipoSalida.setEnabled(false);
                this.consulta.__chkTipoSalida.setEnabled(false);
                this.consulta.__max.setEnabled(true);
                this.consulta.__chkMax.setEnabled(true);
                this.consulta.__min.setEnabled(true);
                this.consulta.__chkMin.setEnabled(true);
                this.consulta.__chkTurno1.setEnabled(false);
                this.consulta.__chkTurno2.setEnabled(false);
                this.consulta.__chkTurno3.setEnabled(false);
                this.consulta.__chkObservaciones.setEnabled(false);
                this.consulta.__Observaciones.setEnabled(false);
                this.consulta.__Solicitante.setEnabled(false);
                this.consulta.__chkSolicitante.setEnabled(false);
                break;
            case __OPTENTRADA:
                this.consulta.__clave.setEnabled(true);
                this.consulta.__chkClave.setEnabled(true);
                this.consulta.__cmbUnidad.setEnabled(true);
                this.consulta.__chkUnidad.setEnabled(true);
                this.consulta.__Cantidad.setEnabled(true);
                this.consulta.__chkCantidad.setEnabled(true);
                this.consulta.__costo.setEnabled(true);
                this.consulta.__chkCosto.setEnabled(true);
                this.consulta.__Descripcion.setEnabled(true);
                this.consulta.__chkDescripcion.setEnabled(true);
                this.consulta.__proveedor.setEnabled(true);
                this.consulta.__chkProveedor.setEnabled(true);
                this.consulta.__Propietario.setEnabled(true);
                this.consulta.__chkPropietario.setEnabled(true);
                this.consulta.__Cliente.setEnabled(true);
                this.consulta.__chkCliente.setEnabled(true);
                this.consulta.__folio.setEnabled(true);
                this.consulta.__chkFolio.setEnabled(true);
                this.consulta.__foliohasta.setEnabled(true);
                this.consulta.__documento.setEnabled(true);
                this.consulta.__chkDocumento.setEnabled(true);
                this.consulta.__OrdenP.setEnabled(true);
                this.consulta.__chkOrdenP.setEnabled(true);
                this.consulta.__OrdenC.setEnabled(true);
                this.consulta.__chkOrdenC.setEnabled(true);
                this.consulta.__Ubicacion.setEnabled(true);
                this.consulta.__chkUbicacion.setEnabled(true);
                this.consulta.__dateIni.setEnabled(true);
                this.consulta.__chkFechaIni.setEnabled(true);
                this.consulta.__datefin.setEnabled(true);
                this.consulta.__chkFechaFin.setEnabled(true);
                this.consulta.__Area.setEnabled(true);
                this.consulta.__chkArea.setEnabled(true);
                this.consulta.__Secion.setEnabled(true);
                this.consulta.__chkMaquina.setEnabled(true);
                this.consulta.__TipoEntrada.setEnabled(true);
                this.consulta.__chkTipoEntrada.setEnabled(true);
                this.consulta.__TipoSalida.setEnabled(false);
                this.consulta.__chkTipoSalida.setEnabled(false);
                this.consulta.__max.setEnabled(false);
                this.consulta.__chkMax.setEnabled(false);
                this.consulta.__min.setEnabled(false);
                this.consulta.__chkMin.setEnabled(false);
                this.consulta.__chkTurno1.setEnabled(true);
                this.consulta.__chkTurno2.setEnabled(true);
                this.consulta.__chkTurno3.setEnabled(true);
                this.consulta.__chkObservaciones.setEnabled(true);
                this.consulta.__Observaciones.setEnabled(true);
                this.consulta.__Solicitante.setEnabled(false);
                this.consulta.__chkSolicitante.setEnabled(false);
                break;
            case __OPTSALIDA:
                this.consulta.__clave.setEnabled(true);
                this.consulta.__chkClave.setEnabled(true);
                this.consulta.__cmbUnidad.setEnabled(true);
                this.consulta.__chkUnidad.setEnabled(true);
                this.consulta.__Cantidad.setEnabled(true);
                this.consulta.__chkCantidad.setEnabled(true);
                this.consulta.__costo.setEnabled(true);
                this.consulta.__chkCosto.setEnabled(true);
                this.consulta.__Descripcion.setEnabled(true);
                this.consulta.__chkDescripcion.setEnabled(true);
                this.consulta.__proveedor.setEnabled(false);
                this.consulta.__chkProveedor.setEnabled(false);
                this.consulta.__Propietario.setEnabled(false);
                this.consulta.__chkPropietario.setEnabled(false);
                this.consulta.__Cliente.setEnabled(false);
                this.consulta.__chkCliente.setEnabled(false);
                this.consulta.__folio.setEnabled(true);
                this.consulta.__chkFolio.setEnabled(true);
                this.consulta.__foliohasta.setEnabled(true);
                this.consulta.__documento.setEnabled(true);
                this.consulta.__chkDocumento.setEnabled(true);
                this.consulta.__OrdenP.setEnabled(true);
                this.consulta.__chkOrdenP.setEnabled(true);
                this.consulta.__OrdenC.setEnabled(false);
                this.consulta.__chkOrdenC.setEnabled(false);
                this.consulta.__Ubicacion.setEnabled(true);
                this.consulta.__chkUbicacion.setEnabled(true);
                this.consulta.__dateIni.setEnabled(true);
                this.consulta.__chkFechaIni.setEnabled(true);
                this.consulta.__datefin.setEnabled(true);
                this.consulta.__chkFechaFin.setEnabled(true);
                this.consulta.__Area.setEnabled(true);
                this.consulta.__chkArea.setEnabled(true);
                this.consulta.__Secion.setEnabled(false);
                this.consulta.__chkMaquina.setEnabled(false);
                this.consulta.__TipoEntrada.setEnabled(false);
                this.consulta.__chkTipoEntrada.setEnabled(false);
                this.consulta.__TipoSalida.setEnabled(true);
                this.consulta.__chkTipoSalida.setEnabled(true);
                this.consulta.__max.setEnabled(false);
                this.consulta.__chkMax.setEnabled(false);
                this.consulta.__min.setEnabled(false);
                this.consulta.__chkMin.setEnabled(false);
                this.consulta.__chkTurno1.setEnabled(true);
                this.consulta.__chkTurno2.setEnabled(true);
                this.consulta.__chkTurno3.setEnabled(true);
                this.consulta.__chkObservaciones.setEnabled(true);
                this.consulta.__Observaciones.setEnabled(true);
                this.consulta.__Solicitante.setEnabled(true);
                this.consulta.__chkSolicitante.setEnabled(true);
                break;
            case __ACEPTARCONSULTA:
                String q="";
                //area, maquina, clave, descripcion, max, min, costopromedio, existencia, ubicacion, op <---
                String select = "SELECT ";
                String campos = "";
                String from =" FROM ";
                String tabla ="";
                String where =" WHERE ";
                String condiciones ="";
                
                //DESCRICPCION, PROVEEDOR,CLIENTE,FOLIO,DOCUMENTO,OP,OC,UBICA,CALVE,UNIDAD,CAMTIDAD,COSTO,AREA,TIPO ENTRADA, T1,T2,T3,FECHA INI FECHA FIN
                
                String descripcion = this.consulta.__Descripcion.getText();
                String clavepro = this.consulta.__clave.getText();
                String uM = (String) this.consulta.__cmbUnidad.getSelectedItem();
                String cantidad = this.consulta.__Cantidad.getText();
                String ubicacion = this.consulta.__Ubicacion.getText();
                String area = this.consulta.__Area.getText();
                String maquina = this.consulta.__Secion.getText();
                String minimo = this.consulta.__min.getText();
                String maximo = this.consulta.__max.getText();
                String op = this.consulta.__OrdenP.getText();
                String proveedor = this.consulta.__proveedor.getText();//
                String cliente = this.consulta.__Cliente.getText();
                String folio = this.consulta.__folio.getText();
                String foliohasta = this.consulta.__foliohasta.getText();
                String documento = this.consulta.__documento.getText();
                String oc = this.consulta.__OrdenC.getText();
                String tipoentrada = this.consulta.__TipoEntrada.getText();
                String tiposalida = this.consulta.__TipoSalida.getText();
                String propi = this.consulta.__Propietario.getText();
                String t1 = this.consulta.__chkTurno1.isSelected()?"t1":"";
                String t2 = this.consulta.__chkTurno2.isSelected()?"t2":"";
                String t3 = this.consulta.__chkTurno3.isSelected()?"t3":"";
                String fechaIni = this.aceptarFecha(consulta.__dateIni,1);
                String fechaFin = this.aceptarFecha(consulta.__datefin,1);
                String observaciones = this.consulta.__Observaciones.getText();
                String solicitante = this.consulta.__Solicitante.getText();
                
                if(this.consulta.__optNinguno.isSelected()){
                    tabla=" vw_descripcionproductos ";
                    //DESCRIPCION ,CLVE,UNIDAD,CANTIDAD,COSTO,UBICAION,AREA, MAQUINA,MINIMO, MAXIMO,OP
                    q+=select;
                    campos +=" * ";
                    campos= campos.replace(" * ", " clave,descripcion,area,maquina as seccion,ubicacion,op, max,min, existencia, unidadmedida, costopromedio as costo");                    
                    q+=campos;
                    q+=from;
                    q+=tabla;
                    if(!descripcion.isEmpty()){
                        condiciones += " and descripcion like '%"+descripcion+"%' ";
                    }
                    if(!clavepro.isEmpty()){
                        condiciones += " and clave='"+clavepro+"' ";
                    }
                    if(!uM.equals("Selecciona...")){
                        condiciones += " and unidadMedida='"+uM+"' ";
                    }
                    if(!cantidad.isEmpty()){
                        condiciones += " and existencia"+cantidad+" ";
                    }
                    if(!ubicacion.isEmpty()){
                        condiciones += " and ubicacion='"+ubicacion+"' ";
                    }
                    if(!area.isEmpty()){
                        condiciones += " and area='"+area+"' ";
                    }
                    if(!maquina.isEmpty()){
                        condiciones += " and maquina='"+maquina+"' ";
                    }
                    if(!op.isEmpty()){
                        condiciones += "and op='"+op+"' ";
                    }
                    if(!minimo.isEmpty()){
                        condiciones +=" and min"+minimo+"  ";
                    }
                    if(!maximo.isEmpty()){
                        condiciones +=" and max"+maximo+"  ";
                    }
                    //DESCRIPCION ,CLVE,UNIDAD,CANTIDAD,COSTO,UBICAION,AREA, MAQUINA,MINIMO, MAXIMO,OP
                    
                    if(!condiciones.equals("")){
                        q+=where;
                        condiciones = condiciones.substring(4);
                        condiciones = condiciones.substring(0,(condiciones.length()-1));
                        q+=condiciones;
                    }
                    
                }
                if(this.consulta.__optEntrada.isSelected()){
                    //folio,documento,op,oc,tipo_entrada,propietario,cliente,turno,observaciones,fecha,claveproducto,descripcion,ubicacion,cantidad,unidadMedida,costo,totalcosto
                    tabla=" vw_infoentrada ";
                    q+=select;
                    campos +=" * ";
                    campos= campos.replace(" * ", "folio, documento, op, oc, proveedor, tipo_entrada,propietario,cliente, turno, observaciones,fecha,  claveproducto as clave,descripcion,area,maquina as seccion,ubicacion, cantidad, unidadmedida,costo,totalcosto ");                    
                    q+=campos;
                    q+=from;
                    q+=tabla;
                    if(!folio.isEmpty()){
                        condiciones +=" and folio >='"+folio+"' ";
                    }
                    if(!foliohasta.isEmpty()){
                        condiciones +=" and folio <='"+foliohasta+"' ";
                    }
                    if(!documento.isEmpty()){
                        condiciones +=" and documento='" +documento+"' ";
                    }
                    if(!op.isEmpty()){
                        condiciones += "and op='"+op+"' ";
                    }
                    if(!oc.isEmpty()){
                        condiciones += "and oc='"+oc+"' ";
                    }
                    if(!tipoentrada.isEmpty()){
                        condiciones +=" and tipo_entrada ='"+tipoentrada+"' ";
                    }
                    if(!propi.isEmpty()){
                        condiciones +=" and propietario ='"+propi+"' ";
                    }
                    if(!cliente.isEmpty()){
                        condiciones +=" and cliente ='"+cliente+"' ";
                    }
                    if(!t1.isEmpty()){
                        condiciones +=" and turno like '%"+t1+"%' ";
                    }
                    if(!t2.isEmpty()){
                        condiciones +=" and turno like '%"+t2+"%' ";
                    }
                    if(!t3.isEmpty()){
                        condiciones +=" and turno like '%"+t3+"%' ";
                    }
                    if(fechaIni!=null){
                        condiciones +=" and fecha >= '"+fechaIni+"' ";
                    }
                    if(fechaFin!=null){
                        condiciones +=" and fecha <= '"+fechaFin+"' ";
                    }
                    if(!clavepro.isEmpty()){
                        condiciones += " and claveproducto='"+clavepro+"' ";
                    }
                    if(!descripcion.isEmpty()){
                        condiciones += " and descripcion like '%"+descripcion+"%' ";
                    }
                    if(!ubicacion.isEmpty()){
                        condiciones += " and ubicacion='"+ubicacion+"' ";
                    }
                    if(!cantidad.isEmpty()){
                        condiciones += " and cantidad"+cantidad+" ";
                    }
                    if(!uM.equals("Selecciona...")){
                        condiciones += " and unidadMedida='"+uM+"' ";
                    }
                    if(!area.isEmpty()){
                        condiciones += " and area='"+area+"' ";
                    }
                    if(!maquina.isEmpty()){
                        condiciones += " and maquina='"+maquina+"' ";
                    }
                    if(!maquina.isEmpty()){
                        condiciones += " and maquina='"+maquina+"' ";
                    }
                    if(!proveedor.isEmpty()){
                        condiciones += " and proveedor='"+proveedor+"' ";
                    }
                    if(!observaciones.isEmpty()){
                        condiciones +=" and observaciones like '%"+observaciones+"%' ";
                    }
                    //DESCRIPCION ,CLVE,UNIDAD,CANTIDAD,COSTO,UBICAION,AREA, MAQUINA,MINIMO, MAXIMO,OP
                    
                    if(!condiciones.equals("")){
                        q+=where;
                        condiciones = condiciones.substring(4);
                        condiciones = condiciones.substring(0,(condiciones.length()-1));
                        q+=condiciones;
                    }
                    
                }
                if(this.consulta.__optSalida.isSelected()){
                    //folio,documento,op,oc,tipo_entrada,propietario,cliente,turno,observaciones,fecha,claveproducto,descripcion,ubicacion,cantidad,unidadMedida,costo,totalcosto
                    tabla=" vw_infosalida ";
                    q+=select;
                    campos +=" * ";
                    campos= campos.replace(" * ", "folio, documento, op,  tipo_salida, area,responsable,turno, observaciones,fecha,  claveproducto as clave,descripcion,ubicacion,cantidad, unidadmedida,costo, totalcosto,solicitante  ");                    
                    q+=campos;
                    q+=from;
                    q+=tabla;
                    if(!folio.isEmpty()){
                        condiciones +=" and folio >='"+folio+"' ";
                    }
                    if(!foliohasta.isEmpty()){
                        condiciones +=" and folio <='"+foliohasta+"' ";
                    }
                    if(!documento.isEmpty()){
                        condiciones +=" and documento='" +documento+"' ";
                    }
                    if(!op.isEmpty()){
                        condiciones += "and op='"+op+"' ";
                    }
                    if(!oc.isEmpty()){
                        condiciones += "and oc='"+oc+"' ";
                    }
                    if(!tiposalida.isEmpty()){
                        condiciones +=" and tipo_salida ='"+tiposalida+"' ";
                    }
                    if(!propi.isEmpty()){
                        condiciones +=" and propietario ='"+propi+"' ";
                    }
                    if(!cliente.isEmpty()){
                        condiciones +=" and cliente ='"+cliente+"' ";
                    }
                    if(!t1.isEmpty()){
                        condiciones +=" and turno like '%"+t1+"%' ";
                    }
                    if(!t2.isEmpty()){
                        condiciones +=" and turno like '%"+t2+"%' ";
                    }
                    if(!t3.isEmpty()){
                        condiciones +=" and turno like '%"+t3+"%' ";
                    }
                    if(fechaIni!=null){
                        condiciones +=" and fecha >= '"+fechaIni+"' ";
                    }
                    if(fechaFin!=null){
                        condiciones +=" and fecha <= '"+fechaFin+"' ";
                    }
                    if(!clavepro.isEmpty()){
                        condiciones += " and claveproducto='"+clavepro+"' ";
                    }
                    if(!descripcion.isEmpty()){
                        condiciones += " and descripcion like '%"+descripcion+"%' ";
                    }
                    if(!ubicacion.isEmpty()){
                        condiciones += " and ubicacion='"+ubicacion+"' ";
                    }
                    if(!cantidad.isEmpty()){
                        condiciones += " and cantidad"+cantidad+" ";
                    }
                    if(!uM.equals("Selecciona...")){
                        condiciones += " and unidadMedida='"+uM+"' ";
                    }
                    if(!area.isEmpty()){
                        condiciones += " and area='"+area+"' ";
                    }
                    if(!maquina.isEmpty()){
                        condiciones += " and maquina='"+maquina+"' ";
                    }
                    if(!maquina.isEmpty()){
                        condiciones += " and maquina='"+maquina+"' ";
                    }
                    if(!proveedor.isEmpty()){
                        condiciones += " and proveedor='"+proveedor+"' ";
                    }
                    if(!observaciones.isEmpty()){
                        condiciones +=" and observaciones like '%"+observaciones+"%' ";
                    }
                    if(!observaciones.isEmpty()){
                        condiciones +=" and observaciones like '%"+observaciones+"%' ";
                    }
                    if(!solicitante.isEmpty()){
                        condiciones +=" and solicitante ='"+solicitante+"' ";
                    }
                    //DESCRIPCION ,CLVE,UNIDAD,CANTIDAD,COSTO,UBICAION,AREA, MAQUINA,MINIMO, MAXIMO,OP
                    
                    if(!condiciones.equals("")){
                        q+=where;
                        condiciones = condiciones.substring(4);
                        condiciones = condiciones.substring(0,(condiciones.length()-1));
                        q+=condiciones;
                    }
                    
                }
                System.out.println(q);
                ResultSet Consulta = mimodelo.Consulta(q);
                try {
                    if(Consulta.next()){
                        Consulta.beforeFirst();
                        ResultSetMetaData metaData = Consulta.getMetaData();
                        int numcolumnas = metaData.getColumnCount();
                        String datosT[] =  new String[numcolumnas];
                        String datosC[] =  new String[numcolumnas];
                        for(int i=0;i<numcolumnas;i++){
                            datosT[i]=metaData.getColumnLabel(i+1).toUpperCase();
                        }
                        DefaultTableModel modelo = new DefaultTableModel();
                        verconsulta.__tConsulta.setModel(modelo);
                        for(int i=0;i<numcolumnas;i++){
                            modelo.addColumn(datosT[i]);
                        }
                        TableColumnModel columnModel = verconsulta.__tConsulta.getColumnModel();
                        TableColumn columnaTabla = columnModel.getColumn(0);
                        String nombreColumna = columnaTabla.getIdentifier().toString();
                        if (nombreColumna.equals("CLAVE")||nombreColumna.equals("CLAVEPAPEL")){
                            columnaTabla.setPreferredWidth(100);
                        }
                        while(Consulta.next()){
                            for(int i=0;i<numcolumnas;i++){
                                datosC[i]=Consulta.getString(i+1);
                            }
                            modelo.addRow(datosC);
                        }
                        int total_filas = verconsulta.__tConsulta.getRowCount();
                        verconsulta.__etqTotal.setText("Total de Registros: "+total_filas);
                        verconsulta.show();
                    }else{
                        mensaje(2,"La consulta a la base de datos no devolvio informacion");
                    }
                } catch (SQLException ex) {
                    mensaje(3,ex.getMessage());
                    
                }
                break;
                case __SALIR_CONSULTA:
                confir=this.mensajeConfirmacion("Estas Seguro de Salir","Confirmación");
                        if (confir==JOptionPane.OK_OPTION){
                            consulta.dispose();
                            menumaster.setVisible(true);
//                            borrarFormularioConsultas();
                        }
                break;
                case __REGRESAR:
                    this.limpiarTabla(verconsulta.__tConsulta);
                    verconsulta.setVisible(false);
                    break;
            case __EXCEL:
                try{
                    File archivo = this.archivo(1);
                    if(archivo==null){
                        return;
                    }
                    boolean arch = this.imprimirExcel(archivo,verconsulta.__tConsulta);
                    if(arch==true){
                        confir = this.mensajeConfirmacion("Archivo "+archivo+".xls creado correctamente, deseas hacer otra operacion", "Adicional");
                        if(confir==JOptionPane.OK_OPTION){
                            correo.setVisible(true);
                            correo.setLocationRelativeTo(null);
                            correo.__archivo.setText(archivo.getAbsolutePath().toLowerCase()+".xls");
                        }
                    }
                }catch(Exception a){
                    mensaje(3,a.getMessage());
                }
                break;
            case __CORREO:
                correo.setVisible(true);
                correo.setLocationRelativeTo(null);
                break;
                case __BUSCARARCHIVO:
                File archivo = this.archivo(2);
                if(archivo==null){
                    break;
                }
                correo.__archivo.setText(archivo.getAbsolutePath().toLowerCase());
                break;
            case __ABRIREXCEL:
                try {
                    String fil = correo.__archivo.getText();
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
            case __ACEPTARCORREO:
                String urlarchivo = this.correo.__archivo.getText();
                if(urlarchivo.isEmpty()){
                    mensaje(3,"Selecciona el archivo a enviar");
                    break;
                }
                String destinatario = correo.__destinatario.getText();
                if(destinatario.isEmpty()){
                    mensaje(3,"Escribe el destinatario");
                    correo.__destinatario.requestFocus();
                    break;
                }
                String msg = correo.__msg.getText();
                if(msg.isEmpty()){
                    confir=this.mensajeConfirmacion("El correo se enviara sin mensaje, deseas continuar?", "Continuar");
                    if(confir!=JOptionPane.OK_OPTION){
                        correo.__msg.requestFocus();
                        break;
                    }else{
                        msg=" ";
                    }
                }
                confir=this.mensajeConfirmacion("Estas seguro de enviar el correo", "Continuar");
                if(confir==JOptionPane.OK_OPTION){
                    if(this.enviaarchivo(urlarchivo, destinatario, msg)){
                        mensaje(1,"Archivo enviado correctamente");
                        correo.__archivo.setText("");
                        correo.__destinatario.setText("");
                        correo.__msg.setText("");
                    }
                }
                break;
            case __SALIRCORREO:
                correo.__archivo.setText("");
                correo.__destinatario.setText("");
                correo.__msg.setText("");
                correo.dispose();
                verconsulta.requestFocus();
                break;
            case __ACEPTAR_SESION:
                sesionactiva();
                break;
            case __CANCELAR_SESION:
                login.setEnabled(true);
                login.__Usuario.setText("");
                login.__Pswd.setText("");
                csesion.__NewPswd.setText("");
                csesion.dispose();
                login.__Usuario.requestFocus();
                break;
        }
    }
    public void sesionactiva(){
        System.out.println(this.user);
                contra=this.csesion.__NewPswd.getText();
                if(contra.isEmpty()||contra.equals("")){
                    mensaje(3,"escribe la contraseña");
                    this.csesion.__NewPswd.requestFocus();
                }else{
                    try {
                        System.out.println(contra);
                        contra= this.encriptaContraseña(contra);
                        ResultSet buscarUser=this.mimodelo.buscarUser1(user);
                        if(!buscarUser.next()){
                            mensaje(3,"Error,  Usuario No Dado de Alta");
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
                            if(mimodelo.cerrarsesion(user)){
                                mensaje(1,"Sesion Cerrada correctamente");
                                login.setEnabled(true);
                                csesion.__NewPswd.setText("");
                                csesion.dispose();
                                login.__Usuario.requestFocus();
                            }
                        }else{
                            mensaje(3,"Error,  Contraseña Erronea");
                            this.csesion.__NewPswd.requestFocus();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
                                    this.confir=this.mensajeConfirmacion("¿Deseas cerrar tu sesion para poder ingresar aqui?", "Sesion Activa");
                                    //formulario cerrar sesion.
                                    if(confir==JOptionPane.OK_OPTION){
                                        login.setEnabled(false);
                                        csesion.setVisible(true);
                                        csesion.setLocationRelativeTo(null);
                                    }else{
                                        login.__Pswd.setText("");
                                        login.__Usuario.setText("");
                                        login.__Usuario.requestFocus();
                                    }
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
        if(((caracter < 'A') || (caracter > 'Z'))  && ((caracter < '0') || (caracter > '9')) && caracter !=' ' && caracter != 'Ñ' && caracter != '/' ){
            evt.consume();
        }
    }
    private void KeyTipedLetrasNumCar(KeyEvent evt) {
        mayusculas();
        char caracter = evt.getKeyChar();
        if(((caracter < 'A') || (caracter > 'Z'))  && ((caracter < '0') || (caracter > '9')) && caracter != '-' && caracter != ',' && caracter != '/'  && caracter != ' ' && caracter != '.' && caracter != 'Ñ' && caracter != '(' && caracter != ')'&& caracter != ':'&& caracter != '-' ){
            evt.consume();
        }
    }
    public void mayusculas(){
        Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
    }
    public void fechaini() {
        if (aceptarfecha()==true){
            if(pedirfecha==0){
                if(cargo==3){
                    menumaster.__etqUsuarioMenuMaster.setText(fecha.__etqUser.getText());
                    menumaster.__ALTAPRODUCTO.setEnabled(false);
                    menumaster.__MOVIMIENTOS.setEnabled(false);
                    menumaster.setLocationRelativeTo(null);
                    menumaster.show();
                }else{
                    menumaster.__etqUsuarioMenuMaster.setText(fecha.__etqUser.getText());                                                  
                    menumaster.setLocationRelativeTo(null);
                    menumaster.show();
                }                    
            }        
                ponerfecha();
                fecha.date.setDate(null);
                login.dispose();
                login.setEnabled(true);
                newP.setEnabled(true);                   
                movimientos.setEnabled(true);                
                reporte.setEnabled(true);                    
                consulta.setEnabled(true);
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
        movimientos.__etqFechaEnt.setText(fec);
        movimientos.__etqFechaSalida.setText(fec);
        reporte.__etqFecha.setText(fec);
        consulta.__etqFechaConMov.setText(fec);
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
            
            System.exit(0);
        } catch (Exception ex) {
            mensaje(3,ex.getMessage());
        }
    }
    public boolean enviaarchivo(String urlarchivo, String destinatario,String msg){
        try{
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "rawmats2014@gmail.com");
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
            message.setSubject("Correo de Raw-Mats "+ fec);
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
            String Nombre = (String)JOptionPane.showInputDialog(null,"Escribe la nueva Sección: ","NUEVA SECCIÓN",JOptionPane.PLAIN_MESSAGE);
                if ((Nombre != null) && (Nombre.length() > 0)) {
                    try {
                        Nombre=Nombre.toUpperCase();
                        int conf=JOptionPane.showConfirmDialog(null,"Se agregara la Sección:, " +Nombre + ".",Nombre,JOptionPane.OK_CANCEL_OPTION);
                        if (conf==JOptionPane.OK_OPTION){
                            boolean altaNombre=mimodelo.newMaquina(Nombre,idarea);
                            if(altaNombre==true){
                                JOptionPane.showMessageDialog(null,"Sección "+Nombre+" Agregada Correctamente.","Correcto",JOptionPane.INFORMATION_MESSAGE);
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
                mensaje(3,"No agregaste Ninguna Nueva Sección.");
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
                                    consulta.dispose();
                                    movimientos.dispose();
                                    this.newP.dispose();
                                    conEP.dispose();
                                    reporte.dispose();                                    
//                                    borrarFormularioNewUser();
//                                    borrarFormularioConEP();
//                                    borrarFormularioAltaProducto();
//                                    borrarFormularioMovimientos();
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
                                    consulta.dispose();
                                    movimientos.dispose();
                                    newP.dispose();
                                    conEP.dispose();
                                    reporte.dispose();                                    
//                                    borrarFormularioNewUser();
//                                    borrarFormularioConEP();
//                                    borrarFormularioAltaProducto();
//                                    borrarFormularioMovimientos();
//                                    borrarFormularioProveedor();
//                                    borrarFormularioConsultas();
//                                    borrarFormularioEmergente();
                                }
                            break;
                        case 3:
                            confir = mensajeConfirmacion("¿Realmente Deseas Regresar al \n Menú Principal?","Salida");
                                if (confir == JOptionPane.OK_OPTION){
                                menumaster.setVisible(true);
                                menumaster.setLocationRelativeTo(null);
                                menumaster.__ALTAPRODUCTO.setEnabled(false);
                                menumaster.__MOVIMIENTOS.setEnabled(false);
                                consulta.dispose();
                                movimientos.dispose();
                                newP.dispose();
                                conEP.dispose();
                                reporte.dispose();
//                                borrarFormularioNewUser();
//                                borrarFormularioAltaProducto();
//                                borrarFormularioMovimientos();
//                                borrarFormularioProveedor();
//                                borrarFormularioConsultas();
//                                borrarFormularioEmergente();
                                }                                                   
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
                                    reporte.setEnabled(true);                    
                                    consulta.setEnabled(true);
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
                                    reporte.setEnabled(true);                    
                                    consulta.setEnabled(true);
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
                    reporte.setEnabled(true);                    
                    consulta.setEnabled(true);
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
                        reporte.setEnabled(true);                    
                        consulta.setEnabled(true);
                        newU.setVisible(false);
                    }
                break;
            case 1:
                 confir = mensajeConfirmacion("¿Desea Cancelar la Modificación?","Salida");
                    if (confir==JOptionPane.OK_OPTION){
                        borrarFormularioNewUser();
                        newP.setEnabled(true);
                        movimientos.setEnabled(true);
                        reporte.setEnabled(true);                    
                        consulta.setEnabled(true);
                        newU.setVisible(false);
                    }
                break;
        }
        
    }
    public void borrarFormularioAltaProducto() {    
        newP.__Refacciones.setEnabled(true);
        newP.__MateriaPrima.setEnabled(true);
        this.newP.__cmbArea.removeAllItems();
        this.newP.__cmbArea.addItem("Selecciona...");
        this.newP.__cmbMaquina.removeAllItems();
        this.newP.__cmbMaquina.addItem("Selecciona...");        
        this.newP.__descripcion.setText("");
        this.newP.__SMin_.setText("");
        this.newP.__SMax_.setText("");
        this.newP.__etqClave.setText("");
        this.newP.buttonGroup1.clearSelection();
        this.addItems("newP");
        newP.__cmbArea.setEnabled(false);
        newP.__etqNewArea.setEnabled(false);
        newP.__etqNewMaquina.setEnabled(false);
        newP.__cmbMaquina.setEnabled(false);
        newP.__Ubicacion.setText("");
        newP.__cmbum.setSelectedIndex(0);
        modificaproducto=0;
        etiqueta="";
        traia="";
        newP.__MateriaPrima.requestFocus();
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
                                reporte.setEnabled(true);
                                consulta.setEnabled(true);
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
            for (int i = 0;i<20; i++) {
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
                TableColumnModel columnModel = conEP.__tablaProductos.getColumnModel();
                TableColumn columnaTabla = columnModel.getColumn(0);
                String nombreColumna = columnaTabla.getIdentifier().toString();
                if (nombreColumna.equals("CLAVE")){
                    columnaTabla.setPreferredWidth(10);
                }
                columnModel.getColumn(0).setWidth(10);
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
    public void altaproveedor(){
            String Nombre=this.NewPC.__NombreProveedor.getText(),
            Direccion=this.NewPC.__DireccionProveedor.getText(),
            telefono=this.NewPC.__TelefonoNProveedor.getText(),
            RfC=this.NewPC.__RFC.getText();
            if(Nombre.isEmpty()){
                mensaje(3,"Debe de Ingresar el Nombre");
                this.NewPC.__NombreProveedor.requestFocus();
                    return;
            }
            switch(clienteprovedor){//en el caso 0 se da de alta un proveedor en el caso de 1 se da de alta un nuevo cliente
                case 1:
                    confir= mensajeConfirmacion("Realmente Desea dar de Alta el Proveedor "+Nombre,"Alta");
                    if(confir==JOptionPane.OK_OPTION){
                        boolean altaprove=mimodelo.newaltaproveedor(Nombre,Direccion,telefono,RfC);                
                            if(altaprove==true){                                       
                                mensaje(1,"Alta Correcta de Nuevo Proveedor: "+Nombre+"");  
                                borrarFormularioProveedor();
                                movimientos.setEnabled(true);
                                NewPC.dispose();
                                movimientos.setVisible(true);
                            }else{
                                mensaje(3,"Ocurrio un Error al Dar de Alta el Nuevo Proveedor");                          
                            }       
                     break;
                    }
                    break;
                case 0:
                    confir= mensajeConfirmacion("Realmente Desea dar de Alta el Cliente: "+Nombre,"Alta");
                    if(confir==JOptionPane.OK_OPTION){
                        boolean altacli=mimodelo.newaltacliente(Nombre,Direccion,telefono,RfC);                
                            if(altacli==true){                                       
                                mensaje(1,"Alta Correcta de Nuevo Cliente: "+Nombre+"");   
                                borrarFormularioProveedor();
                                movimientos.setEnabled(true);
                                NewPC.dispose();
                                movimientos.setVisible(true);
                            }else{
                                mensaje(3,"Ocurrio un Error al Dar de Alta el Nuevo Cliente");                          
                            }                        

                        break;
                    }
                    break;
            }
            addItems("movimientos");
    }
    public void borrarFormularioProveedor(){
        this.NewPC.__NombreProveedor.setText("");
        this.NewPC.__DireccionProveedor.setText("");
        this.NewPC.__TelefonoNProveedor.setText("");
        this.NewPC.__RFC.setText("");
    }
    public void nuevoPropietario(){                                    
               String PROPIEDAD = (String)JOptionPane.showInputDialog(null,"Escribe el Propietario a Registrar:\n","PROPIETARIO DE PAPEL",JOptionPane.PLAIN_MESSAGE);
                if ((PROPIEDAD != null) && (PROPIEDAD.length() > 0)) {
                    int conf=JOptionPane.showConfirmDialog(null,"Se agregara el propietario de papel:, " + PROPIEDAD + ".",PROPIEDAD,JOptionPane.OK_CANCEL_OPTION);
                    if (conf==JOptionPane.OK_OPTION){
                        boolean altapropiedad=mimodelo.nuevopropiedad(PROPIEDAD);
                        if(altapropiedad==true){
                            try {
                                JOptionPane.showMessageDialog(null,"Propiedad de Papel "+PROPIEDAD+" agregado correctamente.","Correcto",JOptionPane.INFORMATION_MESSAGE);                                
                            } catch (Exception ex) {
                                mensaje(3,ex.getMessage());
                            }
                        }
                    }
                    return; 
                }
                JOptionPane.showMessageDialog(null,"No agregaste propietario de papel.","Error",JOptionPane.ERROR_MESSAGE);
            }
    public void nuevaTipoEntrada(){
        String tipoEntra = (String)JOptionPane.showInputDialog(null,"Escribe la Nueva Entrada:\n","NUEVO TIPO ENTRADA",JOptionPane.PLAIN_MESSAGE);
        if ((tipoEntra  != null) && (tipoEntra.length() > 0)) {
            int conf=JOptionPane.showConfirmDialog(null,"Se agregara la Entrada de Papel: " + tipoEntra  + ".",tipoEntra,JOptionPane.OK_CANCEL_OPTION);
            if (conf==JOptionPane.OK_OPTION){
                boolean altanuevoentrada=mimodelo.nuevatipoentrada(tipoEntra);
                if(altanuevoentrada==true){
                        JOptionPane.showMessageDialog(null,"Nuevo Tipo Entrada "+tipoEntra+" Agregado Correctamente.","Correcto",JOptionPane.INFORMATION_MESSAGE);
                        
                }
            }
            return; 
        }
        JOptionPane.showMessageDialog(null,"No Agregaste Nuevo Tipo de Entrada.","Error",JOptionPane.ERROR_MESSAGE);            
    }
    public void nuevoTipoSalida(){
        String tipoSali = (String)JOptionPane.showInputDialog(null,"Escribe la Nueva Salida:\n","NUEVO TIPO SALIDA",JOptionPane.PLAIN_MESSAGE);
        if ((tipoSali  != null) && (tipoSali.length() > 0)) {
            int conf=JOptionPane.showConfirmDialog(null,"Se agregara la Salida de Producto: " + tipoSali  + ".",tipoSali,JOptionPane.OK_CANCEL_OPTION);
            if (conf==JOptionPane.OK_OPTION){
                boolean altanuevasalida=mimodelo.nuevatiposalida(tipoSali);
                if(altanuevasalida==true){
                        JOptionPane.showMessageDialog(null,"Nuevo Tipo Salida "+tipoSali+" Agregado Correctamente.","Correcto",JOptionPane.INFORMATION_MESSAGE);
                        
                }
            }
            return; 
        }
        JOptionPane.showMessageDialog(null,"No Agregaste Nuevo Tipo de Salida.","Error",JOptionPane.ERROR_MESSAGE);            
    }            
    public void nuevaArea(){
        String newArea = (String)JOptionPane.showInputDialog(null,"Escribe la Nueva Area:\n","NUEVA AREA",JOptionPane.PLAIN_MESSAGE);
        if ((newArea  != null) && (newArea.length() > 0)) {
            int conf=JOptionPane.showConfirmDialog(null,"Se agregara el Area: " + newArea  + ".",newArea,JOptionPane.OK_CANCEL_OPTION);
            if (conf==JOptionPane.OK_OPTION){
                boolean altanuevaarea=mimodelo.nuevaarea(newArea);
                if(altanuevaarea==true){
                        JOptionPane.showMessageDialog(null,"Nueva Area "+newArea+" Agregada Correctamente.","Correcto",JOptionPane.INFORMATION_MESSAGE);                        
                }
            }
            return; 
        }
        JOptionPane.showMessageDialog(null,"No Agregaste Nueva Area.","Error",JOptionPane.ERROR_MESSAGE);            
    }            
    String Obs; int tipoentrada,propietario,proveedor,cliente,tiposalida;
    public void EntradaAceptarModificar(){
        String FolioE=movimientos.__FolioEntrada.getText();
        String DocumentoE=movimientos.__documentoEntr.getText();
        if(DocumentoE.isEmpty()){
            mensaje(3,"Debe Especificar el Documento de Entrada");
            movimientos.__documentoEntr.requestFocus();
            return;
        }
        String TipoE=movimientos.__TipoEntrada.getText();
        if(TipoE.isEmpty()){
            mensaje(3,"Debe Especificar un Tipo de Entrada");
            movimientos.__TipoEntrada.requestFocus();
            return;
        }
        tipoentrada = this.busquedaid(movimientos.__TipoEntrada);
        if(tipoentrada==0){
            mensaje(3,"Verifica el tipo de Entrada");
            movimientos.__TipoEntrada.requestFocus();
            return;
        } 
        propietario = this.busquedaid(movimientos.__PropietarioEntr);
        if(propietario==0){
            mensaje(3,"Verifica el Propietario");
            movimientos.__PropietarioEntr.requestFocus();
            return;
        }
        String ClienteE=movimientos.__ClientEntr.getText();
        if(ClienteE.isEmpty()){
            mensaje(3,"Debe Especificar un Cliente");
            movimientos.__ClientEntr.requestFocus();
            return;
        }
        cliente = this.busquedaid(movimientos.__ClientEntr);
        if(propietario==0){
            mensaje(3,"Verifica el Cliente");
            movimientos.__ClientEntr.requestFocus();
            return;
        }
        proveedor = this.busquedaid(movimientos.__ProvEntr);
        if(proveedor==0){
            mensaje(3,"Verifica el Proveedor");
            movimientos.__ProvEntr.requestFocus();
            return;
        }
        String OrdenProducionE=movimientos.__OrdenProduccionEntr.getText();                        
        String OrdenCompraE=movimientos.__OrdenCompraEntr.getText();                                
        
        
        if(movimientos.__chkTurno1Entr.isSelected()){
           t1="t1";
        }else
        if(movimientos.__chkTurno2Entr.isSelected()){
           t2="t2";
        }else
        if(movimientos.__chkTurno3Entr.isSelected()){
           t3="t3";
        }else{
            mensaje(3,"Selecciona al menos un turno");
            return;
        }
        if(movimientos.__tablaEntrada.getValueAt(0, 0)==null||movimientos.__tablaEntrada.getValueAt(0, 0).toString().isEmpty()){
            mensaje(3,"Ingresa Valores a la Tabla");
            return;
        }
        String claveproducto="";
        
        int contador = 0 ;
        for(int i=0;i<movimientos.__tablaEntrada.getRowCount();i++){
            Object valueAt = movimientos.__tablaEntrada.getValueAt(i, 0);
            if(valueAt!=null){
                contador++;
            }
        }
        
        
        for(int i=0;i<contador;i++){
            for (int j=0;j<7;j++){
                Object valueAt =   movimientos.__tablaEntrada.getValueAt(i, j);
                if(valueAt==null||(valueAt.toString()).isEmpty()||valueAt.toString().equals("")){
                    switch(j){
                        case 0:
                            mensaje(2,"Completa la clave en la entrada " +(i+1));
                            break;
                        case 1:
                            mensaje(2,"Completa la descripción en la entrada " +(i+1));
                            break;
                        case 2:
                            mensaje(2,"Completa la ubicación en la entrada " +(i+1));
                            break;
                        case 3:
                            mensaje(2,"Completa la cantidad en la entrada " +(i+1));
                            break;
                        case 4:
                            mensaje(2,"Completa la unidad de medida en la entrada " +(i+1));
                            break;
                        case 5:
                            mensaje(2,"Completa el costo en la entrada " +(i+1));
                            break;
                        case 6:
                            mensaje(2,"Completa el costo total en la entrada " +(i+1));
                            break;
                    }
                    return;
                }
            }
        }
        
        for(int i=0;i<movimientos.__tablaEntrada.getRowCount();i++){
            try {
                String claveProducto = movimientos.__tablaEntrada.getValueAt(i, 0).toString();
                String descripcionProducto=movimientos.__tablaEntrada.getValueAt(i, 1).toString();
                ResultSet producto=mimodelo.buscarProductoByDescripcion(descripcionProducto);
                while(producto.next()){
                    String claveProductobd=producto.getString("clave");
                    if(!claveProductobd.equals(claveProducto)){
                        mensaje(3,"Verifica la Clave del producto en la entrada #"+(i+1)+".");
                        return;
                    }
                }
            } catch (Exception ex) {
                //Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        switch(modificarentrada){
                case 0:
                    Obs=JOptionPane.showInputDialog(null, "Observaciones de la Entrada");
                    confir = mensajeConfirmacion("Se Realizara la Entrada #: "+FolioE,"Nueva Entrada");
                    if (confir==JOptionPane.OK_OPTION){
                        ResultSet buscarMaxEntrada=null;
                        boolean detalleentrada = false;
                        int id_entrada = 0;
                        try {
                            buscarMaxEntrada = mimodelo.bucarMaxEntrada();
                            while(buscarMaxEntrada.next()){
                                id_entrada = buscarMaxEntrada.getInt(1);
                            }
                            buscarMaxEntrada.close();
                            id_entrada++;
                            for(int i=0;i<movimientos.__tablaEntrada.getRowCount();i++){
                                try{
                                claveproducto=movimientos.__tablaEntrada.getValueAt(i, 0).toString();
                                String descripcion =movimientos.__tablaEntrada.getValueAt(i, 1).toString();
                                String ubicacion = movimientos.__tablaEntrada.getValueAt(i, 2).toString();
                                Double cantidadentrada=Double.parseDouble(movimientos.__tablaEntrada.getValueAt(i, 3).toString());
                                String unidad_m = movimientos.__tablaEntrada.getValueAt(i, 4).toString();
                                String costo=movimientos.__tablaEntrada.getValueAt(i, 5).toString();
                                String totalcosto=movimientos.__tablaEntrada.getValueAt(i, 6).toString();
                                ResultSet existenciaPapel = mimodelo.buscarExistenciaProducto(claveproducto);
                                if(existenciaPapel.next()){
                                    detalleentrada = mimodelo.altaDetalleEntrada(id_entrada,claveproducto,descripcion,unidad_m,cantidadentrada,ubicacion,costo,totalcosto);
                                    mimodelo.sumarexistencia(claveproducto);
                                    mimodelo.ubicacion(claveproducto,ubicacion);
                                    mimodelo.um(claveproducto,unidad_m);
                                    mimodelo.opp(claveproducto, OrdenProducionE);
                                    mimodelo.costoprom(claveproducto);
                                }else{
                                    mensaje(3,"EL PRODUCTO NO EXISTE");
                                    return;
                                }
                                }catch(Exception evt){
                                    break;
                                }
                            }
                            String fechaentrada=fec.replaceAll("-", "");
                            boolean altaEntrada = mimodelo.altaEntrada(FolioE,DocumentoE,tipoentrada+"",propietario+"",proveedor+"",OrdenProducionE,OrdenCompraE,cliente+"",t1, t2, t3, id_responsable, fechaentrada,Obs);
                            mimodelo.altaDocEntrada(eval);
                            if(altaEntrada==true && detalleentrada==true){
                                mensaje(1,"Entrada agregada correctamente");
                                this.borrarFormularioMovimientos();
                            }else{
                                mensaje(3,"Ocurrio un error al dar de alta la entrada");
                                break;
                            }
                            } catch (SQLException ex) {
                                mensaje(3,ex.getMessage());
                                break;
                            }
                        break;
                    }
                    break;
                case 1:
                    
                    Obs=JOptionPane.showInputDialog(null, "Observaciones de la Modificación de la Entrada");
                    confir=mensajeConfirmacion("Estas seguro de modificar la entrada","Aceptar");
                    //String folioentrada,String t1, String t2, String t3,String ordenProduccion,String ordenCompra,String documentoEntrada,int propietario,int proveedor, int responsable,String fecha,int tipoentrada,String Observaciones,int cliente){
                    if (confir==JOptionPane.OK_OPTION){
                        //String fechaentrada=fec.replaceAll("-", "");
                        mimodelo.modifEntrada(FolioE,t1,t2,t3,OrdenProducionE,OrdenCompraE,DocumentoE,propietario+"",proveedor+"",id_responsable+"", fec,tipoentrada+"",Obs,cliente+"" );
                        for(int i=0;i<movimientos.__tablaEntrada.getRowCount();i++){
                            try {                            
                                claveproducto=movimientos.__tablaEntrada.getValueAt(i, 0).toString();
                                String descripcion =movimientos.__tablaEntrada.getValueAt(i, 1).toString();
                                String ubicacion = movimientos.__tablaEntrada.getValueAt(i, 2).toString();
                                Double cantidadentrada=Double.parseDouble(movimientos.__tablaEntrada.getValueAt(i, 3).toString());
                                String unidad_m = movimientos.__tablaEntrada.getValueAt(i, 4).toString();
                                String costo=movimientos.__tablaEntrada.getValueAt(i, 5).toString();
                                String totalcosto=movimientos.__tablaEntrada.getValueAt(i, 6).toString();
                                ResultSet existenciaPapel = mimodelo.buscarExistenciaProducto(claveproducto);
                                mimodelo.modifDetalleEntrada(Integer.parseInt(identradas[i]),claveproducto,descripcion,ubicacion,cantidadentrada+"",unidad_m,costo,totalcosto);
                                mimodelo.costopromedio(claveproducto);
                                mimodelo.sumarexistencia(claveproducto);
                                mimodelo.ubicacion(claveproducto, ubicacion);
                                mimodelo.um(claveproducto,unidad_m);
                            } catch (Exception ex) {
                                //Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                                
                            }   
                        }
                        mensaje(1,"modificacion de entrada correcta");
                        this.borrarFormularioMovimientos();
                    }else{
                        return;
                    }
                    break;
            }
        
    }
    public void SalidaAceptarModificar(){
        String FolioS=movimientos.__FolioSalida.getText();
        String DocumentoS=movimientos.__documentoSalida.getText();
        if(DocumentoS.isEmpty()){
            mensaje(3,"Debe Especificar el Documento de Salida");
            movimientos.__documentoSalida.requestFocus();
            return;
        }
        String TipoS=movimientos.__TipoSalida.getText();
        if(TipoS.isEmpty()){
            mensaje(3,"Debe Especificar un Tipo de Salida");
            movimientos.__TipoSalida.requestFocus();
            return;
        }
        int idTipoSal = this.busquedaid(movimientos.__TipoSalida);
        String OrdenProducionS=movimientos.__OrdenProduccionSalida.getText();                        
        String Solicitante=movimientos.__SolicitanteSalida.getText();   
        String AreaSalida=movimientos.__AreaSalida.getText();
        int idAreaSal = this.busquedaid(movimientos.__AreaSalida);
         if(movimientos.__chkTurno1Salida.isSelected()){
           t1="t1";
        }else
        if(movimientos.__chkTurno2Salida.isSelected()){
           t2="t2";
        }else
        if(movimientos.__chkTurno3Salida.isSelected()){
           t3="t3";
        }else{
            mensaje(3,"Debes seleccionar almenos un turno");
            return;
        }
        //FolioS,DocumentoS,TipoS,OrdenProducionS,Solicitante,AreaSalida,t1,t2,t3,fechaentrada,Obs
         //mimodelo.altaSalida(FolioS,t1, t2, t3, OrdenProduccionS, ordenCompra,documentoSalida, propietario, proveedor, id_responsable, fechaentrada, tiposalida,Obs,cliente);
        if(movimientos.__tablaSalida.getValueAt(0, 0)==null||movimientos.__tablaSalida.getValueAt(0, 0).toString().isEmpty()){
            mensaje(3,"Ingresa Valores a la Tabla");
            return;
        }
        int contador = 0 ;
        for(int i=0;i<movimientos.__tablaSalida.getRowCount();i++){
            Object valueAt = movimientos.__tablaSalida.getValueAt(i, 0);
            if(valueAt!=null){
                contador++;
            }
        }
        
        for(int i=0;i<contador;i++){
            for (int j=0;j<5;j++){
                Object valueAt =   movimientos.__tablaSalida.getValueAt(i, j);
                if(valueAt==null||(valueAt.toString()).isEmpty()||valueAt.toString().equals("")){
                    switch(j){
                        case 0:
                            mensaje(2,"Completa la clave en la salida " +(i+1));
                            break;
                        case 1:
                            mensaje(2,"Completa la descripción en la salida " +(i+1));
                            break;
                        case 2:
                            mensaje(2,"Completa la ubicación en la salida " +(i+1));
                            break;
                        case 3:
                            mensaje(2,"Completa la cantidad en la salida " +(i+1));
                            break;
                        case 4:
                            mensaje(2,"Completa la unidad de medida en la salida " +(i+1));
                            break;
                    }
                    return;
                }
            }
        }
        
        
        for(int i =0;i< movimientos.__tablaSalida.getRowCount();i++){
            try {
                String claveProducto = movimientos.__tablaSalida.getValueAt(i,0).toString();
                Double cantidad_salida =  Double.parseDouble(movimientos.__tablaSalida.getValueAt(i,3).toString());
                ResultSet existencia = mimodelo.buscarExistenciaProductofecha(claveProducto, fec);
                Double cantidadbd=0.0;
                if(existencia.next()){
                    existencia.beforeFirst();
                    while(existencia.next()){
                        if(existencia.getString("cantidadalafecha")==null||existencia.getString("cantidadalafecha").isEmpty()){
                            mensaje(3,"No hay suficiente existencia en la bd para realizar la salida #" +(i+1)+" hay "+cantidadbd);
                            return;
                        }    
                        cantidadbd = Double.parseDouble(existencia.getString("cantidadalafecha"));
                    }
                    if(cantidadbd<cantidad_salida){
                        mensaje(3,"No hay suficiente existencia en la bd para realizar la salida #" +(i+1)+" hay "+cantidadbd);
                        return;
                    }
                }else{
                    mensaje(3,"Verifica la clave de la salida #" +(i+1));
                    return;
                }
            } catch (Exception ex) {
                //Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        for(int i=0;i<movimientos.__tablaSalida.getRowCount();i++){
            try {
                String claveProducto = movimientos.__tablaSalida.getValueAt(i, 0).toString();
                String descripcionProducto=movimientos.__tablaSalida.getValueAt(i, 1).toString();
                ResultSet producto=mimodelo.buscarProductoByDescripcion(descripcionProducto);
                while(producto.next()){
                    String claveProductobd=producto.getString("clave");
                    if(!claveProductobd.equals(claveProducto)){
                        mensaje(3,"Verifica la Clave del producto en la entrada #"+(i+1)+".");
                        return;
                    }
                }
            } catch (Exception ex) {
                //Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        switch(SalidaMovimientos){
            case 0:
                Obs=JOptionPane.showInputDialog(null, "Observaciones de la Salida");
                confir=mensajeConfirmacion("Estas seguro de registrar la Salida","Aceptar");
                if (confir==JOptionPane.OK_OPTION){
                    ResultSet buscarMaxSalida=null;
                    boolean detallesalida = false;
                    int id_salida = 0;
                    try{
                        buscarMaxSalida = mimodelo.bucarMaxSalida();
                        while(buscarMaxSalida.next()){
                            id_salida = buscarMaxSalida.getInt(1);
                        }
                        buscarMaxSalida.close();
                        id_salida++;
                        for(int i =0;i< movimientos.__tablaSalida.getRowCount();i++){
                            try {
                                String claveProducto = movimientos.__tablaSalida.getValueAt(i,0).toString();
                                String Descripcion = movimientos.__tablaSalida.getValueAt(i,1).toString();
                                String Ubicacion = movimientos.__tablaSalida.getValueAt(i,2).toString();
                                String Unidad = movimientos.__tablaSalida.getValueAt(i,4).toString();
                                entradas="";
                                costoconsumo=0.0;
                                Double conscant = Double.parseDouble(movimientos.__tablaSalida.getValueAt(i,3).toString());
                                this.Peps3(claveProducto, conscant);
                                Double costo = costoconsumo / conscant;
                                mimodelo.sumarexistencia(claveProducto);
                                
                                detallesalida = mimodelo.altaDetalleSalida(id_salida,claveProducto,Descripcion,Ubicacion,conscant,Unidad,costo,costoconsumo,entradas);
                                mimodelo.costoprom(claveProducto);
                                
                            } catch (Exception ex) {
                                //Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                        }
                        String fechaentrada=fec.replaceAll("-", "");
                        boolean altaSalida = mimodelo.altaSalida(FolioS,DocumentoS,idTipoSal,OrdenProducionS,Solicitante,idAreaSal,t1,t2,t3,fechaentrada,Obs,id_responsable);
                        if(altaSalida==true && detallesalida==true){
                            mensaje(1,"Salida agregada correctamente");
                            this.borrarFormularioMovimientos();
                        }else{
                            mensaje(3,"Ocurrio un error al dar de alta la salida");
                            break;
                        }
                    }catch(Exception e){
                    }
                }
                break;
            case 1:
                Obs=JOptionPane.showInputDialog(null, "Observaciones de la Salida");
                confir=mensajeConfirmacion("Estas seguro de modificar la Salida","Aceptar");
                boolean modifsalida=false;
                if (confir==JOptionPane.OK_OPTION){
                    for(int i =0;i< movimientos.__tablaSalida.getRowCount();i++){
                        try {
                            String claveProducto = movimientos.__tablaSalida.getValueAt(i,0).toString();
                            String Descripcion = movimientos.__tablaSalida.getValueAt(i,1).toString();
                            String Ubicacion = movimientos.__tablaSalida.getValueAt(i,2).toString();
                            Double conscant =  Double.parseDouble(movimientos.__tablaSalida.getValueAt(i,3).toString());
                            String Unidad = movimientos.__tablaSalida.getValueAt(i,4).toString();
                            entradas="";
                            costoconsumo=0.0;
                            this.Peps3(claveProducto, conscant);
                            Double costo = costoconsumo / conscant;
                            mimodelo.sumarexistencia(claveProducto);
                            modifsalida= mimodelo.modifDetalleSalida(Integer.parseInt(idsalidas[i]),claveProducto,Descripcion,Ubicacion,conscant,Unidad,costo,costoconsumo,entradas);
                        } catch (Exception ex) {
                            //Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                    }
                    String fechaentrada=fec.replaceAll("-", "");
                        boolean altaSalida = mimodelo.modifSalida(FolioS,DocumentoS,idTipoSal,OrdenProducionS,Solicitante,idAreaSal,t1,t2,t3,fechaentrada,Obs,id_responsable);
                        if(altaSalida==true && modifsalida==true){
                            mensaje(1,"Salida modificada correctamente");
                            this.borrarFormularioMovimientos();
                        }else{
                            mensaje(3,"Ocurrio un error al Modificar la salida");
                            break;
                        }
                }else{
                    
                }
                break;
        }
    } 
    int id_salida = 0;
    String eval;
    public void docentrada(){
        String doc = movimientos.__documentoEntr.getText();
                if(doc.isEmpty()){
                    return;
                }else{
                    if(doc.length()>3&&doc.contains("-")){
                        String[] partido = doc.split("-");
                        for(int i=0;i<partido.length;i++){
                            partido[i]=partido[i].replace(" ", "");
                        }
                        eval="";
                        for(int i=1;i<partido.length;i++){
                            eval+=partido[i]+"-";
                        }
                        try{
                            eval=eval.substring(0,eval.length()-1);
                        }catch(StringIndexOutOfBoundsException e){
                            mensaje(2,"Para Capturar el Documento de Entrada es Necesario Hacerlo de la Siguiente Manera: TIPO DE DOCUMENTO - ######");
                            movimientos.__documentoEntr.requestFocus();
                            return;
                        }
                        if(eval.length()==0){
                            mensaje(2,"Para Capturar el Documento de Entrada es Necesario Hacerlo de la Siguiente Manera: TIPO DE DOCUMENTO - ######");
                            movimientos.__documentoEntr.requestFocus();
                            return;
                        }
                        try{
                            ResultSet documento = mimodelo.buscaDocumentoEntrada(eval);
                            if(documento.next()){
                                mensaje(3,"Esta Documento ya Fue Capturado");
                                movimientos.__documentoEntr.requestFocus();
                            }
                        }catch(Exception e){}
                        
                    }else{
                        mensaje(2,"Para Capturar el Documento de Entrada es Necesario Hacerlo de la Siguiente Manera: TIPO DE DOCUMENTO - ######");
                        movimientos.__documentoEntr.requestFocus();
                    }
                }
    }    
    public void borrarFormularioMovimientos() {
        this.limpiarTabla(movimientos.__tablaEntrada);
        this.limpiarTabla(movimientos.__tablaSalida);
        this.maximoentrada();
        this.maximosalida();
        movimientos.__AreaSalida.setText("");
        movimientos.__ClientEntr.setText("");
        movimientos.__OrdenCompraEntr.setText("");
        movimientos.__OrdenProduccionEntr.setText("");
        movimientos.__OrdenProduccionSalida.setText("");
        movimientos.__PropietarioEntr.setText("");
        movimientos.__ProvEntr.setText("");
        movimientos.__TipoEntrada.setText("");
        movimientos.__SolicitanteSalida.setText("");
        movimientos.__TipoSalida.setText("");
        movimientos.__documentoEntr.setText("");
        movimientos.__documentoSalida.setText("");
        movimientos.__chkTurno1Entr.setSelected(false);
        movimientos.__chkTurno2Entr.setSelected(false);
        movimientos.__chkTurno3Entr.setSelected(false);
        movimientos.__chkTurno1Salida.setSelected(false);
        movimientos.__chkTurno2Salida.setSelected(false);
        movimientos.__chkTurno3Salida.setSelected(false);
        this.movimientos.jMenuBar1.setEnabled(true);
        modificarentrada=0;
        SalidaMovimientos=0;
        this.movimientos.__MODIFICACIONENTRADA.setEnabled(true);
        this.movimientos.__MODIFICACIONSALIDA.setEnabled(true);
        this.movimientos.__Archivo.setEnabled(true);
        this.movimientos.__Edicion.setEnabled(true);
        this.movimientos.JPanel.setEnabledAt(0, true);
        this.movimientos.JPanel.setEnabledAt(1, true);
        
    }
    private void maximoentrada() {
        try {
            String folio = mimodelo.buscarFolioEntrada();
            movimientos.__FolioEntrada.setText(folio);
        } catch (SQLException ex) {
            mensaje(3,ex.getMessage());
        }
    }
    private void maximosalida() {
        try {
            String folio = mimodelo.buscarFolioSalida();
            movimientos.__FolioSalida.setText(folio);
        } catch (SQLException ex) {
            mensaje(3,ex.getMessage());
        }
    }
    public int busquedaid(JTextField campo){
        String parametro = campo.getText();
        if(parametro.isEmpty()){
            return 0;
        }else{
            ResultSet p=null;
            switch (campo.getName()){
                case "__TipoEntrada":
                    p = mimodelo.buscaTipoEntrada(parametro,false);
                    break;
                case "__PropietarioEntr":
                    p = mimodelo.buscarPropiedad(parametro,false);
                    break; 
                case "__ProvEntr":
                    p = mimodelo.buscaProveedor(parametro,false);
                    break;
                case "__ClientEntr":
                    p=mimodelo.buscarCliente(parametro,false);
                    break;
                case  "__ClienteSalidaH":
                    p=mimodelo.buscarCliente(parametro,false);
                    break;
                case "__PropietarioSalidaH":
                    p = mimodelo.buscarPropiedad(parametro,false);
                    break;
                case  "__ClienteSalidaB":
                    p=mimodelo.buscarCliente(parametro,false);
                    break;
                case "__PropietarioSalidaB":
                    p = mimodelo.buscarPropiedad(parametro,false);
                    break;
                case "__AreaSalida":
                    p=mimodelo.buscaArea(parametro, false);
                    break;
                case "__TipoSalida":
                    p = mimodelo.buscaTipoSalida(parametro,false);
                    break;
                case "__PropietarioSalida":
                    p = mimodelo.buscarPropiedad(parametro,false);
                    break; 
                case "__ProvSalida":
                    p = mimodelo.buscaProveedor(parametro,false);
                    break;
                case "__ClientSalida":
                    p=mimodelo.buscarCliente(parametro,false);
                    break;
                case "__Origen":
                    p=mimodelo.buscarPropiedad(parametro,false);
                    break;
                case "__Destino":
                    p=mimodelo.buscarPropiedad(parametro,false);
                    break;
            }
            try{
                while(p.next()){
                    return p.getInt("id");
                }
            }catch(Exception a){
                return 0;
            }
        }
    return 0;
    }    
    //HUEVOS
    public String busquedaNombre(String tabla, int id){
        ResultSet p=null;
        switch(tabla){
            case "tipo_entrada":
                p= mimodelo.buscaTipoEntrada(id);
                break;
            case "id_propietario":
                p=mimodelo.buscarPropiedad(id);
                break;
            case "id_proveedores":
                p=mimodelo.buscaProveedor(id);
                break;
            case "cliente":
                p=mimodelo.buscarCliente(id);
                break;
            case "tipo_salida":
                p= mimodelo.buscaTipoSalida(id);
                break;
        }
        try {
            while(p.next()){
                return p.getString("descripcion");
            }
        } catch (SQLException ex) {
           return null;
        }
    return null;
    }
    
    public boolean imprimirExcel(File archivo,JTable table){
        try {
            HSSFWorkbook libro = new HSSFWorkbook();
            HSSFSheet hoja = libro.createSheet();
            for (int i = 0; i <= table.getRowCount(); i++) {
                HSSFRow fila = hoja.createRow(i);          
                if(i==0){
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        HSSFCell celda = fila.createCell(j);
                        celda.setCellValue(new HSSFRichTextString(table.getColumnModel().getColumn(j).getHeaderValue().toString()));
                    }
                }
                if(i!=0){
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        HSSFCell celda = fila.createCell(j); 
                        if(table.getValueAt(i-1, j)!=null)
                            celda.setCellValue(new HSSFRichTextString(table.getValueAt(i-1, j).toString()));
                    }
                }
            }   
            FileOutputStream elFichero = new FileOutputStream(archivo.getAbsolutePath()+".xls");
            libro.write(elFichero);
            elFichero.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    Double newtemcant,costoconsumo;
    String entradas;
    public void Peps3(String claveProducto, Double conscant) {
        try {
            ResultSet primeraentrada = mimodelo.primeraentrada(claveProducto);
            while(primeraentrada.next()){
                int identrada= primeraentrada.getInt("id");
                Double canttemp = primeraentrada.getDouble("canttemp");
                Double costoentrada = primeraentrada.getDouble("costo");
                newtemcant = canttemp-conscant;
                System.out.println(newtemcant);
                if(newtemcant<0){
                    //falta consumo
                    mimodelo.updateteporalde(0.0, identrada);
                    
                    newtemcant=newtemcant*-1;
                    costoconsumo = costoconsumo +(costoentrada *canttemp);
                    Peps3(claveProducto,newtemcant);
                    entradas+=identrada+","+canttemp+",/";
                    System.out.println("a la entrada "+identrada+" se le va a dejar en 0" );
                }else{
                    costoconsumo = costoconsumo +(costoentrada *conscant);
                    entradas+=identrada+","+conscant+",/";
                    mimodelo.updateteporalde(newtemcant, identrada);
                    System.out.println("a la entrada "+identrada+" se le va a restar "+conscant );
                    return;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void antipeps3(String entradas){
        String entrada[] = entradas.split("/");
        for(int i=0;i<entrada.length;i++){
            try {
                String detalleentrada[]=entrada[i].split(",");
                int identrada= Integer.parseInt(detalleentrada[0]);
                Double cant= Double.parseDouble(detalleentrada[1]);
                Double temcant=0.0;
                ResultSet buscaentrada = mimodelo.buscaentrada(identrada);
                while(buscaentrada.next()){
                    temcant=buscaentrada.getDouble("canttemp");
                }
                Double newtemcant = temcant+cant;
                mimodelo.updateteporalde(newtemcant, identrada);
            } catch (SQLException ex) {
                Logger.getLogger(jControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
    

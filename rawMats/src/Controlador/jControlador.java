/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.modelo;
import Vista.Fecha;
import Vista.HiloProgreso;
import Vista.Login;
import Vista.MenuMaster;
import Vista.Splash;
import Vista.newProducto;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Irving
 */
public class jControlador implements ActionListener {
    HiloProgreso hilo;
    private static  Vista.Splash splash = new Splash();  
    private final  Login login = new Login();
    private final  modelo mimodelo = new modelo();
    private final  Fecha fecha = new Fecha(); 
    private final  MenuMaster menumaster = new MenuMaster();
    private final  newProducto newP = new newProducto();
    int a=1,id_responsable,cargo,pedirfecha,confir;
    String fec,user="",contra,pswd,fech,horaentrada,horasalida;
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
        this.menumaster.__CANCELAR.setActionCommand("__MENU_MASTER_CANCELAR");
        this.menumaster.__CANCELAR.setMnemonic('A');
        this.menumaster.__CANCELAR.addActionListener(this);
        //FORMULARIO NEW PRODUCTO
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
                ///
                ///
                ///
            }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });
        
        //MENUS
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
                addItems("newP");
                menumaster.dispose();
                newP.setVisible(true);
                ponerfecha();
                newP.setName("Alta de Producto");
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
                      while(buscarUser.next()){
                           pswd = buscarUser.getString(1);
                           id_responsable = buscarUser.getInt(2);
                           cargo = buscarUser.getInt(3);
                      }
                       if(contra.equals(pswd)){
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
                           mensaje(3,"Error, Usuario o Contraseña Erroneas y/o el usuario esta Bloqueado o la sesion esta activa");
                           login.__Pswd.setText("");
                           login.__Usuario.setText("");
                           login.__Usuario.requestFocus();
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
                /*movimientos.setEnabled(true);
                ap.setEnabled(true);                   
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
                     this.mensaje(2,"Ingresa la fecha a capturar");
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
                    id=mimodelo.buscaridMaquina(nombre);
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
}

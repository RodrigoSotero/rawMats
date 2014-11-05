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
import Vista.Splash;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
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
    int a=1,id_responsable,cargo;
    String user="",contra,pswd,fech,horaentrada;
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
    }

    public enum Accion{
        __INICIA_SESION,
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(Accion.valueOf(e.getActionCommand())){
            case __INICIA_SESION:
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
    public void mayusculas(){
        Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
    }
}

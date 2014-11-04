/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Vista.HiloProgreso;
import Vista.Splash;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author Irving
 */
public class jControlador {
    HiloProgreso hilo;
    private static  Vista.Splash splash = new Splash();  
    
    
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
                    //login.setLocationRelativeTo(null);
                    //login.setVisible(true);
                    //login.setDefaultCloseOperation(0);
                }
            }
        });
    }
}

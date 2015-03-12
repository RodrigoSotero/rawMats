/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import Controlador.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
/**
 *
 * @author Irving
 */
public class prueba {
    
    private final modelo mimodelo = new modelo();
    
    public static void main(String[]args) throws SQLException{
        prueba a = new prueba();
        /*ResultSet desc=a.buscadescr();
        while(desc.next()){
            int id = desc.getInt("idproductos");
            String descr = desc.getString("descripcion");
            descr=descr.replaceAll("\n", "");
            descr=descr.replaceAll("\t", "");
            a.opp(id, descr);
        }*/
        a.clavesentrada();
        
        
    }
    public prueba(){
        
    }
    
    public void bd(){
        mimodelo.bp("base_de_datos");
    }
    
    
    
    public void clavesentrada(){
        try {
            String q = "select distinct(clave) from vw_infocard";
            
            PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while (res.next()){
                String q1 = "select sum(cantidad) from vw_infocard where clave='"+res.getString(1)+"'";
                PreparedStatement pstm1 = mimodelo.getConexion().prepareStatement(q1);
                ResultSet res1 = pstm1.executeQuery();
                while (res1.next()){
                    String q2 = "select cantidad from inventario where claveProducto='"+res.getString(1)+"'";
                    PreparedStatement pstm2 = mimodelo.getConexion().prepareStatement(q2);
                    ResultSet res2 = pstm2.executeQuery();
                    while (res2.next()){
                        //System.out.println(res.getString(1)+" - "+res1.getString(1)+" - "+res2.getString(1));
                        if(!res2.getString(1).equals(res1.getString(1))){
                            System.out.println(res.getString(1)+"---"+res1.getString(1) );
                        }
                    }
                    
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    
    
    
    
    }

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
        a.bd();
        
        
    }
    public prueba(){
        
    }
    
    public void bd(){
        mimodelo.bp("base_de_datos");
    }
    
    
    
    public void clavesentrada(){
        String q = "select distinct (clave) from vw_infocard";
        try {
                PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                while (res.next()){
                    String  q2 ="select sum(cantidad) from vw_infocard where clave='"+res.getString(1)+"'";
                    PreparedStatement pstm2 = mimodelo.getConexion().prepareStatement(q2);
                    ResultSet res2 = pstm2.executeQuery();
                    while(res2.next()){
                        String query=" UPDATE  inventario  SET  cantidad =  '"+res2.getString(1)+"' WHERE claveProducto =  '"+res.getString(1)+"' ;";
                        System.out.println(query);
                        try{
                            PreparedStatement pstm3 = mimodelo.getConexion().prepareStatement(query);
                            pstm3.execute();
                            pstm3.close();
                        }catch(SQLException e){
                            
                        }
                    }
                    
                    
                }
                return;
            }catch(SQLException e){
                
                return;
            }
    }
    
    
    
    }

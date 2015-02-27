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
        String q = "select distinct (claveProducto) from inventario";
        try {
            PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while (res.next()){
                mimodelo.sumarexistencia(res.getString(1));
            }
            }catch(SQLException e){
            }
    }
    
    
    
    }

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
        a.bp("asa");
    }
    
    public ResultSet buscadescr() {
        String q = "SELECT idproductos,descripcion FROM productos";
        try {
                PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    public boolean opp(int id, String descr) {
      String q=" UPDATE  productos SET descripcion='"+descr+"' where idproductos ='"+id+"';";
        try{
            PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    public void bp(String fecha){
        database.backupbd(fecha);
    }
    
    }

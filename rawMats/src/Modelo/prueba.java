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
        mimodelo.bp("prubeapru-pppppppAAas");
    }
    
    public void consumir(String claveProducto,Double conscant,String fecha) {
        try {
            ResultSet existenciaactual = this.buscarExistenciaProductofecha(claveProducto, fecha);
            while(existenciaactual.next()){
                Double exiscant = existenciaactual.getDouble("cantidad");
                if(conscant>exiscant){
                    System.out.println("no hay suficiente existencia");
                    return;
                }else{
                        Peps3(claveProducto,conscant);
                        this.sumarexistencia(claveProducto);
                        System.out.println(costoconsumo);
                        System.out.println(entradas);
                    }
                }
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clavesentrada(){
        String q = "select distinct (claveproducto) from detalleentrada";
        try {
                PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                while (res.next()){
                    this.sumarexistencia(res.getString(1));
                    mimodelo.costoprom(res.getString(1));
                }
                return;
            }catch(SQLException e){
                
                return;
            }
    }
    
    
    
    Double newtemcant,costoconsumo;
    String entradas;
    public void Peps3(String claveProducto, Double conscant) {
        try {
            ResultSet primeraentrada = primeraentrada(claveProducto);
            while(primeraentrada.next()){
                int identrada= primeraentrada.getInt("id");
                Double canttemp = primeraentrada.getDouble("canttemp");
                Double costoentrada = primeraentrada.getDouble("costo");
                newtemcant = canttemp-conscant;
                if(newtemcant<0){
                    //falta consumo
                    this.updateteporalde(0, identrada);
                    newtemcant=newtemcant*-1;
                    costoconsumo = costoconsumo +(costoentrada *canttemp);
                    Peps3(claveProducto,newtemcant);
                    entradas+=identrada+","+canttemp+",/";
                }else{
                    costoconsumo = costoconsumo +(costoentrada *conscant);
                    entradas+=identrada+","+conscant+",/";
                    this.updateteporalde(newtemcant, identrada);
                    return;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet primeraentrada(String claveProducto) {
        String q = "select iddetalleentrada as id, cantidadtemporal as canttemp,costo from detalleentrada where claveProducto = '"+claveProducto+"' and cantidadTemporal>0 limit 1";
        try {
                PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }  
    
    public ResultSet buscaentrada(int identrada) {
        String q = "select id_detalleentrada as id, cantidadtemporal as canttemp  from detalleentrada where iddetalleentrada='"+identrada+"' and total_temporal>0 limit 1";
        try {
                PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }  
   
    public boolean updateteporalde(double cantidadtemporal,int id) {
        String q=" UPDATE  `detalleentrada` SET  `cantidadtemporal` =  '"+cantidadtemporal+"' WHERE `identrada` =  '"+id+"' ;";
        try{
            PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage() + "------");
            return false;
        }
    }
    
    public ResultSet buscarExistenciaProductofecha(String claveProducto,String fecha) {
        String q = "call sumaxistencia_fecha('"+claveProducto+"','"+fecha+"');";
        try {
                PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    public boolean sumarexistencia(String claveproducto) {
        String q1="CALL sumaexistencia('"+claveproducto+"')";//aqui es sumaexistenca ....
        System.out.println(q1);
         try{
            PreparedStatement pstm = mimodelo.getConexion().prepareStatement(q1);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    public void antipeps3(String entradas){
        String entrada[] = entradas.split("/");
        for(int i=0;i<entrada.length;i++){
            try {
                System.out.println(entrada[i]);
                String detalleentrada[]=entrada[i].split(",");
                int identrada= Integer.parseInt(detalleentrada[0]);
                Double cant= Double.parseDouble(detalleentrada[1]);
                Double temcant=0.0;
                ResultSet buscaentrada = this.buscaentrada(identrada);
                while(buscaentrada.next()){
                    temcant=buscaentrada.getDouble("canttemp");
                }
                Double newtemcant = temcant+cant;
                this.updateteporalde(newtemcant, identrada);
            } catch (SQLException ex) {
                Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Irving
 */
public class modelo extends database{
    public modelo(){} //constructor de la clase.
    
    public boolean conexionbd() throws SQLException {
        return this.getConexion().isClosed();
    }
    public ResultSet buscarUser1(String nombre) throws java.sql.SQLException{       
        String q = "SELECT pswd,id_responsable,cargo_id_cargo,nombre,usuario,Activo FROM responsable where usuario='"+nombre+"' and Activo=1 and sesion<>1 " ;
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                System.out.println(q);
                return null;
            }
    }
    public boolean abrirsesion(String nombre) throws java.sql.SQLException{       
        String q = "update responsable set sesion=1 where usuario ='"+nombre+"';" ;
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return true;
            }
    }
    public boolean registraracceso(String user, String fech, String horaentrada) {
           String q = "INSERT INTO `reporusuario` (`id_ReporUsuario`, `NombreEmpleado`, `Fecha`, `HoraEntrada`, `HoraSalida`)VALUES (NULL,'"+user+"','"+fech+"','"+horaentrada+"',' ')"; 
           try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
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
    public boolean cerrarsesion(String nombre) throws java.sql.SQLException{       
        String q = "update responsable set sesion=0 where usuario ='"+nombre+"';" ;
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return true;
            }
    }
    public boolean horasalida(String horasalida, String user) {
       try{
           String q1 ="SELECT MAX(id_reporusuario) from reporusuario where nombreempleado='"+user+"';";
            PreparedStatement pstm = this.getConexion().prepareStatement(q1);
            ResultSet res = pstm.executeQuery();
            String id="";
            while(res.next()){
                id=res.getString(1);
            }
            String q=" UPDATE  `dis_paper`.`reporusuario` SET  `HoraSalida` =  '"+horasalida+"' WHERE `reporusuario`.`id_reporusuario` =  '"+id+"';";
            PreparedStatement pstm2 = this.getConexion().prepareStatement(q);
            pstm2.execute();
            pstm2.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    
}

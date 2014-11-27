/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
        String q = "SELECT pswd,id_responsable,cargo_id_cargo,nombre,usuario,Activo,Sesion FROM responsable where usuario='"+nombre+"'" ;
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
    
    public boolean newArea(String Nombre) {
        String q = "INSERT INTO `Area` (idarea,`descripcion`) VALUES (null,'"+Nombre+"');";
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
    public boolean newMaquina(String Nombre, int idarea) {
        String q = "INSERT INTO `Maquina` (idmaquina,idarea,descripcion) VALUES (null,'"+idarea+"','"+Nombre+"');";
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
    public ResultSet buscarArea() throws java.sql.SQLException{       
        String q = "SELECT descripcion FROM area order by descripcion ;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    public ResultSet buscaridArea(String nombre){ 
        String q = "SELECT idarea as id FROM area where descripcion='"+nombre+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    public ResultSet buscaridMaquina(String nombre){       
        String q = "SELECT idmaquina as id FROM maquina where descripcion='"+nombre+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    public ResultSet buscarMaquina(int idarea) throws java.sql.SQLException{       
        String q = "SELECT descripcion FROM maquina where idarea ='"+idarea+"' order by descripcion ;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }

    public boolean cerrarsesiones(int SesionCerrada) {
            String q=" UPDATE  `dis_paper`.`responsable` SET Sesion ='"+SesionCerrada+"';";
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

    public boolean newaltausuario(String nombreus, String usuario, String contraseña, int nivel, int bloqueo) {
        String q = "INSERT INTO  `responsable` (`id_responsable` ,`CARGO_id_cargo` ,`nombre` ,`usuario` ,`pswd`,`Activo`,Sesion,_cntr)"
                                                              + "VALUES (NULL,'"+nivel+"','"+nombreus+"','"+usuario+"',MD5('"+contraseña+"'),'"+bloqueo+"','0','"+contraseña+"');";         
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            if(e.getMessage().equals("Duplicate entry '"+usuario+"' for key 'usuario'")){
                JOptionPane.showMessageDialog(null, "Ya existe el usuario " + usuario);
            }else{
               System.err.println(e.getMessage()); 
            }
            return false;
        }
    }

    public ResultSet buscarUser(String nombre) throws java.sql.SQLException{       
        String q = "SELECT pswd,id_responsable,cargo_id_cargo,nombre,usuario,Activo,_cntr FROM responsable where usuario='"+nombre+"' and usuario <>'root'" ;
        String pswd="";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }

    public boolean bloquearusuario(String bloquearuser) {
        String q ="UPDATE  `responsable` SET  `Activo` =  '0' WHERE  `responsable`.`usuario` ='"+bloquearuser+"'";
        //UPDATE  `dis_paper`.`responsable` SET  `Activo` =  '0' WHERE  `responsable`.`id_responsable` =6;
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

    public boolean modificaruser(String nombreus, String usuario, String contraseña, int nivel, int bloqueo,String modificaruser) {
        String q=" UPDATE  `responsable` SET  `CARGO_id_cargo` =  '"+nivel+"',`nombre` =  '"+nombreus+"',`usuario` = '"+usuario+"',`pswd` =  MD5('"+contraseña+"'),`Activo` =  '"+bloqueo+"',_cntr='"+contraseña+"' WHERE  `responsable`.`usuario` ='"+modificaruser+"';";                
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

    public boolean cambiocontrasena(String newpswd1,String newpswd2, int id_responsable) {
        String q ="UPDATE  `responsable` SET  `pswd` =  '"+newpswd1+"', `_cntr` = '"+newpswd2+"'  WHERE  `responsable`.`id_responsable` ="+id_responsable;
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

    public ResultSet buscartodoreporte(){  
        
        String q = "SELECT nombreempleado as Nombre_de_Empleado, CONCAT( fecha,' - ',horaentrada) AS Fecha_y_Hora_de_Ingreso, CONCAT( fecha,' - ',horasalida) AS Fecha_y_Hora_de_Salida FROM `reporusuario`;";
        //          SELECT nombreempleado, CONCAT( fecha,horaentrada) AS ingreso, CONCAT( fecha,horasalida) AS salida FROM `reporusuario` WHERE nombreempleado =  'jhafet' LIMIT 0 , 30
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    public ResultSet buscarReporteUser(String uusuario){  
        
        String q = "SELECT nombreempleado as Nombre_de_Empleado, CONCAT( fecha,' - ',horaentrada) AS Fecha_y_Hora_de_Ingreso, CONCAT( fecha,' - ',horasalida) AS Fecha_y_Hora_de_Salida FROM `reporusuario` WHERE nombreempleado='"+uusuario+"'";
        //          SELECT nombreempleado, CONCAT( fecha,horaentrada) AS ingreso, CONCAT( fecha,horasalida) AS salida FROM `reporusuario` WHERE nombreempleado =  'jhafet' LIMIT 0 , 30
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    public ResultSet buscarReporteDate(String datee) {
        String q = "SELECT nombreempleado as Nombre_de_Empleado, CONCAT( fecha,' - ',horaentrada) AS Fecha_y_Hora_de_Ingreso, CONCAT( fecha,' - ',horasalida) AS Fecha_y_Hora_de_Salida FROM `reporusuario` WHERE fecha='"+datee+"'";
        //          SELECT nombreempleado, CONCAT( fecha,horaentrada) AS ingreso, CONCAT( fecha,horasalida) AS salida FROM `reporusuario` WHERE nombreempleado =  'jhafet' LIMIT 0 , 30
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    public ResultSet buscarReporteUserDate(String uusuario,String datee){  
        
        String q = "SELECT nombreempleado as Nombre_de_Empleado, CONCAT( fecha,' - ',horaentrada) AS Fecha_y_Hora_de_Ingreso, CONCAT( fecha,' - ',horasalida) AS Fecha_y_Hora_de_Salida FROM `reporusuario` WHERE nombreempleado='"+uusuario+"' and fecha ='"+datee+"'";
        //          SELECT nombreempleado, CONCAT( fecha,horaentrada) AS ingreso, CONCAT( fecha,horasalida) AS salida FROM `reporusuario` WHERE nombreempleado =  'jhafet' LIMIT 0 , 30        
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                System.err.println( e.getMessage() );                
                return null;
                
            }
    }

    public ResultSet buscardor(String des) {
        String q = "Select clave, descripcion from productos where descripcion like '%"+des+"%' or clave like '%"+des+"%';";
        //          Select clave, descripcion from productos where descripcion like '% %'
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                System.err.println( e.getMessage() );
                return null;
            }
    }

    public boolean newaltaproveedor(String Nombre, String Direccion, String telefono, String RfC) {
         String q = "INSERT INTO `proveedores` (`idproveedores`, `Nombre`, `Direccion`, `Telefono`, `RFC`)"
                  + "VALUES (NULL,'"+Nombre+"','"+Direccion+"','"+telefono+"','"+RfC+"')";        
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){            
               if(e.getMessage().equals("Duplicate entry '"+Nombre+"' for key 'Nombre'")){                   
                JOptionPane.showMessageDialog(null, "Ya Existe Este Proveedor: " +Nombre);
            }else{
               System.err.println(e.getMessage()); 
            }            
            return false;
        }
    }

    public boolean newaltacliente(String Nombre, String Direccion, String telefono, String RfC) {
        String q = "INSERT INTO `clientes` (`id_clientes`, `Nombre`, `Direccion`, `Telefono`, `RFC`)"
                  +"VALUES (NULL,'"+Nombre+"','"+Direccion+"','"+telefono+"','"+RfC+"')";         
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){            
               if(e.getMessage().equals("Duplicate entry '"+Nombre+"' for key 'Nombre'")){                   
                JOptionPane.showMessageDialog(null, "Ya Existe Este Cliente: " +Nombre);
            }else{
               System.err.println(e.getMessage()); 
            }            
            return false;
        }
    }       

    public boolean nuevopropiedad(String PROPIEDAD) {
        String q ="INSERT INTO propietarios (nombre) values ('"+PROPIEDAD+"');";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
        return false;
    }    

    public boolean nuevatipoentrada(String tipoEntra) {
        String q = "INSERT INTO  `tipo_entrada` (`id_tipo_en` ,`Tipo_Entrada`)VALUES (NULL, '"+tipoEntra+"')";                 
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            if(e.getMessage().equals("Duplicate entry '"+tipoEntra+"' for key 'Tipo_Entrada'")){
                JOptionPane.showMessageDialog(null, "Ya Existe la Entrada: " +tipoEntra);
            }else{
               System.err.println(e.getMessage()); 
            }
            return false;
        }   
    } 
    public ResultSet buscarProductos(String likeproductos) throws java.sql.SQLException{       
        String q = "select clave from productos where clave like  '"+likeproductos+"%' order by clave desc limit 1 ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }

    public boolean NuevaEntrada(String FolioE, String DocumentoE, String TipoE, String PropietarioE, String ProvedorE, String OrdenProducionE, String OrdenCompraE, String ClienteE, String t1, String t2, String t3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


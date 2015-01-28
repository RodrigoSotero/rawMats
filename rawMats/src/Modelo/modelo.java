/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

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
            String q=" UPDATE  `reporusuario` SET  `HoraSalida` =  '"+horasalida+"' WHERE `reporusuario`.`id_reporusuario` =  '"+id+"';";
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
    public ResultSet buscarMaquina() throws java.sql.SQLException{       
        String q = "SELECT descripcion FROM maquina order by descripcion ;";
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
        String q = "Select clave, descripcion from vw_infoproducto where descripcion like '%"+des+"%' or clave like '%"+des+"%' order by clave;";
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
         String q = "INSERT INTO `proveedores` (`id_proveedores`, `Nombre`, `Direccion`, `Telefono`, `RFC`)"
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
    public ResultSet buscarProducto(String clave) throws java.sql.SQLException{       
        String q = "select * from vw_infoproducto where clave = '"+clave+"';";
        System.out.println(q);
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
                }
    }
    public ResultSet buscarProductoByDescripcion(String descripcion) throws java.sql.SQLException{       
        String q = "select * from vw_descripcionproductos where descripcion like '%"+descripcion+"%';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    
    public boolean nuevoProducto(int area, int maquina,String clave,String descripcion, int max, int min) {
        String q = "INSERT INTO `rawmats`.`productos` (`idproductos`, `area`, `maquina`, `clave`, `descripcion`, `max`, `min`) VALUES (null, '"+area+"', '"+maquina+"', '"+clave+"', '"+descripcion+"', '"+max+"', '"+min+"');";                 
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }   
    }
    public ResultSet bucarMaxEntrada() {
        String q = "SELECT MAX( identrada ) FROM entrada";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    
    public ResultSet buscarExistenciaProducto(String claveProducto) {
        String q = "SELECT * FROM inventario where claveProducto='"+claveProducto+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    
    public boolean nuevaexistencia(String clave) {
      String q ="INSERT INTO  `inventario` (`idinventario` ,`claveProducto` ,`cantidad`,costopromedio,ubicacion)VALUES (NULL ,  '"+clave+"', '0','0','');";
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

    public boolean altaDetalleEntrada(int id_entrada, String claveproducto, String descripcion, String unidad_m, int cantidadentrada, String ubicacion, String costo, String totalcosto) {
        //INSERT INTO `rawmats`.`detalleentrada` (`iddetalleEntrada`, `idEntrada`, `claveProducto`, `descripcion`, `Ubicacion`, `cantidad`, `unidadMedida`, `costo`, `totalcosto`, `CantidadTemporal`, `costopromedio`) VALUES ('1', '1', 'clave', 'desc', 'ubic', '100', 'pz', '1.02', '102.0', '100', '1.02');
        String q ="INSERT INTO `rawmats`.`detalleentrada` (`iddetalleEntrada`, `idEntrada`, `claveProducto`, `descripcion`, `Ubicacion`, `cantidad`, `unidadMedida`, `costo`, `totalcosto`, `CantidadTemporal`, `costopromedio`) VALUES"
                + " (null, '"+id_entrada+"', '"+claveproducto+"', '"+descripcion+"', '"+ubicacion+"', '"+cantidadentrada+"', '"+unidad_m+"', '"+costo+"', '"+totalcosto+"', '"+cantidadentrada+"', '"+costo+"');";
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

    public boolean sumarexistencia(String claveproducto) {
        String q1="CALL sumaexistencia('"+claveproducto+"')";//aqui es sumaexistenca ....
         try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q1);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean ubicacion(String claveproducto, String ubicacion) {
      String q=" UPDATE  inventario SET ubicacion='"+ubicacion+"' where claveproducto ='"+claveproducto+"';";
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

    public boolean altaEntrada(String FolioE, String DocumentoE, String TipoE, String PropietarioE, String ProveedorE, String OrdenProducionE, String OrdenCompraE, String ClienteE, String t1, String t2, String t3, int id_responsable, String fechaentrada, String Obs) {
        //INSERT INTO `rawmats`.`entrada` (`identrada`, `folioe`, `documentoE`, `tipoE`, `propietarioE`, `provedorE`, `OP`, `OC`, `responsable`, `t`folioe`, `documentoE`, `tipoE`, `propietarioE`, `provedorE`, `OP`, `O1`, `t2`, `t3`, `observaciones`, `fecha`) VALUES ('1', 'folio', 'docu', '1', '1', '1', 'op', 'oc', '1', 't1', 't2', 't3', 'obset', 'fech');
        String q = "INSERT INTO `rawmats`.`entrada` ( `folioe`, `documentoE`, `tipoE`, `propietarioE`, `provedorE`, `OP`, `OC`, `responsable`, `Cliente`, `t1`, `t2`, `t3`, `observaciones`, `fecha`) VALUES "
                + "( '"+FolioE+"', '"+DocumentoE+"', '"+TipoE+"', '"+PropietarioE+"', '"+ProveedorE+"', '"+OrdenProducionE+"', '"+OrdenCompraE+"', '"+id_responsable+"', '"+ClienteE+"', '"+t1+"', '"+t2+"', '"+t3+"', '"+Obs+"', '"+fechaentrada+"');";  
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
    
    public ResultSet buscaDocumentoEntrada(String eval) {
        String q = "SELECT * FROM documento_entrada where documento='"+eval+"'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    public boolean altaDocEntrada(String eval){
         String q1 = "INSERT INTO `documento_entrada` (`Documento`) VALUES ('"+eval+"');";
         try{
             PreparedStatement pstm1 = this.getConexion().prepareStatement(q1);
             pstm1.execute();
             pstm1.close();
             return true;
         }
         catch(SQLException e){
             return false;
         }
     }
    public boolean costopromedio(String clave) {
        String q1="CALL preciopromedio('"+clave+"')";
         try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q1);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    public ResultSet buscarPropiedad(String parametro,boolean like) {
        String q = like ==true? "SELECT id_propietarios as id, nombre as descripcion  FROM `propietarios` where nombre like '%"+parametro+"%';": "SELECT id_propietarios as id, nombre FROM `propietarios` where nombre = '"+parametro+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    public ResultSet buscaTipoEntrada(String parametro,boolean like) {
        String q = like ==true? "SELECT id_tipo_en as id, tipo_entrada as descripcion FROM TIPO_ENTRADA where tipo_entrada like '%"+parametro+"%' ":"SELECT id_tipo_en as id, tipo_entrada FROM TIPO_ENTRADA where tipo_entrada = '"+parametro+"' ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    public ResultSet buscaProveedor(String parametro,boolean like) {
        String q = like==true? "SELECT id_proveedores as id,nombre as descripcion  from proveedores where nombre like '%"+parametro+"%'":"SELECT id_proveedores as id,nombre from proveedores where nombre= '"+parametro+"'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    public ResultSet buscarCliente(String parametro, boolean like) {
        String q = like==true? "SELECT id_clientes as id,nombre as descripcion  FROM `clientes` where nombre like '%"+parametro+"%'" : "SELECT id_clientes as id,nombre FROM `clientes` where nombre ='"+parametro+"'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
   
     public String buscarFolioEntrada() throws java.sql.SQLException{
         
        Calendar Cal= Calendar.getInstance();
        int anio=Integer.parseInt(Cal.get(Cal.YEAR)+"");
        
        try {
            String id = "SELECT folioE from entrada order by identrada desc limit 1 ";
            PreparedStatement pstm = this.getConexion().prepareStatement(id);
            ResultSet res = pstm.executeQuery();
            if(!res.next()){
                return "ENT"+anio+"-1";
            }
            res.beforeFirst();
            while(res.next()){
                String folio = res.getString("folioE");
                if(folio==null||folio.isEmpty()){
                    folio= "ENT"+anio+"-1";
                }else{
                    
                    String aniofolio = folio.substring(3);
                    String[] aniof = aniofolio.split("-");
                    if(Integer.parseInt(aniof[0])<anio){
                        int fol = Integer.parseInt(folio.replace("ENT"+aniof[0]+"-", ""));
                        int ano = Integer.parseInt(folio.substring(3, 7));
                        if(anio>ano) fol=1; else fol++;
                        folio= "ENT"+anio+"-"+fol; 
                    }else
                    if(Integer.parseInt(aniof[0])==anio){
                        int fol = Integer.parseInt(folio.replace("ENT"+aniof[0]+"-", ""));
                        int ano = Integer.parseInt(folio.substring(3, 7));
                        if(anio>ano) fol=1; else fol++;
                        folio= "ENT"+anio+"-"+fol; 
                    }
                }             
                return folio;
           }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            if(e.getMessage().equals("Illegal operation on empty result set.")){
                return "ENT"+anio+"-1";
            }else{
                return null;
            }
        }
        return null;
    }
     public String buscarFolioSalida() throws java.sql.SQLException{
        Calendar Cal= Calendar.getInstance();
        int anio=Integer.parseInt(Cal.get(Cal.YEAR)+"");
        try {
            String id = "SELECT folio from salida order by id_salida desc limit 1  ";
            PreparedStatement pstm = this.getConexion().prepareStatement(id);
            ResultSet res = pstm.executeQuery();
            if(!res.next()){
                return "SAL"+anio+"-1";
            }
                res.beforeFirst();
                while(res.next()){
                String folio = res.getString("folio");
                System.err.println(folio);
                if(folio==null||folio.isEmpty()){
                    folio= "SAL"+anio+"-1";
                }else{
                    String aniofolio = folio.substring(3);
                    String aniof[] = aniofolio.split("-");
                    if(Integer.parseInt(aniof[0])<anio){
                        int fol = Integer.parseInt(folio.replace("SAL"+aniof[0]+"-", ""));
                        System.err.println(fol);
                        int ano = Integer.parseInt(folio.substring(3, 7));
                        if(anio>ano) fol=1; else fol++;
                        folio= "SAL"+anio+"-"+fol;
                    }else
                     if(Integer.parseInt(aniof[0])==anio){
                        int fol = Integer.parseInt(folio.replace("SAL"+aniof[0]+"-", ""));
                        System.err.println(fol);
                        int ano = Integer.parseInt(folio.substring(3, 7));
                        if(anio>ano) fol=1; else fol++;
                        folio= "SAL"+anio+"-"+fol;
                    }                   
                     
                }               
                return folio;
            
               }
            }catch(SQLException e){
                if(e.getMessage().equals("Illegal operation on empty result set.")){
                    return "SAL"+anio+"-1";
                }else{
                    //return "ENT"+anio+"-1";
                    return null;
                }
            }
        return null;
    }
    
   
     public ResultSet buscaClaveProductoSalir(String parametro) {
        String q = "select claveproducto from inventario where claveproducto like '%"+parametro+"%' and cantidad >0;";
        System.out.println(q);
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    public ResultSet buscaClaveProducto(String parametro) {
        String q = "select claveproducto from inventario where clavePRODUCTO like '%"+parametro+"%' ;";
        System.out.println(q);
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    public ResultSet buscaDescripcionProducto(String parametro) {
        String q = "select descripcion,clave from productos where descripcion like '%"+parametro+"%' ;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
    public ResultSet ultimocosto(String clave) {
        String q = "select costo,ubicacion,unidadmedida from detalleentrada where claveproducto='"+clave+"' order by iddetalleentrada desc limit 1";
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
   
    public ResultSet buscaTipoSalida(String parametro,boolean like) {
        String q = like ==true? "SELECT idtipo_salida as id, tipo_salida as descripcion FROM tipo_salida where tipo_salida like '%"+parametro+"%' ":"SELECT idtipo_salida as id, tipo_salida as descripcion FROM tipo_salida where tipo_salida = '"+parametro+"' ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }    

    public ResultSet buscaArea(String parametro, boolean like) {
        String q = like ==true? "SELECT idarea as id, descripcion as descripcion FROM area where descripcion like '%"+parametro+"%' ":"SELECT idarea as id, descripcion as descripcion FROM area where descripcion = '"+parametro+"' ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                return null;
            }
    }
     public boolean nuevatiposalida(String tipoSali) {
        String q = "INSERT INTO  `tipo_salida` (`idtipo_salida` ,`tipo_salida`)VALUES (NULL, '"+tipoSali+"')";                 
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            if(e.getMessage().equals("Duplicate entry '"+tipoSali+"' for key 'tipo_salida_UNIQUE'")){
                JOptionPane.showMessageDialog(null, "Ya Existe la Entrada: " +tipoSali);
            }else{
               System.err.println(e.getMessage()); 
            }
            return false;
        } 
     }
      public boolean nuevaarea(String newArea) {
        String q = "INSERT INTO  `area` (`idarea` ,`descripcion`)VALUES (NULL,'"+newArea+"')";                 
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            if(e.getMessage().equals("Duplicate entry '"+newArea+"' for key 'descripcion_UNIQUE'")){
                JOptionPane.showMessageDialog(null, "Ya Existe la Entrada: " +newArea);                
            }else{
               System.err.println(e.getMessage()); 
            }
            return false;
        } 
     }
    //MAS HUEVOS
    public ResultSet buscarEntrada(String buscarfolio) {
        String q = "SELECT * FROM entrada where folioe = '"+buscarfolio+"'; ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscaTipoEntrada(int id) {
        String q = "SELECT id_tipo_en as id, tipo_entrada as descripcion FROM TIPO_ENTRADA where id_tipo_en='"+id+"' ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarPropiedad(int id) {
        String q = "SELECT id_propietarios as id, nombre as descripcion  FROM `propietarios` where id_propietarios='"+id+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscaProveedor(int id) {
        String q ="SELECT id_proveedores as id,nombre as descripcion  from proveedores where id_proveedores='"+id+"'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarCliente(int id) {
        String q = "SELECT id_clientes as id,nombre as descripcion  FROM `clientes` where id_clientes = '"+id+"'" ;
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscaTipoSalida(int id) {
        String q = "SELECT idtipo_salida as id, tipo_salida as descripcion from tipo_salida where idtipo_salida="+id+" ;";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarDetalleEntrada(int idfolio) {
        String q = "SELECT * FROM detalleentrada where identrada = '"+idfolio+"'; ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarExistenciaPapel(String clavePapel) {
        String q = "SELECT * FROM inventario where claveProducto='"+clavePapel+"';";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
   
    public boolean updateteporalde(int cantidadtemporal,String id) {
        String q=" UPDATE  `detalleentrada` SET  `cantidadtemporal` =  '"+cantidadtemporal+"' WHERE `identrada` =  '"+id+"' ;";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage() + "nuevexis");
            return false;
        }
    }

    public void modifDetalleEntrada(int identrada, String claveproducto, String descripcion, String ubicacion, String cantidad, String unidad_m, String costo, String totalcosto) {
        String q="UPDATE detalleentrada set "
                + "Claveproducto='"+claveproducto+"',"
                + "descripcion='"+descripcion+"',"
                + "ubicacion='"+ubicacion+"',"
                +"cantidad='"+cantidad+"', "
                +"unidadmedida='"+unidad_m+"',"
                +"costo='"+costo+"', "
                +"totalcosto='"+totalcosto+"', "
                +"cantidadtemporal='"+cantidad+"', "
                +"costopromedio='"+costo+"' where iddetalleentrada='"+identrada+"';";
        
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
        }
    }
    
    public boolean modifEntrada(String folioentrada,String t1, String t2, String t3,String ordenProduccion,String ordenCompra,String documentoEntrada,String propietario,String proveedor, String responsable,String fecha,String tipoentrada,String Observaciones,String cliente){
        System.out.println();
        String q1="UPDATE `entrada` SET `T1`='"+t1+"', "
                + "`T2`='"+t2+"', "
                + "`T3`='"+t3+"', "
                + "`op`='"+ordenProduccion+"', "
                + "`oc`='"+ordenCompra+"', "
                + "`documentoE`='"+documentoEntrada+"', "
                + "`propietarioE`='"+propietario+"', "
                + "`provedorE`='"+proveedor+"', "
                + "`responsable`='"+responsable+"', "
                + "`fecha`='"+fecha+"', "
                + "`tipoE`='"+tipoentrada+"', "
                + "`observaciones`='"+Observaciones+"', "
                + "`cliente`='"+cliente+"' "
                + "WHERE `folioe`='"+folioentrada+"';";
        System.out.println(q1);
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q1);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
    }
    
    public ResultSet buscarExistenciaProductofecha(String clavePapel,String fecha) {
        String q = "call sumaxistencia_fecha('"+clavePapel+"','"+fecha+"');";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet bucarMaxSalida() {
        String q = "SELECT MAX( id_salida ) FROM salida";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarPrimeraEntrada2(String clave,String fec) {
        String q = "select d.iddetalleentrada as id, claveproducto,cantidadtemporal as cantidad,min(fecha) as fecha_entrada from detalleentrada d, entrada e where claveproducto='"+clave+"' and e.identrada=d.identrada  and fecha<='"+fec+"' and(cantidadtemporal>0) ;";

        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public boolean nuevacantidadtemporal(String id,int cantidad) {
        String q1="UPDATE `detalleentrada` SET cantidadtemporal='"+cantidad+"' where iddetalleentrada='"+id+"'";
         try{

            PreparedStatement pstm = this.getConexion().prepareStatement(q1);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }
    }

    public boolean altaDetalleSalida(int id_salida, String claveProducto, String Descripcion, String Ubicacion, int cantidad_salida, String Unidad, Double costo, Double Totalcosto, String identradas_) {
        String q ="INSERT INTO `detallesalida` (`id_salida`, `claveproduto`, `descripcion`, `ubicacion`, `cantidad_salida`, `unidad`, `costo`, `totalcosto`, `entradas`) VALUES"
                + " ('"+id_salida+"', '"+claveProducto+"', '"+Descripcion+"', '"+Ubicacion+"', '"+cantidad_salida+"', '"+Unidad+"', '"+costo+"', '"+Totalcosto+"', '"+identradas_+"');";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage()+" ALTA DETALLE SALIDA");
            return false;
        }
    }

    public boolean altaSalida(String FolioS, String DocumentoS, int TipoS, String OrdenProducionS, String Solicitante, int AreaSalida, String t1, String t2, String t3, String fecha, String Obs, int id_responsable) {
        String q = "INSERT INTO `rawmats`.`salida` (`folio`, `TURNO1`, `TURNO2`, `TURNO3`, `orden_produccion`, `Fecha`, `tipo_salida`, `documento_salida`, `solicitante`, `id_area`, `id_responsable`, `observaciones`) "
                + "VALUES ('"+FolioS+"', '"+t1+"', '"+t2+"', '"+t3+"', '"+OrdenProducionS+"', '"+fecha+"', '"+TipoS+"', '"+DocumentoS+"', '"+Solicitante+"', '"+AreaSalida+"', '"+id_responsable+"', '"+Obs+"');";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            System.err.println(e.getMessage()+" ALTA SALIDA");
            return false;
        }
    }
        JasperReport archivo ;
            JasperPrint jasperPrint;
    public void abrirReporte(String report,HashMap map){
        try{
            String in ="C:/rawMats/Reportes/"+report;
            archivo= JasperCompileManager.compileReport(in);
            jasperPrint = JasperFillManager.fillReport(archivo,map,this.getConexion());
            java.util.List<JRPrintPage> pages = jasperPrint.getPages();
            if(pages.isEmpty()){
                JOptionPane.showMessageDialog(null, "Sin paginas, el reporte no se ha creado...");
                return;
            }
            JasperViewer jviewer= new JasperViewer(jasperPrint,false);
            jviewer.setTitle("Reportes de rawMats");
            jviewer.setVisible(true);
            Dimension screenSize = jviewer.getToolkit().getScreenSize();
            jviewer.setSize(screenSize.width-40, screenSize.height-40);
            jviewer.setIconImage(Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("imagenes/logo.png")));
            jviewer.setLocationRelativeTo(null);
            /*JasperExportManager.exportReportToPdfFile(jasperPrint, "C://IEXSA//"+report+".pdf");
            File f = new File("C://IEXSA//"+report+".pdf");
                        Desktop.getDesktop().open(f);*/
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
     public boolean updateProducto(int area, int maquina,String clave,String descripcion, int max, int min) {
        String q = "update productos set area="+area+", descripcion='"+descripcion+"', max="+max+", min="+min+" where clave ='"+clave+"';";                 
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        }catch(SQLException e){
            
            return false;
        }   
    }
     
     public ResultSet algo() {
        String q = "select * from vw_descripcionproductos";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }

    public ResultSet buscarSalida(String buscarfolio) {
        String q = "select * from vw_infosalida where folio ='"+buscarfolio+"' limit 1";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarDetalleSalida(int idfolio) {
        String q = "SELECT * FROM detallesalida where id_salida = '"+idfolio+"'; ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    public ResultSet buscarEntradaID(String id) {
        String q = "SELECT cantidad,cantidadtemporal FROM detalleentrada where iddetalleentrada="+id+"; ";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(SQLException e){
                
                return null;
            }
    }
    
    public ResultSet Consulta(String consulta) {
        String q = consulta;
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                
                return null;
            }
    }

    public ResultSet buscaSolicitante(String sol) {
        String q = "select distinct solicitante from salida where solicitante like '%"+sol+"%'";
        try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                ResultSet res = pstm.executeQuery();
                return res;
            }catch(Exception e){
                
                return null;
            }
    }
    public boolean bajaTipoSalida(int id) {
            String q="delete from tipo_salida where idtipo_salida='"+id+"'";
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
    public boolean bajaTipoEntrada(int id) {
            String q="delete from tipo_entrada where id_tipo_en='"+id+"'";
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
    public boolean bajaPropietarios(int id) {
            String q="delete from propietarios where id_propietarios='"+id+"'";
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
    public boolean bajaProveedor(int id) {
            String q="delete from proveedores where id_provedores='"+id+"'";
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
    public boolean bajaClientes(int id) {
            String q="delete from clientes where id_clientes='"+id+"'";
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

    public boolean bajaArea(int id) {
        String q="delete from area where idarea='"+id+"'";
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
    public boolean bajaMaquina(int id) {
        String q="delete from maquina where idmaquina='"+id+"'";
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
    
    public boolean tottemp(int canttem,String id) {
            String q=" UPDATE  detalleentrada SET cantidadtemporal='"+canttem+"' where iddetalleentrada ='"+id+"';";
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
    
    // de aqui pabajo
}


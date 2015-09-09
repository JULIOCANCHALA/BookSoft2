package booksoft2;

import java.sql.*;//importo todo
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julio
 */

public class BookSoft2 {

    public static void main(String[] args) {
        
        String user= "sql289524";//internet
        String pasword="sK8!qL6%";//internet
        Scanner teclado=new Scanner(System.in);

            
        try {    
            System.out.println("Intentando conectar a la base de datos..");
            Class.forName("com.mysql.jdbc.Driver");       
            
            
            //Connection con=DriverManager.getConnection("jdbc:mysql://localhost/contactos",user,pasword);            
            Connection con=DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net/sql289524",user,pasword);//para internet      
            System.out.println("Conexion exitosa...");           
            
            
            
        //Cargar todos los contactos
            Statement estado=con.createStatement(); 
            System.out.println("Impresion de tabla:"+"\n");
            ResultSet resultado=estado.executeQuery("SELECT * FROM  `registro`");//traemos la tabla 
            
            
            while (resultado.next())
            {//imprime todas las casillas de la tabla
                System.out.println("Codigo: "+resultado.getString("codigo")+"\n"+
                        "Nombre: "+resultado.getString("nombre")+"\n"+
                        "Autor: "+resultado.getString("autor")+"\n"+
                        "Area: "+resultado.getString("area")+"\n"+
                        "Año: "+resultado.getInt("publicacion")+"\n"+
                        "Cantidad: " +resultado.getInt("cantidad")+"\n");
            }
            
            
        //buscar por nombre
            System.out.println("\n");
            
            System.out.println("Busqueda por Nombre:");
            System.out.println("Digite el nombre a buscar: ");
            String nombre=teclado.nextLine();  
           
            resultado=estado.executeQuery("SELECT * FROM `registro` WHERE `nombre` LIKE '"+nombre+"'");//se tiene qe concatenar la varibale +"nombre"+
            
            while (resultado.next())
            {//imprime todas las casillas de la tabla
                System.out.println("---------------------------------------"+"\n"+
                        "Codigo: "+resultado.getString("codigo")+"\n"+
                        "Nombre: "+resultado.getString("nombre")+"\n"+
                        "Autor: "+resultado.getString("autor")+"\n"+
                        "Area: "+resultado.getString("area")+"\n"+
                        "Año: "+resultado.getInt("publicacion")+"\n"+
                        "Cantidad: " +resultado.getInt("cantidad")+"\n"
                        +"---------------------------------------");
            }
            
            //Ingresar contacto
            System.out.println("Digite el Codigo: ");
            String codigo=teclado.nextLine();
            System.out.println("Digite el Nombre: ");
            String nombre2=teclado.nextLine();
            System.out.println("Digite el Autor: ");
            String autor=teclado.nextLine();
            System.out.println("Digite el Area: ");
            String area=teclado.nextLine();
            System.out.println("Digite el Añor: ");
            int publicacion=teclado.nextInt();
            System.out.println("Digite el Cantidad: ");
            int cantidad=teclado.nextInt();
            estado.executeUpdate("INSERT INTO `registro` VALUES (NULL, '"+codigo+"','"+nombre2+"','"+autor+"','"+area+"','"+publicacion+"','"+cantidad+"')");
            
            System.out.println("Cliente Agregado con exito");
            
            
            //Borrar  contacto
            System.out.println("Digite el nombre: ");
            String nombre3=teclado.nextLine();
            
            estado.executeUpdate("DELETE FROM `registro` WHERE `nombre` LIKE '"+nombre3+"'");
            
            System.out.println("Cliente Eliminado con exito");
                    
            
        } catch (SQLException ex) {//error en la sintaxis de mysql
            System.out.println("Error de mysql");
        }
         catch (Exception e) {//cualquier otro error
            System.out.println("Se ha encontrado un error: "+ e.getMessage());
        }
      
    }
    
}
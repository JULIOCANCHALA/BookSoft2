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
        
        String user= "root";//local
        String pasword="1036661631";//local
        String user2= "sql289524";//internet
        String pasword2="sK8!qL6%";//internet
        Scanner teclado=new Scanner(System.in);

            
        try {    
            System.out.println("Intentando conectar a la base de datos..");
            Class.forName("com.mysql.jdbc.Driver");       
            
            
            //Connection con=DriverManager.getConnection("jdbc:mysql://localhost/contactos",user,pasword);            
            Connection con=DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net/sql289524",user2,pasword2);//para internet      
            System.out.println("Conexion exitosa...");           
            
            
            
        //Cargar todos los contactos
            Statement estado=con.createStatement(); 
            System.out.println("Imprecion de tabla:");
            ResultSet resultado=estado.executeQuery("SELECT * FROM `registro`");//traemos la tabla 
            
            while (resultado.next())
            {//imprime todas las casillas de la tabla
                System.out.println(resultado.getString("id")+ "\t" +resultado.getInt("codigo")
                +"\t"+resultado.getString("nombre")+"\t"+resultado.getString("autor")+"\t"+resultado.getString("area")
                        + "\t" +resultado.getInt("año")+ "\t" +resultado.getInt("cantidad"));
            }
            
            
        } catch (SQLException ex) {//error en la sintaxis de mysql
            System.out.println("Error de mysql");
        }
         catch (Exception e) {//cualquier otro error
            System.out.println("Se ha encontrado un error: "+ e.getMessage());
        }
      
    }
    
}
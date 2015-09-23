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
    
    private Scanner teclado=new Scanner(System.in);
    
    public void menu1()
    {
        System.out.println("REGISTRO BIBLIOTECA MUNICIPAL");
        System.out.println("---------------------------------");
        System.out.println("(1) GESTION DE INFORMACION");
        System.out.println("(2) GESTION DE PRESTAMOS");
        System.out.println("(3) SALIR");
                
    }
    
    public void menu2()
    {
        System.out.println("GESTION DE INFORMACION");
        System.out.println("---------------------------------");
        System.out.println("(1) Ingresar Libro");
        System.out.println("(2) Actualizar Libro");
        System.out.println("(3) Eliminar Libro");
        System.out.println("(4) Buscar Libro");
        System.out.println("(5) Salir");
                
    }

    public void menu3()
    {
        System.out.println("GESTION DE PRESTAMOS");
        System.out.println("---------------------------------");
        System.out.println("(1) Prestar Libro");
        System.out.println("(2) Devolver Libro");
        System.out.println("(3) Libros Prestados");
        System.out.println("(4) Salir");
                
    }
    
    public void limpiar()
    {
        for ( int i=0;i<=2;i++) 
        System.out.println(" "); 
    }
       
    public static void main(String[] args) {
        
    String user= "sql289524";//internet
    String pasword="sK8!qL6%";//internet
    
    try {    
            System.out.println("Intentando conectar a la base de datos..");
            Class.forName("com.mysql.jdbc.Driver");       
            
            Connection con=DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net/sql289524",user,pasword);//para internet      
            System.out.println("Conexion exitosa...");     
    
        BookSoft2 nuevo;
        nuevo=new BookSoft2();
        Scanner teclado=new Scanner(System.in);
        
        int opg=0;
        while(opg==0)
        {
        nuevo.limpiar();
        nuevo.menu1();
        int op=teclado.nextInt();
        while(op==1)
        {
            nuevo.limpiar();
            nuevo.menu2();
            int op2=teclado.nextInt();
            
            switch(op2)
            {
                case 1:
                    //nuevo.ingresar();
                    break;
                case 2:
                    //nuevo.actualizar();
                    break;
                case 3:
                    //nuevo.eliminar();
                    break;
                case 4:
                    //nuevo.buscar();
                    break;
                case 5:
                    op=0;
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;    
            }
            
        }
        
    while(op==2)
        {
            nuevo.limpiar();
            nuevo.menu3();
            
            int op3=teclado.nextInt();
            
            switch(op3)
            {
                case 1:
                    //nuevo.prestarlibro();
                    break;
                case 2:
                    //nuevo.devolverlibro();
                    break;
                case 3:
                    //nuevo.listarprestados();
                    break;
                case 4:
                    op=0;
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;    
            }
            
        }
    
        if(op==3)
        {
        opg=1;
         }

        
        }
    
      } //Cierre try
        catch (SQLException ex) {//error en la sintaxis de mysql
            System.out.println("Error de mysql");
        }
         catch (Exception e) {//cualquier otro error
            System.out.println("Se ha encontrado un error: "+ e.getMessage());
        }
        
    }  
}
    

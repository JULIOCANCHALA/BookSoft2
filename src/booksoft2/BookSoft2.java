package booksoft2;

import java.sql.*;//importo todo
import java.util.Scanner;

/**
 *
 * @author Julio
 */

public class BookSoft2 {
    
    private Scanner teclado=new Scanner(System.in);
    private final String user= "sql289524";//internet
    private final String pasword="sK8!qL6%";//internet
    
 
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
    
    public Statement conectar()
    {  
    
        try {    
            System.out.println("Intentando conectar a la base de datos..");
            Class.forName("com.mysql.jdbc.Driver");       
            
            Connection con=DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net/sql289524",user,pasword);//para internet      
            System.out.println("Conexion exitosa...");     
            
            Statement estado=con.createStatement(); 
            
            return estado;
            
             } //Cierre try
        
        catch (SQLException ex) {//error en la sintaxis de mysql
            System.out.println("Error de mysql");
            return null;
        }
         catch (ClassNotFoundException e) {//cualquier otro error
            System.out.println("Se ha encontrado un error: "+ e.getMessage());
            return null;
        }
        
        
    }
            
    public void ingresar(Statement estado) throws SQLException
    {

        System.out.println("Digite el Codigo: ");
        String codigo=teclado.next();
        
        System.out.println("Digite el Nombre: ");
        String nombre=teclado.next();
        
        System.out.println("Digite el Autor: ");
        String autor=teclado.next();
        
        System.out.println("Digite el Area: ");
        System.out.println("(1)Quimica (2)Fisica (3)Tengologia (4)Calculo (5)Programacion");        
        String area;    
               
        int op=teclado.nextInt();
        
        switch(op)
        {
            case 1:
                area="Quimica";
                break;
            case 2:
                area="Fisica";
                break;
            case 3:
                area="Tegnologia";
                break;
            case 4:
                area="Calculo";
                break;
            case 5:
                area="Programacion";
                break;
            default:
                System.out.println("No ingresaste una Area valida");
                System.out.println("Este libro requiere Actualizar");
                area="Null";
                break;
        }
                
        System.out.println("Digite el Año: ");
        int publicacion=teclado.nextInt();
        
        System.out.println("Digite el Cantidad: ");
        int cantidad=teclado.nextInt();   
        
        estado.executeUpdate("INSERT INTO `registro` VALUES (NULL, '"+codigo+"','"+nombre+"','"+autor+"','"+area+"','"+publicacion+"','"+cantidad+"')");
        System.out.println("Libro INGRESADO con exito");
   
    }
    
    public int buscar(Statement estado) throws SQLException
    {
            System.out.println("Digite el nombre: ");
            String nombre=teclado.next();  
           
            ResultSet resultado=estado.executeQuery("SELECT * FROM `registro` WHERE `nombre` LIKE '"+nombre+"'");
            int m=0;
            while (resultado.next())
            {
                System.out.println("\n"+"Libro encontrado: ");
                System.out.println("---------------------------------------"+"\n"+
                        "Codigo: "+resultado.getString("codigo")+"\n"+
                        "Nombre: "+resultado.getString("nombre")+"\n"+
                        "Autor: "+resultado.getString("autor")+"\n"+
                        "Area: "+resultado.getString("area")+"\n"+
                        "Año: "+resultado.getInt("publicacion")+"\n"+
                        "Cantidad: " +resultado.getInt("cantidad")+"\n"
                        +"---------------------------------------");
                m=1;
                return 1;
            } 
            if(m==0)
            {
                System.out.println("\n"+"Libro NO encontrado: ");
                return 0;
            }
        return 0;
        
    }
    
    public int buscar(String name, Statement estado) throws SQLException
    {
            
            String nombre=name;
           
            ResultSet resultado=estado.executeQuery("SELECT * FROM `registro` WHERE `nombre` LIKE '"+nombre+"'");
            int m=0;
            while (resultado.next())
            {
                System.out.println("\n"+"Libro encontrado: ");
                System.out.println("---------------------------------------"+"\n"+
                        "Codigo: "+resultado.getString("codigo")+"\n"+
                        "Nombre: "+resultado.getString("nombre")+"\n"+
                        "Autor: "+resultado.getString("autor")+"\n"+
                        "Area: "+resultado.getString("area")+"\n"+
                        "Año: "+resultado.getInt("publicacion")+"\n"+
                        "Cantidad: " +resultado.getInt("cantidad")+"\n"
                        +"---------------------------------------");
                m=1;
                return 1;
                
            } 
            if(m==0)
            {
                System.out.println("\n"+"Libro NO encontrado: ");
                return 0;
                
            }
        return 0;
    }
    
    public void actualizar(Statement estado) throws SQLException
    {
        
        System.out.println("Digite el nombre: ");        
        String nombre=teclado.next();
        int a=buscar(nombre,estado);
        
        if(a==1)
        {
        estado.executeUpdate("DELETE FROM `registro` WHERE `nombre` LIKE '"+nombre+"'");
        
        System.out.println("Ingrese la nueva Informacion:");
        ingresar(estado);
        System.out.println("Libro ACTUALIZADO con exito");
        }
        
    }
    
    public void eliminar(Statement estado) throws SQLException
    {
        
        System.out.println("Digite el nombre: ");        
        String nombre=teclado.next();
        int a=buscar(nombre,estado);
        if(a==1)
        {
        estado.executeUpdate("DELETE FROM `registro` WHERE `nombre` LIKE '"+nombre+"'");
        System.out.println("Libro ELIMINADO con exito");
        }
    }
    
    public void prestarlibro(Statement estado) throws SQLException
    {
        System.out.println("Digite el nombre libro: ");        
        String libro=teclado.next();
        int a=buscar(libro,estado);
        if(a==1)
        {            
            ResultSet resultado=estado.executeQuery("SELECT * FROM `registro` WHERE `nombre` LIKE '"+libro+"'");
            int cantidad = 0;
            while (resultado.next())
            {
                cantidad=resultado.getInt("cantidad");
            }
            
            if (cantidad>0)
                {
                    System.out.println("Digite la cedula: ");        
                    String cedula=teclado.next();
                    
                    estado.executeUpdate("INSERT INTO `prestamos` VALUES (NULL, '"+cedula+"','"+libro+"')");
                    estado.executeUpdate("UPDATE  `registro` SET  `cantidad` =  '"+(cantidad-1)+"' WHERE  `registro`.`nombre` ='"+libro+"'");  
                    
                    System.out.println("Libro PRESTADO con exito");
                }
                else
                {
                    System.out.println("No hay existencias en el momento");
                }
    
        }
        
    }
          
    public static void main(String[] args) throws SQLException {
        
        BookSoft2 nuevo;
        nuevo=new BookSoft2();
        Scanner teclado=new Scanner(System.in);
        Statement estado=nuevo.conectar();
                      
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
                    nuevo.ingresar(estado);
                    break;
                case 2:
                    System.out.println("Actualización de información:");
                    nuevo.actualizar(estado);
                    break;
                case 3:
                    nuevo.eliminar(estado);
                    break;
                case 4:
                    System.out.println("Busqueda por Nombre:");
                    nuevo.buscar(estado);
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
                    nuevo.prestarlibro(estado);
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
    
     
        
    }  
}
    

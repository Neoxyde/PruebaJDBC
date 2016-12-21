/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebajdbc;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dam2
 */
public class PruebaJDBC
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            Driver myDriver = new com.mysql.jdbc.Driver();
            
            DriverManager.registerDriver(myDriver);
            
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root"))
            {
                Statement statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet = statement.executeQuery("SELECT * FROM pruebajdbc.usuarios");
                
                // Check if the query could retrieve anything?
                if (resultSet.first())
                {
                    System.out.println("Nombre de usuario: " + resultSet.getString("name"));
                    System.out.println("Contraseña: " + resultSet.getString("pass"));
                    
                    if (resultSet.getDate("lastConn") != null)
                    {
                        System.out.println("Última conexión: " + resultSet.getDate("lastConn"));
                    }
                    else
                    {
                        System.out.println("Última conexión: NULL");
                        
                    }
                    if (resultSet.getTime("lastConnT") != null)
                    {
                        System.out.println("Última conexión hora: " + resultSet.getTime("lastConnT"));
                    }
                    else
                    {
                        System.out.println("Última conexión hora: NULL");
                        
                    }
                    
                }
                else
                {
                    System.out.println("No hay usuarios registrados");
                }
                
                
                resultSet.updateDate("lastConn", new Date(System.currentTimeMillis()));
                resultSet.updateTime("lastConnT", new Time(System.currentTimeMillis()));
                
                // Here the JDBC updates the table rows
                resultSet.updateRow();
                System.out.println("_____________________________");
            }
            
        } catch (SQLException ex)
        {
            Logger.getLogger(PruebaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

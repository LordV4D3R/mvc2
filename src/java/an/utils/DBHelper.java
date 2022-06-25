/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package an.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 *
 * @author Admin
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection() 
        throws /*ClassNotFoundException*/ NamingException, SQLException{
            //1. Get current system file(lấy file system của máy tính)
            Context context = new InitialContext();
            //2. get container context
            Context tomcatContext =(Context)context.lookup("java:comp/env");
            //3. get datasource
            DataSource ds = (DataSource) tomcatContext.lookup("DSBlink");
            //4.get connection
            Connection con = ds.getConnection();
            
            return con;
//            //1. Load Driver
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//classnotfound
//
//            //2. Make connection String (port):protocol://ip:port;databaseName=db {: instanceName: Name}
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=prjKTK;instanceName=ANTQ";
//            
//            //3. Open connection 
//            Connection con = DriverManager.getConnection(url, "sa", "12345");//SQLException
//            return con;
        
    }
}


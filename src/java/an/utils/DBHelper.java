/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package an.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author Admin
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection()
            throws /*ClassNotFoundException*/ NamingException, SQLException {
        //1. Get current system file(lấy file system của máy tính)
        Context context = new InitialContext();
        //2. get container context
        Context tomcatContext = (Context) context.lookup("java:comp/env");
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

    public static void getSiteMaps(ServletContext context)
            throws IOException {
        String siteMapsFilePath = context.getInitParameter("SITEMAPS_FILE_PATH");
        InputStream is = null;
        Properties siteMaps = null;

        try {
            is = context.getResourceAsStream(siteMapsFilePath);
            siteMaps = new Properties();
            siteMaps.load(is);
            context.setAttribute("SITEMAPS", siteMaps);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}

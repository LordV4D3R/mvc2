/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package an.product;

import an.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author tranq
 */
public class ProductDAO {

    public ProductDAO() {
    }

    private List<ProductDTO> items;

    public List<ProductDTO> getItem() {
        return items;
    }

    public void getItemList()
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. make connecttion
            con = DBHelper.makeConnection();

            //2. create sql String
            
            //3. create stament
            
            //4. execute stament
            
            //5. process result
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }catch(NamingException ex){
            ex.printStackTrace();
        }finally {

        }
    }
}

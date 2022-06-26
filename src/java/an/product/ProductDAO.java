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
import java.util.ArrayList;
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

    public void getSearchItemList(String searchItem)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. make connecttion
            con = DBHelper.makeConnection();
            if (con != null) {

                //2. create sql String
                String sql = "Select sku, name, description, price "
                        + "From product "
                        + "Where name Like ?";

                //3. create stament
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchItem + "%");

                //4. execute stament
                rs = stm.executeQuery();

                //5. process result
                while (rs.next()) {
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    int description = rs.getInt("description");
                    int price = rs.getInt("price");

                    ProductDTO dto = new ProductDTO(sku, name,
                            price, description);

                    if (this.items == null) {
                        this.items = new ArrayList<>();
                    }//end items is initialized
                    this.items.add(dto);
                }//end traverse Result Set (end rs has more than 1 record)            

            }// end con

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    public void getItemList()
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. create sql string
                String sql = "Select sku, name, description, price "
                        + "From product";
                //3. create stm string
                stm = con.prepareStatement(sql);
                //4. execute query
                rs = stm.executeQuery();
                //5. process result
                while (rs.next()) {
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    int description = rs.getInt("description");
                    int price = rs.getInt("price");

                    ProductDTO dto = new ProductDTO(sku, name, price, description);

                    if (this.items == null) {
                        this.items = new ArrayList<>();
                    }//end items is initialized
                    this.items.add(dto);
                }
            }//end if connection existed
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }
}

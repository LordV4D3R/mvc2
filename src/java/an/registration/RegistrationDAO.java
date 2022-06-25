/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package an.registration;

import an.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            //1. Make connection
            connection = DBHelper.makeConnection();

            if (connection != null) {

                //2. Create SQL String
                String sql = "Select username "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";
                //o day co dau "?" nen chung ta phai thiet lap tham so
                //dua vao trong lenh bang ham set

                //3. Create Statement to set SQL
                stm = connection.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);

                //4. Execute Query
                rs = stm.executeQuery();// boi vi cau lenh sql la Select nen
                //phai dung executeQuery, neu cau lenh la insert, delete,
                //update thi dung executeUpdate

                //5. Process result
                if (rs.next()) {
                    result = true;
                }
            }//end if connection is existed
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    private List<RegistrationDTO> accounts;

    //móc dữ liệu lên nên ko cần set chỉ cần phương thức get
    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Make connection
            connection = DBHelper.makeConnection();

            if (connection != null) {

                //2. Create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";
                //o day co dau "?" nen chung ta phai thiet lap tham so
                //dua vao trong lenh bang ham set

                //3. Create Statement to set SQL
                stm = connection.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //dấu % dùng để thay thế cho kí tự trc đó hoặc ko có kí tự nào

                //4. Execute Query
                rs = stm.executeQuery();// boi vi cau lenh sql la Select nen
                //phai dung executeQuery, neu cau lenh la insert, delete,
                //update thi dung executeUpdate

                //5. Process result
                while (rs.next()) { //thực hiện nhiều dòng dùng while()
                    //get field/column
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    //create DTO instance
                    RegistrationDTO dto = new RegistrationDTO(username, password,
                            lastname, role);

                    //add to accounts list
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }//end account is initialized
                    //accounts is available
                    this.accounts.add(dto);
                }//end traverse Result Set (end rs has more than 1 record)

            }//end if connection is existed
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

    }

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement stm = null;
        boolean result = false;
        //ko cần ResultSet vì ko phải trả về boolean mà trả về số dòng đã execute
        //lệnh delete

        try {
            //1. Make connection
            connection = DBHelper.makeConnection();

            if (connection != null) {

                //2. Create SQL String
                //nếu cố tính viết Delete *From nó sẽ ko phát sinh bất kì lỗi cú
                //pháp nào, nhưng khi thực thi nó sẽ ko có dòng nào ảnh hưởng
                //luôn trả ra là 0 row effect
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //o day co dau "?" nen chung ta phai thiet lap tham so
                //dua vao trong lenh bang ham set

                //3. Create Statement to set SQL
                stm = connection.prepareStatement(sql);
                stm.setString(1, username);

                //4. Execute Query
                int row = stm.executeUpdate();

                //5. Process result
                if (row > 0) {
                    result = true;
                }
            }//end if connection is existed
        } finally {

            if (stm != null) {
                stm.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    public boolean updateAccount(String username, String password, boolean isAdmin)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1.make connection
            connection = DBHelper.makeConnection();
            if (connection != null) {
                
                //2.create sql string
                String sql = "Update Registration "
                        + "Set password = ?, isAdmin = ? "
                        + "Where username = ?";
                
                //3.create stament to sql
                stm = connection.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                
                //4.execute statement
                int row = stm.executeUpdate();
                
                //5.process result
                if (row > 0) {
                    result = true;
                }
            }//end if connecttion
        } finally {
            if (stm != null) {
                stm.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
    
}

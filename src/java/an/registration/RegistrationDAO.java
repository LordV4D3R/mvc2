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

    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;

        try {
            //1. Make connection
            connection = DBHelper.makeConnection();

            if (connection != null) {

                //2. Create SQL String
                String sql = "Select username, lastname, isAdmin "
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
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, password, fullname, role);
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

    //m??c d??? li???u l??n n??n ko c???n set ch??? c???n ph????ng th???c get
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
                //d???u % d??ng ????? thay th??? cho k?? t??? trc ???? ho???c ko c?? k?? t??? n??o

                //4. Execute Query
                rs = stm.executeQuery();// boi vi cau lenh sql la Select nen
                //phai dung executeQuery, neu cau lenh la insert, delete,
                //update thi dung executeUpdate

                //5. Process result
                while (rs.next()) { //th???c hi???n nhi???u d??ng d??ng while()
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
        //ko c???n ResultSet v?? ko ph???i tr??? v??? boolean m?? tr??? v??? s??? d??ng ???? execute
        //l???nh delete

        try {
            //1. Make connection
            connection = DBHelper.makeConnection();

            if (connection != null) {

                //2. Create SQL String
                //n???u c??? t??nh vi???t Delete *From n?? s??? ko ph??t sinh b???t k?? l???i c??
                //ph??p n??o, nh??ng khi th???c thi n?? s??? ko c?? d??ng n??o ???nh h?????ng
                //lu??n tr??? ra l?? 0 row effect
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

    public boolean createAccount(RegistrationDTO dto)
            throws NamingException, SQLException {
        //?????i vs tr???ng th??i truy???n obj th?? ph???i ki???m tra xem obj c?? b???ng null
        //hay ko, n???u b???ng null th?? ko l??m j c???
        if (dto == null) {
            return false;
        }
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;


        try {
            //1. make connection
            con = DBHelper.makeConnection();

            //2. create sql string
            String sql = "Insert Into Registration(username, password, lastname, isAdmin) "
                    + "Values(?, ?, ?, ?)";
            //3. create stament
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getUsername());
            stm.setString(2, dto.getPassword());
            stm.setString(3, dto.getLastname());
            stm.setBoolean(4, dto.isRole());
            //4. execute stament
            int row = stm.executeUpdate();
            //5. proccess result
            if (row > 0) {
                result = true;
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return result;
    }
}

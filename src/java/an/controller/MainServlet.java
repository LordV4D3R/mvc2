/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package an.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tranq
 */
//name is javaclass (name đang mapping cho javaclass này)
//@WebServlet là servlet mapping (sử dụng 2 có lợi điểm gì?)
//đối vs egine nào hiểu đc nó thì nó coi như là 1 dòng lệnh nó sẽ phát sinh code
//cho mình. Còn đối vs engine nào k hiểu lệnh đc nó thì @ chỉ là 1 chuỗi vô 
//nghĩa ko ảnh hưởng j cả
//vs cách khai báo như vậy khi nó chạy deploy thì bản chất nó container sẽ 
//convert nó thành .xml tương ứng như web.xml để nó deploy cho mình but nó viết
//ngay vào trong code
//Dở: khi cần chỉnh sửa chúng ta phải có source code để chỉnh sửa lại -> cực kì
//phi lý
//nếu tên của thằng @ này trùng vs thằng .xml thì thằng .xml sẽ override tất cả
//thành phần định nghĩa
//DÙNG: cái gì ít bị thay đổi trong program, còn cái gì phải đi cấu hình, phải 
//thay đổi trong lúc quá trình triển khai ứng dụng thì nên viết trong web.xml
@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginController";//lấy ở .xml
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameController";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountController";
    private final String UPDATE_PASSWORD_AND_ROLE = "UpdatePasswordandRoleController";
    private final String PROCESS_REQUEST_CONTROLLER ="ProcessRequestController";
    private final String ADD_TO_CART = "AddToCartController";
    private final String LOGOUT_CONTROLLER = "LogoutController";
    private final String VIEW_CART = "viewcart.jsp";
    private final String REMOVE_ITEMS = "RemoveItemsController";
    private final String CHECKOUT_CART = "CheckOutController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //cái này để chuyển qua cho sitemaps để che đường dẫn
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(LOGIN_PAGE);
        
        
        //which button did user click?
        String action = request.getParameter("btAction");// thực chất là đặt cái
        //này trc then copy nó vào bên trong các form của mình
        
        try  {
            if(action == null){
                url = PROCESS_REQUEST_CONTROLLER;
            } else if(action.equals("Login")){
                url = LOGIN_CONTROLLER;
            } else if(action.equals("Search")){
                url = SEARCH_LASTNAME_CONTROLLER; 
            }else if(action.equals("Delete")){
                url = DELETE_ACCOUNT_CONTROLLER;
            }else if(action.equals("Update")){
                url = UPDATE_PASSWORD_AND_ROLE;
            } else if (action.equals("Add to cart")){
                url = ADD_TO_CART;
            }else if(action.equals("Logout")){
                url = LOGOUT_CONTROLLER;
            } else if(action.equals("View your cart")){
                url = VIEW_CART;
            }else if(action.equals("Remove Selectd Items")){
                url = REMOVE_ITEMS;
            }else if(action.equals("Check Out")){
                url = CHECKOUT_CART;
            }
            
        } finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request,response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

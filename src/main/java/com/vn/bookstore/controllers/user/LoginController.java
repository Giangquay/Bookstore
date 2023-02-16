package com.vn.bookstore.controllers.user;

import com.vn.bookstore.encrypt.EncryptPass;
import com.vn.bookstore.models.KhachHang;
import com.vn.bookstore.services.IKhachHangServices;
import com.vn.bookstore.services.Impl.KhachHangServicesImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    IKhachHangServices khachHangServices = new KhachHangServicesImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/user/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("tenDangNhap");
        String passWord = req.getParameter("password");


        req.setAttribute("userName",userName);

        passWord = EncryptPass.SHA1(passWord);
        String url="";
        if("null".equals(userName)||"null".equals(passWord))
        {
            url = "/views/user/login.jsp";
            req.setAttribute("error", "User and Pass is empty");
        }else {
            KhachHang khachHang = new KhachHang();
            khachHang.setTenDangNhap(userName);
            khachHang.setMaKhachHang(passWord);
            khachHang = khachHangServices.selectByUsernameAndPassWord(khachHang);
            if (khachHang != null) {
                url = "index.jsp";
                HttpSession session = req.getSession();
                session.setAttribute("user", khachHang);
            } else {
                url = "/views/user/login.jsp";
                req.setAttribute("error", "User and Pass is exists");

            }
        }

        RequestDispatcher reDispatcher = req.getRequestDispatcher(url);
        reDispatcher.forward(req,resp);
    }
}

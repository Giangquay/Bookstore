package com.vn.bookstore.controllers.admin;

import com.vn.bookstore.models.KhachHang;
import com.vn.bookstore.services.IKhachHangServices;
import com.vn.bookstore.services.Impl.SanPhamServicesImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/khach-hang/list")
public class KhachHangController extends HttpServlet {
    IKhachHangServices khachHangServices = new SanPhamServicesImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<KhachHang> kh = khachHangServices.selectAll();
        req.setAttribute("listKH",kh);
        RequestDispatcher rq=req.getRequestDispatcher("/views/admin/list-khachhang.jsp");
        rq.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

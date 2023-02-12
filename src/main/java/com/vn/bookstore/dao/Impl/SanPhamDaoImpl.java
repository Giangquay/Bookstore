package com.vn.bookstore.dao.Impl;

import com.vn.bookstore.connection.ConnectMYSQL;
import com.vn.bookstore.dao.ISanPham;
import com.vn.bookstore.models.SanPham;
import com.vn.bookstore.models.TacGia;
import com.vn.bookstore.models.TheLoai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDaoImpl implements ISanPham {

    private static SanPhamDaoImpl instance=null;
    public static SanPhamDaoImpl getInstance()
    {
        synchronized (SanPhamDaoImpl.class)
        {
            if(instance==null)
            {
                instance = new SanPhamDaoImpl();
            }
        }
        return instance;
    }
    @Override
    public List<SanPham> getAll() {
        Connection connection = ConnectMYSQL.getConnect();
        List<SanPham> sanPhamList = new ArrayList<>();
        String query = "select * from sanpham";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            ResultSet resultNext = prepared.executeQuery();
            while (resultNext.next())
            {
                String masanpham = resultNext.getString("masanpham");
                String tensanpham = resultNext.getString("tensanpham");
                String matacgia = resultNext.getString("matacgia");
                int namxuatban = resultNext.getInt("namxuatban");
                String linkanh= resultNext.getString("linkanh");
                double gianhap = resultNext.getDouble("gianhap");
                double giagoc = resultNext.getDouble("giagoc");
                double giaban = resultNext.getDouble("giaban");
                int soluong = (int) resultNext.getDouble("soluong");
                String matheloai = resultNext.getString("matheloai");
                String ngonngu = resultNext.getString("ngonngu");
                String mota = resultNext.getString("mota");
                TacGia tacGia =  (new TacGiaDaoImpl().selectById(new TacGia(matacgia,"",null,"")));
                TheLoai theLoai = (new TheLoaiDaoIml().selectById(new TheLoai(matheloai, "")));
                sanPhamList.add(new SanPham(masanpham,tensanpham,tacGia,namxuatban,linkanh,gianhap,giagoc,giaban,soluong,theLoai,ngonngu,mota));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  sanPhamList;
    }

    public static void main(String[] args) {
        for(SanPham sanP:SanPhamDaoImpl.getInstance().getAll())
        {
            System.out.println(sanP);
        }
    }
}

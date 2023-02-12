package com.vn.bookstore.dao.Impl;

import com.vn.bookstore.connection.ConnectMYSQL;
import com.vn.bookstore.dao.ITacGia;
import com.vn.bookstore.models.SanPham;
import com.vn.bookstore.models.TacGia;
import com.vn.bookstore.models.TheLoai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TacGiaDaoImpl implements ITacGia {
    private Connection connection = null;
    private static TacGiaDaoImpl instance;
    public static TacGiaDaoImpl getInstance()
    {
        synchronized (TacGiaDaoImpl.class)
        {
            if(instance==null)
            {
                instance = new TacGiaDaoImpl();
            }
        }
        return  instance;
    }
    @Override
    public List<TacGia> getAll() {
        connection = ConnectMYSQL.getConnect();
        List<TacGia> tacGiaList = new ArrayList<>();
        String query = "select * from tacgia";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            ResultSet resultNext = prepared.executeQuery();
            while (resultNext.next())
            {
                String maTacGia = resultNext.getString("matacgia");
                String hoVaTen = resultNext.getString("hovaten");
                Date ngaySinh = resultNext.getDate("ngaysinh");
                String tieuSu = resultNext.getString("tieusu");
                TacGia tacGia = new TacGia(maTacGia,hoVaTen,ngaySinh,tieuSu);
                tacGiaList.add(tacGia);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  tacGiaList;
    }

    @Override
    public TacGia selectById(TacGia tg) {
        connection = ConnectMYSQL.getConnect();
        TacGia ketQua = null;
        String query = "Select * from tacgia where matacgia = ?";
        try {
            PreparedStatement prepared= connection.prepareStatement(query);
            prepared.setString(1, tg.getMaTacGia());
            ResultSet resultNext = prepared.executeQuery();
            while (resultNext.next()){
                String maTacGia = resultNext.getString("matacgia");
                String hoVaTen = resultNext.getString("hovaten");
                Date ngaySinh = resultNext.getDate("ngaysinh");
                String tieuSu = resultNext.getString("tieusu");
                ketQua =  new TacGia(maTacGia,hoVaTen,ngaySinh,tieuSu);
                break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ketQua;
    }

    public static void main(String[] args) {
        for(TacGia tg: TacGiaDaoImpl.getInstance().getAll())
        {
            System.out.println(tg);
        }
    }
}

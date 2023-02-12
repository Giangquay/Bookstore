package com.vn.bookstore.dao.Impl;

import com.vn.bookstore.connection.ConnectMYSQL;
import com.vn.bookstore.dao.ITheLoai;
import com.vn.bookstore.models.TheLoai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TheLoaiDaoIml implements ITheLoai {
    private Connection connection = null;
    private static TheLoaiDaoIml instance;

    public static TheLoaiDaoIml getInstance()
    {
        synchronized (TheLoaiDaoIml.class)
        {
            if(instance==null)
            {
                instance = new TheLoaiDaoIml();
            }
        }
        return  instance;
    }
    @Override
    public List<TheLoai> getAll() {
        connection = ConnectMYSQL.getConnect();
        List<TheLoai> list= new ArrayList<>();
        String query ="select * from theloai";
        try {
            PreparedStatement prepared= connection.prepareStatement(query);
            ResultSet resultNext = prepared.executeQuery();
            while (resultNext.next())
            {
                String maSanPham= resultNext.getString("masanpham");
                String tenSanPham=resultNext.getString("tensanpham");
                list.add(new TheLoai(maSanPham,tenSanPham));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  list;
    }

    @Override
    public TheLoai selectById(TheLoai tl) {
        connection = ConnectMYSQL.getConnect();
        TheLoai ketQua = null;
        String query = "Select * from theloai where matheloai= ?";
        try {
            PreparedStatement prepared= connection.prepareStatement(query);
            prepared.setString(1, tl.getMaTheLoai());
            ResultSet resultNext = prepared.executeQuery();
            while (resultNext.next()){
                String maTheLoai = resultNext.getString("matheloai");
                String tenTheLoai=resultNext.getString("tentheloai");
                ketQua =  new TheLoai(maTheLoai,tenTheLoai);
                break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ketQua;
    }
}

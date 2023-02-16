package com.vn.bookstore.dao.Impl;

import com.vn.bookstore.connection.ConnectMYSQL;
import com.vn.bookstore.dao.ITheLoai;
import com.vn.bookstore.models.TheLoai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public int insert(TheLoai tl) {

        int ketQua = 0;
        try {
            Connection con = ConnectMYSQL.getConnect();

            String sql = "INSERT INTO theloai (matheloai, tentheloai) "+
                    " VALUES (?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, tl.getMaTheLoai());
            st.setString(2, tl.getTenTheLoai());

            ketQua = st.executeUpdate();

            System.out.println("Bạn đã thực thi: "+ sql);
            System.out.println("Có "+ ketQua+" dòng bị thay đổi!");

            ConnectMYSQL.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int delete(TheLoai tl) {
        int ketQua = 0;
        try {
            Connection con = ConnectMYSQL.getConnect();

            String sql = "DELETE from theloai "+
                    " WHERE matheloai=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, tl.getMaTheLoai());

            System.out.println(sql);
            ketQua = st.executeUpdate();

            System.out.println("Bạn đã thực thi: "+ sql);
            System.out.println("Có "+ ketQua+" dòng bị thay đổi!");

            ConnectMYSQL.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int update(TheLoai tl) {
        int ketQua = 0;
        try {
            Connection con = ConnectMYSQL.getConnect();

            String sql = "UPDATE theloai "+
                    " SET " +
                    " tenTheLoai=?"+
                    " WHERE matheloai=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, tl.getTenTheLoai());

            System.out.println(sql);
            ketQua = st.executeUpdate();

            System.out.println("Bạn đã thực thi: "+ sql);
            System.out.println("Có "+ ketQua+" dòng bị thay đổi!");

            ConnectMYSQL.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }
}

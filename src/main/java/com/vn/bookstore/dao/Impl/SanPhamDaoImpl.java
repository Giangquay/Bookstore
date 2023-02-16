package com.vn.bookstore.dao.Impl;

import com.mysql.cj.MysqlConnection;
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

    @Override
    public SanPham selectById(SanPham sp) {
        SanPham ketQua = null;
        try {
            Connection conn = ConnectMYSQL.getConnect();

            String sql = "SELECT * FROM sanpham WHERE masanpham=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, sp.getMaSanPham());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String masanpham = rs.getString("masanpham");
                String tensanpham = rs.getString("tensanpham");
                String matacgia = rs.getString("matacgia");
                int namxuatban = rs.getInt("namxuatban");
                String linkAnh =rs.getString("linkAnh");
                double gianhap = rs.getDouble("gianhap");
                double giagoc = rs.getDouble("giagoc");
                double giaban = rs.getDouble("giaban");
                int soluong = (int) rs.getDouble("soluong");
                String matheloai = rs.getString("matheloai");
                String ngonngu = rs.getString("ngonngu");
                String mota = rs.getString("mota");

                TacGia tacGia = (new TacGiaDaoImpl().selectById(new TacGia(matacgia, "", null, "")));
                TheLoai theLoai = (new TheLoaiDaoIml().selectById(new TheLoai(matheloai, "")));

                ketQua = new SanPham(masanpham, tensanpham, tacGia, namxuatban,linkAnh, gianhap, giagoc, giaban, soluong,
                        theLoai, ngonngu, mota);
                break;
            }

            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int insert(SanPham sp) {
        int ketQua = 0;
        try {
            // Bước 1: tạo kết nối đến CSDL
            Connection conn = ConnectMYSQL.getConnect();

            // Bước 2: tạo ra đối tượng statement
            String sql = "INSERT INTO sanpham (masanpham,tensanpham, matacgia, namxuatban,"
                    + " gianhap, giagoc, giaban, soluong, matheloai, ngonngu, mota) "
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, sp.getMaSanPham());
            st.setString(2, sp.getTenSanPham());
            st.setString(3, sp.getTacGia().getMaTacGia());
            st.setInt(4, sp.getNamXuatBan());
            st.setDouble(5, sp.getGiaNhap());
            st.setDouble(6, sp.getGiaGoc());
            st.setDouble(7,sp.getGiaBan());
            st.setInt(8, sp.getSoLuong());
            st.setString(9, sp.getTheLoai().getMaTheLoai());
            st.setString(10, sp.getNgonNgu());
            st.setString(11,sp.getMoTa());

            // Bước 3: thực thi câu lệnh SQL
            ketQua = st.executeUpdate();

            // Bước 4:
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

            // Bước 5:
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int delete(SanPham sp) {
        int ketQua = 0;
        try {
            Connection con = ConnectMYSQL.getConnect();

            String sql = "DELETE from sanpham " + " WHERE masanpham=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, sp.getMaSanPham());


            System.out.println(sql);
            ketQua = st.executeUpdate();

            // Bước 4:
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int update(SanPham sp) {
        int ketQua = 0;
        try {

            Connection con = ConnectMYSQL.getConnect();

            String sql = "UPDATE sanpham " + " SET " + "tensanpham=?, matacgia=?, namxuatban=?, gianhap=?, giagoc=?, "
                    + "giaban=?, soluong=?, matheloai=?, ngonngu=?, mota=?" + " WHERE masanpham=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, sp.getTenSanPham());
            st.setString(2, sp.getTacGia().getMaTacGia());
            st.setInt(3, sp.getNamXuatBan());
            st.setDouble(4, sp.getGiaNhap());
            st.setDouble(5, sp.getGiaGoc());
            st.setDouble(6, sp.getGiaBan());
            st.setInt(7, sp.getSoLuong());
            st.setString(8, sp.getTheLoai().getMaTheLoai());
            st.setString(9, sp.getNgonNgu());
            st.setString(10, sp.getMoTa());
            st.setString(11, sp.getMaSanPham());

            System.out.println(sql);
            ketQua = st.executeUpdate();

            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

            ConnectMYSQL.closeConnection(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

}

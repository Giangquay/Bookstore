package com.vn.bookstore.dao.Impl;

import com.mysql.cj.MysqlConnection;
import com.vn.bookstore.connection.ConnectMYSQL;
import com.vn.bookstore.dao.IChiTietDonHang;
import com.vn.bookstore.models.ChiTietDonHang;
import com.vn.bookstore.models.DonHang;
import com.vn.bookstore.models.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChiTietDonHangImpl implements IChiTietDonHang {
    @Override
    public ArrayList<ChiTietDonHang> selectAll() {
        ArrayList<ChiTietDonHang> ketQua = new ArrayList<ChiTietDonHang>();

        try {
            Connection con = ConnectMYSQL.getConnect();

            String sql = "SELECT * FROM chitietdonhang";
            PreparedStatement st = con.prepareStatement(sql);

            System.out.println(sql);
            ResultSet rs = st.executeQuery();


            while (rs.next()) {
                String maChiTietDonHang = rs.getString("machitietdonhang");
                String madonhang = rs.getString("madonhang");
                String masanpham = rs.getString("masanpham");
                double soluong = rs.getDouble("soluong");
                double giagoc = rs.getDouble("giagoc");
                double giamgia = rs.getDouble("giamgia");
                double giaban = rs.getDouble("giaban");
                double thuevat = rs.getDouble("thuevat");
                double tongtien = rs.getDouble("tongtien");

                DonHang dh1 = new DonHang();
                dh1.setMaDonHang(madonhang);

                SanPham sp1 = new SanPham();
                sp1.setMaSanPham(masanpham);
                DonHang dh = (new DonHangImpl()).selectById(dh1);
                SanPham sp = (new SanPhamDaoImpl()).selectById(sp1);

                ChiTietDonHang ctdh = new ChiTietDonHang(maChiTietDonHang, dh, sp, soluong, giagoc, giamgia, giaban,
                        thuevat, tongtien);
                ketQua.add(ctdh);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public ChiTietDonHang selectById(ChiTietDonHang t) {
        ChiTietDonHang ketQua = null;
        try {
            Connection con = ConnectMYSQL.getConnect();

            String sql = "SELECT * FROM chitietdonhang WHERE machitietdonhang=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaChiTietDonHang());

            System.out.println(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String maChiTietDonHang = rs.getString("machitietdonhang");
                String madonhang = rs.getString("donhang");
                String masanpham = rs.getString("sanpham");
                double soluong = rs.getDouble("soluong");
                double giagoc = rs.getDouble("giagoc");
                double giamgia = rs.getDouble("giamgia");
                double giaban = rs.getDouble("giaban");
                double thuevat = rs.getDouble("thuevat");
                double tongtien = rs.getDouble("tongtien");

                DonHang dh1 = new DonHang();
                dh1.setMaDonHang(madonhang);

                SanPham sp1 = new SanPham();
                sp1.setMaSanPham(masanpham);

                DonHang dh = new DonHangImpl().selectById(dh1);
                SanPham sp = new SanPhamDaoImpl().selectById(sp1);

                ketQua = new ChiTietDonHang(maChiTietDonHang, dh, sp, soluong, giagoc, giamgia, giaban, thuevat,
                        tongtien);
                break;
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int insert(ChiTietDonHang t) {
        int ketQua = 0;
        try {

            Connection con = ConnectMYSQL.getConnect();

            String sql = "INSERT INTO chitietdonhang (machitietdonhang, donhang,sanpham, soluong, giagoc,giamgia,giaban,thuevat,tongtien) "
                    + " VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaChiTietDonHang());
            st.setString(2, t.getDonHang().getMaDonHang());
            st.setString(3, t.getSanPham().getMaSanPham());
            st.setDouble(4, t.getSoLuong());
            st.setDouble(5, t.getGiaGoc());
            st.setDouble(7, t.getGiamGia());
            st.setDouble(6, t.getGiaBan());
            st.setDouble(8, t.getThueVAT());
            st.setDouble(9, t.getTongTien());

            ketQua = st.executeUpdate();

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
    public int update(ChiTietDonHang t) {
        int ketQua = 0;
        try {
            Connection con = ConnectMYSQL.getConnect();


            String sql = "UPDATE chitietdonhang SET donhang=?, sanpham=?, soluong=?, giagoc=?, giamgia=?, giaban=?, thuevat=?, tongtien=?"
                    + " WHERE machitietdonhang=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getDonHang().getMaDonHang());
            st.setString(2, t.getSanPham().getMaSanPham());
            st.setDouble(3, t.getSoLuong());
            st.setDouble(4, t.getGiaGoc());
            st.setDouble(5, t.getGiamGia());
            st.setDouble(6, t.getGiaBan());
            st.setDouble(7, t.getThueVAT());
            st.setDouble(8, t.getTongTien());
            st.setString(9, t.getMaChiTietDonHang());


            System.out.println(sql);
            ketQua = st.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }
}

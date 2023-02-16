package com.vn.bookstore.dao.Impl;

import com.vn.bookstore.connection.ConnectMYSQL;
import com.vn.bookstore.dao.IDonHang;
import com.vn.bookstore.models.DonHang;
import com.vn.bookstore.models.KhachHang;

import java.sql.*;
import java.util.ArrayList;

public class DonHangImpl implements IDonHang {
    @Override
    public ArrayList<DonHang> selectAll() {
        ArrayList<DonHang> ketQua = new ArrayList<DonHang>();
        Connection con = ConnectMYSQL.getConnect();
        String sql = "SELECT * FROM donhang";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String maDonHang = rs.getString(1);
                String maKhachHang = rs.getString(2);
                String diaChiNguoiMua = rs.getString(3);
                String diaChiNhanHang = rs.getString(4);
                String trangThai = rs.getString(5);
                String hinhThucThanhToan = rs.getString(6);
                String trangThaiThanhToan = rs.getString(7);
                double soTienDaThanhToan = rs.getDouble(8);
                double soTienConThieu = rs.getDouble(9);
                Date ngayDatHang = rs.getDate(10);
                Date ngayGiaoHang = rs.getDate(11);

                KhachHang kh1 = new KhachHang();
                kh1.setMaKhachHang(maKhachHang);

                KhachHang khachHang = new KhachHangImpl().selectByID(kh1);

                DonHang dh = new DonHang(maDonHang, khachHang, diaChiNguoiMua, diaChiNhanHang, trangThai, hinhThucThanhToan, trangThaiThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang, ngayGiaoHang);

                ketQua.add(dh);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public DonHang selectById(DonHang t) {
        DonHang ketQua = null;
        Connection con = ConnectMYSQL.getConnect();
        String sql = "SELECT * FROM donhang WHERE madonhang = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaDonHang());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String maDonHang = rs.getString(1);
                String maKhachHang = rs.getString(2);
                String diaChiNguoiMua = rs.getString(3);
                String diaChiNhanHang = rs.getString(4);
                String trangThai = rs.getString(5);
                String hinhThucThanhToan = rs.getString(6);
                String trangThaiThanhToan = rs.getString(7);
                double soTienDaThanhToan = rs.getDouble(8);
                double soTienConThieu = rs.getDouble(9);
                Date ngayDatHang = rs.getDate(10);
                Date ngayGiaoHang = rs.getDate(11);

                KhachHang kh1 = new KhachHang();
                kh1.setMaKhachHang(maKhachHang);

                KhachHang khachHang = new KhachHangImpl().selectByID(kh1);

                DonHang dh = new DonHang(maDonHang, khachHang, diaChiNguoiMua, diaChiNhanHang, trangThai, hinhThucThanhToan, trangThaiThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang, ngayGiaoHang);

                ketQua = dh;
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int insert(DonHang t) {
        int kq = 0;
        Connection con = ConnectMYSQL.getConnect();
        String sql = "INSERT INTO donhang(madonhang, khachhang, diachinguoimua, diachinguoinhan, trangthai, thanhtoan,trangthaithanhtoan ,tienthanhtoan, tienthieu,ngaydathang,ngaygiaohang)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaDonHang());
            st.setString(2, t.getKhachHang().getMaKhachHang());
            st.setString(3, t.getDiaChiMuaHang());
            st.setString(4, t.getDiaChiNhanHang());
            st.setString(5, t.getTrangThai());
            st.setString(6, t.getHinhThucThanhToan());
            st.setString(7, t.getTrangThaiThanhToan());
            st.setDouble(8, t.getSoTienDaThanhToan());
            st.setDouble(9, t.getSoTienConThieu());
            st.setDate(10, t.getNgayDatHang());
            st.setDate(11, t.getNgayGiaoHang());

            kq = st.executeUpdate();
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public int delete(DonHang t) {
        int kq = 0;
        Connection con = ConnectMYSQL.getConnect();
        String sql = "DELETE FROM donhang WHERE madonhang = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaDonHang());
            kq = st.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public int update(DonHang t) {
        int kq = 0;
        Connection con = ConnectMYSQL.getConnect();

        String sql = "UPDATE donhang" + " SET " + "khachhang=?" + ", diachinguoimua=?" + ",diachinguoinhan=?"
                + ",trangthai=?" + ",thanhtoan=?"+ ",trangthaithanhtoan=?" + ",tienthanhtoan=?" + ",tienthieu=?" + ",ngaydathang=?"
                + ",ngaygiaohang=?" + " WHERE madonhang=?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getMaDonHang());
            st.setString(2, t.getKhachHang().getMaKhachHang());
            st.setString(3, t.getDiaChiMuaHang());
            st.setString(4, t.getDiaChiNhanHang());
            st.setString(5, t.getTrangThai());
            st.setString(6, t.getHinhThucThanhToan());
            st.setString(7, t.getTrangThaiThanhToan());
            st.setDouble(8, t.getSoTienDaThanhToan());
            st.setDouble(9, t.getSoTienConThieu());
            st.setDate(10, t.getNgayDatHang());
            st.setDate(11, t.getNgayGiaoHang());

            kq = st.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }
}

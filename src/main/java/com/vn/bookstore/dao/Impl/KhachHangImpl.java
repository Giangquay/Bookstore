package com.vn.bookstore.dao.Impl;

import com.vn.bookstore.connection.ConnectMYSQL;
import com.vn.bookstore.dao.IKhachHang;
import com.vn.bookstore.encrypt.EncryptPass;
import com.vn.bookstore.models.KhachHang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangImpl implements IKhachHang {
    private Connection connection = null;
    private static KhachHangImpl instance;

    public static KhachHangImpl getInstance()
    {
        synchronized (KhachHangImpl.class)
        {
            if(instance==null)
            {
                instance = new KhachHangImpl();
            }
        }
        return instance;
    }
    @Override
    public List<KhachHang> getAll() {
        connection = ConnectMYSQL.getConnect();
        List<KhachHang> list = new ArrayList<>();
        String query = "select * from khachhang";
        try {
            PreparedStatement prepared=connection.prepareStatement(query);
            ResultSet resultNext = prepared.executeQuery();
            while (resultNext.next())
            {
                String maKhacHang = resultNext.getString("makhachhang");
                String tenDangNhap = resultNext.getString("tendangnhap");
                String matKhau = resultNext.getString("matkhau");
                String hoVaTen = resultNext.getString("hoten");
                String gioiTinh = resultNext.getString("gioitinh");
                String diaChi = resultNext.getString("diachi");
                String diaChiNhanHang = resultNext.getString("diachinhanhang");
                String diaChiMuaHang = resultNext.getString("diachimuahang");
                Date ngaySinh = resultNext.getDate("ngaysinh");
                String soDienThoai = resultNext.getString("sodienthoai");
                String email = resultNext.getString("email");
                boolean dangKyNhanBangTin = resultNext.getBoolean("dangkinhanbangtin");
                String duongDanAnh = resultNext.getString("duongdananh");
                list.add(new KhachHang(maKhacHang,tenDangNhap,matKhau,hoVaTen,gioiTinh,diaChi,diaChiNhanHang,
                        diaChiMuaHang,ngaySinh,soDienThoai,email,dangKyNhanBangTin,duongDanAnh));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  list;
    }

    @Override
    public int insert(KhachHang kh) {
        int ketQua = 0;
        connection = ConnectMYSQL.getConnect();

        String sql = "INSERT INTO khachhang (makhachhang, tendangnhap, matkhau, hoten, gioitinh, diachi," +
                " diachinhanhang, diachimuahang, ngaysinh, sodienthoai, email, dangkinhanbangtin,duongdananh) "
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement prepare = connection.prepareStatement(sql);
            prepare.setString(1, kh.getMaKhachHang());
            prepare.setString(2, kh.getTenDangNhap());
            prepare.setString(3, kh.getMatKhau());
            prepare.setString(4, kh.getHoVaTen());
            prepare.setString(5, kh.getGioiTinh());
            prepare.setString(6, kh.getDiaChi());
            prepare.setString(7, kh.getDiaChiNhanHang());
            prepare.setString(8, kh.getDiaChiMuaHang());
            prepare.setDate(9, kh.getNgaySinh());
            prepare.setString(10, kh.getSoDienThoai());
            prepare.setString(11, kh.getEmail());
            prepare.setBoolean(12, kh.isDangKyNhanBangTin());
            prepare.setString(13, kh.getDuongDanAnh());
            ketQua = prepare.executeUpdate();

            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if(connection!=null)
            {
                ConnectMYSQL.closeConnection(connection);
            }
        }

        return ketQua;
    }

    @Override
    public KhachHang selectByUsernameAndPassWord(KhachHang kh) {
        KhachHang khachHang = null;
        try {
            connection = ConnectMYSQL.getConnect();
            String sql = "SELECT * from khachhang where tendangnhap =? and matkhau=?";

            PreparedStatement prepare =connection.prepareStatement(sql);
            prepare.setString(1, kh.getTenDangNhap());
            prepare.setString(2, kh.getMatKhau());
            ResultSet rs= prepare.executeQuery();
            System.out.println(sql);
            while(rs.next())
            {
                String maKhachHang = rs.getString("makhachhang");
                String tenDangNhap = rs.getString("tendangnhap");
                String matKhau = rs.getString("matkhau");
                String hoVaTen = rs.getString("hoten");
                String gioiTinh = rs.getString("gioitinh");
                String diaChi = rs.getString("diachi");
                String diaChiNhanHang = rs.getString("diachinhanhang");
                String diaChiMuaHang = rs.getString("diachimuahang");
                Date ngaySinh = rs.getDate("ngaysinh");
                String soDienThoai = rs.getString("sodienthoai");
                String email = rs.getString("email");
                boolean dangKyNhanBangTin = rs.getBoolean("dangkinhanbangtin");
                String duongDanAnh = rs.getString("duongdananh");
                khachHang = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh,
                        diaChi, diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBangTin,duongDanAnh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(connection!=null)
            {
                ConnectMYSQL.closeConnection(connection);
            }
        }
        return khachHang;
    }

    public static void main(String[] args) {
    }
}

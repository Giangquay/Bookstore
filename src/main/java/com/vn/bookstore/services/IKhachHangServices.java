package com.vn.bookstore.services;

import com.vn.bookstore.models.KhachHang;
import com.vn.bookstore.models.SanPham;

import java.util.List;

public interface IKhachHangServices {
    List<KhachHang> selectAll();

    KhachHang selectByUsernameAndPassWord(KhachHang kh);
}

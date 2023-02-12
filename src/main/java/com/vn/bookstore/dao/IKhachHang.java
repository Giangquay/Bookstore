package com.vn.bookstore.dao;


import com.vn.bookstore.models.KhachHang;

import java.util.List;

public interface IKhachHang {
    List<KhachHang> getAll();

    int insert(KhachHang kh);

    KhachHang selectByUsernameAndPassWord(KhachHang kh);
}

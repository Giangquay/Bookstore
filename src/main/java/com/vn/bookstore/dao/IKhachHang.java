package com.vn.bookstore.dao;


import com.vn.bookstore.models.KhachHang;

import java.util.List;

public interface IKhachHang {


     KhachHang selectByID(KhachHang kh);

    List<KhachHang> getAll();

    int insert(KhachHang kh);

    KhachHang selectByUsernameAndPassWord(KhachHang kh);

     int updateImage(KhachHang kh);

    boolean changePassword(KhachHang kh);

    boolean checkUser(String nameLogin);
}

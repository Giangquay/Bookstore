package com.vn.bookstore.dao;

import com.vn.bookstore.models.DonHang;

import java.util.ArrayList;

public interface IDonHang {

    ArrayList<DonHang> selectAll();
     DonHang selectById(DonHang t) ;
    int insert(DonHang t);
    int delete(DonHang t);
    int update(DonHang t);

}

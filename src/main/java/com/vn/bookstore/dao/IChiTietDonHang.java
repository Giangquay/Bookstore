package com.vn.bookstore.dao;

import com.vn.bookstore.models.ChiTietDonHang;

import java.util.ArrayList;

public interface IChiTietDonHang {

    public ArrayList<ChiTietDonHang> selectAll();
    ChiTietDonHang selectById(ChiTietDonHang t);
    int insert(ChiTietDonHang t);
    public int update(ChiTietDonHang t);
}

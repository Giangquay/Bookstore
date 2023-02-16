package com.vn.bookstore.dao;

import com.vn.bookstore.models.SanPham;

import java.util.List;

public interface ISanPham {

    List<SanPham> getAll();
    SanPham selectById(SanPham sp);
    int insert(SanPham sp);
    int delete(SanPham sp);

    int update(SanPham sp);
}

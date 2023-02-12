package com.vn.bookstore.dao;

import com.vn.bookstore.models.TacGia;

import java.util.List;

public interface ITacGia {

    List<TacGia> getAll();
    TacGia selectById(TacGia tg);
}

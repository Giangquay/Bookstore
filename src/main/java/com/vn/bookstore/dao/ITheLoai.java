package com.vn.bookstore.dao;


import com.vn.bookstore.models.TheLoai;

import java.util.List;

public interface ITheLoai {

    List<TheLoai> getAll();
    TheLoai selectById(TheLoai tl);

    int insert(TheLoai tl);

    int delete(TheLoai tl);

    int update(TheLoai tl);
}

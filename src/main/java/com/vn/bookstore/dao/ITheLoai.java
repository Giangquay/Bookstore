package com.vn.bookstore.dao;


import com.vn.bookstore.models.TheLoai;

import java.util.List;

public interface ITheLoai {

    List<TheLoai> getAll();
    TheLoai selectById(TheLoai tl);
}

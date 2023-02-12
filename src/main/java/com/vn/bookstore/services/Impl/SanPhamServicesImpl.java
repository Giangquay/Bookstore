package com.vn.bookstore.services.Impl;

import com.vn.bookstore.dao.Impl.KhachHangImpl;
import com.vn.bookstore.models.KhachHang;
import com.vn.bookstore.services.IKhachHangServices;

import java.util.List;

public class SanPhamServicesImpl implements IKhachHangServices {
    @Override
    public List<KhachHang> selectAll() {
        return KhachHangImpl.getInstance().getAll();
    }
}

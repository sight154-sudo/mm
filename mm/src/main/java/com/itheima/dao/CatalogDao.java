package com.itheima.dao;

import com.itheima.domain.Catalog;

import java.util.List;

/**
 * @author: tang
 * @date: Create in 18:47 2021/7/1
 * @description:
 */
public interface CatalogDao {
    Catalog findById(String did);
    List<Catalog> findAll();

    int save(Catalog catalog);

    int update(Catalog catalog);

    int delete(String did);
}

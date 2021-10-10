package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Catalog;

import java.util.List;

/**
 * @author: tang
 * @date: Create in 18:47 2021/7/1
 * @description:
 */
public interface CatalogService {
    /**
     * 保存企业
     * @param catalog
     */
    void save(Catalog catalog);

    /**
     * 删除企业
     * @param catalog
     */
    void delete(Catalog catalog);

    void delete(String ids);
    /**
     * 修改企业
     * @param catalog
     */
    void update(Catalog catalog);

    /**
     * 查询单个企业
     * @param id
     * @return
     */
    Catalog findCatalogById(String id);
    /**
     * 查询所有
     * @return
     */
    List<Catalog> findAllCatalog();

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<Catalog> findAllCatalog(Integer currentPage, Integer pageSize);
}

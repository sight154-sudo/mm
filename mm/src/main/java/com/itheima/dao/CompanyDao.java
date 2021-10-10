package com.itheima.dao;

import com.itheima.domain.Company;

import java.util.List;

/**
 * @author: tang
 * @date: Create in 9:18 2021/7/1
 * @description:
 */
public interface CompanyDao {

    Company findById(String cid);
    List<Company> findAll();

    int save(Company company);

    int update(Company company);

    int delete(String cid);
}

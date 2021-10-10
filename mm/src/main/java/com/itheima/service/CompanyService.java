package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Company;

import java.util.List;

/**
 * @author: tang
 * @date: Create in 9:21 2021/7/1
 * @description:
 */
public interface CompanyService {

    /**
     * 保存企业
     * @param company
     */
    void save(Company company);

    /**
     * 删除企业
     * @param company
     */
    void delete(Company company);

    void delete(String ids);
    /**
     * 修改企业
     * @param company
     */
    void update(Company company);

    /**
     * 查询单个企业
     * @param id
     * @return
     */
    Company findCompanyById(String id);
    /**
     * 查询所有
     * @return
     */
    List<Company> findAllCompany();

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<Company> findAllCompany(Integer currentPage, Integer pageSize);
}

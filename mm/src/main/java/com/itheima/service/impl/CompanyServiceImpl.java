package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.CompanyDao;
import com.itheima.domain.Company;
import com.itheima.service.CompanyService;
import com.itheima.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author: tang
 * @date: Create in 9:22 2021/7/1
 * @description:
 */
public class CompanyServiceImpl implements CompanyService {
    @Override
    public void save(Company company) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);
            //给企业设置id
            String id = UUID.randomUUID().toString();
            company.setId(id);
            companyDao.save(company);
            MybatisUtil.commit(sqlSession);
        } catch (Exception e) {
            MybatisUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void delete(Company company) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);
            companyDao.delete(company.getId());
            MybatisUtil.commit(sqlSession);
        } catch (Exception e) {
            MybatisUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            try {
                MybatisUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String ids) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);
            String[] strs = ids.split(",");
            Arrays.stream(strs).forEach(id->companyDao.delete(id));
            MybatisUtil.commit(sqlSession);
        } catch (Exception e) {
            MybatisUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            try {
                MybatisUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Company company) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);
            companyDao.update(company);
            MybatisUtil.commit(sqlSession);
        } catch (Exception e) {
            MybatisUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            try {
                MybatisUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Company findCompanyById(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);
            return companyDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                MybatisUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Company> findAllCompany() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);
            return companyDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                MybatisUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo<Company> findAllCompany(Integer currentPage, Integer pageSize) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);
            PageHelper.startPage(currentPage,pageSize);
            List<Company> list = companyDao.findAll();
            PageInfo<Company> pageInfo = new PageInfo<>(list);
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                MybatisUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

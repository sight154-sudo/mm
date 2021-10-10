package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.CatalogDao;
import com.itheima.domain.Catalog;
import com.itheima.service.CatalogService;
import com.itheima.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: tang
 * @date: Create in 18:48 2021/7/1
 * @description:
 */
public class CatalogServiceImpl implements CatalogService {
    @Override
    public void save(Catalog catalog) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CatalogDao catalogDao = sqlSession.getMapper(CatalogDao.class);
            //给企业设置id
            String id = UUID.randomUUID().toString();
            catalog.setId(id);
            catalog.setCreateTime(new Date());
            catalogDao.save(catalog);
            MybatisUtil.commit(sqlSession);
        } catch (Exception e) {
            MybatisUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void delete(Catalog catalog) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CatalogDao catalogDao = sqlSession.getMapper(CatalogDao.class);
            catalogDao.delete(catalog.getId());
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
            CatalogDao catalogDao = sqlSession.getMapper(CatalogDao.class);
            String[] strs = ids.split(",");
            Arrays.stream(strs).forEach(id->catalogDao.delete(id));
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
    public void update(Catalog catalog) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CatalogDao catalogDao = sqlSession.getMapper(CatalogDao.class);
            catalogDao.update(catalog);
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
    public Catalog findCatalogById(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CatalogDao catalogDao = sqlSession.getMapper(CatalogDao.class);
            return catalogDao.findById(id);
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
    public List<Catalog> findAllCatalog() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CatalogDao catalogDao = sqlSession.getMapper(CatalogDao.class);
            return catalogDao.findAll();
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
    public PageInfo<Catalog> findAllCatalog(Integer currentPage, Integer pageSize) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            CatalogDao catalogDao = sqlSession.getMapper(CatalogDao.class);
            PageHelper.startPage(currentPage,pageSize);
            List<Catalog> list = catalogDao.findAll();
            PageInfo<Catalog> pageInfo = new PageInfo<>(list);
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

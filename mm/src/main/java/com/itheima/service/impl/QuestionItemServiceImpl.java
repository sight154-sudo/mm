package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.QuestionItemDao;
import com.itheima.domain.QuestionItem;
import com.itheima.service.QuestionItemService;
import com.itheima.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: tang
 * @date: Create in 20:21 2021/7/1
 * @description:
 */
public class QuestionItemServiceImpl  implements QuestionItemService{
    @Override
    public String save(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            QuestionItemDao questionItemDao = sqlSession.getMapper(QuestionItemDao.class);
            //给企业设置id
            String id = UUID.randomUUID().toString();
            questionItem.setId(id);
            questionItemDao.save(questionItem);
            MybatisUtil.commit(sqlSession);
            return id;
        } catch (Exception e) {
            MybatisUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void delete(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            QuestionItemDao questionItemDao = sqlSession.getMapper(QuestionItemDao.class);
            questionItemDao.delete(questionItem.getId());
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
    public void delete(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            QuestionItemDao questionItemDao = sqlSession.getMapper(QuestionItemDao.class);
            questionItemDao.delete(id);
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
    public void update(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            QuestionItemDao questionItemDao = sqlSession.getMapper(QuestionItemDao.class);
            questionItemDao.update(questionItem);
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
    public QuestionItem findQuestionItemById(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            QuestionItemDao questionItemDao = sqlSession.getMapper(QuestionItemDao.class);
            return questionItemDao.findById(id);
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
    public List<QuestionItem> findAllQuestionItem() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            QuestionItemDao questionItemDao = sqlSession.getMapper(QuestionItemDao.class);
            return questionItemDao.findAll();
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
    public PageInfo<QuestionItem> findAllQuestionItem(Integer currentPage, Integer pageSize) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            QuestionItemDao questionItemDao = sqlSession.getMapper(QuestionItemDao.class);
            PageHelper.startPage(currentPage,pageSize);
            List<QuestionItem> list = questionItemDao.findAll();
            PageInfo<QuestionItem> pageInfo = new PageInfo<>(list);
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

    @Override
    public List<QuestionItem> findAllQuestionItemByQid(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            QuestionItemDao questionItemDao = sqlSession.getMapper(QuestionItemDao.class);
            List<QuestionItem> list = questionItemDao.findAllByQid(id);
            return list;
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

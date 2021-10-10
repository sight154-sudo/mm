package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.MD5Util;
import com.itheima.utils.MybatisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @author: tang
 * @date: Create in 15:21 2021/7/1
 * @description:
 */
public class UserServiceImpl implements UserService {
    @Override
    public void save(User user) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            //给企业设置id
            String id = UUID.randomUUID().toString();
            user.setId(id);
            user.setPassword(MD5Util.md5(user.getPassword()));
            userDao.save(user);
            MybatisUtil.commit(sqlSession);
        } catch (Exception e) {
            MybatisUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void delete(User user) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            userDao.delete(user.getId());
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
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            String[] strs = ids.split(",");
            Arrays.stream(strs).forEach(id->userDao.delete(id));
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
    public void update(User user) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            userDao.update(user);
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
    public User findUserById(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            return userDao.findById(id);
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
    public List<User> findAllUser() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            return userDao.findAll();
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
    public PageInfo<User> findAllUser(Integer currentPage, Integer pageSize) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            PageHelper.startPage(currentPage,pageSize);
            List<User> list = userDao.findAll();
            PageInfo<User> pageInfo = new PageInfo<>(list);
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
    public List<Map<String,String>> findAllRole(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            List<Map<String,String>> list = userDao.findAllRoleByUid(id);
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

    @Override
    public List<String> findRoleIdsByUserId(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            List<String> ids = userDao.findRoleIdsByUserId(id);
            return ids;
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
    public void bindRoleByUserId(String uid, String ids) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            //先删除
            userDao.deleteRoleByUid(uid);
            if(StringUtils.isNotBlank(ids)){
                String[] strs = ids.split(",");
                Arrays.stream(strs).forEach(rid->userDao.bindRoleByUserId(uid,rid));
            }
            MybatisUtil.commit(sqlSession);
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
    public User login(String email, String password) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            password = MD5Util.md5(password);
            User user = userDao.findUserByEmailAndPwd(email,password);
            return user;
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
    public List<String> findAllPermission(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            List<String> list = userDao.findAllPermission(id);
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

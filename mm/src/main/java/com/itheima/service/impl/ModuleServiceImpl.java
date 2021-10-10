package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.ModuleDao;
import com.itheima.domain.Module;
import com.itheima.service.ModuleService;
import com.itheima.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: tang
 * @date: Create in 22:05 2021/7/1
 * @description:
 */
public class ModuleServiceImpl implements ModuleService {
    @Override
    public void save(Module module) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            ModuleDao moduleDao = sqlSession.getMapper(ModuleDao.class);
            //给企业设置id
            String id = UUID.randomUUID().toString();
            module.setId(id);
            moduleDao.save(module);
            MybatisUtil.commit(sqlSession);
        } catch (Exception e) {
            MybatisUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void delete(Module module) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            ModuleDao moduleDao = sqlSession.getMapper(ModuleDao.class);
            moduleDao.delete(module.getId());
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
            ModuleDao moduleDao = sqlSession.getMapper(ModuleDao.class);
            String[] strs = ids.split(",");
            Arrays.stream(strs).forEach(id->moduleDao.delete(id));
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
    public void update(Module module) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            ModuleDao moduleDao = sqlSession.getMapper(ModuleDao.class);
            moduleDao.update(module);
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
    public Module findModuleById(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            ModuleDao moduleDao = sqlSession.getMapper(ModuleDao.class);
            return moduleDao.findById(id);
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
    public List<Module> findAllModule() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            ModuleDao moduleDao = sqlSession.getMapper(ModuleDao.class);
            return moduleDao.findAll();
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
    public PageInfo<Module> findAllModule(Integer currentPage, Integer pageSize) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            ModuleDao moduleDao = sqlSession.getMapper(ModuleDao.class);
            PageHelper.startPage(currentPage,pageSize);
            List<Module> list = moduleDao.findAll();
            PageInfo<Module> pageInfo = new PageInfo<>(list);
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
    public List<Module> findAllModule(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            ModuleDao moduleDao = sqlSession.getMapper(ModuleDao.class);
            List<Module> list = moduleDao.findAllExcept(id);
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
    public List<Map<String,String>> findAuthorDataByRoleId(String roleId) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            ModuleDao moduleDao = sqlSession.getMapper(ModuleDao.class);
            return moduleDao.findAuthorDataByRoleId(roleId);
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
    public List<Module> findAllModuleAndLevel(String uid) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            ModuleDao moduleDao = sqlSession.getMapper(ModuleDao.class);
            List<Module> list = moduleDao.findAllByUid(uid);
            List<Module> moduleList = list.stream().filter(module -> module.getParentId() == null).collect(Collectors.toList());
            moduleList.stream().forEach(module->{
                module.setChildren(list.stream().filter(m->module.getId().equals(m.getParentId())).collect(Collectors.toList()));
            });
            return moduleList;
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
    public List<String> findPermissionByUserId(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            ModuleDao moduleDao = sqlSession.getMapper(ModuleDao.class);
            List<Module> moduleList = moduleDao.findAllByUid(id);
            List<String> curl = new ArrayList<>();
            for (Module module : moduleList) {
                curl.add(module.getCurl());
            }
            return curl;
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

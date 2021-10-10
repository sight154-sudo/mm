package com.itheima.service;

import com.itheima.dao.ModuleDao;
import com.itheima.domain.Module;
import com.itheima.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

/**
 * @author: tang
 * @date: Create in 18:09 2021/7/2
 * @description:
 */
public class ModuleServiceTest {

    private SqlSession sqlSession;

    @Before
    public void init(){
        sqlSession = MybatisUtil.getSqlSession();
    }


    @Test
    public void findModuleByUid(){
        //使用普通for
        ModuleDao mapper = sqlSession.getMapper(ModuleDao.class);
        List<Module> list = mapper.findAllByUid("8776625d-de7f-4e1c-ac44-3d2cd53247ff");
        List<Module> moduleList = list.stream().filter(module -> module.getParentId() == null).collect(Collectors.toList());
        for (Module parentModule : moduleList) {
            List<Module> tmp = new ArrayList<>();
            for (Module module : list) {
                if(parentModule.getId().equals(module.getParentId())){
                    tmp.add(module);
                }
            }
            parentModule.setChildren(tmp);
        }
        System.out.println(moduleList);
//        System.out.println(list);
//        System.out.println("----");
//        System.out.println(moduleList);
    }

    @Test
    public void findModuleByUid2(){
        //使用箭头函数
        ModuleDao mapper = sqlSession.getMapper(ModuleDao.class);
        List<Module> list = mapper.findAllByUid("8776625d-de7f-4e1c-ac44-3d2cd53247ff");
        List<Module> moduleList = list.stream().filter(module -> module.getParentId() == null).collect(Collectors.toList());
        moduleList.stream().forEach(module->{
            module.setChildren(list.stream().filter(m->module.getId().equals(m.getParentId())).collect(Collectors.toList()));
        });
        System.out.println(moduleList);
    }
    @After
    public void destory(){
        if(sqlSession!=null)sqlSession.close();
    }
}

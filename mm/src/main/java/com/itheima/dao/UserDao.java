package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: tang
 * @date: Create in 15:20 2021/7/1
 * @description:
 */
public interface UserDao {
    User findById(String did);
    List<User> findAll();

    int save(User user);

    int update(User user);

    int delete(String did);

    List<Map<String,String>> findAllRoleByUid(String id);

    List<String> findRoleIdsByUserId(String id);

    void bindRoleByUserId(@Param("uid") String uid, @Param("rid") String rid);

    void deleteRoleByUid(String uid);

    User findUserByEmailAndPwd(@Param("email") String email,@Param("password") String password);

    List<String> findAllPermission(String id);
}

package com.itheima.dao;

import com.itheima.domain.Question;

import java.util.List;

/**
 * @author: tang
 * @date: Create in 19:04 2021/7/1
 * @description:
 */
public interface QuestionDao {
    Question findById(String did);
    List<Question> findAll();

    int save(Question question);

    int update(Question question);

    int delete(String did);
}

package com.itheima.dao;

import com.itheima.domain.QuestionItem;

import java.util.List;

/**
 * @author: tang
 * @date: Create in 20:19 2021/7/1
 * @description:
 */
public interface QuestionItemDao {
    QuestionItem findById(String did);
    List<QuestionItem> findAll();

    int save(QuestionItem questionItem);

    int update(QuestionItem questionItem);

    int delete(String did);

    List<QuestionItem> findAllByQid(String id);
}

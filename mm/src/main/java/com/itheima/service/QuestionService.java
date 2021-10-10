package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Question;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * @author: tang
 * @date: Create in 19:04 2021/7/1
 * @description:
 */
public interface QuestionService {
    /**
     * 保存企业
     * @param question
     */
    void save(Question question);

    /**
     * 删除企业
     * @param question
     */
    void delete(Question question);

    void delete(String ids);
    /**
     * 修改企业
     * @param question
     */
    void update(Question question);

    /**
     * 查询单个企业
     * @param id
     * @return
     */
    Question findQuestionById(String id);
    /**
     * 查询所有
     * @return
     */
    List<Question> findAllQuestion();

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<Question> findAllQuestion(Integer currentPage, Integer pageSize);

    ByteArrayOutputStream toExport();
}

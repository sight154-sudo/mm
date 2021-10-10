package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.QuestionItem;

import java.util.List;

/**
 * @author: tang
 * @date: Create in 20:20 2021/7/1
 * @description:
 */
public interface QuestionItemService {
    /**
     * 保存企业
     * @param questionItem
     */
    String save(QuestionItem questionItem);

    /**
     * 删除企业
     * @param questionItem
     */
    void delete(QuestionItem questionItem);

    void delete(String ids);
    /**
     * 修改企业
     * @param questionItem
     */
    void update(QuestionItem questionItem);

    /**
     * 查询单个企业
     * @param id
     * @return
     */
    QuestionItem findQuestionItemById(String id);
    /**
     * 查询所有
     * @return
     */
    List<QuestionItem> findAllQuestionItem();

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageInfo<QuestionItem> findAllQuestionItem(Integer currentPage, Integer pageSize);

    /**
     * 根据题目id查询所有的题目选项
     * @param id
     * @return
     */
    List<QuestionItem> findAllQuestionItemByQid(String id);
}

package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.QuestionDao;
import com.itheima.domain.Question;
import com.itheima.service.QuestionService;
import com.itheima.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: tang
 * @date: Create in 19:05 2021/7/1
 * @description:
 */
public class QuestionServiceImpl implements QuestionService {
    @Override
    public void save(Question question) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
            //给企业设置id
            String id = UUID.randomUUID().toString();
            question.setId(id);
            question.setCreateTime(new Date());
            questionDao.save(question);
            MybatisUtil.commit(sqlSession);
        } catch (Exception e) {
            MybatisUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            MybatisUtil.close(sqlSession);
        }
    }

    @Override
    public void delete(Question question) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
            questionDao.delete(question.getId());
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
            QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
            String[] strs = ids.split(",");
            Arrays.stream(strs).forEach(id->questionDao.delete(id));
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
    public void update(Question question) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
            questionDao.update(question);
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
    public Question findQuestionById(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
            return questionDao.findById(id);
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
    public List<Question> findAllQuestion() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
            return questionDao.findAll();
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
    public PageInfo<Question> findAllQuestion(Integer currentPage, Integer pageSize) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
            PageHelper.startPage(currentPage,pageSize);
            List<Question> list = questionDao.findAll();
            PageInfo<Question> pageInfo = new PageInfo<>(list);
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
    public ByteArrayOutputStream toExport() {
        ByteArrayOutputStream os = null;
        Workbook wb = null;
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
            //创建工作薄
            wb = new SXSSFWorkbook();
            //创建工作表
            Sheet sheet = wb.createSheet();

            //第一步  创建标题
            //合并单元格
            sheet.addMergedRegion(new CellRangeAddress(1,1,1,12));
            //获取行
            Row row_1 = sheet.createRow(1);
            //获取列
            Cell cell_1_1 = row_1.createCell(1);
            cell_1_1.setCellValue("在线试题导出信息");
            //设置样式
            //创建样式
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            //给单元格设置样式
            cell_1_1.setCellStyle(cellStyle);

            //第二步  创建表头
            Row row_2 = sheet.createRow(2);
            String[] fileds = {"题目ID","所属公司ID","所属目录ID","题目简介","题干描述","题干配图","题目分析","题目类型","题目难度","是否经典题","题目状态","审核状态"};
            for (int i = 0; i < fileds.length; i++) {
                Cell cell_2_1 = row_2.createCell(1+i);
                cell_2_1.setCellValue(fileds[i]);
            }

            List<Question> list = questionDao.findAll();
            //第三步  写入数据

            int i = 3;
            for (Question q : list) {
                Row row_3 = sheet.createRow(i++);
                int index = 1;
                Cell cell_3_1 = row_3.createCell(index++);
                cell_3_1.setCellValue(q.getId());
                cell_3_1.setCellStyle(cellStyle);

                Cell cell_3_2 = row_3.createCell(index++);
                cell_3_2.setCellValue(q.getCompanyId());
                cell_3_2.setCellStyle(cellStyle);

                Cell cell_3_3 = row_3.createCell(index++);
                cell_3_3.setCellValue(q.getCatalogId());
                cell_3_3.setCellStyle(cellStyle);

                Cell cell_3_4 = row_3.createCell(index++);
                cell_3_4.setCellValue(q.getRemark());
                cell_3_4.setCellStyle(cellStyle);

                Cell cell_3_5 = row_3.createCell(index++);
                cell_3_5.setCellValue(q.getSubject());
                cell_3_5.setCellStyle(cellStyle);

                Cell cell_3_6 = row_3.createCell(index++);
                cell_3_6.setCellValue(q.getPicture());
                cell_3_6.setCellStyle(cellStyle);

                Cell cell_3_7 = row_3.createCell(index++);
                cell_3_7.setCellValue(q.getAnalysis());
                cell_3_7.setCellStyle(cellStyle);

                Cell cell_3_8 = row_3.createCell(index++);
                cell_3_8.setCellValue(q.getType());
                cell_3_8.setCellStyle(cellStyle);

                Cell cell_3_9 = row_3.createCell(index++);
                cell_3_9.setCellValue(q.getDifficulty());
                cell_3_9.setCellStyle(cellStyle);

                Cell cell_3_10 = row_3.createCell(index++);
                cell_3_10.setCellValue(q.getIsClassic());
                cell_3_10.setCellStyle(cellStyle);

                Cell cell_3_11 = row_3.createCell(index++);
                cell_3_11.setCellValue(q.getState());
                cell_3_11.setCellStyle(cellStyle);

                Cell cell_3_12 = row_3.createCell(index++);
                cell_3_12.setCellValue(q.getReviewStatus());
                cell_3_12.setCellStyle(cellStyle);
            }


            //写入本地文件
            os = new ByteArrayOutputStream();
            wb.write(os);
            return os;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(wb != null)wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

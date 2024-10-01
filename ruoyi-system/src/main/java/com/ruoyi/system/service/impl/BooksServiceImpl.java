package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BooksMapper;
import com.ruoyi.system.domain.Books;
import com.ruoyi.system.service.IBooksService;

/**
 * 图书信息Service业务层处理
 * 
 * @author 图书管理系统
 * @date 2024-09-28
 */
@Service
public class BooksServiceImpl implements IBooksService 
{
    @Autowired
    private BooksMapper booksMapper;

    /**
     * 查询图书信息
     * 
     * @param id 图书信息主键
     * @return 图书信息
     */
    @Override
    public Books selectBooksById(Long id)
    {
        return booksMapper.selectBooksById(id);
    }

    /**
     * 查询图书信息列表
     * 
     * @param books 图书信息
     * @return 图书信息
     */
    @Override
    public List<Books> selectBooksList(Books books)
    {
        return booksMapper.selectBooksList(books);
    }

    /**
     * 新增图书信息
     * 
     * @param books 图书信息
     * @return 结果
     */
    @Override
    public int insertBooks(Books books)
    {
        books.setCreateTime(DateUtils.getNowDate());
        return booksMapper.insertBooks(books);
    }

    /**
     * 修改图书信息
     * 
     * @param books 图书信息
     * @return 结果
     */
    @Override
    public int updateBooks(Books books)
    {
        books.setUpdateTime(DateUtils.getNowDate());
        return booksMapper.updateBooks(books);
    }

    /**
     * 批量删除图书信息
     * 
     * @param ids 需要删除的图书信息主键
     * @return 结果
     */
    @Override
    public int deleteBooksByIds(Long[] ids)
    {
        return booksMapper.deleteBooksByIds(ids);
    }

    /**
     * 删除图书信息信息
     * 
     * @param id 图书信息主键
     * @return 结果
     */
    @Override
    public int deleteBooksById(Long id)
    {
        return booksMapper.deleteBooksById(id);
    }
}

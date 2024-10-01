package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Books;

/**
 * 图书信息Mapper接口
 * 
 * @author 图书管理系统
 * @date 2024-09-28
 */
public interface BooksMapper 
{
    /**
     * 查询图书信息
     * 
     * @param id 图书信息主键
     * @return 图书信息
     */
    public Books selectBooksById(Long id);

    /**
     * 查询图书信息列表
     * 
     * @param books 图书信息
     * @return 图书信息集合
     */
    public List<Books> selectBooksList(Books books);

    /**
     * 新增图书信息
     * 
     * @param books 图书信息
     * @return 结果
     */
    public int insertBooks(Books books);

    /**
     * 修改图书信息
     * 
     * @param books 图书信息
     * @return 结果
     */
    public int updateBooks(Books books);

    /**
     * 删除图书信息
     * 
     * @param id 图书信息主键
     * @return 结果
     */
    public int deleteBooksById(Long id);

    /**
     * 批量删除图书信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBooksByIds(Long[] ids);
}

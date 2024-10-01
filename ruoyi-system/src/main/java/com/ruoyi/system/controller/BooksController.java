package com.ruoyi.system.controller;

import cn.hutool.json.JSONUtil;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Books;
import com.ruoyi.system.service.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 图书信息Controller
 *
 * @author 图书管理系统
 * @date 2024-09-28
 */
@RestController
@RequestMapping("/system/books")
public class BooksController extends BaseController {
    @Autowired
    private IBooksService booksService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 查询图书信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:books:list')")
    @GetMapping("/list")
    @Anonymous
    public TableDataInfo list(Books books) {
        // 检查传入的books对象是否为null
        if (books == null) {
            throw new IllegalArgumentException("Books object must not be null");
        }
        // 从Redis中获取缓存数据
        String list1 = (String) redisTemplate.opsForValue().get("list");
        if (list1 != null) {
            // 说明缓存有数据
            // 将list1转换成List<Books>
            List<Books> list = JSONUtil.toList(list1, Books.class);
            return getDataTable(list);
        } else {
            // 说明缓存没有数据
            startPage();
            // 检查booksService是否被正确注入
            if (booksService == null) {
                throw new IllegalStateException("BooksService is not initialized");
            }
            List<Books> list = booksService.selectBooksList(books);
            // 将数据添加到缓存
            redisTemplate.opsForValue().set("list", JSONUtil.toJsonStr(list), 60, TimeUnit.MINUTES);
            return getDataTable(list);
        }
    }


    /**
     * 导出图书信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:books:export')")
    @Log(title = "图书信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @Anonymous
    public void export(HttpServletResponse response, Books books) {
        List<Books> list = booksService.selectBooksList(books);
        ExcelUtil<Books> util = new ExcelUtil<Books>(Books.class);
        util.exportExcel(response, list, "图书信息数据");
    }

    /**
     * 获取图书信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:books:query')")
    @GetMapping(value = "/{id}")
    @Anonymous
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(booksService.selectBooksById(id));
    }

    /**
     * 新增图书信息
     */
//    @PreAuthorize("@ss.hasPermi('system:books:add')")
    @Log(title = "图书信息", businessType = BusinessType.INSERT)
    @PostMapping
    @Anonymous
    public AjaxResult add(@RequestBody Books books) {
        return toAjax(booksService.insertBooks(books));
    }

    /**
     * 修改图书信息
     */
//    @PreAuthorize("@ss.hasPermi('system:books:edit')")
    @Log(title = "图书信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @Anonymous
    public AjaxResult edit(@RequestBody Books books) {
        return toAjax(booksService.updateBooks(books));
    }

    /**
     * 删除图书信息
     */
//    @PreAuthorize("@ss.hasPermi('system:books:remove')")
    @Log(title = "图书信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @Anonymous
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(booksService.deleteBooksByIds(ids));
    }


}

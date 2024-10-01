package com.ruoyi.common.utils;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;

import java.util.List;
import java.util.stream.Collectors;

public class PageHelperUtils {
    public static TableDataInfo page(List<?> list){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();

        //获取处理好的list集合
        int num = list.size();
        list = list.stream().skip((pageNum - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(list);
        rspData.setTotal(num);
        return rspData;

    }
}
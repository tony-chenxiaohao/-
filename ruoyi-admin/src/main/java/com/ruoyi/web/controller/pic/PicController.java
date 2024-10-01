package com.ruoyi.web.controller.pic;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.pic.domain.VmpRelationFile;
import com.ruoyi.pic.mapper.VmpRelationFileMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pic")
public class PicController {


    @Resource
    private VmpRelationFileMapper fileMapper;
    @GetMapping
    public AjaxResult pics(){
        return AjaxResult.success(fileMapper.selectList(new LambdaQueryWrapper<VmpRelationFile>().lt(VmpRelationFile::getId,5)));
    }
}

//package com.ruoyi.gonggao.service.impl;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import com.github.yulichang.base.MPJBaseServiceImpl;
//import com.github.yulichang.wrapper.MPJLambdaWrapper;
//import com.ruoyi.common.annotation.DataScope;
//import com.ruoyi.common.core.domain.entity.SysDept;
//import com.ruoyi.common.core.domain.entity.SysUser;
//import com.ruoyi.common.utils.DateUtils;
//import com.ruoyi.common.utils.SecurityUtils;
//import lombok.Builder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.ruoyi.gonggao.mapper.GonggaoMapper;
//import com.ruoyi.gonggao.domain.Gonggao;
//import com.ruoyi.gonggao.service.IGonggaoService;
//
///**
// * 公告Service业务层处理
// *
// * @author deng
// * @date 2024-09-01
// */
//@Service
//public class GonggaoServiceImpl extends MPJBaseServiceImpl<GonggaoMapper, Gonggao> implements IGonggaoService {
//    @Autowired
//    private GonggaoMapper gonggaoMapper;
//
//    /**
//     * 查询公告
//     *
//     * @param id 公告主键
//     * @return 公告
//     */
//    @Override
//    public Gonggao selectGonggaoById(Long id) {
//        return gonggaoMapper.selectGonggaoById(id);
//    }
//
//    /**
//     * 查询公告列表
//     *
//     * @param gonggao 公告
//     * @return 公告
//     */
//    @Override
//    @DataScope(deptAlias = "d", userAlias = "u")
//    public List<Gonggao> selectGonggaoList(Gonggao gonggao) {
//        // 获取当前用户的数据范围
//        String dataScope = Optional.ofNullable((String) gonggao.getParams().get("dataScope"))
//                .map(String::trim)
//                .orElse("");
//        // 构建查询
//        MPJLambdaWrapper<Gonggao> wrapper = new MPJLambdaWrapper<Gonggao>()
//                .selectAll(Gonggao.class)
//                .leftJoin(SysUser.class, "u", SysUser::getUserId, Gonggao::getCreateBy)
//                .leftJoin(SysDept.class, "d", SysDept::getDeptId, SysUser::getDeptId)
//                .likeIfExists(Gonggao::getName, gonggao.getName())
//                .likeIfExists(Gonggao::getContent, gonggao.getContent())
//                .isNotNull(SysDept::getDeptId);
//
//        // 手动应用数据范围，移除多余的 "AND" 前缀
//        if (!dataScope.isEmpty()) {
//            if (dataScope.toUpperCase().startsWith("AND")) {
//                dataScope = dataScope.substring(3).trim();
//            }
//            wrapper.apply(dataScope);
//        }
//
//        // 执行查询
//        return gonggaoMapper.selectJoinList(Gonggao.class, wrapper);
//    }
//
//
//
//
//    /**
//     * 新增公告
//     *
//     * @param gonggao 公告
//     * @return 结果
//     */
//    @Override
//    public int insertGonggao(Gonggao gonggao) {
//        gonggao.setCreateTime(DateUtils.getNowDate());
//        gonggao.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
//        return gonggaoMapper.insertGonggao(gonggao);
//    }
//
//    /**
//     * 修改公告
//     *
//     * @param gonggao 公告
//     * @return 结果
//     */
//    @Override
//    public int updateGonggao(Gonggao gonggao) {
//        gonggao.setCreateBy(String.valueOf(SecurityUtils.getUserId()));
//        return gonggaoMapper.updateGonggao(gonggao);
//    }
//
//    /**
//     * 批量删除公告
//     *
//     * @param ids 需要删除的公告主键
//     * @return 结果
//     */
//    @Override
//    public int deleteGonggaoByIds(Long[] ids) {
//        return gonggaoMapper.deleteGonggaoByIds(ids);
//    }
//
//    /**
//     * 删除公告信息
//     *
//     * @param id 公告主键
//     * @return 结果
//     */
//    @Override
//    public int deleteGonggaoById(Long id) {
//        return gonggaoMapper.deleteGonggaoById(id);
//    }
//}

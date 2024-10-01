package com.ruoyi.gonggao.mapper;

import java.util.List;

import com.github.yulichang.base.MPJBaseMapper;
import com.ruoyi.gonggao.domain.Gonggao;

/**
 * 公告Mapper接口
 * 
 * @author deng
 * @date 2024-09-01
 */
public interface GonggaoMapper extends MPJBaseMapper<Gonggao>
{
    /**
     * 查询公告
     * 
     * @param id 公告主键
     * @return 公告
     */
    public Gonggao selectGonggaoById(Long id);

    /**
     * 查询公告列表
     * 
     * @param gonggao 公告
     * @return 公告集合
     */
    public List<Gonggao> selectGonggaoList(Gonggao gonggao);

    /**
     * 新增公告
     * 
     * @param gonggao 公告
     * @return 结果
     */
    public int insertGonggao(Gonggao gonggao);

    /**
     * 修改公告
     * 
     * @param gonggao 公告
     * @return 结果
     */
    public int updateGonggao(Gonggao gonggao);

    /**
     * 删除公告
     * 
     * @param id 公告主键
     * @return 结果
     */
    public int deleteGonggaoById(Long id);

    /**
     * 批量删除公告
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGonggaoByIds(Long[] ids);
}

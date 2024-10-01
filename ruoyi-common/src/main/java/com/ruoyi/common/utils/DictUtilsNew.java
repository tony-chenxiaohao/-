package com.ruoyi.common.utils;

import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.spring.SpringUtils;

import java.util.Collection;
import java.util.List;

/**
 * 字典工具类
 * 
 * @author ruoyi
 */
public class DictUtilsNew
{
    /**
     * 分隔符
     */
    public static final String SEPARATOR = ",";

    /**
     * 设置字典缓存
     * 
     * @param key 参数键
     * @param dictDatas 字典数据列表
     */
    public static void setDictCache(String key, List<SysDictData> dictDatas)
    {
        SpringUtils.getBean(RedisCache.class).setCacheObject(getCacheKey(key), dictDatas);
    }

    /**
     * 获取字典缓存
     * 
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    public static List<SysDictData> getDictCache(String key)
    {
        JSONArray arrayCache = SpringUtils.getBean(RedisCache.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(arrayCache))
        {
            return arrayCache.toList(SysDictData.class);
        }
        return null;
    }

    /**
     * 根据字典类型和字典值获取字典标签
     * 
     * @param dictType 字典类型
     * @param dictValue 字典值
     * @return 字典标签
     */
    public static String getDictLabel(String dictType, String dictValue)
    {
        return getDictLabel(dictType, dictValue, SEPARATOR);
    }

    /**
     * 根据字典类型和字典标签获取字典值
     * 
     * @param dictType 字典类型
     * @param dictLabel 字典标签
     * @return 字典值
     */
    public static String getDictValue(String dictType, String dictLabel)
    {
        return getDictValue(dictType, dictLabel, SEPARATOR);
    }

    /**
     * 根据字典类型和字典值获取字典标签
     * 
     * @param dictType 字典类型
     * @param dictValue 字典值
     * @param separator 分隔符
     * @return 字典标签
     */
    public static String getDictLabel(String dictType, String dictValue, String separator)
    {
        StringBuilder propertyString = new StringBuilder();
        List<SysDictData> datas = getDictCache(dictType);

        if (StringUtils.isNotNull(datas))
        {
            if (StringUtils.containsAny(separator, dictValue))
            {
                for (SysDictData dict : datas)
                {
                    for (String value : dictValue.split(separator))
                    {
                        if (value.equals(dict.getDictValue()))
                        {
                            propertyString.append(dict.getDictLabel()).append(separator);
                            break;
                        }
                    }
                }
            }
            else
            {
                for (SysDictData dict : datas)
                {
                    if (dictValue.equals(dict.getDictValue()))
                    {
                        return dict.getDictLabel();
                    }
                }
            }
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 根据字典类型和字典标签获取字典值
     * 
     * @param dictType 字典类型
     * @param dictLabel 字典标签
     * @param separator 分隔符
     * @return 字典值
     */
    public static String getDictValue(String dictType, String dictLabel, String separator)
    {
        StringBuilder propertyString = new StringBuilder();
        List<SysDictData> datas = getDictCache(dictType);

        if (StringUtils.containsAny(separator, dictLabel) && StringUtils.isNotEmpty(datas))
        {
            for (SysDictData dict : datas)
            {
                for (String label : dictLabel.split(separator))
                {
                    if (label.equals(dict.getDictLabel()))
                    {
                        propertyString.append(dict.getDictValue()).append(separator);
                        break;
                    }
                }
            }
        }
        else
        {
            for (SysDictData dict : datas)
            {
                if (dictLabel.equals(dict.getDictLabel()))
                {
                    return dict.getDictValue();
                }
            }
        }
        return StringUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 删除指定字典缓存
     * 
     * @param key 字典键
     */
    public static void removeDictCache(String key)
    {
        SpringUtils.getBean(RedisCache.class).deleteObject(getCacheKey(key));
    }

    /**
     * 清空字典缓存
     */
    public static void clearDictCache()
    {
        Collection<String> keys = SpringUtils.getBean(RedisCache.class).keys(CacheConstants.SYS_DICT_KEY + "*");
        SpringUtils.getBean(RedisCache.class).deleteObject(keys);
    }

    /**
     * 设置cache key
     * 
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey)
    {
        return CacheConstants.SYS_DICT_KEY + configKey;
    }

    /**
     * 根据指定的type ，value 获取 label
     * @param value
     * @param type
     * @param defaultLabel 默认label
     * @return
     */
    public static String getDictLabelByTypeAndValue(String value, String type, String defaultLabel){
        if (StringUtils.isNotBlank(type) && ObjectUtils.isNotNull(value)){
//        if (StringUtils.isNotBlank(type)){
            List<SysDictData> dataList = getDictCache(type);
            for (SysDictData dict : dataList){
                if (type.equals(dict.getDictType()) && value.equals(dict.getDictCode().toString())){
                    return dict.getDictLabel();
                }
            }
        }
        return defaultLabel;
    }

    /**
     * 根据type,label 获取value
     *
     * @param label
     * @param type
     * @param defaultValue
     * @return
     */
    public static Long getDictValueByTypeAndLabel(String label,String type,String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
            for (SysDictData dict : getDictCache(type)){
                if(type.equals(dict.getDictType())  && label.equals(dict.getDictLabel())) {
                    return dict.getDictCode();
                }
            }
        }
        return null;
    }

    /**
     * 根据 type 将 lable组成 string数组
     * @param type
     * @return
     */
    public static String[] getLabelArr(String type) {
        String[] arr0 = new String[0];
        if (StringUtils.isNotBlank(type)) {
            List<SysDictData> dictList = getDictCache(type);
            String[] strArr = new String[dictList.size()];
            for (int i = 0 ; i < dictList.size() ; i ++) {
                strArr[i] = dictList.get(i).getDictLabel();
            }
            return strArr;
        }
        return arr0;
    }
}

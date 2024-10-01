package com.ruoyi.web.controller.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysUserProfileDto extends BaseEntity {


    /**
     * 用户ID
     */
    @Excel(name = "用户序号", cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 部门ID
     */
    @Excel(name = "部门编号", type = Excel.Type.IMPORT)
    private Long deptId;

    /**
     * 用户账号
     */

    @Excel(name = "登录名称", cellType = Excel.ColumnType.TEXT)
//    @Sensitives(DesensitizedUtil.DesensitizedType.MOBILE_PHONE)
//    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    private String userName;

    /**
     * 用户昵称
     */
//    @Sensitives(DesensitizedUtil.DesensitizedType.CHINESE_NAME)
//    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    @Excel(name = "用户名称")
    private String nickName;

    /**
     * 用户邮箱
     */
    @Excel(name = "用户邮箱")
    private String email;

    /**
     * 手机号码
     */

    @Excel(name = "手机号码", cellType = Excel.ColumnType.TEXT)
//    @Sensitives(DesensitizedUtil.DesensitizedType.MOBILE_PHONE)
//    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    private String phonenumber;

    /**
     * 用户性别
     */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 最后登录IP
     */
    @Excel(name = "最后登录IP", type = Excel.Type.EXPORT)
    private String loginIp;

    /**
     * 最后登录时间
     */
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    private Date loginDate;

    /**
     * 部门对象
     */
    @Excels({
            @Excel(name = "部门名称", targetAttr = "deptName", type = Excel.Type.EXPORT),
            @Excel(name = "部门负责人", targetAttr = "leader", type = Excel.Type.EXPORT)
    })
    @TableField(exist = false)
    private SysDept dept;

    /**
     * 角色对象
     */
    @TableField(exist = false)
    private List<SysRole> roles;

    /**
     * 角色组
     */
    @TableField(exist = false)
    private Long[] roleIds;

    /**
     * 岗位组
     */
    @TableField(exist = false)
    private Long[] postIds;

    /**
     * 角色ID
     */
    @TableField(exist = false)
    private Long roleId;

    //区域id
    private Long regionId;
    //区域名称
    @TableField(exist = false)
    @Excel(name = "区域名称")
    private String regionName;


}

package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 车辆信息
 * @TableName vmp_vehicle
 */
@TableName(value ="vmp_vehicle")
@Data
public class VmpVehicle1 implements Serializable {
    /**
     * 车辆ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     * 车辆类型
     */
    private String vehicleCategory;

    /**
     * 车辆类型 0：私车 1：公车
     */
    private Integer vehicleType;

    /**
     * 公车类型: 1生产用车 2行政用车
     */
    private Integer publicType;

    /**
     * 状态  0：未出车 1：已出车
     */
    private Integer status;

    /**
     * 当前里程
     */
    private Integer currentKm;

    /**
     * 部门id 
     */
    private Long deptId;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        VmpVehicle1 other = (VmpVehicle1) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPlateNumber() == null ? other.getPlateNumber() == null : this.getPlateNumber().equals(other.getPlateNumber()))
            && (this.getVehicleCategory() == null ? other.getVehicleCategory() == null : this.getVehicleCategory().equals(other.getVehicleCategory()))
            && (this.getVehicleType() == null ? other.getVehicleType() == null : this.getVehicleType().equals(other.getVehicleType()))
            && (this.getPublicType() == null ? other.getPublicType() == null : this.getPublicType().equals(other.getPublicType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCurrentKm() == null ? other.getCurrentKm() == null : this.getCurrentKm().equals(other.getCurrentKm()))
            && (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPlateNumber() == null) ? 0 : getPlateNumber().hashCode());
        result = prime * result + ((getVehicleCategory() == null) ? 0 : getVehicleCategory().hashCode());
        result = prime * result + ((getVehicleType() == null) ? 0 : getVehicleType().hashCode());
        result = prime * result + ((getPublicType() == null) ? 0 : getPublicType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCurrentKm() == null) ? 0 : getCurrentKm().hashCode());
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", plateNumber=").append(plateNumber);
        sb.append(", vehicleCategory=").append(vehicleCategory);
        sb.append(", vehicleType=").append(vehicleType);
        sb.append(", publicType=").append(publicType);
        sb.append(", status=").append(status);
        sb.append(", currentKm=").append(currentKm);
        sb.append(", deptId=").append(deptId);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
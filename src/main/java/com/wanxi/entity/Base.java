package com.wanxi.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "公共实体",description = "用于共用实体")
@Data
public class Base extends Page {
    @ApiModelProperty(value = "id",example = "唯一主键")
    private Integer id;
    @ApiModelProperty(value = "enable",example = "是否启用")
    private Integer enable;
    @ApiModelProperty(value = "createTime",example = "createTime")
    private String createTime;
    @ApiModelProperty(value = "updateTime",example = "updateTime")
    private String updateTime;
    private String remark;
    private String infName;
    private String infValue;
    private String infType;
    private String infLink;
    private String infBelongTo;
    private Integer isShow;
    private String img;
    private String code;

}

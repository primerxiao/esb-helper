package com.primer.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EsbExcelData {
    //原始接口 src
    //英文名称
    private String srcEnName;
    //中文名称
    private String srcCnName;
    //数据类型
    private String srcDataType;
    //数据长度
    private String srcDataLength;
    //是否必输
    private String srcIsMustNeed;
    //备注
    private String srcRemark;
    //G列
    private String colG;
    //英文名称
    private String esbEnName;
    //中文名称
    private String esbCnName;
    //数据类型
    private String esbDataType;
    //是否必输
    private String esbIsMustNeed;
    //约束条件
    private String esbConstraintCondition;
    //备注
    private String esbRemark;

}

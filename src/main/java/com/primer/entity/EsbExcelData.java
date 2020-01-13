package com.primer.entity;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import lombok.Data;
import lombok.ToString;
import org.springframework.util.StringUtils;

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

    public String getLenght() {
        try {
            if (StringUtils.isEmpty(srcDataLength)) {
                String lengthByDataType = getLengthByDataType();
                if (lengthByDataType.contains(",")) {
                    return lengthByDataType.trim().split(",")[0];
                }
                if (lengthByDataType.contains("，")) {
                    return lengthByDataType.trim().split("，")[0];
                }
            } else {
                //判断是否有，号
                if (srcDataLength.contains(",")) {
                    return srcDataLength.trim().split(",")[0];
                }
                if (srcDataLength.contains("，")) {
                    return srcDataLength.trim().split("，")[0];
                }
                return srcDataLength;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    public String getLengthByDataType() {
        final String[] split = srcDataType.split("\\(");
        final String[] split1 = split[1].split("\\)");
        return split1[0];
    }

}

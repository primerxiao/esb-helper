package com.primer.entity;

import lombok.Data;
import lombok.ToString;

/**
 * 索引sheet封装对象
 * @author HIFeng
 */
@Data
@ToString
public class EsbExcelIndexData {
    /**接口ID**/
    private String id;
    /**交易代码**/
    private String dealCode;
    /**交易名称**/
    private String dealName;
    /**服务名称**/
    private String serviceName;
    /**场景名称**/
    private String stageName;
    /**服务消费者**/
    private String serviceConsumer;
    /**服务提供者**/
    private String serviceProvider;
    /**所属产品**/
    private String product;
    /**修订人**/
    private String reviser;
    /**修订日期**/
    private String revisionDate;
    /**报文格式**/
    private String messageFormat;
    /**备注**/
    private String remark;

    public String getServiceCode(){
        return serviceName.split("\\(")[0];
    }
    public String getStageCode(){
        return stageName.split("\\(")[0];
    }

}

<#list esbReqDataList as esbReqData>
package com.irdstudio.efp.esb.api.bean.自己填呀.自己填吧;

import com.alibaba.fastjson.annotation.JSONField;
import com.irdstudio.basic.framework.core.annotation.DicType;
import com.irdstudio.basic.framework.core.annotation.NumType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


/**
* 需求编号：【】
* 问题编号：【】
* 开发人员：
* 创建日期：${.now?date} ${.now?time}
* 功能描述：个人借据信息查询请求参数封装
*/
public class ReqPersonalLoanInfo {

<#list esbReqData.esbExcelDataList as esbExcelData>
    <#if esbExcelData.srcCnName=='数组_开始'>
    "备注：${esbExcelData.esbEnName!''}": "${esbExcelData.esbCnName!''}"<#if esbExcelData_has_next>,</#if>
    "${esbExcelData.esbEnName!''}": [{
    </#if>
    <#if (esbExcelData.srcCnName!='数组_开始'&&esbExcelData.srcCnName!='数组_结束')>
    "备注：${esbExcelData.esbEnName!''}": "${esbExcelData.srcCnName!''} | ${esbExcelData.esbDataType!''} | ${esbExcelData.srcDataLength!''} | 必输：${esbExcelData.srcIsMustNeed!''}",
    "${esbExcelData.esbEnName!''}": ""<#if esbExcelData_has_next>,</#if>
    </#if>
    <#if esbExcelData.srcCnName=='数组_结束'>
     }]<#if esbExcelData_has_next>,</#if>
    </#if>
</#list>
    <#list esbReqData.esbExcelDataList as esbExcelData>
        <#if esbExcelData.srcCnName=='数组_开始'>
            "备注：${esbExcelData.esbEnName!''}": "${esbExcelData.esbCnName!''}"<#if esbExcelData_has_next>,</#if>
            "${esbExcelData.esbEnName!''}": [{
        </#if>
        <#if (esbExcelData.srcCnName!='数组_开始'&&esbExcelData.srcCnName!='数组_结束')>
            "备注：${esbExcelData.esbEnName!''}": "${esbExcelData.srcCnName!''} | ${esbExcelData.esbDataType!''} | ${esbExcelData.srcDataLength!''} | 必输：${esbExcelData.srcIsMustNeed!''}",
            "${esbExcelData.esbEnName!''}": ""<#if esbExcelData_has_next>,</#if>
        </#if>
        <#if esbExcelData.srcCnName=='数组_结束'>
            }]<#if esbExcelData_has_next>,</#if>
        </#if>
    </#list>
</#list>
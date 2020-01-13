
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
* 功能描述：${esbExcelIndexData.dealName!''}请求参数封装
*/
public class Req${esbExcelIndexData.dealCode?cap_first}Info <#noparse>{</#noparse>

<#list fieldList as fList>
    /**
    * ${fList.srcCnName}
    */
    @JSONField(name = "${fList.esbEnName}")
    <#if fList.esbIsMustNeed??><#if (fList.esbIsMustNeed == 'Y'||fList.esbIsMustNeed == 'y') >
    @NotNull(groups = {GroupeInterface.psd.class})
    @NotEmpty(groups = {GroupeInterface.psd.class})
    </#if></#if>
    @Length(max = ${fList.lenght}, groups = {GroupeInterface.psd.class})
    private String ${fList.srcEnName?uncap_first};
</#list>
<#if arrayExcelData??>
    /**
    * ${arrayExcelData.srcCnName}
    */
    @JSONField(name = "${arrayExcelData.esbCnName}")
    private List<${arrayExcelData.srcEnName?cap_first}Info> ${arrayExcelData.srcEnName?uncap_first};
</#if>
<#noparse>}</#noparse>


<#if arrayExcelData??>
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
* 功能描述：${esbExcelIndexData.dealName!''}请求数组参数封装
*/
public class ${arrayExcelData.srcEnName?cap_first}Info <#noparse>{</#noparse>

<#list arrayFieldList as arrayList>
    /**
    * ${arrayList.srcCnName}
    */
    @JSONField(name = "${arrayList.esbEnName}")
    <#if arrayList.esbIsMustNeed??><#if (arrayList.esbIsMustNeed == 'Y'||arrayList.esbIsMustNeed == 'y') >
    @NotNull(groups = {GroupeInterface.psd.class})
    @NotEmpty(groups = {GroupeInterface.psd.class})
    </#if></#if>
    @Length(max = ${arrayList.lenght}, groups = {GroupeInterface.psd.class})
    private String ${arrayList.srcEnName?uncap_first};
</#list>

<#noparse>}</#noparse>
</#if>
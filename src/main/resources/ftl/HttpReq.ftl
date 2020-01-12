<#list esbReqDataList as esbReqData>
###
### 接口ID：${esbReqData.esbExcelIndexData.id}
### 交易代码：${esbReqData.esbExcelIndexData.dealCode}
### 交易名称：${esbReqData.esbExcelIndexData.dealName}
### 服务名称：${esbReqData.esbExcelIndexData.serviceName}
### 场景名称：${esbReqData.esbExcelIndexData.stageName}
### 服务消费者：${esbReqData.esbExcelIndexData.serviceConsumer}
### 服务提供者：${esbReqData.esbExcelIndexData.serviceProvider}
### 所属产品：${esbReqData.esbExcelIndexData.product}

POST https://httpbin.org/post
Content-Type: application/json

{
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
}

</#list>
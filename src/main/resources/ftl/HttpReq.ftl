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

POST http://localhost:8080/esb/esbBusiness/doRecv
Accept: application/json
Content-Type: application/json

{
"SysHead": {
"SvcCd": "${esbReqData.esbExcelIndexData.serviceCode}",
"ScnCd": "${esbReqData.esbExcelIndexData.stageCode}",
"CnsmrSysID": "1001000",
"CnsmrSrlNo": "f7b8566726d9448b9c2f9c84f7ee94eb",
"GlblSrlNo": "10010012019121700000000009",
"RelGlblSrlNo": "",
"TxnDt": "20210702",
"TxnTm": "023711211",
"OrgnlCnsmrSysID": "1001000",
"TxnMd": "ONLINE",
"OrgnlTmlIdNo": "IOP",
"TmlIdNo": "IOP",
"OrgnlCnsmrSvcNo": "10.139.2.120",
"CnsmrSvcNo": "10.139.2.120",
"UsrLng": "CHINESE",
"FileFlg": "0",
"MACVal": "",
"MACFctr": "",
"EryptMd": "",
"PINSd": "",
"SvcVerNo": "",
"VerfFlg": "",
"MsgTp": "",
"PltfmClntNo": "",
"PlafmUsrID": "525",
"PlafmID": "R00001",
"PrdctID": ""
},
"AppHead": {
"InstId": "0316",
"UsrNo": "90087",
"UsrPswd": "",
"UsrLvl": "",
"UsrTp": "",
"TlrSrlNo": "",
"CnlTp": "",
"CnlDtlTp": "",
"CnsmrTxnCd": "${esbReqData.esbExcelIndexData.dealCode}"
},
"BODY":{
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
}
</#list>
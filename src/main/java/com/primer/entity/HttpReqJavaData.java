package com.primer.entity;

import lombok.Data;

import java.util.List;

/**
 * @author HIFeng
 */
@Data
public class HttpReqJavaData {
    private EsbExcelIndexData esbExcelIndexData;
    private EsbExcelData arrayExcelData;
    private List<EsbExcelData> fieldList;
    private List<EsbExcelData> arrayFieldList;

}

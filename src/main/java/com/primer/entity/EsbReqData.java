package com.primer.entity;

import lombok.Data;

import java.util.List;

@Data
public class EsbReqData {
    private EsbExcelIndexData esbExcelIndexData;
    private List<EsbExcelData> esbExcelDataList;
}

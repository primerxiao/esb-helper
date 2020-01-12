package com.primer.controller;

import com.alibaba.excel.EasyExcel;
import com.primer.MainApplication;
import com.primer.common.config.FreemarkerConfiguration;
import com.primer.entity.*;
import de.felixroske.jfxsupport.FXMLController;
import freemarker.template.Template;
import io.micrometer.core.instrument.util.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@FXMLController
public class MainController implements Initializable {

    @FXML
    public AnchorPane ctt;
    @FXML
    public TextArea console;
    @FXML
    public Button generateRequestJson;
    @FXML
    public Button loadEsbExcel;
    @FXML
    public ListView indexListView;

    public static File file;
    @FXML
    public TextField fileName;

    private List<EsbReqData> httpReqDataList = new ArrayList<>();

    public static FreemarkerConfiguration freemarker = new FreemarkerConfiguration("/ftl");
    ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        indexListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        indexListView.setCellFactory(e -> new TitleCell());

    }

    @FXML
    public void loadEsbExcel(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        if (MainController.file != null) {
            fileChooser.setInitialDirectory(MainController.file.getParentFile());
        }
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xlsx", "*.xlsx"));
        File file = fileChooser.showOpenDialog(MainApplication.getStage());
        if (file == null) {
            return;
        }
        if (!file.isFile()) {
            return;
        }

        fileName.setText(file.getPath());

        MainController.file = file;
        final EsbExcelIndexDataListener esbExcelIndexDataListener = new EsbExcelIndexDataListener();
        //获取索引这个sheet8
        EasyExcel.read(file, EsbExcelIndexData.class, esbExcelIndexDataListener).sheet("索引").doRead();
        final List<EsbExcelIndexData> list = esbExcelIndexDataListener.getList();
        if (list.size() <= 0) {
            return;
        }
        for (EsbExcelIndexData esbExcelIndexData : list) {
            System.out.println(esbExcelIndexData.toString());
        }
        ObservableList<EsbExcelIndexData> esbExcelIndexData = FXCollections.observableList(list);
        indexListView.setItems(esbExcelIndexData);
    }

    @FXML
    public void generateRequestJson(ActionEvent actionEvent) throws Exception {
        console.clear();
        httpReqDataList.clear();
        ObservableList<EsbExcelIndexData> selectedItems = indexListView.getSelectionModel().getSelectedItems();
        for (EsbExcelIndexData selectedItem : selectedItems) {
            //读取sheet的数据
            final EsbExcelDataListener esbExcelDataListener = new EsbExcelDataListener();
            //获取索引这个sheet
            EasyExcel.read(file, EsbExcelData.class, esbExcelDataListener).sheet(selectedItem.getId().trim()).headRowNumber(0).doRead();
            final List<EsbExcelData> list = esbExcelDataListener.getList();
            //获取输入的索引
            if (list == null || list.isEmpty()) {
                continue;
            }
            int reqIndex = 0;
            int respIndex = 0;
            for (int i = 0; i < list.size(); i++) {
                EsbExcelData esbExcelData = list.get(i);
                if (StringUtils.isNotEmpty(esbExcelData.getSrcEnName())) {
                    if ("输入".equals(esbExcelData.getSrcEnName().trim())) {
                        System.out.println("输入的索引为：" + i);
                        reqIndex = i;
                    }
                    if ("输出".equals(esbExcelData.getSrcEnName().trim())) {
                        System.out.println("输出的索引为：" + i);
                        respIndex = i;
                    }
                }
            }
            List<EsbExcelData> reqArr = list.stream().filter(e -> "数组".equals(e.getSrcCnName()) && "Array".equals(e.getEsbDataType())).collect(Collectors.toList());
            if (reqArr != null && !reqArr.isEmpty()) {
                reqArr.get(0).setSrcCnName("数组_开始");
                reqArr.get(1).setSrcCnName("数组_结束");
            }
            List<EsbExcelData> reqEsbExcelDataList = list.subList(reqIndex + 1, respIndex);
            EsbReqData esbReqData = new EsbReqData();
            esbReqData.setEsbExcelDataList(reqEsbExcelDataList);
            esbReqData.setEsbExcelIndexData(selectedItem);
            httpReqDataList.add(esbReqData);
        }
        if (!httpReqDataList.isEmpty()) {
            HttpReqData httpReqData = new HttpReqData(httpReqDataList);
            StringWriter stringWriter = new StringWriter();
            Template template = freemarker.getTemplate("HttpReq.ftl");
            template.process(httpReqData, stringWriter);
            console.appendText(stringWriter.toString().replaceAll(",\n" +
                    "     }", "\n" +
                    "     }"));
        }
    }

    @FXML
    public void generateRequestJavaBean(ActionEvent actionEvent) throws Exception {
        console.clear();
        ObservableList<EsbExcelIndexData> selectedItems = indexListView.getSelectionModel().getSelectedItems();
        if (selectedItems == null || selectedItems.isEmpty() || selectedItems.size() != 1) {
            return;
        }
        //读取sheet的数据
        final EsbExcelDataListener esbExcelDataListener = new EsbExcelDataListener();
        //获取索引这个sheet
        EasyExcel.read(file, EsbExcelData.class, esbExcelDataListener).sheet(selectedItems.get(0).getId().trim()).headRowNumber(0).doRead();
        final List<EsbExcelData> list = esbExcelDataListener.getList();
        //获取输入的索引
        if (list == null || list.isEmpty()) {
            return;
        }
        int reqIndex = 0;
        int respIndex = 0;
        int arrayStartIndex = -1;
        int arrayEndIndex = -1;
        EsbExcelData arrayExcelData = null;
        for (int i = 0; i < list.size(); i++) {
            EsbExcelData esbExcelData = list.get(i);
            if (StringUtils.isNotEmpty(esbExcelData.getSrcEnName())) {
                if ("输入".equals(esbExcelData.getSrcEnName().trim())) {
                    System.out.println("输入的索引为：" + i);
                    reqIndex = i;
                }
                if ("数组".equals(esbExcelData.getSrcCnName()) && "Array".equals(esbExcelData.getEsbDataType())) {
                    if (!Objects.isNull(arrayExcelData)) {
                        arrayEndIndex = i;
                    } else {
                        arrayStartIndex = i;
                        arrayExcelData = esbExcelData;
                    }
                }
                if ("输出".equals(esbExcelData.getSrcEnName().trim())) {
                    System.out.println("输出的索引为：" + i);
                    respIndex = i;
                }
            }
        }
        //截取输出-输出的部分
        List<EsbExcelData> reqEsbExcelDataList = list.subList(reqIndex + 1, respIndex);
        //从输入-输出部分获取字段（除了array）
        List<EsbExcelData> fieldList = ((arrayStartIndex>reqIndex)?reqEsbExcelDataList.subList(reqIndex + 1, arrayStartIndex):reqEsbExcelDataList);
        if (arrayEndIndex < respIndex - 1 && arrayEndIndex > arrayStartIndex) {
            fieldList.addAll(reqEsbExcelDataList.subList(arrayEndIndex + 1, respIndex));
        }
        //获取array的字段
        List<EsbExcelData> arrayFieldList = null;
        if (!Objects.isNull(arrayExcelData)) {
            arrayFieldList = list.subList(arrayStartIndex + 1, arrayEndIndex);
        }
        HttpReqJavaData httpReqJavaData = new HttpReqJavaData();
        httpReqJavaData.setEsbExcelIndexData(selectedItems.get(0));
        httpReqJavaData.setArrayExcelData(arrayExcelData);
        httpReqJavaData.setArrayFieldList(arrayFieldList);
        httpReqJavaData.setFieldList(fieldList);
        StringWriter stringWriter = new StringWriter();
        Template template = freemarker.getTemplate("HttpReqJava.ftl");
        template.process(httpReqJavaData, stringWriter);
        console.appendText(stringWriter.toString());
    }

    @FXML
    public void generateRespJavaBean(ActionEvent actionEvent) {

    }

    private class TitleCell extends ListCell<EsbExcelIndexData> {

        @Override
        public void updateItem(EsbExcelIndexData item, boolean empty) {
            super.updateItem(item, empty);

            if (!empty && item != null) {
                BorderPane cell = new BorderPane();

                Text title = new Text(item.getDealName() + "  " + (StringUtils.isNotEmpty(item.getProduct()) ? item.getProduct() : ""));
                title.setFont(Font.font(14));

                Text date = new Text(item.getRevisionDate());
                date.setFont(Font.font(10));

                Text source = new Text(item.getId());
                source.setFont(Font.font(10));

                cell.setTop(title);
                cell.setLeft(date);
                cell.setRight(source);

                setGraphic(cell);
            } else if (empty) {
                setText(null);
                setGraphic(null);
            }
        }
    }
}


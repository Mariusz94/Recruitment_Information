package pl.lyszczarzmariusz.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import pl.lyszczarzmariusz.model.RecruitmentModel;
import pl.lyszczarzmariusz.model.service.ListCompanyService;

import java.io.IOException;
import java.time.LocalDate;


public class HomeController {
    @FXML
    private TableView<RecruitmentModel> table;

    @FXML
    private TableColumn<RecruitmentModel,String> nameCompanyTableColumn, workPlaceTableColumn, offerURLTableColumn;

    @FXML
    private TableColumn<RecruitmentModel,LocalDate> applicationDateTableColumn;

    @FXML
    private TextField nameCompanyField, workPlaceField, offerURLField;

    @FXML
    private Button addButton;

    public HomeController() {
    }

    @FXML
    void initialize() {
        nameCompanyTableColumn.setCellValueFactory(
                new PropertyValueFactory<RecruitmentModel, String>("nameCompany")
        );
        workPlaceTableColumn.setCellValueFactory(
                new PropertyValueFactory<RecruitmentModel, String>("workPlace")
        );
        applicationDateTableColumn.setCellValueFactory(
                new PropertyValueFactory<RecruitmentModel, LocalDate>("applicationDate")
        );
        offerURLTableColumn.setCellValueFactory(
                new PropertyValueFactory<RecruitmentModel, String>("offerURL")
        );
        nameCompanyTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        workPlaceTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        offerURLTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        try {
            ListCompanyService.readListFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        printDataInTable();
    }

    @FXML
    public void addButtonClicked(){
        if (!nameCompanyField.getText().equals("") &&
                !offerURLField.getText().equals("") &&
                !workPlaceField.getText().equals("")) {

            ListCompanyService.saveRecord(new RecruitmentModel(nameCompanyField.getText(),
                    offerURLField.getText(),
                    workPlaceField.getText()));

            nameCompanyField.clear();
            offerURLField.clear();
            workPlaceField.clear();

            printDataInTable();
        }
    }

    private void printDataInTable() {
        ObservableList<RecruitmentModel> data = FXCollections.observableArrayList();

            data.addAll(ListCompanyService.getList());


        table.setItems(data);
    }

    @FXML
    public void offerURLTableColumnOnEditCommit(TableColumn.CellEditEvent<RecruitmentModel,String> recruitmentModelStringCellEditEvent){
        RecruitmentModel recruitmentModel = table.getSelectionModel().getSelectedItem();
        recruitmentModel.setOfferURL(recruitmentModelStringCellEditEvent.getNewValue());
    }
    @FXML
    public void nameCompanyTableColumnOnEditCommit(TableColumn.CellEditEvent<RecruitmentModel, String> recruitmentModelStringCellEditEvent) {
        RecruitmentModel recruitmentModel = table.getSelectionModel().getSelectedItem();
        recruitmentModel.setOfferURL(recruitmentModelStringCellEditEvent.getNewValue());
    }
    @FXML
    public void workPlaceTableColumnOnEditCommit(TableColumn.CellEditEvent<RecruitmentModel, String> recruitmentModelStringCellEditEvent) {
        RecruitmentModel recruitmentModel = table.getSelectionModel().getSelectedItem();
        recruitmentModel.setOfferURL(recruitmentModelStringCellEditEvent.getNewValue());
    }
    @FXML
    public void deleteMenuItem(ActionEvent actionEvent) {
        RecruitmentModel recruitmentModel = table.getSelectionModel().getSelectedItem();
        ListCompanyService.getList().remove(recruitmentModel);
        printDataInTable();
    }
}

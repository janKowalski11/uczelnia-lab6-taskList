package controllers;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Task;

public class MainController {

    private  ObservableList<Task> listItems = FXCollections.observableArrayList();
    @FXML
    private ListView<Task> todolist;
    @FXML
    private void handleButtonAction(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/add_task_view.fxml"));
            Scene scene = new Scene(loader.load(), 474, 356);
            Stage stage = new Stage();
            stage.setTitle("Add new task");
            stage.setScene(scene);
            AddTaskController taskController = loader.getController();
            taskController.setDialogStage(stage);
            taskController.setData(listItems);
            todolist.setItems(listItems);

            stage.show();


        }
        catch (Exception e){
            System.out.println("Nie da rady otworzyc okna");
        }

    }

    @FXML
    private void handleCloseAction(){
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void handleAboutAction(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About the author");
        alert.setHeaderText(null);
        alert.setContentText("Created by Michał Słomski \n Version 1.0");

        alert.showAndWait();
    }



}

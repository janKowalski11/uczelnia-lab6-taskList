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

    private  ObservableList<Task> taskObservableList = FXCollections.observableArrayList();
    @FXML
    private ListView<Task> taskListView;
    @FXML
    private void onAddNewTaskButtonClick(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/add_task_view.fxml"));
            Scene scene = new Scene(loader.load(), 474, 356);
            Stage stage = new Stage();
            stage.setTitle("Add new task");
            stage.setScene(scene);

            TaskController taskController = loader.getController();
            taskController.setDialogStage(stage);
            taskController.setObservableLists(taskObservableList);
            taskListView.setItems(taskObservableList);

            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Nie mozna  otworzyc okna");
        }

    }

    @FXML
    private void onCloseItemClick(){
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void onAboutItemClick(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About the author");
        alert.setHeaderText(null);
        alert.setContentText("Created by Marcin Pływacz \n Version 0.1");

        alert.showAndWait();
    }



}

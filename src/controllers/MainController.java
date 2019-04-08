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

import java.io.IOException;

public class MainController {


    private Scene addTaskScene;
    private Stage taskStage;
    private ObservableList<Task> taskTodoObservableList = FXCollections.observableArrayList();

    @FXML
    private ListView<Task> taskTodoListView;

    public MainController() {

        taskTodoListView = new ListView<>();


    }

    @FXML
    private void onAddNewTaskButtonClick() {
        try {

            //todo Extract possible variables
            //why this declaration of loader must be here? when loader set as final variable then throws exceptions XD
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/add_task_view.fxml"));

            addTaskScene = new Scene(loader.load(), 474, 356);
            taskStage = new Stage();
            taskStage.setTitle("Add new task");
            taskStage.setScene(addTaskScene);

            TaskController taskController = loader.getController();
            taskController.setDialogStage(taskStage);

            //inject lists
            taskController.setObservableLists(taskTodoObservableList);
            taskTodoListView.setItems(taskTodoObservableList);
            taskController.setTaskTodoListView(taskTodoListView);


            taskStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nie mozna  otworzyc okna");
        }

    }


    @FXML
    private void onCloseItemClick() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void onAboutItemClick() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About the author");
        alert.setHeaderText(null);
        alert.setContentText("Created by Marcin Pływacz \n Version 0.1");

        alert.showAndWait();
    }


}

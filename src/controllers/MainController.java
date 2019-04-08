package controllers;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import model.Task;

import java.io.IOException;

public class MainController {

    private ObservableList<Task> selectedTasks = FXCollections.observableArrayList();
    private Scene addTaskScene;
    private Stage taskStage;

    private ObservableList<Task> taskTodoObservableList = FXCollections.observableArrayList();
    private ObservableList<Task> taskInprocessObservableList = FXCollections.observableArrayList();
    private ObservableList<Task> taskDoneObservableList = FXCollections.observableArrayList();

    @FXML
    private ListView<Task> taskTodoListView;

    @FXML
    private ListView<Task> inprogressListView;

    @FXML
    private ListView<Task> doneListView;


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

            inprogressListView.setItems(taskInprocessObservableList);
            doneListView.setItems(taskDoneObservableList);

            TaskController taskController = loader.getController();
            taskController.setDialogStage(taskStage);

            //inject lists
            taskController.setObservableLists(taskTodoObservableList);
            taskTodoListView.setItems(taskTodoObservableList);
            taskController.setTaskTodoListView(taskTodoListView);


            //todo : put this code somewhere ? where can i put that
            taskTodoListView.setCellFactory(lv -> new ListCell<Task>() {
                @Override
                protected void updateItem(Task item, boolean empty) {
                    if (item == null)
                        return;

                    super.updateItem(item, empty);
                    setText(empty ? null : item.getTitle());
                    setTextFill(item.getTaskColor()); //adds coloring
                    setTooltip(new Tooltip(item.getDescription())); //adds tooltip
                }
            });


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

    @FXML
    private void onDragDetected(Event event) {
        //copy selected Items
        ObservableList<Task> selectedItemsTmp = taskTodoListView.getSelectionModel().getSelectedItems();
        selectedItemsTmp.forEach(task -> {
            //clone
            Task tmpTask = new Task();
            tmpTask.setTitle(task.getTitle());
            tmpTask.setDescription(task.getDescription());
            tmpTask.setExpDate(task.getExpDate());
            tmpTask.setPriority(task.getPriority());

            selectedTasks.add(tmpTask);
        });

        selectedItemsTmp.forEach(task-> taskTodoListView.getItems().remove(task));


    }




    @FXML
    private void onDragDroppedInprocessList(Event event) {
        selectedTasks.stream().forEach(task -> {
            inprogressListView.getItems().add(task);
        });
        selectedTasks = FXCollections.observableArrayList();

    }

}

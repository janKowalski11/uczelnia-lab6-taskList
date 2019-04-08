package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Priority;
import model.Task;


public class TaskController {

    private Stage dialogStage;
    private ObservableList<Task> taskTodoObservableList;
    private ListView<Task> taskTodoListView;

    private ObservableList<Priority> priorityValuesList;

    @FXML
    private ComboBox priorityComboBox;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextField titleTextField;

    @FXML
    private DatePicker expDateField;

    private Task task = new Task();

    public TaskController() {
        taskTodoObservableList = FXCollections.observableArrayList();
        priorityValuesList = FXCollections.observableArrayList(Priority.values());

    }

    @FXML
    public void initialize() {

        priorityComboBox.setValue(Priority.LOW);
        priorityComboBox.setItems(priorityValuesList);
    }

    @FXML
    public void onAddTaskButtonClick() {

        task.setTitle(titleTextField.getText());
        task.setDescription(descriptionTextArea.getText());
        task.setExpDate(expDateField.getValue());

        Priority priority = (Priority) priorityComboBox.getValue();
        task.setPriority(priority);

        Tooltip tooltip=new Tooltip(descriptionTextArea.getText());

        taskTodoListView.getItems().add(task);

        dialogStage.close();

        //change cells color
    }

    void setObservableLists(ObservableList<Task> data) {
        this.taskTodoObservableList = data;
    }

    void setTaskTodoListView(ListView<Task> taskTodoListView) {
        this.taskTodoListView = taskTodoListView;
    }

    void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}

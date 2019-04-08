package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Priority;
import model.Task;


public class AddTaskController {

    private Stage dialogStage;
    private  ObservableList<Task> taskFeaturesList = FXCollections.observableArrayList();

    public void setData(ObservableList<Task> data) {
        this.taskFeaturesList= data ;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private ObservableList<Priority> priorityComboBoxList = FXCollections.observableArrayList(Priority.values());

    @FXML
    private ComboBox priorityComboBox;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextField titleTextField;

    @FXML
    private DatePicker expDateField;

    private Task task = new Task();

    @FXML
    public  void initialize(){

        priorityComboBox.setValue(Priority.LOW);
        priorityComboBox.setItems(priorityComboBoxList);
    }

    @FXML
    public void handleAddAction(){

        task.setTitle(titleTextField.getText());
        task.setDescription(descriptionTextArea.getText());
        task.setExpDate(expDateField.getValue());
        //task.setPriority(priorityComboBox.);
        taskFeaturesList.add(task);

        dialogStage.close();


    }






    

}

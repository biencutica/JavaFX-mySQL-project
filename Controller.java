package gui;

import domain.Meal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.Service;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Service service;

    @FXML
    private TextField textField;

    @FXML
    private TextField scoreField;

    @FXML
    private ListView<Meal> questionList;

    @FXML
    private TextField addIngredients;

    @FXML
    private TextField addTime;

    @FXML
    private TextField addName;



    public Controller(Service service) {
        this.service = service;
    }

    public void initialize() {
        populateQuestions();
    }

    void populateQuestions() {
        List<Meal> questions = new ArrayList<>(service.getAll());
        ObservableList<Meal> orderedList = FXCollections.observableArrayList(questions);
        questionList.setItems(orderedList);
    }

    @FXML
    void searchItem(ActionEvent event){
        String searchtext = textField.getText();

        int searchScore = 0;
        if(!scoreField.getText().isEmpty()) searchScore = Integer.parseInt(scoreField.getText());

        if(searchtext.isEmpty()) populateQuestions();
        else{
            List<Meal> questions = service.filterBy(searchtext, searchScore);
            ObservableList<Meal> found = FXCollections.observableArrayList(questions);
            questionList.setItems(found);
        }
    }

    @FXML
    void addMeal(ActionEvent event){
        String add_name = addName.getText();
        int add_time = 0;
        if(!addTime.getText().isEmpty()) add_time = Integer.parseInt(addTime.getText());
        String add_ingredients = addIngredients.getText();

        Meal m = new Meal(add_name,add_time,add_ingredients);
        service.addItem(m);
        questionList.getItems().add(m);
    }



}


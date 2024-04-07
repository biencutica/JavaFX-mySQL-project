package service;

import domain.Meal;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    //filter
    public List<Meal> filterBy(String ingredient, int time){
        List<Meal> questions = getAll();
        return questions.stream().filter(x -> x.getIngredients().contains(ingredient) && x.getCooking_time() < time).collect(Collectors.toList());
    }

    public List<Meal> getAll() {
        /*List<Question> sortedq = repo.getAll().stream().sorted((x,y)->{
            if(x.getScore() < y.getScore())
                return -1;
            if(x.getScore() > y.getScore())
                return 1;
            return 0;
        }).toList();*/
        return repo.getAll().stream().sorted(Comparator.comparing(Meal::getName)).collect(Collectors.toList());
    }

    public void addItem(Meal m){
        repo.addItem(m);}

}


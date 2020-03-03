package com.example.afternooncontacts;

import android.util.Log;

import java.util.ArrayList;

public class ChoiceModel {

    public static class Choice {
        public String name;
      //  public Integer age;

        public Choice(String name) {
            if (name == null)
                this.name = "Default";
            else
                this.name = name;

        }
    }

    // SingleTon
    private static ChoiceModel singleton= null;
    //
    public static ChoiceModel getSingleton() {
        if (singleton == null)
            singleton = new ChoiceModel();
        return singleton;
    }

    public ArrayList<Choice> choiceList ;

    private ChoiceModel() {
        choiceList = new ArrayList<Choice>();
        loadChoices();
    }
    private void loadChoices() {


        choiceList.add(new Choice("Go to the beach"));
        choiceList.add(new Choice("Read a Book"));
        choiceList.add(new Choice("Eat a snack"));


    }
    public void reset(){

        choiceList.clear();
        int size=choiceList.size();
        String si=String.valueOf(size);
        Log.d("size",si);
        loadChoices();

    }


}

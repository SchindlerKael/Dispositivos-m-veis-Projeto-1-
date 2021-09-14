package com.example.cafecompao.validator;


public final class CheckBoxValidator {
    public static Integer qtdChecked;
    private static Integer limitChecked;

    private static CheckBoxValidator instance;

    private CheckBoxValidator(Integer limitChecked) {
        this.qtdChecked = 0;
        this.limitChecked = limitChecked;
    }

    private CheckBoxValidator() {
        this.qtdChecked = 0;
        this.limitChecked = 3;
    }

    public static CheckBoxValidator getInstance() {
        if(instance == null)
            instance = new CheckBoxValidator();
        return instance;
    }

    public static CheckBoxValidator getInstance(Integer limit) {
        if(instance == null)
            instance = new CheckBoxValidator(limit);
        return instance;
    }

    public static Boolean isFull() {
        if(qtdChecked >= limitChecked)
            return true;
        return false;
    }

    public static Boolean validate(Boolean checked) {
        if(checked){
            if(isFull()){
                return false;
            }
            qtdChecked++;
            return true;
        } else{
            qtdChecked--;
            return false;
        }
    }

}
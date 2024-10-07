package pl.edu.pjatk.MPR_spring.model;

public class Capybara {
    private String name;
    private String color;

    public Capybara(String name, String color) {
        this.name = name;
        this.color = color;
    }

    //bierze dane
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }



    //daje danne
    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }


}

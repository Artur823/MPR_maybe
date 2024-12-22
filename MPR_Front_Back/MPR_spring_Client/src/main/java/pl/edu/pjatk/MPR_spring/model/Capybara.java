package pl.edu.pjatk.MPR_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Capybara {
    private Long id;
    private String name;
    private String color;

    @JsonIgnore
    private long identification;


    public Capybara() {}

    public Capybara(String name, String color, Long id) {
        this.name = name;
        this.color = color;
        this.id = id;
        generateHashCode();
    }

    public void generateHashCode() {
        if (name != null && color != null) {
            this.identification = name.hashCode() + color.hashCode();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        generateHashCode();
    }

    public void setColor(String color) {
        this.color = color;
        generateHashCode();
    }

}

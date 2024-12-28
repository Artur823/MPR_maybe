package pl.edu.pjatk.MPR_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Capybara {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@JsonIgnore
    private Long id;
    private String name;
    private String color;

    @JsonIgnore
    private long identification;


    public Capybara() {}

    public Capybara(String name, String color) {
        this.name = name;
        this.color = color;
        generateHashCode();
    }

    private void generateHashCode() {
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

    public long getIdentification() {
        return identification;
    }

}

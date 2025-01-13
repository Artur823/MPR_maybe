package pl.edu.pjatk.MPR_spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
@Table(name = "capybara")
public class Capybara {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "capybara_seq")
    @SequenceGenerator(name = "capybara_seq", sequenceName = "capybara_seq", allocationSize = 1)
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
    public Capybara(String name) {
        this.name = name;
    }

    private void generateHashCode() {
        if (name != null && color != null) {
            this.identification = name.hashCode() + color.hashCode();
        }
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Capybara{name='" + name + "', color='" + color + "'}";
    }

}

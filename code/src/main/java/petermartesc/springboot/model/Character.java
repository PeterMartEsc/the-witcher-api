package petermartesc.springboot.model;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name = "characters")
public class Character {
    @Id
    private int id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "description", nullable = false)
    private String description;

    public Character() {
    }

    public Character(int id, String name, String surname, String description) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.description = description;
    }


    //@GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname", nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return id == character.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Character {" +
                "id=" + id + ", " + '\''  +
                "name='" + name + ", " + '\'' +
                "surname='" + surname + ", " + '\''+
                "description='" + description + '\'' +
                '}';
    }
}

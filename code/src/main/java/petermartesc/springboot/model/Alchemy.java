package petermartesc.springboot.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name= "alchemys")
public class Alchemy {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "material", nullable = false)
    private String material;

    public Alchemy() {
    }

    public Alchemy(int id, String name, String material) {
        this.id = id;
        this.name = name;
        this.material = material;
    }

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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alchemy alchemy = (Alchemy) o;
        return id == alchemy.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Alchemy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}

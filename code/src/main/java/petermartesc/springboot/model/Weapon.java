package petermartesc.springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Weapons {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "rarity", nullable = false)
    private String rarity;


}

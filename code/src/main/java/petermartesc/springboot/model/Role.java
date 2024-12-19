package petermartesc.springboot.model;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "role", nullable = false)
    private String roleName;

    //El valor de mappedBy tiene que coincidir con el nombre del campo al que esté mapeando (role en clase User)
    //@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<User> usuarios;

    public Role() {
    }

    public Role(int id, String roleName/*, Set<User> usuarios*/) {
        this.id = id;
        this.roleName = roleName;
        //this.usuarios = new HashSet<>();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    /*public Set<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<User> usuarios) {
        this.usuarios = usuarios;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Role[" + '\'' +
                        "id=" + id + ", " + '\'' +
                        "role='" + roleName + '\'' +
                    ']';
    }
}

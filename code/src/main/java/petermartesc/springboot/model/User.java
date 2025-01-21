package petermartesc.springboot.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = true)
	private Role role;
	
	public User() {	
	}
	
	public User(String name, Role role) {
		this.name = name;
		this.role = role;
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

	public Role getIdrole() {
		return role;
	}
	public void setIdrole(Role idrole) {
		this.role = idrole;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id == user.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "User [" + '\'' +
						"id=" + id + ", " + '\'' +
						"name=" + name + '\'' +
						"role=" + role + '\'' +
				"]";
	}
	
}

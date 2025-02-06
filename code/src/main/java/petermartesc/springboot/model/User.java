package petermartesc.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name="password", nullable = false)
	private String password;

	@ManyToOne
	@JoinColumn(name = "role"/*, nullable = true*/)
	private Role role;
	
	public User() {	
	}

	public User(int id, String name, Role role) {
		this.id = id;
		this.name = name;
		this.role = role;
	}

	public User(int id, String name, String password,  Role role) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
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

	public Role getRole() {
		return role;
	}
	public void setRole(Role idrole) {
		this.role = idrole;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", role=" + role +
				'}';
	}
}

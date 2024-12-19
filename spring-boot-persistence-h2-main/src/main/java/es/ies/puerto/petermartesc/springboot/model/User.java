package es.ies.puerto.petermartesc.springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

	private int id;
	private String name;
	
	public User() {	
	}
	
	public User(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "User [" + '\'' +
						"id=" + id + ", " + '\'' +
						"name=" + name + '\'' +
					"]";
	}
	
}

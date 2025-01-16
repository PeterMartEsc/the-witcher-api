package petermartesc.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import jakarta.annotation.PostConstruct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
@ImportResource("classpath:cxf-service.xml")
public class Application {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	private void initDb() {
		System.out.println(String.format("****** Creating table: %s, and Inserting test data ******", "users"));

		// Cambiar "serial" por "auto_increment" para H2
		String sqlStatements[] = {
						//"DROP DATABASE the_witcher_api",
						//"CREATE DATABASE the_witcher_api;",
						//"USE the_witcher_api",
						"DROP TABLE IF EXISTS users;",
						"DROP TABLE IF EXISTS roles;",
						"DROP TABLE IF EXISTS characters;",
						"DROP TABLE IF EXISTS monsters;",
						"DROP TABLE IF EXISTS weapons;",
						"DROP TABLE IF EXISTS alchemys;",
						"DROP TABLE IF EXISTS locations;",


						"CREATE TABLE roles (id INT AUTO_INCREMENT, role VARCHAR(20), PRIMARY KEY (id));",
						"CREATE TABLE users (id INT AUTO_INCREMENT, name VARCHAR(50), role_id INT, PRIMARY KEY (id), " +
								"FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE SET NULL ); ",
						"CREATE TABLE characters ( id INT AUTO_INCREMENT,name VARCHAR(50),surname VARCHAR(100), " +
								"description VARCHAR(250), PRIMARY KEY (id) );" ,
						"CREATE TABLE monsters (id INT AUTO_INCREMENT, name VARCHAR(50), " +
								"difficulty VARCHAR(25), PRIMARY KEY (id));",
						"CREATE TABLE weapons (id INT AUTO_INCREMENT, name VARCHAR(30), " +
								"rarity VARCHAR(10), PRIMARY KEY (id));",
						"CREATE TABLE alchemys (id INT AUTO_INCREMENT, name VARCHAR(20), " +
								"material VARCHAR(20), PRIMARY KEY (id));",
						"CREATE TABLE locations (id INT AUTO_INCREMENT, name VARCHAR(20), " +
								"kingdom VARCHAR(20), PRIMARY KEY (id));",


						"INSERT INTO roles (id, role) VALUES (1, 'Admin'), (2, 'User');",
						"INSERT INTO users (id, name, role_id) VALUES (1, 'Manuel', 2), (2, 'Pedro', 1);",
						"INSERT INTO characters (id, name, surname, description) VALUES " +
						"(1, 'Geralt', 'of Rivia', 'A witcher known for his skills with a sword')," +
						"(2, 'Ciri', 'of Cintra', 'A princess with extraordinary powers');",
						"INSERT INTO monsters (id, name, difficulty) VALUES " +
								"(1, 'Drowned', 'easy'), (2, 'Kikimora', 'mid'), (3, 'Queen Kikimora', 'impossible');",
						"INSERT INTO weapons (id, name, rarity) VALUES " +
								"(1, 'Silver Sword', 'uncommon'), (2, 'Witchers Iron Sword', 'uncommon');",
						"INSERT INTO alchemys (id, name, material) VALUES (1, 'Alp Fangs', 'Eter'), (2, 'Cadaverine', 'Rebis');",
						"INSERT INTO locations (id, name, kingdom) VALUES (1, 'Wyzima', 'Temeria'), (2, 'Blaviken', 'Redania');",

						//"ALTER TABLE roles AUTO_INCREMENT = (SELECT MAX(id) + 1 FROM roles);",
		};

		Arrays.asList(sqlStatements).stream().forEach(sql -> {
			System.out.println(sql);
			jdbcTemplate.execute(sql);
		});

		System.out.println(String.format("****** Fetching from table: %s ******", "users"));
		jdbcTemplate.query("select id, name from users",
				new RowMapper<Object>() {
					@Override
					public Object mapRow(ResultSet rs, int i) throws SQLException {
						System.out.println(String.format("id:%s, name:%s",
								rs.getString("id"),
								rs.getString("name")));
						return null;
					}
				});
	}
}

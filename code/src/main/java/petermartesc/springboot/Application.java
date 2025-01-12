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


						"INSERT INTO roles (role) VALUES ('Admin'), ('User');",
						"INSERT INTO users (name, role_id) VALUES ('Manuel', 2), ('Pedro', 1);",
						"INSERT INTO characters (name, surname, description) VALUES " +
						"('Geralt', 'of Rivia', 'A witcher known for his skills with a sword')," +
						"('Ciri', 'of Cintra', 'A princess with extraordinary powers');",
						"INSERT INTO monsters (name, difficulty) VALUES " +
								"('Drowned', 'easy'), ('Kikimora', 'mid'), ('Queen Kikimora', 'impossible');",
						"INSERT INTO weapons (name, rarity) VALUES " +
								"('Silver Sword', 'uncommon'), ('Witchers Iron Sword', 'uncommon');",
						"INSERT INTO alchemys (name, material) VALUES ('Alp Fangs', 'Eter'), ('Cadaverine', 'Rebis');",
						"INSERT INTO locations (name, kingdom) VALUES ('Wyzima', 'Temeria'), ('Blaviken', 'Redania');",

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

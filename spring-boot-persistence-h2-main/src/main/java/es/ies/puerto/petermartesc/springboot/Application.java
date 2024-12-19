package es.ies.puerto.petermartesc.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import jakarta.annotation.PostConstruct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
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
				"drop table if exists users",
				"drop table if exists characters",
				"create table users(id int auto_increment, name varchar(50), primary key (id))",
				"create table characters(id int auto_increment, name varchar(50), " +
						"surname varchar(100), description varchar(250), primary key (id))",
				"insert into users(name) values ('Manuel'), ('Pedro')",
				"insert into characters(name, surname, description) values " +
						"('Geralt', 'of Rivia', 'A witcher known for his skills with a sword')," +
						"('Ciri', 'of Cintra', 'A princess with extraordinary powers')"

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

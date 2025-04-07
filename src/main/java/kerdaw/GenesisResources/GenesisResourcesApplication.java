package kerdaw.GenesisResources;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import kerdaw.GenesisResources.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.function.Consumer;

@SpringBootApplication
@ComponentScan(basePackages = "kerdaw.GenesisResources")
public class GenesisResourcesApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(GenesisResourcesApplication.class, args);
	}

//	@Configuration
//	@ComponentScan(basePackages = "kerdaw.GenesisResources")
//	public class DataSourceConfig {
//		@Bean
//		public DataSource getDataSource() {
//			return DataSourceBuilder.create()
//					.driverClassName("com.mysql.cj.jdbc.Driver")
//					.url("jdbc:mysql://localhost:3306/Genesis")
//					.username("root")
//					.password("1234")
//					.build();
//		}
//	}
}

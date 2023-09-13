package com.example.dbmigrator;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.util.ObjectUtils;

import javax.sql.DataSource;

public class EnvDataSourceProvider {

	public static DataSource getDataSource() {
		String url = System.getenv("DATASOURCE_URL");
		String username = System.getenv("DATASOURCE_USERNAME");
		String password = System.getenv("DATASOURCE_PASSWORD");
		if (ObjectUtils.isEmpty(url) || ObjectUtils.isEmpty(username) || ObjectUtils.isEmpty(password)) {
			throw new RuntimeException(
					"Environment variables with database connection configuration must be provided (DATASOURCE_URL, DATASOURCE_USERNAME, DATASOURCE_PASSWORD)");
		}

		return DataSourceBuilder.create()
				.url(url)
				.username(username)
				.password(password)
				.build();
	}
}

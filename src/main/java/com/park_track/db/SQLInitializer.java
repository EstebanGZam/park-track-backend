package com.park_track.db;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class SQLInitializer {

	private final JdbcTemplate jdbcTemplate;

	@PostConstruct
	@Transactional
	public void init() throws IOException {
		loadImportSql();
	}

	private void loadImportSql() throws IOException {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sql/data.sql")) {
			if (inputStream == null) {
				throw new IllegalArgumentException("Archivo 'sql/data.sql' no encontrado en el classpath");
			}
			// Leer el contenido del archivo como String
			String sql = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
			// Ejecutar el contenido del archivo SQL
			jdbcTemplate.execute(sql);
		}
	}

}
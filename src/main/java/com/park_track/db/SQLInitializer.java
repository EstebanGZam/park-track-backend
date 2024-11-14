package com.park_track.db;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
		String sql = new String(Files.readAllBytes(Paths.get("src/main/resources/sql/data.sql")));
		jdbcTemplate.execute(sql);
	}

}
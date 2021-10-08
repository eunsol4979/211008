package co.micol.prj.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DataSource {
	private static DataSource dataSource = new DataSource(); // 싱글톤 생성
	private Connection conn;
	private String driver;
	private String url;
	private String user;
	private String password;

	private DataSource() { // 외부에서 생성할 수 없도록 생성자 private으로 설정
		dbconfig();
	}

	public static DataSource getInstance() {
		return dataSource;
	}

	// ------------------------- 여기까지 싱글톤 생성

	private void dbconfig() { // 외부 properties파일을 읽어 값을 저장함
		Properties properties = new Properties();
		String resource = getClass().getResource("/db.properties").getPath(); // getFile, getPath중 아무거나 선택
		try {
			properties.load(new FileReader(resource));
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public Connection getConnection() {
		try {
			Class.forName(driver); // ojdbc load 연결
			conn = DriverManager.getConnection(url, user, password);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn; // 생성된 컨넥션 객체를 돌려준다
	}

}

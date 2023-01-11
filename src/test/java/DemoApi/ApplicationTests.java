package DemoApi;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@LocalServerPort
	private int port;

	@Before
	public void setUp() throws Exception {
		RestAssured.port = port;
	}

	@Test
	void contextLoads() {
	}

	@Test
	void fileUploadTest() {
		List<String[]> dataLines = new ArrayList<>();
		dataLines.add(new String[]
				{ "id","userId","name","amount","ip" });
		dataLines.add(new String[]
				{ "b031c031a8305092487716f","0d731898-af87-4590-971e-246fe7415c21","Gal","260","47.117.227.215" });

		File csvOutputFile = new File("src/test/resources/transactions-test-file.csv");
		try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
			dataLines.stream()
					.map(this::convertToCSV)
					.forEach(pw::println);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String resp = RestAssured.given().multiPart("file", csvOutputFile).when().post("http://localhost:"+port+"/fraud/transaction/transactions-test-file.csv").then().assertThat().statusCode(200).and().extract().body().asString();
		assertEquals(resp, "file was processed successfully");
	}

	private String convertToCSV(String[] data) {
		return Stream.of(data)
				.map(this::escapeSpecialCharacters)
				.collect(Collectors.joining(","));
	}

	private String escapeSpecialCharacters(String data) {
		String escapedData = data.replaceAll("\\R", " ");
		if (data.contains(",") || data.contains("\"") || data.contains("'")) {
			data = data.replace("\"", "\"\"");
			escapedData = "\"" + data + "\"";
		}
		return escapedData;
	}



}

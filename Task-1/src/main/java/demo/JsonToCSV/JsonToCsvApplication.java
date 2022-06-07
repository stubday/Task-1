package demo.JsonToCSV;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class JsonToCsvApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(JsonToCsvApplication.class, args);

		JsonNode jsonTree = new ObjectMapper().readTree(new File("src/main/resources/order.json"));

		CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
		JsonNode firstObject = jsonTree.elements().next();
		firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
		CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

		CsvMapper csvMapper = new CsvMapper();
		csvMapper.writerFor(JsonNode.class)
				.with(csvSchema)
				.writeValue(new File("src/main/resources/order.csv"), jsonTree);








	}

}

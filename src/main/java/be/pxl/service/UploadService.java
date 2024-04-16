package be.pxl.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class UploadService {

	@Async
	public void createTeam(String city, MultipartFile file) throws IOException {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String[] HEADERS = {"Name","Email","Position","ShirtNumber"};
			CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
					.setHeader(HEADERS)
					.setSkipHeaderRecord(true)
					.setQuote('"')
					.build();

			CSVParser records = csvFormat.parse(bufferedReader);
			for (CSVRecord record : records) {
			}
		}
	}
}

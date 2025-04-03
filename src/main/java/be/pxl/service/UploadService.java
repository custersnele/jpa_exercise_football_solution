package be.pxl.service;

import be.pxl.domain.FootballPlayer;
import be.pxl.domain.FootballTeam;
import be.pxl.domain.Position;
import be.pxl.repository.FootballPlayerRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class UploadService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);
	private final FootballPlayerRepository footballPlayerRepository;

	public UploadService(FootballPlayerRepository footballPlayerRepository) {
		this.footballPlayerRepository = footballPlayerRepository;
	}

	@Async
	@Transactional
	public void createTeam(FootballTeam footballTeam, MultipartFile file) {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String[] HEADERS = { "Name", "Email", "Position", "ShirtNumber" };
			CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
					.setHeader(HEADERS)
					.setSkipHeaderRecord(true)
					.setQuote('"')
					.build();

			CSVParser records = csvFormat.parse(bufferedReader);
			for (CSVRecord csvRecord : records) {
				FootballPlayer footballPlayer = new FootballPlayer();
				footballPlayer.setName(csvRecord.get(0));
				footballPlayer.setEmail(csvRecord.get(1));
				footballPlayer.setPosition(Position.valueOf(csvRecord.get(2).toUpperCase()));
				footballPlayer.setShirtNumber(Integer.parseInt(csvRecord.get(3)));
				footballPlayer.setTeam(footballTeam);
				footballPlayerRepository.save(footballPlayer);
			}
		} catch (IOException e) {
			LOGGER.error("Error processing file. ", e);
		}
	}
}

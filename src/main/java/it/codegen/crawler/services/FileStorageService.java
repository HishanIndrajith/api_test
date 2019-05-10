package it.codegen.crawler.services;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import it.codegen.crawler.FileStorageException;
import it.codegen.crawler.models.CrawlingCriteria;
import it.codegen.crawler.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileStorageService
{
	private final Path fileStorageLocation;
	@Autowired
	CrawlingCriteriaService crawlingCriteriaRepositor;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
				.toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {

			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
		}
	}

	public String storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if(fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

//			Scanner s = new Scanner(file.getInputStream()).useDelimiter("\\A");
//			String result = s.hasNext() ? s.next() : "";
//			System.out.println(result);
//			// Copy file to the target location (Replacing existing file with the same name)
//			Path targetLocation = this.fileStorageLocation.resolve(fileName);
//			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			saveData( file );


			return fileName;
		} catch (Exception ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	private void saveData (MultipartFile file) throws Exception{
		List<CrawlingCriteria> criteriaList = readFile(convert(file));
		crawlingCriteriaRepositor.insertCrawlingCriteriaList( criteriaList );
	}

	public static List<CrawlingCriteria> readFile(File csvFile) throws Exception {
		CsvMapper csvMapper = new CsvMapper();
		csvMapper.enable( CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE);
		MappingIterator<CrawlingCriteria> criteriaIterator = csvMapper.readerWithTypedSchemaFor(CrawlingCriteria.class).readValues(csvFile);

		return criteriaIterator.readAll();
	}

	public static File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

}

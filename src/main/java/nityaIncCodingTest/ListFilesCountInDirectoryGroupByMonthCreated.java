package nityaIncCodingTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ListFilesCountInDirectoryGroupByMonthCreated {
	private static final Logger LOGGER = Logger.getLogger(ListFilesCountInDirectoryGroupByMonthCreated.class.getName());

	public static void main(String args[]) {
		
		File directory = new File(System.getProperty("user.dir")+"\\src\\main\\java\\nityaIncCodingTest");
		//replace System.getProperty("user.dir")+"\\src\\main\\java\\nityaIncCodingTest" this one with the directory path we want to search.
		
		//String directoryPath = "D:\\farebox";
		//File directory = new File(directoryPath);

		Map<Month, List<String>> filemap = new HashMap<Month, List<String>>();

		File[] files = directory.listFiles();
		Arrays.stream(files).forEach(file -> {
			if (file.isFile()) {
				BasicFileAttributes fatr = null;
				try {
					fatr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
				} catch (IOException e1) {
					LOGGER.warning("Failed to read file Attributes :" + e1.getMessage());
					e1.printStackTrace();
				}
				LocalDate localDate = null;
				try {
					localDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
							.parse(fatr.creationTime().toString()).getTime()).toLocalDate();

				} catch (ParseException e) {
					LOGGER.warning("could not parse the String to Date :" + e.getMessage());
				}
				if (filemap.containsKey(localDate.getMonth())) {
					filemap.get(localDate.getMonth()).add(file.getName());
				} else {
					filemap.put(localDate.getMonth(), new ArrayList<String>(Arrays.asList(file.getName())));

				}
			}

		});
		System.out.println("CreatedMonth | number of files created in the " + directory.getAbsolutePath() + " directory");
		filemap.forEach((k, v) -> System.out.println((k + ":" + v.size())));

	}
}
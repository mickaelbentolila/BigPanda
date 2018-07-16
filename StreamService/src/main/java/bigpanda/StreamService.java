package bigpanda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.cli.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import bigpanda.processing.StreamProcessing;


@SpringBootApplication
public class StreamService {

	public static void main(String[] args) throws IOException {
		Options options = new Options(); 
        options.addOption("gen", true, "generator file path");
        CommandLineParser parser = new BasicParser();
        String generatorPath = "";
        try {
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("gen")) {
            	generatorPath = line.getOptionValue("gen");
            } else {
    			System.out.println("Wrong argument number");
    			System.out.println("You must enter the path of events generator.");
    			System.exit(0);
    		}
        } catch (ParseException e) {
            System.exit(0);
        }
		
		ConfigurableApplicationContext applicationContext = SpringApplication.run(StreamService.class, args);
		StreamProcessing streamProcessing = applicationContext.getBean(StreamProcessing.class);
		
		// Launch Json generator
		ProcessBuilder processBuilder = new ProcessBuilder(generatorPath);
		Process process = processBuilder.start();
		
		// Start processing
		InputStream inputStream = process.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        streamProcessing.startProcessing(bufferedReader);
	}

}

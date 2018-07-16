package bigpanda.processing;

import bigpanda.model.Event;
import java.io.BufferedReader;
import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Service
public class StreamProcessing {
	private ConcurrentHashMap<String, Integer> counterByEvent;
	private ConcurrentHashMap<String, Integer> counterByWord;
	
	public int getCountByEventType(String eventType) {
		return counterByEvent.getOrDefault(eventType, 0);
	}
	
	public int getCountByWord(String word) {
		return counterByWord.getOrDefault(word, 0);
	}
	
	@PostConstruct
    private void setup(){
		counterByEvent = new ConcurrentHashMap<String, Integer>();
		counterByWord = new ConcurrentHashMap<String, Integer>();
	}

	public void startProcessing(BufferedReader bufferedReader) {
		while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                    continue;
                }

                new Thread(() -> { 
                	processEvent(line);
                }).start();
            } catch (Exception e) {
            	System.out.println(e);
            }
        }
	}

	private void processEvent(String line) {
		Event event = parseLine(line);
		if (event != null) {
			System.out.println(MessageFormat.format("Event: ''{0}''", line));
			counterByEvent.put(event.getType(), counterByEvent.getOrDefault(event.getType(), 0) + 1);
			counterByWord.put(event.getData(), counterByWord.getOrDefault(event.getData(), 0) + 1);
		}
	}
	
	private Event parseLine(String line) {
		Event event = null;
		
		try{
			Gson gson = new Gson();
			event = gson.fromJson(line, Event.class);
		} catch (JsonSyntaxException e) {
			System.out.println(MessageFormat.format("Failed to parse event: ''{0}''", line));
        }
		
		return event;
	}

}

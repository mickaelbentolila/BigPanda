package bigpanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bigpanda.processing.StreamProcessing;

@RestController
public class BigpandaController {
	@Autowired
	private StreamProcessing streamProcessing;
	
	@RequestMapping("/eventType")
	public int getCountByEventType(@RequestParam(value="type") String eventType) {
		return streamProcessing.getCountByEventType(eventType);
	}
	
	@RequestMapping("/eventWord")
	public int getCountByWord(@RequestParam(value="word") String word) {
		return streamProcessing.getCountByWord(word);
	}
}

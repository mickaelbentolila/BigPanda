package bigpanda.model;

public class Event {
	private String event_type;
	private String data;
	private long timestamp;
	
	public Event(String type, String data, long timeStamp) {
		this.event_type = type;
        this.data = data;
        this.timestamp = timeStamp;
	}
	
	public String getType() {
        return event_type;
    }

    public void setType(String type) {
        this.event_type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timestamp = timeStamp;
    }
}

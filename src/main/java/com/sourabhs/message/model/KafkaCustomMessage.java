package com.sourabhs.message.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="GREETINGS_MESSAGES")
public class KafkaCustomMessage {
	
	@Id @GeneratedValue
	private int id;
    private String message;
    private String name;
    @Column(name="timeinms")
    private long timeInMilliSeconds;

	public KafkaCustomMessage() {}

    public KafkaCustomMessage(String msg, String name, long time) {
        this.message = msg;
        this.name = name;
        this.timeInMilliSeconds = time;
    }
    
    public KafkaCustomMessage(int id, String msg, String name, long time) {
        this.message = msg;
        this.name = name;
        this.timeInMilliSeconds = time;
        this.id = id;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public long getTimeInMilliSeconds() {
		return timeInMilliSeconds;
	}

	public void setTimeInMilliSeconds(long timeInMilliSeconds) {
		this.timeInMilliSeconds = timeInMilliSeconds;
	}
    @Override
    public String toString() {
        return "message: "+ message + ", " + "name: " + name + "for time: " + timeInMilliSeconds + " ms!";
    }

}

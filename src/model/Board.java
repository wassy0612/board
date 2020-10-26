package model;

import java.sql.Timestamp;

public class Board {

	private int id;
	private String name;
	private String comment;
	private Timestamp time;

	public String getName() {
		return name;
	}
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
	public String getComment() {
		return comment;
	}
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }

}

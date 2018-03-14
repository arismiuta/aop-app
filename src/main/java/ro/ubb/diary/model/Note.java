package ro.ubb.diary.model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;

public class Note implements Serializable {

    private static final long serialVersionUID = 2L;

    private long noteId;
    private long custId;
    private Date date;
    private Time time;
    private String text;

    public Note() {

    }

    public Note(long noteId, long custId, Date date, Time time, String text) {
        this.noteId = noteId;
        this.custId = custId;
        this.date = date;
        this.time = time;
        this.text = text;
    }


    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", custId=" + custId +
                ", date=" + date +
                ", time=" + time +
                ", text='" + text + '\'' +
                '}';
    }
}

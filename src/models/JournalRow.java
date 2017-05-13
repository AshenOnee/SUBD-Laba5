package models;

public class JournalRow 
{
	private String id;
	private String pupilFIO;
	private String teacherFIO;
	private String subject;
	private String date;
	private String mark;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPupilFIO() {
		return pupilFIO;
	}
	public void setPupilFIO(String pupilFIO) {
		this.pupilFIO = pupilFIO;
	}
	public String getTeacherFIO() {
		return teacherFIO;
	}
	public void setTeacherFIO(String teacherFIO) {
		this.teacherFIO = teacherFIO;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
}

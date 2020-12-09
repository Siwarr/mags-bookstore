package bookstore.entites;

import java.sql.Date;

public class Emprunt {
	private String bookName;
	private String memberName;
	private Date issueTime;
	private int renew_count;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Date getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}
	public int getRenew_count() {
		return renew_count;
	}
	public void setRenew_count(int renew_count) {
		this.renew_count = renew_count;
	}
	public Emprunt(String bookName, String memberName, Date issueTime, int renew_count) {
		super();
		this.bookName = bookName;
		this.memberName = memberName;
		this.issueTime = issueTime;
		this.renew_count = renew_count;
	}
	
	public Emprunt() {}
	@Override
	public String toString() {
		return "Emprunt [bookName=" + bookName + ", memberName=" + memberName + ", issueTime=" + issueTime
				+ ", renew_count=" + renew_count + "]";
	}
	
}

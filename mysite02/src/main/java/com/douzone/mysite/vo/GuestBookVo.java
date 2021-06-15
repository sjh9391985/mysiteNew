package com.douzone.mysite.vo;

public class GuestBookVo {
	private long no;
	private String name;
	private String password;
	private String redDate;
	private String message;

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRedDate() {
		return redDate;
	}

	public void setRedDate(String redDate) {
		this.redDate = redDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "GuestBookVo [no=" + no + ", name=" + name + ", password=" + password + ", redDate=" + redDate
				+ ", message=" + message + "]";
	}
}

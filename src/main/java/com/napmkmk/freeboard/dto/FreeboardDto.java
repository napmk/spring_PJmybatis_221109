package com.napmkmk.freeboard.dto;

import java.sql.Timestamp;

public class FreeboardDto {
	private int fnum; //자유게시판번호
	private int fid; //글쓴이아이디
	private String fname; //글쓴이
	private String ftitle; //게시판제목
	private String fcontent; //게시판내용
	private Timestamp fdate;//글쓴시간
	private int fhit; //조회수

	
	public FreeboardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FreeboardDto(int fnum, int fid, String fname, String ftitle, String fcontent, Timestamp fdate, int fhit) {
		super();
		this.fnum = fnum;
		this.fid = fid ;
		this.fname = fname;
		this.ftitle = ftitle;
		this.fcontent = fcontent;
		this.fdate = fdate;
		this.fhit = fhit;
	}

	public int getFnum() {
		return fnum;
	}

	public void setFnum(int fnum) {
		this.fnum = fnum;
	}
	
	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}
	

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFtitle() {
		return ftitle;
	}

	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}

	public String getFcontent() {
		return fcontent;
	}

	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}

	public Timestamp getFdate() {
		return fdate;
	}

	public void setFdate(Timestamp fdate) {
		this.fdate = fdate;
	}

	public int getFhit() {
		return fhit;
	}

	public void setFhit(int fhit) {
		this.fhit = fhit;
	}
	
	
	
}

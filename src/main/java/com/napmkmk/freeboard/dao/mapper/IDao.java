package com.napmkmk.freeboard.dao.mapper;

public interface IDao {
	// member 관련 메서드
	public void joinMemberDao(String mid, String mpw,String mname, String memail); //회원가입
	public int checkIdDao(String id); //회원가입 여부 확인 (1반환이미 존재 , 0 이면 가입가능)
	public int checkPwDao(String mid, String mpw);// 아이디와 비밀번호 일치 여부 체크
}

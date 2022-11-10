package com.napmkmk.freeboard.dao.mapper;

import java.util.ArrayList;

import com.napmkmk.freeboard.dto.FreeboardDto;
import com.napmkmk.freeboard.dto.MemberDto;

public interface IDao {
	
	// member 관련 메서드
	public void joinMemberDao(String mid, String mpw,String mname, String memail); //회원가입
	public int checkIdDao(String id); //회원가입 여부 확인 (1반환이미 존재 , 0 이면 가입가능)
	public int checkPwDao(String mid, String mpw);// 아이디와 비밀번호 일치 여부 체크
	public MemberDto memberInfoDao(String mid); //가입된 회원정보를 불러옴
	
	// board 관련 메서드
	public void writeDao(String mid,String mname, String ftitle,String fcontent);
	public ArrayList<FreeboardDto> listDao();
	public FreeboardDto contentView(String fnum);//글 내용보기 (글 하나를 받을 준비해야함.)
	public void deleteDao(String fnum); //글삭제
//	public FreeboardDto mviewDto(String fnum); ??  몰라
	public void modifyDao(String fnum, String fname, String ftitle,String fcontent);//글수정
	public void upHitDao(String fnum);
	
}

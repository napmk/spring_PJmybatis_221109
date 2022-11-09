package com.napmkmk.freeboard.controller;
import com.napmkmk.freeboard.dao.mapper.IDao;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class FBoardController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "joinMember" )
	public String joinMember() {
		
		return "joinMember";
	}
	
	@RequestMapping(value = "joinOk" , method = RequestMethod.POST)
	public String joinOk(HttpServletRequest request, Model model) {
		//여기에 넣기 위해 @Autowired SqlSession 넣어준다.
		
		IDao dao = sqlSession.getMapper(IDao.class);
		int checkIdFlag = dao.checkIdDao(request.getParameter("mid"));
		//checkIDFlag값이 1이면 이미 가입하려는 아이디 db에 존재, 0 이면 가입가능
		//model.. add.(checkIdFlag);
		model.addAttribute("checkIdFlag", checkIdFlag);
		
		if(checkIdFlag == 0) {
			dao.joinMemberDao(request.getParameter("mid"), request.getParameter("mpw"), request.getParameter("mname"), request.getParameter("memail"));
			model.addAttribute("mname", request.getParameter("mname"));
		}
		return "joinOk";
	}
	 
	@RequestMapping(value = "checkId")
	public String checkId(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		int checkIdFlag = dao.checkIdDao(request.getParameter("checkId"));
		//checkIDFlag값이 1이면 이미 가입하려는 아이디 db에 존재, 0 이면 가입가능
		//model.. add.(checkIdFlag);
		model.addAttribute("checkIdFlag", checkIdFlag);
		
		
		return "checkId";
	}
	
	@RequestMapping(value = "login" )
	public String login() {
		
		return "login";
	}
	
	@RequestMapping(value = "loginOk", method = RequestMethod.POST)
	public String loginOk(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");

		int checkIdFlag = dao.checkIdDao(mid); //*** 중요 1이면 아이디 존재
		int checkPwFlag = dao.checkPwDao(mid, mpw); //*** 중요  1이면 아이디 비번 모두 일치
		
		model.addAttribute("checkIdFlag", checkIdFlag);
		model.addAttribute("checkPwFlag", checkPwFlag);
		model.addAttribute("mid" , mid);
		
		return "loginOk";
	}
	
}

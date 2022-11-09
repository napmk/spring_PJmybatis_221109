package com.napmkmk.freeboard.controller;
import com.napmkmk.freeboard.dao.mapper.IDao;
import com.napmkmk.freeboard.dto.MemberDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

		int checkIdFlag = dao.checkIdDao(mid); //*** 중요 1이면 아이디 존재 0이면 기존 가입한 아이디 없음
		int checkPwFlag = dao.checkPwDao(mid, mpw); //*** 중요  1이면 아이디 비번 모두 일치 0이면 비밀번호 틀림
		
		model.addAttribute("checkIdFlag", checkIdFlag);
		model.addAttribute("checkPwFlag", checkPwFlag);
		model.addAttribute("mid" , mid);
		
		if(checkPwFlag == 1) { //로그인 성공시 세션에 아이디와 로그인 유효값을 설정
			
			HttpSession  session =request.getSession();
			
			session.setAttribute("sessionId", mid); //로그인 하려면 서버세션에 저장해놓아야 하므로
			session.setAttribute("ValidMem","yes");
			
			//로그인 성공시 이름 노출되고 싶으면 여기다 작성
			MemberDto dto= dao.memberInfoDao(mid);
			String mname = dto.getMname();
			model.addAttribute("mname" , mname);
			
		} else {
			
			model.addAttribute("mname" , "guest");
			
		}

		return "loginOk";
		
		
	}
	@RequestMapping (value = "writeForm")
	public String writeForm(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		HttpSession  session =request.getSession(); // 세션 가져옴
		String sid = (String) session.getAttribute("sessionId"); //로그인 하려면 서버세션에 저장해놓아야 하므로
		// 메게변수에 넣어주기 위한 sid
		
		if(sid.equals(null)) {
			return "redirect:login";
		} else {
			MemberDto dto= dao.memberInfoDao(sid); // 메게변수에 넣어주기 위한 sid  밑에 이름이 겹쳐서 sid
			String mname = dto.getMname();
			String mid = dto.getMid();
			model.addAttribute("mname" , mname);
			model.addAttribute("mid" , mid);
			
			return "writeForm";
		}
		
	
	}
	@RequestMapping(value = "write")
	public String write(HttpServletRequest request) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		//ContentDao dao = new ContentDao();

		HttpSession  session =request.getSession(); // 세션 가져옴
		String sid = (String) session.getAttribute("sessionId"); //로그인 하려면 서버세션에 저장해놓아야 하므로
		// 메게변수에 넣어주기 위한 sid
		
//		String mname= "";
//		String mid= "";
//		
//		if (sid.equals(null)) {
//			mname = "손님";
//			mid = "게스트";
//			
//		} else {
			
	
		MemberDto dto= dao.memberInfoDao(sid); // 메게변수에 넣어주기 위한 sid  밑에 이름이 겹쳐서 sid
		String mname = dto.getMname();
		String mid = dto.getMid();
		
//		}
		
		String ftitle = request.getParameter("ftitle");
		String fcontent = request.getParameter("fcontent");
		
		dao.writedao(mid, mname, ftitle,fcontent);
		
		return "redirect:list";
	}
	
}

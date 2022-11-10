package com.napmkmk.freeboard.controller;
import com.napmkmk.freeboard.dto.FreeboardDto;

import com.napmkmk.freeboard.dao.mapper.IDao;
import com.napmkmk.freeboard.dto.MemberDto;

import java.util.ArrayList;

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
		
		if(sid == null) {
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
		
		dao.writeDao(mid, mname, ftitle,fcontent);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "logOut")
	   public String logOut(HttpServletRequest request) {
	      
	      HttpSession session = request.getSession();
	      session.invalidate();//섹션삭제 즉 로그아웃
	      
	      return"logOut";
	   }
	
	
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request,Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		ArrayList<FreeboardDto> dtos = dao.listDao();
		
		
		HttpSession session = request.getSession(); //현재 세션가져 오기
		
		String sid = (String) session.getAttribute("sessionId");//현재 세션에 로그인 되어 있는 아이디 가져오기
		
		
		
		int idflag = 0; //초기값 0 이고 
		
		if((sid != null) ) { //로그인이 되어있는 경우
			idflag = 1;
		
			model.addAttribute("sid", sid); //모델에 싣고 보내자
		
			
		}
		model.addAttribute("idflag",idflag); //1이면 로그인 0이면 로그인 아님

		model.addAttribute("boardSum",dtos.size()); // 게시판 리스트 갯수
		model.addAttribute("list", dtos);
		
		return "list";
	}
	
	@RequestMapping(value = "content_view")
	public String content_view(HttpServletRequest request,Model model) { //받을땐 리퀘스트 보낼땐 모델
		
		String fnum = request.getParameter("fnum");
		
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		FreeboardDto  dtos = dao.contentView(fnum);
		
		HttpSession session = request.getSession(); //현재 세션가져 오기
		
		String sid = (String) session.getAttribute("sessionId");//현재 세션에 로그인 되어 있는 아이디 가져오기
		
		String fid = dtos.getFid(); //현재 보고 있는 글을 쓴 아이디
		
		int idflag = 0; //초기값 0 이고 
		
		if((sid != null) && (sid.equals(fid))) {
			idflag = 1;
			
		}
		model.addAttribute("idflag",idflag); //idflag ==  1이면 수정 삭제 권한 설정 
		
		
		dao.upHitDao(fnum);
		model.addAttribute("viewdto", dtos);
		
		return "content_view";
	}
	
	@RequestMapping(value = "delete")
		public String delete(HttpServletRequest request) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		String fnum = request.getParameter("fnum");
		dao.deleteDao(fnum); 
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "modifyView")
	public String modifyView(HttpServletRequest request, Model model) {
		
		String fnum = request.getParameter("fnum");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		FreeboardDto fbdto = dao.contentView(fnum);
		
		model.addAttribute("fbdto", fbdto);
		
		return "modifyView";
	}
	
	@RequestMapping(value = "modify")
	   public String modify(HttpServletRequest request) {
	      String fnum = request.getParameter("fnum");
	      String fname = request.getParameter("fname");
	      String ftitle = request.getParameter("ftitle");
	      String fcontent = request.getParameter("fcontent");
	       
	       IDao dao = sqlSession.getMapper(IDao.class);
	      
	       dao.modifyDao(fnum, ftitle, fcontent, fname);
	       
	      
	      return "redirect:list";
	   }

	
	
	@RequestMapping(value = "/")
	public String home() {
		return "redirect:list";
	}
	

}

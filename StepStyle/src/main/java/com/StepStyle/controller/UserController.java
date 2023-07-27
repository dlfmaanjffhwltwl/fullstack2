package com.StepStyle.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.StepStyle.service.UserService;
import com.StepStyle.vo.UserVO;


@RequestMapping(value="/user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public void login(UserVO vo,HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		HttpSession session = req.getSession();
		
		UserVO loginVO = userService.selectUserByLogin(vo);
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = res.getWriter();
		
		
		if(loginVO != null) {
			//login�� ȸ���� �����ͺ��̽��� ����
			System.out.println("ȸ������");
			
			session.setAttribute("login", loginVO);
			pw.append("<script>alert('�α��ο� �����߽��ϴ�.');location.href='"+req.getContextPath()+"/index.do';</script>");
			
		}else {
			//login�� ȸ���� �����ͺ��̽��� ���� X
			System.out.println("ȸ������ X");
			pw.append("<script>alert('�α��ο� �����߽��ϴ�.');location.href='"+req.getContextPath()+"/user/login.do';</script>");
		}
		
		pw.flush();
	}
	
	@RequestMapping(value="/join.do")
	public String join()
	{
		return "user/join";
	}
	
}















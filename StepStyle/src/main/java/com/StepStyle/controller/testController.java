package com.StepStyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.StepStyle.vo.testVO;



@Controller
public class testController {
	
	@Autowired
	private com.StepStyle.service.testService testService;
	
	@RequestMapping(value="/test.do")
	public String test(int noticeNo,Model model) {
		
		//1.ȭ�鿡�� �Ѿ���� bidx ����
		
		//2.������ bidx�� �̿��Ͽ� ��ġ�ϴ� ������ ��ȸ
		testVO vo = testService.selectOneBynoticeNo(noticeNo);
		
		//3.2�� �����͸� ȭ������ ���� 
		model.addAttribute("vo", vo);
		
		return "views/test";
	}
	
	
	
	
	
	
	
	
	
	
	
}
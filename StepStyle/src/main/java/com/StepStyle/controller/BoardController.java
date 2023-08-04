package com.StepStyle.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.StepStyle.service.BoardService;
import com.StepStyle.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	
    @Autowired
    private BoardService boardService;

    @RequestMapping("/community.do")
    public String community(Model model) {
        List<BoardVO> community = boardService.getBoardList();
        model.addAttribute("community", community);
        return "board/community";
    }
    
    @RequestMapping(value = "/search.do", method = RequestMethod.GET)
    public String searchBoard(@RequestParam("keyword") String keyword, Model model) {
        // �˻�� ���� �Խù��� �˻��մϴ�.
        List<BoardVO> searchResult = boardService.searchBoardByKeyword(keyword);

        // �˻� ����� �𵨿� �߰��Ͽ� JSP���� ����� �� �ֵ��� �մϴ�.
        model.addAttribute("community", searchResult);

        // community.jsp�� �̵��մϴ�. (�˻� ����� ǥ���ϱ� ����)
        return "board/community";
    }
    
    @RequestMapping("/view.do")
    public String viewBoard(@RequestParam("bidx") int bidx, Model model) {
        BoardVO board = boardService.selectOneByBidx(bidx); // ����� �κ�
        model.addAttribute("board", board);
        return "board/view";
    }
    
    @RequestMapping("/shoesPage.do")
    public String shoesPage() {
    	return "board/shoesPage";
    }
    
    @RequestMapping("/shoesDetaPage.do")
    public String shoesDetaPage() {
    	return "board/shoesDetaPage";
    }
    
    @RequestMapping(value = "/write.do", method = RequestMethod.GET)
    public String showWriteForm(Model model) {
        // �� �ۼ� ���� �����ֱ� ���� �� BoardVO ��ü�� Model�� �߰�
        model.addAttribute("board", new BoardVO());
        return "board/write"; // �� �ۼ� �� �������� �̵�
    }
    
    
    @RequestMapping(value = "/write.do", method = RequestMethod.POST)
    public String insertBoard(@ModelAttribute BoardVO vo) {
    	 // �� �ۼ� �ð��� ���� �ð����� ����
    	vo.setWdate(new Date());
        // �� �ۼ��� ���� ���� �޼��带 ȣ���Ͽ� �����ͺ��̽��� �� �����ϱ�
        boardService.insertBoard(vo);
        return "redirect:/board/community.do"; // �� �ۼ� ��, ��� �������� �����̷�Ʈ
    }
    
    
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    public String delete(@RequestParam("bidx") int bidx, Model model) {
        int result = boardService.deleteBoard(bidx);

        if (result > 0) {
            // ���� ���� �� �޽����� �𵨿� �߰��Ͽ� ȭ�鿡 ������
            model.addAttribute("message", "�����Ǿ����ϴ�.");
        } else {
            // ���� ���� �� �޽����� �𵨿� �߰��Ͽ� ȭ�鿡 ������
            model.addAttribute("message", "�������� �ʾҽ��ϴ�.");
        }

        // ���� �� Ŀ�´�Ƽ �������� �����̷�Ʈ
        return "redirect:/board/community.do";
    }
    
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String showUpdateForm(@RequestParam("bidx") int bidx, Model model) {
        BoardVO board = boardService.selectOneByBidx(bidx);
        if (board != null) {
            model.addAttribute("board", board);
            return "board/modify"; // ���� �������� �����ݴϴ�.
        } else {
            // �ش� �Խñ��� ���� ��쿡 ���� ���� ó�� ����
            return "error"; // ���� ó�� �������� �����ݴϴ�.
        }
    }

    // �Խñ� ���� ó���� ���� �ڵ鷯
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String updateBoard(@ModelAttribute BoardVO vo, Model model) {
        // ���� �ð��� �����Ͽ� wdate ���� ����
        vo.setWdate(new Date());

        int result = boardService.updateBoard(vo);
        if (result > 0) {
            // ���� ���� �� �޽����� �𵨿� �߰��Ͽ� ȭ�鿡 ������
            model.addAttribute("message", "�����Ǿ����ϴ�.");
        } else {
            // ���� ���� �� �޽����� �𵨿� �߰��Ͽ� ȭ�鿡 ������
            model.addAttribute("message", "�������� �ʾҽ��ϴ�.");
        }
        // ���� �� �� �������� �����̷�Ʈ
        return "redirect:/board/view.do?bidx=" + vo.getBidx();
    }
    
 
}

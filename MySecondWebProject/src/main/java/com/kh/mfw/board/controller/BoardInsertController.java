package com.kh.mfw.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mfw.board.model.dto.BoardDTO;
import com.kh.mfw.board.model.service.BoardService;
import com.kh.mfw.member.model.dto.MemberDTO;

@WebServlet("/insert.board")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardInsertController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 게시글 번호, 카테고리, 제목, 내용, 작성자, 조회수, 작성일
		// 시퀀스    , 앞단   , 앞단, 앞단,      , DEFAULT값 있음
		BoardDTO board = new BoardDTO();
		board.setBoardCategory(request.getParameter("category"));
		board.setBoardTitle(request.getParameter("title"));
		board.setBoardContent(request.getParameter("content"));
		
		// 작성자에 대한 정보도 담아서 보내야 하는데
		// 로그인하지 않은 사용자는 게시글 작성 요청을 보낼 수 없게끔
		
		// session.getAttribute("loginMember"): Object형 주소값 or Null 값
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			request.setAttribute("message", "로그인해주세요.");
			response.sendRedirect(request.getContextPath());
			return;
		}
		String memberId = ((MemberDTO)session.getAttribute("loginMember"))
											 .getMemberId();
		board.setBoardWriter(memberId);
		
		// 요청 처리
		new BoardService().insertBoard(board);
		
		// 1. 포워딩 처리
		// 2. sendRedirect
		session.setAttribute("message", "게시글 작성에 성공했습니다.");
		response.sendRedirect(request.getContextPath() + "/boards");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

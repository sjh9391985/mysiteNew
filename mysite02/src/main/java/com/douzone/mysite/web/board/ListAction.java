package com.douzone.mysite.web.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ListAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("p");
		int nowPage = 0;
		if ("".equals(page) || page == null) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		List<BoardVo> list = new BoardRepository().findAll();
		
		
		
		List<BoardVo> listPage = new BoardRepository().findPage(nowPage);
		
		
		int index = (int)new BoardRepository().findTotalPage();
		double totalPage = Math.ceil(new BoardRepository().findTotalPage()/5);
		int lastPage = (int)Math.ceil((double)nowPage/5)*5;
		int firstPage = lastPage-4;
		
		Map<String, Integer> map = new HashMap<>();
		map.put("firstPage", firstPage);
		map.put("lastPage", lastPage);
		map.put("nextPage", nowPage+1);
		map.put("prevPage", nowPage-1);
		map.put("nowPage", nowPage);
		map.put("totalPage", (int)totalPage);
		
		
		
		
		request.setAttribute("boardList", list);
		request.setAttribute("pageInfo", map);
		request.setAttribute("total", index);
		
		MvcUtils.forward("board/list", request, response);

	}

}

package com.lhs.home.board.sevice;

import java.util.List;

import com.lhs.home.board.domain.BoardDTO;

public interface BoardService {
	
	/* 게시판 목록 조회 */
	public List<BoardDTO> selectBoardListByBoardCode(int boardCode);
	
	/* 게시글 등록 */
	public int insertBoard(BoardDTO boardDTO);

}

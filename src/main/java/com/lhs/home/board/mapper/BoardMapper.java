package com.lhs.home.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lhs.home.board.domain.BoardDTO;

@Mapper
public interface BoardMapper {
	
	/* 게시판 목록 조회 */
	public List<BoardDTO> selectBoardListByBoardCode(int boardCode);
	
	/* 게시글 등록 */
	public int insertBoard(BoardDTO boardDTO);
}

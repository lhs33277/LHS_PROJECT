package com.lhs.home.board.sevice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.home.board.domain.BoardDTO;
import com.lhs.home.board.mapper.BoardMapper;
import com.lhs.home.board.sevice.BoardService;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDTO> selectBoardListByBoardCode(int boardCode) {
		
		return boardMapper.selectBoardListByBoardCode(boardCode);
	}
	
	@Override
	public int insertBoard(BoardDTO boardDTO) {
		
		return boardMapper.insertBoard(boardDTO);
	}

}

package com.lhs.home.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lhs.home.base.domain.FileDTO;
import com.lhs.home.base.service.FileService;
import com.lhs.home.board.domain.BoardDTO;
import com.lhs.home.board.sevice.BoardService;
import com.lhs.home.user.domain.UserDTO;
import com.lhs.home.utils.FileUtils;
import com.lhs.home.utils.JSPUtils;
import com.lhs.home.utils.StringUtils;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/selectBoardList.do")
	public String selectBoardList(Model model, @RequestParam String boardCode) {
		
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		boardList = boardService.selectBoardListByBoardCode(boardCode);
		model.addAttribute(boardList);
		
		return JSPUtils.modelAndJsp(model, "/board/notice");
	}
	
	/* 게시글 등록 */
	@RequestMapping("/insertBoard.do")
	public String insertBoard(Model model, HttpServletRequest request, MultipartHttpServletRequest multipartRequest, BoardDTO boardDTO) throws Exception {
		
		HttpSession session = request.getSession();
		UserDTO userDTO = (UserDTO)session.getAttribute("userDTO");
		int userNo = userDTO.getUserNo();
		
		/* 파일처리 */
		List<MultipartFile> fileList = new ArrayList<>();
		
		fileList.add(multipartRequest.getFile("file1"));
		fileList.add(multipartRequest.getFile("file2"));
		fileList.add(multipartRequest.getFile("file3"));
		
		int fileNo = fileService.selectNextFileNo();
		
		for(int i = 0; i < fileList.size(); i++) {
			
			MultipartFile multipartFile = fileList.get(i);
			
			if(multipartFile.getSize() > 0) {
				
				File uploadFile = FileUtils.fileUpload(multipartFile);
				
				FileDTO fileDTO = FileDTO.builder()
						.fileNo(fileNo)
						.fileName(uploadFile.getName())
						.originalFileName(multipartFile.getOriginalFilename())
						.filePath(uploadFile.getPath())
						.fileSize(uploadFile.length())
						.fileCntn("게시글 첨부파일")
						.registUserNo(userNo)
						.build();
				
				fileService.insertFile(fileDTO);
			}
			
			
		}
		
		return null;
		
		
		
	}

}

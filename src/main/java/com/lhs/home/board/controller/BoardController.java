package com.lhs.home.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lhs.home.base.domain.BaseDTO;
import com.lhs.home.base.domain.FileDTO;
import com.lhs.home.base.service.FileService;
import com.lhs.home.board.domain.BoardDTO;
import com.lhs.home.board.sevice.BoardService;
import com.lhs.home.user.domain.UserDTO;
import com.lhs.home.utils.FileUtils;
import com.lhs.home.utils.JSPUtils;
import com.lhs.home.utils.PageUtils;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private FileService fileService;
	
	/* 게시글 목록 조회 */
	@RequestMapping("/selectBoardList.do")
	public String selectBoardList(Model model, @RequestParam int boardCode) {
		
		/* 메뉴 정보 */
		model.addAttribute("boardCode", boardCode);
		
		/* 게시글 조회 */
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		boardList = boardService.selectBoardListByBoardCode(boardCode);
		model.addAttribute("boardList", boardList);
		
		/* 페이징 set */
		BaseDTO pageDTO = new BaseDTO();
		pageDTO = PageUtils.getPageInfo(pageDTO);
		
		model.addAttribute("pageDTO", pageDTO);
		
		return JSPUtils.modelAndJsp(model, "/board/notice");
	}
	
	/* 게시글 등록 */
	@RequestMapping("/insertBoard.do")
	public String insertBoard(RedirectAttributes redirectAttribues
			, HttpServletRequest request, MultipartHttpServletRequest multipartRequest, BoardDTO boardDTO) throws Exception {
		
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
		
		/* 게시글 등록 */
		boardDTO.setUserNo(userNo);
		boardDTO.setFileNo(fileNo);
		
		boardService.insertBoard(boardDTO);
		
		return JSPUtils.alertAndRedirect(redirectAttribues, "게시글이 등록되었습니다.", "selectBoardList.do?boardCode=" + boardDTO.getBoardCode());
		
		
		
	}

}

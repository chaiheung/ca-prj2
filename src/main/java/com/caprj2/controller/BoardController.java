package com.caprj2.controller;

import com.caprj2.domain.Board;
import com.caprj2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService service;

    // 새 게시물 작성
    @GetMapping("/add")
    public String add() {
        return "board/add";
    }

    @PostMapping("/add")
    public String addPost(Board board, RedirectAttributes rttr) {
        System.out.println("board = " + board);
        service.add(board);

        rttr.addAttribute("id", board.getId());
        return "redirect:/board";
    }

    // 게시물 조회
    // /board?id=1
    @GetMapping("/board")
    public String view(Integer id, Model model) {
        // 게시물 조회(select)
        Board board = service.get(id);
        // 모델에 넣고
        model.addAttribute("board", board);
        // jsp 로 포워드
        return "board/view";
    }
}

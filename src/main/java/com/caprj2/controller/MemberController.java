package com.caprj2.controller;

import com.caprj2.domain.Member;
import com.caprj2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {
    private final MemberService service;

    // 회원 가입
    @GetMapping("signup")
    public String signupForm() {
        return "member/signup";
    }

    @PostMapping("signup")
    public String signup(Member member) {
        service.signup(member);
        return "redirect:/";
    }

    // 회원 목록
    @GetMapping("list")
    public String list(Model model) {
        model.addAttribute(service.list());
        return "member/list";
    }

    // 회원 정보
    @GetMapping("")
    public String info(Integer id, Model model) {
        model.addAttribute("member", service.get(id));
        return "member/info";
    }

    @PostMapping("remove")
    public String remove(Integer id) {
        service.remove(id);
        return "redirect:/member/signup";
    }
}

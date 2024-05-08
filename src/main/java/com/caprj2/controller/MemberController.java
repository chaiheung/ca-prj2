package com.caprj2.controller;

import com.caprj2.domain.Member;
import com.caprj2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // 회원 정보 수정
    @GetMapping("modify")
    public String modifyForm(Integer id, Model model) {
        model.addAttribute("member", service.get(id));
        return "member/modify";
    }

    @PostMapping("modify")
    public String modify(Member member, RedirectAttributes rttr) {
        service.modify(member);
        rttr.addAttribute("id", member.getId());
        return "redirect:/member";
    }

    // 계정 중복 여부 확인
    @GetMapping("email")
    @ResponseBody // 응답한 데이터를 view 로 해석하지 않음
    public String emailCheck(String email) {
        System.out.println("email = " + email);
        String message = service.emailCheck(email);
        return message;
    }
}

package com.caprj2.service;

import com.caprj2.domain.Member;
import com.caprj2.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper mapper;
    private final BCryptPasswordEncoder encoder;

    public void signup(Member member) {
//        String password = member.getPassword();
//        String encodedPassword = encoder.encode(password);
//        member.setPassword(encodedPassword);
        member.setPassword(encoder.encode(member.getPassword()));
        mapper.insert(member);
    }

    public List<Member> list() {
        return mapper.selectAll();
    }

    public Member get(Integer id) {
        return mapper.selectById(id);
    }

    public void remove(Integer id) {
        mapper.deleteById(id);
    }

    public void modify(Member member) {
        if (member.getPassword() != null && member.getPassword().length() > 0) {
            // 암호를 입력했을 때만 변경
            member.setPassword(encoder.encode(member.getPassword()));
        } else {
            // 기존 암호 유지
            Member old = mapper.selectById(member.getId());
            old.setPassword(old.getPassword());
        }
        mapper.update(member);
    }

    public String emailCheck(String email) {
        Member member = mapper.selectByEmail(email);
        if (member == null) {
            // 사용 가능한 이메일
            return "사용 가능한 메일입니다.";
        } else {
            // 이미 존재하는 이메일
            return "이미 존재하는 메일입니다.";
        }
    }
}
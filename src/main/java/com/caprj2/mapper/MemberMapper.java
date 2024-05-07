package com.caprj2.mapper;

import com.caprj2.domain.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    @Insert("""
            INSERT INTO member (email, password, nickname)
            VALUES (#{email}, #{password}, #{nickname})
            """)
    int insert(Member member);
}

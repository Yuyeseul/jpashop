package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("yeseul");

        // when
        Long saveId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("yeseul");

        Member member2 = new Member();
        member2.setName("yeseul");

        // when
        memberService.join(member1);

        // then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

    @Test
    public void 회원_전체_조회() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("yeseul");

        Member member2 = new Member();
        member2.setName("yu");

        memberService.join(member1);
        memberService.join(member2);

        // when
        List<Member> members = memberService.findMembers();

        // then
        assertEquals(2, members.size());
        assertTrue(members.contains(member1));
        assertTrue(members.contains(member2));
    }
}
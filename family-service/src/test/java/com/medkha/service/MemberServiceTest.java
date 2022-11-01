package com.medkha.service;

import com.medkha.entity.Family;
import com.medkha.entity.Member;
import com.medkha.service.impl.MemberServiceNaive;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class MemberServiceTest {

    private MemberService memberService;

    public MemberServiceTest() {
        this.memberService = MemberServiceNaive.getInstance();
    }

    @Test
    public void test_getMembersByFamily(){
        // given
        Family family = new Family("family1");
        Family family2 = new Family("family2");
        Member memberWithoutParents1 =
                new Member.Builder("lastName", "firstName", LocalDate.of(1978, 1, 1), "somewhere",family)
                        .build();
        Member memberWithoutParents2 =
                new Member.Builder("lastName2", "firstName2", LocalDate.of(1978, 1, 1), "somewhere2",family2)
                        .build();
        Member memberWithParent=
                new Member.Builder("lastName3", "firstName3", LocalDate.of(2000, 1, 1), "somewhere3",family)
                        .father(memberWithoutParents1)
                        .mother(memberWithoutParents2)
                        .build();

        this.memberService.createMember(memberWithoutParents1);
        this.memberService.createMember(memberWithoutParents2);
        this.memberService.createMember(memberWithParent);

        // when
        Set<Member> resultAll = this.memberService.getAllMembers();
        Set<Member> resultFamily1 = this.memberService.getMembersByFamily(family);
        Set<Member> resultFamily2 = this.memberService.getMembersByFamily(family2);

        // then
        assertAll(
                () -> assertEquals(2, resultFamily1.size()),
                () -> assertEquals(1, resultFamily2.size()),
                () -> assertEquals(3, resultAll.size()),
                () -> assertTrue(resultFamily2.contains(memberWithoutParents2)),
                () -> assertFalse(resultFamily1.contains(memberWithoutParents2))
                );

    }

    @Test
    public void test_getMemberByUser() {
        // given
        final Family __FAMILY__ = new Family("family1");
        final String __USERID__ = "1";
        final String __OTHER_USERID__ = "2";
        final String __TOTALY_DIFF_USERID__ = "3";
        final Member __MEMBER_WITHOUT_USER__ =
                new Member.Builder("lastName", "firstName", LocalDate.of(1978, 1, 1), "somewhere",__FAMILY__)
                        .build();
        final Member __MEMBER_WITH_RIGHT_USER__ =
                new Member.Builder("lastName1", "firstName1", LocalDate.of(1978, 1, 1), "somewhere1",__FAMILY__)
                        .user(__USERID__)
                        .build();
        final Member __MEMBER_WITH_OTHER_USER__ =
                new Member.Builder("lastName1", "firstName1", LocalDate.of(1978, 1, 1), "somewhere1",__FAMILY__)
                        .user(__OTHER_USERID__)
                        .build();
        this.memberService.createMember(__MEMBER_WITH_RIGHT_USER__);
        this.memberService.createMember(__MEMBER_WITHOUT_USER__);
        this.memberService.createMember(__MEMBER_WITH_OTHER_USER__);

        // when
        Optional<Member> result_userIdExisting = this.memberService.getMemberByUser(__USERID__);
        Optional<Member> result_userIdNotExisting = this.memberService.getMemberByUser(__TOTALY_DIFF_USERID__);

        // then
        assertAll(
                () -> assertEquals(Optional.of(__MEMBER_WITH_RIGHT_USER__), result_userIdExisting),
                () ->  assertTrue(result_userIdNotExisting.isEmpty())
        );

    }
}

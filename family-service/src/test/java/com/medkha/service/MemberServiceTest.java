package com.medkha.service;

import com.google.common.collect.Multimap;
import com.medkha.entity.CloseMemberType;
import com.medkha.entity.Family;
import com.medkha.entity.Member;
import com.medkha.service.impl.MemberServiceNaive;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class MemberServiceTest {

    private MemberService memberService;

    public MemberServiceTest() {
        this.memberService = MemberServiceNaive.getInstance();
    }

    @AfterEach
    public void reset() {
        this.memberService.clearMembers();
    }
    @Test
    public void test_getMembersByFamily(){
        // given
        Family family = new Family("family1");
        Family family2 = new Family("family2");
        Member memberWithoutParents1 =
                new Member.Builder("lastName", "firstName", LocalDate.of(1978, 1, 1), "agadir",family)
                        .build();
        Member memberWithoutParents2 =
                new Member.Builder("lastName2", "firstName2", LocalDate.of(1978, 1, 1), "agadir",family2)
                        .build();
        Member memberWithParent=
                new Member.Builder("lastName3", "firstName3", LocalDate.of(2000, 1, 1), "agadir",family)
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
                new Member.Builder("lastName", "firstName", LocalDate.of(1978, 1, 1), "agadir",__FAMILY__)
                        .build();
        final Member __MEMBER_WITH_RIGHT_USER__ =
                new Member.Builder("lastName1", "firstName1", LocalDate.of(1978, 1, 1), "agadir",__FAMILY__)
                        .user(__USERID__)
                        .build();
        final Member __MEMBER_WITH_OTHER_USER__ =
                new Member.Builder("lastName1", "firstName1", LocalDate.of(1978, 1, 1), "agadir",__FAMILY__)
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

    @Test
    public void test_getCloseMembers(){
        // given
        final Family __FAMILY__ = new Family("family1");
        final Family __FAMILY2__ = new Family("family2");
        final Family __FAMILY3__ = new Family("family3");
        final Member father =
                new Member.Builder("family1", "firstName", LocalDate.of(1978, 1, 1), "agadir",__FAMILY__)
                        .build();
        final Member mother =
                new Member.Builder("family2", "firstName1", LocalDate.of(1978, 1, 1), "agadir",__FAMILY2__)
                        .build();
        final Member memberSpecified =
                new Member.Builder("family1", "member", LocalDate.of(1994, 1, 1), "agadir",__FAMILY__)
                        .father(father)
                        .mother(mother)
                        .build();
        final Member spouseSibling1 =
                new Member.Builder("family2", "spouseSibling1", LocalDate.of(1995, 1, 1), "agadir",__FAMILY2__)
                        .build();
        final Member sibling1 =
                new Member.Builder("family1", "sibling1", LocalDate.of(1995, 1, 1), "agadir",__FAMILY__)
                        .father(father)
                        .mother(mother)
                        .build();

        final Member spouseMember =
                new Member.Builder("family3", "spouseSibling1", LocalDate.of(1995, 1, 1), "agadir",__FAMILY3__)
                        .build();

        final Member sibling2 =
                new Member.Builder("family1", "sibling2", LocalDate.of(1992, 1, 1), "agadir",__FAMILY__)
                        .father(father)
                        .mother(mother)
                        .build();
        final Member childSibling1 =
                new Member.Builder("family1", "childSibling1", LocalDate.of(2010, 1, 1), "agadir",__FAMILY__)
                        .father(sibling1)
                        .mother(spouseSibling1)
                        .build();
        final Member childMember =
                new Member.Builder("family1", "childMember", LocalDate.of(2010, 1, 1), "agadir",__FAMILY__)
                        .father(memberSpecified)
                        .mother(spouseMember)
                        .build();
        this.memberService.createMember(father);
        this.memberService.createMember(mother);
        this.memberService.createMember(memberSpecified);
        this.memberService.createMember(spouseSibling1);
        this.memberService.createMember(sibling1);
        this.memberService.createMember(spouseMember);
        this.memberService.createMember(sibling2);
        this.memberService.createMember(childSibling1);
        this.memberService.createMember(childMember);

        // ChildSibling1 should not be part of the close members.

        // when
        Multimap<CloseMemberType, Member> closeMembersResult = this.memberService.getCloseMembers(memberSpecified);
        long sizeCloseMemberResult = Arrays.stream(CloseMemberType.values())
                                            .map(type -> closeMembersResult.get(type).size())
                                            .reduce(0, (f,l)-> f+l);

        // then
        assertAll(
                () -> assertFalse(closeMembersResult.get(CloseMemberType.CHILD).contains(childSibling1)),
                () -> assertEquals(this.memberService.getAllMembers().size() - 4, sizeCloseMemberResult)
        );
    }

    @Test
    public void testCreateMemberWithCityValidation(){
        // given
        final Family __FAMILY__ = new Family("family1");
        final String __VALID_CITY__ = "agadir";
        final String __VALID_CITY_UPPER_LOWER_CASE__ = "aGaDIr";
        final String __NOT_VALID_CITY__ = "notValid";
        final String __NOT_VALID_DEATH_CITY__ = "notValid";
        final Member memberWithValidCity =
                new Member.Builder("family1", "firstName", LocalDate.of(1978, 1, 1), __VALID_CITY__,__FAMILY__)
                        .build();
        final Member memberWithValidCityUpperLowerCase =
                new Member.Builder("family11", "firstName", LocalDate.of(1978, 1, 1), __VALID_CITY_UPPER_LOWER_CASE__,__FAMILY__)
                        .build();
        final Member memberWithNotValidCity =
                new Member.Builder("family1", "firstName", LocalDate.of(1978, 1, 1), __NOT_VALID_CITY__,__FAMILY__)
                        .build();
        final Member memberWithNotValidDeathCity =
                new Member.Builder("family1", "firstName", LocalDate.of(1978, 1, 1), __VALID_CITY__,__FAMILY__)
                        .deathPlace(__NOT_VALID_DEATH_CITY__)
                        .build();

        // when
        this.memberService.createMember(memberWithValidCity);
        this.memberService.createMember(memberWithValidCityUpperLowerCase);


        // then
        assertAll(
                () -> assertTrue(this.memberService.getAllMembers().contains(memberWithValidCity)),
                () -> assertTrue(this.memberService.getAllMembers().contains(memberWithValidCityUpperLowerCase)),
                () -> assertThrows(IllegalArgumentException.class, () -> this.memberService.createMember(memberWithNotValidCity)),
                () -> assertThrows(IllegalArgumentException.class, () -> this.memberService.createMember(memberWithNotValidDeathCity))
        );
    }

    @Test
    public void testCreatMemberWithCityAndCountryValidation() {
        //given
        final Family __FAMILY__ = new Family("family1");
        final String __VALID_CITY_AND_COUNTRY__ = "agadir, morocco";
        final String __VALID_CITY_AND_COUNTRY__NOT_WELL_FORMED__ = "aGaDIr ,MoRocco";
        final String __NOT_VALID_CITY_AND_VALID_COUNTRY = "notvalid, Morocco";
        final String __VALID_CITY_AND_NOT_VALID_COUNTRY__ = "Agadir, notValid";
        final Member memberWithValidCityAndCoutry =
                new Member.Builder("family1", "firstName", LocalDate.of(1978, 1, 1), __VALID_CITY_AND_COUNTRY__,__FAMILY__)
                        .build();
        final Member memberWithValidCityAndCountryNotWellFormed =
                new Member.Builder("family11", "firstName", LocalDate.of(1978, 1, 1), __VALID_CITY_AND_COUNTRY__NOT_WELL_FORMED__,__FAMILY__)
                        .build();
        final Member memberWithNotValidCityAndValidCountry =
                new Member.Builder("family1", "firstName", LocalDate.of(1978, 1, 1), __VALID_CITY_AND_COUNTRY__,__FAMILY__)
                        .deathPlace(__NOT_VALID_CITY_AND_VALID_COUNTRY)
                        .build();
        final Member memberWithValidCityAndNotValidCountry =
                new Member.Builder("family1", "firstName", LocalDate.of(1978, 1, 1), __VALID_CITY_AND_NOT_VALID_COUNTRY__,__FAMILY__)
                        .deathPlace(__VALID_CITY_AND_COUNTRY__)
                        .build();

        // when
        this.memberService.createMember(memberWithValidCityAndCoutry);
        this.memberService.createMember(memberWithValidCityAndCountryNotWellFormed);

        // then
        assertAll(
                () -> this.memberService.getAllMembers().contains(memberWithValidCityAndCoutry),
                () -> this.memberService.getAllMembers().contains(memberWithValidCityAndCountryNotWellFormed),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> this.memberService.createMember(memberWithNotValidCityAndValidCountry)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> this.memberService.createMember(memberWithValidCityAndNotValidCountry))
        );

    }
}

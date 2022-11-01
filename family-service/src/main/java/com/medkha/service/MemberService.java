package com.medkha.service;

import com.google.common.collect.Multimap;
import com.medkha.entity.CloseMemberType;
import com.medkha.entity.Family;
import com.medkha.entity.Member;

import java.util.Optional;
import java.util.Set;

public interface MemberService {
    public Set<Member> getMembersByFamily(Family family);
    public Set<Member> getAllMembers();
    public void createMember(Member member);
    public Optional<Member> getMemberByUser(String idUser);
    public Multimap<CloseMemberType, Member> getCloseMembers(Member member);

    /**
     * Only used for test, this is a problematic method. should fix this.
     */
    public void clearMembers();
}

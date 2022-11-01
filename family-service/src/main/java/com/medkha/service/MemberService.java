package com.medkha.service;

import com.medkha.entity.Family;
import com.medkha.entity.Member;

import java.util.Optional;
import java.util.Set;

public interface MemberService {
    public Set<Member> getMembersByFamily(Family family);
    public Set<Member> getAllMembers();
    public void createMember(Member member);
    public Optional<Member> getMemberByUser(String idUser);
    public Set<Member> getCloseMembers(Member member);
}

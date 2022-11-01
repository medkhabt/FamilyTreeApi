package com.medkha.service.impl;

import com.medkha.entity.Family;
import com.medkha.entity.Member;
import com.medkha.service.MemberService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MemberServiceNaive implements MemberService {
    private static MemberService instance = null;
    private Set<Member> members = new HashSet<>();
    private MemberServiceNaive(){

    }

    @Override
    public Set<Member> getMembersByFamily(Family family) {
        return getAllMembers().stream().filter(m -> m.getFamily().equals(family)).collect(Collectors.toSet());
    }

    @Override
    public Set<Member> getAllMembers() {
        return new HashSet<>(members);
    }

    @Override
    public void createMember(Member member) {
        if(member.getUserId().isPresent()) {
            this.members.stream().filter(m -> m.getUserId().equals(member.getUserId())).findAny().ifPresent(
                    (m) -> {throw new IllegalArgumentException("Failed to create a new Member, There is already a member with " +
                            "the user id you specified"); }
            );
        }
        this.members.add(member);
    }

    @Override
    public Optional<Member> getMemberByUser(String idUser) {
        return this.members.stream().filter(m -> m.getUserId().equals(Optional.of(idUser))).findFirst();
    }

    @Override
    public Set<Member> getCloseMembers(Member member) {
        return null;
    }

    public static MemberService getInstance(){
        if(instance == null) {
            instance = new MemberServiceNaive();
        }
        return instance;
    }
}

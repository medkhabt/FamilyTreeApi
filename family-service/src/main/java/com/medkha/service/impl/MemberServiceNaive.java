package com.medkha.service.impl;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.medkha.entity.CloseMemberType;
import com.medkha.entity.Family;
import com.medkha.entity.Member;
import com.medkha.service.LocationService;
import com.medkha.service.LocationServices;
import com.medkha.service.MemberService;

import java.util.*;
import java.util.stream.Collectors;

public class MemberServiceNaive implements MemberService {
    private static MemberService instance = null;
    private Set<Member> members = new HashSet<>();
    private LocationService locationService;
    private MemberServiceNaive(){
       locationService = LocationServices.getLocationService();
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
        //TODO for all the locations not just the birthplace. (also for deathplace , and look if there is other locations.)
        if(!this.locationService.isValidCity(member.getBirthPlace())) {
            throw new IllegalArgumentException("member with invalid city " + member.getBirthPlace());
        }
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

    /**
     * get a set of members which are close to the specified member, which are the person's parent, siblings,
     * (not yet for 'spouses') and children.
     * @param member the member that we're looking for his/her close family members.
     * @return a Map of close family members.
     */
    @Override
    public Multimap<CloseMemberType, Member> getCloseMembers(Member member) {
        Multimap<CloseMemberType, Member> closeMembersMultiMap = HashMultimap.create();
        // get parents
        member.getFather().ifPresent(f -> closeMembersMultiMap.put(CloseMemberType.FATHER, f));
        member.getMother().ifPresent(m -> closeMembersMultiMap.put(CloseMemberType.MOTHER, m));
        // get children
        getChildren(member).forEach(c -> closeMembersMultiMap.put(CloseMemberType.CHILD, c));
        // get siblings
        if( !(closeMembersMultiMap.get(CloseMemberType.FATHER).isEmpty())
            &&
            !(closeMembersMultiMap.get(CloseMemberType.MOTHER).isEmpty())) {
//            Set<Member> setResult = getChildren(member.getFather().get());
            getChildren(member.getFather().get()).stream()
                    .filter(m -> !(m.equals(member)))
                    .forEach(
                        childOfFather -> {
                            if(childOfFather.getMother().equals(member.getMother())) {
                                closeMembersMultiMap.put(CloseMemberType.SIBLING, childOfFather);
                            }
                        }
                    );
        }
        // get spouses

        return closeMembersMultiMap;
    }

    private Set<Member> getChildren(Member member) {
        return getAllMembers().stream()
                .filter(m ->
                        m.getFather().equals(Optional.ofNullable(member))
                        ||
                        m.getMother().equals(Optional.ofNullable(member))
                )
                .collect(Collectors.toSet());
    }

    public static MemberService getInstance(){
        if(instance == null) {
            instance = new MemberServiceNaive();
        }
        return instance;
    }

    @Override
    public void clearMembers() {
        this.members = new HashSet<>();
    }
}

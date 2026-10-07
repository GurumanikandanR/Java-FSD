package com.librarymanagement.model;

import com.librarymanagement.enums.MemberShipType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String email;
    private MemberShipType memberShipType;

    @CreationTimestamp
    private Instant joinedAt;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", memberShipType=" + memberShipType +
                ", joinedAt=" + joinedAt +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MemberShipType getMemberShipType() {
        return memberShipType;
    }

    public void setMemberShipType(MemberShipType memberShipType) {
        this.memberShipType = memberShipType;
    }

    public Instant getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Instant joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Member() {
    }

    public Member(Long id, String name, String email, MemberShipType memberShipType, Instant joinedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.memberShipType = memberShipType;
        this.joinedAt = joinedAt;
    }
}
//id Long (auto-generated)
//name String
//email String (unique)
//membershipType enum MembershipType → STANDARD, PREMIUM
//joinedAt Instant (use @CreationTimestamp)
package com.librarymanagement.service;

import com.librarymanagement.exception.ResourceNotFoundException;
import com.librarymanagement.model.Book;
import com.librarymanagement.model.Member;
import com.librarymanagement.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getById(Long id) {

        Optional<Member> optional =memberRepository.getById(id);
        if(optional.isEmpty()){
            throw new ResourceNotFoundException("Invalid Id");
        }
        return optional.get();
    }
}

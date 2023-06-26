package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    //@Autowired
    private final MemberRepository memberRepository;

    public Long join (Member member){
        validateDuplicate(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicate(Member member) {
        List<Member> findMembers =  memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미존재하는 회원입니다");
        }

    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne (Long memberId){
        return memberRepository.findOne(memberId);
    }
}

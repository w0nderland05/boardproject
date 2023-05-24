package org.boardpj.repositories;

import org.boardpj.entities.Member;
import org.boardpj.entities.QMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member,Long>, QuerydslPredicateExecutor {

     Member findByUserId(String userId);//아이디로 회원조회

    default  boolean exists(String userId){ //아이디로 회원 조회
        QMember member= QMember.member;
        return exists(member.userId.eq(userId)); //여러개일경우 BooleanBuilder

    }
}

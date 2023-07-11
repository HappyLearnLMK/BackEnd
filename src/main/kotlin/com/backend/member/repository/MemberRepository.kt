package com.backend.member.repository

import com.backend.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
    fun deleteByMemberCode(memberCode:String):Int
    fun findFirstByOrderByMemberCodeDesc():Member
    fun findByMemberCode(memberCode: String): Member
}
package com.backend.member.service

import com.backend.member.domain.Member
import com.backend.member.dto.request.MemberSaveRequest
import com.backend.member.dto.request.MemberUpdateRequest
import com.backend.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    @Transactional
    fun saveMember(dto: MemberSaveRequest) {
        memberRepository.save(
                        Member(dto.memberName,
                            dto.birthDate,
                            dto.mobile,
                            dto.signUpDate,
                            dto.gender,
                            dto.type,
                            dto.memberId,
                            encryptPassword(dto.password),
                            createNewMemberCode())
        )
    }

    private fun createNewMemberCode(): String {
        val lastMember = memberRepository.findFirstByOrderByMemberCodeDesc()
        val lastMemberCode = lastMember.memberCode
        val firstLetter = lastMemberCode!!.substring(0, 1)
        val fromSecondLetter = lastMemberCode!!.substring(1, lastMemberCode.length).toInt()+1
        return  firstLetter+fromSecondLetter
    }

    @Transactional
    fun getMembers(): MutableList<Member> {
        return memberRepository.findAll()
    }
    @Transactional
    fun updateMember(request: MemberUpdateRequest) {
        val member = memberRepository.findByMemberCode(request.memberCode)
        member.memberName = request.memberName
        member.mobile = request.mobile
        member.gender = request.gender
        member.type = request.type
        println(passwordCheck(request))
        memberRepository.save(member)
    }
    @Transactional
    fun deleteMember(memberCode: String) {
        memberRepository.deleteByMemberCode(memberCode)
    }

    private fun encryptPassword(password:String):String{
        val btPassword = password.toByteArray()
        val encoder: Base64.Encoder = Base64.getEncoder()
        return String(encoder.encode(btPassword))
    }

    private fun passwordCheck(request: MemberUpdateRequest):Boolean{
        val member = memberRepository.findByMemberCode(request.memberCode)
        val enteredPassword = request.password
        val decrypted = decryptPassword(member.password)
        println("!!!!!!!$decrypted")

        println("@@@@@@@@@@@@@@@@@@@@@@@")
        println("!!!!!!!$enteredPassword")

        return decrypted == enteredPassword
    }

    private fun decryptPassword(password: String): String {
        val decoder: Base64.Decoder = Base64.getDecoder()
        return String(decoder.decode(password))
    }
}
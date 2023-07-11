package com.backend.member.controller

import com.backend.member.domain.Member
import com.backend.member.dto.request.MemberSaveRequest
import com.backend.member.dto.request.MemberUpdateRequest
import com.backend.member.service.MemberService
import com.backend.util.ResponseDto
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
class MemberController(
    private val memberService: MemberService,
    ) {
    @PostMapping("/member")
    fun saveMember(@RequestBody @Valid request: MemberSaveRequest, bindingResult: BindingResult):ResponseEntity<*> {
        memberService.saveMember(request)
        return ResponseEntity(ResponseDto(1, "멤버 등록 완료", null), HttpStatus.OK)
    }
    @GetMapping("/member")
    fun getMembers(): MutableList<Member> {
        return memberService.getMembers()
    }
    @PutMapping("/member")
    fun updateMember(@RequestBody request: MemberUpdateRequest):ResponseEntity<*> {
        memberService.updateMember(request)
        return ResponseEntity(ResponseDto(1, "멤버 수정 완료", null), HttpStatus.OK)
    }
    @DeleteMapping("/member")
    fun deleteMember(@RequestParam memberCode: String) {
        memberService.deleteMember(memberCode)
    }
}
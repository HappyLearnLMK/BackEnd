package com.backend.member.dto.request

import com.backend.member.domain.MemberType

class MemberUpdateRequest(
    var memberCode: String,
    var memberName:String,
    var mobile: String,
    var gender: Char,
    var type: MemberType,
    var password: String
)
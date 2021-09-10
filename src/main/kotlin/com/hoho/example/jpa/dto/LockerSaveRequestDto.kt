package com.hoho.example.jpa.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class LockerSaveRequestDto (
    @field:NotNull
    var id: Long,

    @field:NotBlank
    @field:Size(max = 10, message = "size error")
    var name: String
)
package com.todo.todo.models

import org.springframework.http.HttpStatus
import java.util.*

class ErrorResponse {

    var timestamp: Date? = null
    var code = 0
    var status: String = ""
    var message: String? = ""

    constructor() {
        timestamp = Date()
    }

    constructor(httpStatus: HttpStatus, message: String?) {
        timestamp = Date()
        code = httpStatus.value()
        status = httpStatus.name
        this.message = message
    }
}
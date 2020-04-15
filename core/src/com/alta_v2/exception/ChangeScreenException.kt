package com.alta_v2.exception

class ChangeScreenException : RuntimeException {

    constructor(message: String) : super(message)
    constructor(t: Throwable) : super(t)

}
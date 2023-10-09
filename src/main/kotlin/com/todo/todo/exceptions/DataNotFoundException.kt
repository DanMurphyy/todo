package com.todo.todo.exceptions

class DataNotFoundException : Exception {
    constructor() : super() {}
    constructor(message: String) : super(message)
}
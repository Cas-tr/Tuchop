package com.example.tuchop.models

class Teacher {
    var name:String = ""
    var email:String = ""
    var phoneNumber :String = ""
    var levelOfEducation = ""
    var school :String = ""
    var subject :String = ""
    var teacherId:String = ""

    constructor(name: String, email: String, phoneNumber: String, levelOfEducation: String,school:String,subject :String, teacherId: String
    ) {
        this.name = name
        this.email = email
        this.phoneNumber = phoneNumber
        this.levelOfEducation = levelOfEducation
        this.school = school
        this.subject = subject
        this.teacherId = teacherId
    }
    constructor()
}
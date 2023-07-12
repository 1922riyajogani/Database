package com.example.database.Modelclass

class StudentModel {

    var id:Int = 0
    lateinit var  name:String
    lateinit var  surname:String

    constructor(id: Int,name: String,surname:String) {
        this.id =id
        this.name = name
        this.surname = surname
    }
}
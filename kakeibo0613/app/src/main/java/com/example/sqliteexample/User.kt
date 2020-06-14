package com.example.sqliteexample

class User {

    var id : Int = 0
    var message : String = ""
    var price : Int = 0

    var date : String = ""

    constructor(message:String,price:Int,date:String){
        this.message = message
        this.price = price

        this.date = date
    }

    constructor(){
    }

}
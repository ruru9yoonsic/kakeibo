package com.example.sqliteexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new.*

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val context = this
        var db = DataBaseHandler(context)

        btn_back.setOnClickListener {
            val intent =Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


        btn_read.setOnClickListener {
            var data = db.readData()
            tvResult.text = ""
            for (i in 0..(data.size - 1)){
                tvResult.append(data.get(i).id.toString() + " " + data.get(i).message + " "+ data.get(i).price + " " + data.get(i).date + "\n")
            }
        }

        btn_delete.setOnClickListener {
//            db.deleteData()
            db.deleteAllData()
            btn_read.performClick()
        }


    }
}
package com.example.sqliteexample

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        var db = DataBaseHandler(context)

        btn_insert.setOnClickListener {
            if (etvName.text.toString().length > 0 &&
                    etvAge.text.toString().length >0 &&
                        tvDate.text.toString().length >0){
                var user = User(etvName.text.toString(),etvAge.text.toString().toInt(),tvDate.text.toString())
                var db = DataBaseHandler(context)
                db.insertData(user)

            }else{
                Toast.makeText(context,"Please Fill All Data", Toast.LENGTH_SHORT).show()
            }
        }

        btn_list.setOnClickListener {
            val intent = Intent(this,NewActivity::class.java)
            startActivity(intent)
        }

        //日付入力
        btn_date.setOnClickListener {
            var formate = SimpleDateFormat("dd MMM, YYYY", Locale.US)
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR,year)
                selectedDate.set(Calendar.MONTH,month)
                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val date = formate.format(selectedDate.time)

                    tvDate.text = "$date"
            }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }


    }
}
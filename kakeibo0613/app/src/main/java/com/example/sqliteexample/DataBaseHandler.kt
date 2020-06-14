package com.example.sqliteexample

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "Users"
val COL_MESSAGE = "message"
val COL_PRICE = "price"
val COL_ID = "id"

val COL_DATE = "date"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_MESSAGE + " VARCHAR(256)," +
                COL_PRICE + " INTEGER," +
                COL_DATE + " VARCHAR(256)";

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(user: User){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_MESSAGE,user.message)
        cv.put(COL_PRICE,user.price)

        cv.put(COL_DATE,user.date)

        var result = db.insert(TABLE_NAME,null,cv)
/*        if (result == -1.toLong())
            Toast.makeText(context,"Faild",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()

 */
    }

    fun readData() : MutableList<User>{
        var list : MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()){
            do{
                var user = User()
                user.id =result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.message =result.getString(result.getColumnIndex(COL_MESSAGE))
                user.price =result.getString(result.getColumnIndex(COL_PRICE)).toInt()

                user.date =result.getString(result.getColumnIndex(COL_DATE))

                list.add(user)
            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }


    fun deleteData(){
        val db = this.writableDatabase

        db.delete(TABLE_NAME, COL_ID+"=?", arrayOf(1.toString()))
        db.close()
    }

    fun deleteAllData(){
        val db = this.writableDatabase

        db.delete(TABLE_NAME, null, null)
        db.close()
    }
}
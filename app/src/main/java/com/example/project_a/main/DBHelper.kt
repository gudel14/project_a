package com.example.project_a.main

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Pompa.db", null, 1) {
    companion object{
        private var instance: DBHelper? = null
        @Synchronized
        fun getInstance(ctx: Context) : DBHelper{

            if (instance==null){
                instance = DBHelper(ctx.applicationContext)
            }
            return instance as DBHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Pompa.Tabel_Pompa, true,
            Pompa.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Pompa.Pompa to TEXT,
            Pompa.Shift to TEXT,
            Pompa.Status to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.dropTable(Pompa.Tabel_Pompa, true)
    }
}

val Context.database : DBHelper
    get() = DBHelper.getInstance(applicationContext)
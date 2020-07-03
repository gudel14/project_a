package com.example.project_a.pompa

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper (ctx:Context):ManagedSQLiteOpenHelper(ctx,"Pompa.db, null, 1") {

    companion object{
        private var insatance: DBHelper?=null
        @Synchronized
        fun getInstance(ctx: Context): DBHelper{
            if (insatance == null){
                insatance = DBHelper(ctx.applicationContext)
            }
            return insatance as DBHelper
        }

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.createTable(Pompa.Tabel_Pompa, true,
            Pompa.ID to INTEGER+ PRIMARY_KEY+ AUTOINCREMENT,
        Pompa.POMPA to TEXT,
        Pompa.SHIFT to TEXT,
        Pompa.STATUS to TEXT)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.dropTable(Pompa.Tabel_Pompa, true)
    }
}
val Context.database : DBHelper
    get() = DBHelper.getInstance(applicationContext)
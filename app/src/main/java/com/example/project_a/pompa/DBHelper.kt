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
            Pompa.STATUS to TEXT,
            Pompa.RPM to TEXT,
            Pompa.HM to TEXT,
            Pompa.FUEL to TEXT,
            Pompa.ENGINE to TEXT,
            Pompa.PREASURE to TEXT,
            Pompa.DEBIT to TEXT,
            Pompa.ELEVASI to TEXT,
            Pompa.TANGGAL to TEXT
        )

        p0?.createTable(Tower.Tabel_Tower, true,
            Tower.ID to INTEGER+ PRIMARY_KEY+ AUTOINCREMENT,
            Tower.LAMP to TEXT,
            Tower.SHIFT to TEXT,
            Tower.STATUS to TEXT,
            Tower.TANGGAL to TEXT,
            Tower.HM to TEXT,
            Tower.FUEL to TEXT
        )

        p0?.createTable(Cek.Tabel_Cek, true,
            Cek.ID to INTEGER+ PRIMARY_KEY+ AUTOINCREMENT,
            Cek.POMPA to TEXT,
            Cek.SHIFT to TEXT,
            Cek.TANGGAL to TEXT,
            Cek.HM to TEXT,
            Cek.NAMA to TEXT,
            Cek.SPINER1  to TEXT,
            Cek.SPINER2  to TEXT,
            Cek.SPINER3  to TEXT,
            Cek.SPINER4  to TEXT,
            Cek.SPINER5  to TEXT,
            Cek.SPINER6  to TEXT,
            Cek.SPINER7  to TEXT,
            Cek.SPINER8  to TEXT,
            Cek.SPINER9  to TEXT,
            Cek.SPINER10 to TEXT,
            Cek.SPINER11 to TEXT,
            Cek.SPINER12 to TEXT,
            Cek.SPINER13 to TEXT,
            Cek.SPINER14 to TEXT,
            Cek.SPINER15 to TEXT,
            Cek.SPINER16 to TEXT,
            Cek.SPINER17 to TEXT,
            Cek.SPINER18 to TEXT,
            Cek.SPINER19 to TEXT,
            Cek.SPINER20 to TEXT,
            Cek.SPINER21 to TEXT,
            Cek.SPINER22 to TEXT,
            Cek.SPINER23 to TEXT,
            Cek.SPINER24 to TEXT,
            Cek.SPINER25 to TEXT,
            Cek.SPINER26 to TEXT,
            Cek.SPINER27 to TEXT,
            Cek.SPINER28 to TEXT,
            Cek.SPINER29 to TEXT,
            Cek.SPINER30 to TEXT,
            Cek.SPINER31 to TEXT,
            Cek.SPINER32 to TEXT

        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.dropTable(Pompa.Tabel_Pompa, true)
        p0?.dropTable(Tower.Tabel_Tower, true)
        p0?.dropTable(Cek.Tabel_Cek, true)

    }
}
val Context.database : DBHelper
    get() = DBHelper.getInstance(applicationContext)
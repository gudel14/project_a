package com.example.project_a.main

data class Pompa (var id:Long?, var pompa:String, var shift:String, var status:String) {
    companion object {
        const val Tabel_Pompa: String = "Tabel_Pompa"
        const val ID: String = "ID_"
        const val Pompa: String = "Pompa"
        const val Shift: String = "Shift"
        const val Status: String = "Status"
    }
}
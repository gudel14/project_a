package com.example.project_a.pompa

data class Pompa (var id:Long?, var pompa:String?, var shift:String?, var status:String? ) {
    companion object {
        const val Tabel_Pompa:String ="Tabel_Pompa"
        const val ID:String ="ID"
        const val POMPA:String ="Pompa"
        const val SHIFT:String ="Shift"
        const val STATUS:String ="Status"
    }
}
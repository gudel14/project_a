package com.example.project_a.pompa

data class Pompa (
    var id:Long?=null,
    var pompa:String?=null,
    var shift:String?=null,
    var status:String?=null,
    var hm:String?=null

) {
    companion object {
        const val Tabel_Pompa:String ="Tabel_Pompa"
        const val ID:String ="ID"
        const val POMPA:String ="Pompa"
        const val SHIFT:String ="Shift"
        const val STATUS:String ="Status"
        const val HM:String ="Hm"
    }





}
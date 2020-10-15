package com.example.project_a.pompa

data class Pompa (
    var id:Long?=null,
    var pompa:String?=null,
    var shift:String?=null,
    var status:String?=null,
    var rpm:String?=null,
    var hm:String?=null,
    var fuel:String?=null,
    var engine:String?=null,
    var preasure:String?=null,
    var debit:String?=null,
    var elevasi:String?=null,
    var tanggal:String?=null

) {
    companion object {
        const val Tabel_Pompa:String ="Tabel_Pompa"
        const val ID:String ="ID"
        const val POMPA:String ="Pompa"
        const val SHIFT:String ="Shift"
        const val STATUS:String ="Status"
        const val RPM:String ="Rpm"
        const val HM:String ="Hm"
        const val FUEL:String ="Fuel"
        const val ENGINE:String ="Engine"
        const val PREASURE:String ="Preasure"
        const val DEBIT:String ="Debit"
        const val ELEVASI:String ="Elevasi"
        const val TANGGAL:String ="Tanggal"
    }
}
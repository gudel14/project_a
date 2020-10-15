package com.example.project_a.pompa

data class Tower (
    var id:Long?=null,
    var lamp:String?=null,
    var shift:String?=null,
    var status:String?=null,
    var tanggal:String?=null,
    var hm:String?=null,
    var fuel:String?=null

) {
    companion object {
        const val Tabel_Tower:String ="Tabel_Tower"
        const val ID:String ="ID"
        const val LAMP:String ="Lamp"
        const val SHIFT:String ="Shift"
        const val STATUS:String ="Status"
        const val TANGGAL:String ="Tanggal"
        const val HM:String ="Hm"
        const val FUEL:String ="Fuel"
    }
}
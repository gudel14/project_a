package com.example.project_a.pompa

data class Cek (
    var id:Long?=null,
    var pompa:String?=null,
    var shift:String?=null,
    var tanggal:String?=null,
    var hm:String?=null,
    var nama:String?=null

) {
    companion object {
        const val Tabel_Cek:String ="Tabel_Cek"
        const val ID:String ="ID"
        const val POMPA:String ="Pompa"
        const val SHIFT:String ="Shift"
        const val TANGGAL:String ="Tanggal"
        const val HM:String ="Hm"
        const val NAMA:String ="Nama"

    }
}

package com.example.lomboktravel
object Kendaraan{
        private val nama = arrayOf(
                "Jazz",
                "Alphard"

        )
        private val harga = arrayOf(
                "599.000",
                "1.000.000"
        )
        private val kapasitas = arrayOf(
                "6",
                '4'
        )
        private val extra = arrayOf(
                "120.000",
                "250.000"
        )
        private val gambar = intArrayOf(
                R.drawable.latar,
                R.drawable.latarr
        )
        val listData: ArrayList<Travel>

                get() {
                        val list = arrayListOf<Travel>()
                        for (position in nama.indices) {
                                val travel = Travel()
                                travel.nama = nama[position]
                                travel.harga = harga[position]
                                travel.extra = extra[position]
                                travel.gambar = gambar[position]

                                list.add(travel)
                        }
                        return list
                }

}
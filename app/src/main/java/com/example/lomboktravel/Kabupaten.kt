package com.example.lomboktravel

object Kabupaten {
    private val nama = arrayOf(
        "Pantai Kute", "Pantai Seger",
        "Air Terjun Benang kelambu"
    )
    private val lokasi = arrayOf(
        "Kute, Lombok Tengah","Kute, Lombok Tengah",
        "Mantang, Lombok Tengah"
    )
    private val desc = arrayOf(
        "Pantai Kute adalah pantai yang indah",
        "Pantai Seger adalah pantai yang indah",
        "Air Terjun Benang Kelambu memiliki air yang sangat bersih"
    )
    private val gambar = intArrayOf(
        R.drawable.batukliang,
        R.drawable.kute,
        R.drawable.mawun
    )
    val listData: ArrayList<Wisata>

        get() {
            val list = arrayListOf<Wisata>()
            for (position in Kabupaten.nama.indices) {
                val wisata = Wisata()
                wisata.nama = Kabupaten.nama[position]
                wisata.desc = Kabupaten.desc[position]
                wisata.lokasi = Kabupaten.lokasi[position]
                wisata.gambar = Kabupaten.gambar[position]


                list.add(wisata)
            }
            return list
        }
}
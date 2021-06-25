package com.example.lomboktravel.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lomboktravel.Kendaraan
import com.example.lomboktravel.R

class CheckOutFragment : Fragment() {

    private lateinit var datalist : ArrayList<Kendaraan>

    val EXTRA_NAME : ArrayList<Kendaraan>? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_out, container, false)
    }


}
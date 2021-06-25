package com.example.lomboktravel.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lomboktravel.R
import kotlinx.android.synthetic.main.fragment_wisata.*

class WisataFragment : Fragment() {

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wisata, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            val name = arguments?.getString(EXTRA_NAME)
            tvWIsataClicked.text = name
        }
    }
}
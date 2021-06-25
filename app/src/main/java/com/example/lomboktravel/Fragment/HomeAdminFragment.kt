package com.example.lomboktravel.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lomboktravel.R
import kotlinx.android.synthetic.main.fragment_home_admin.*

class HomeAdminFragment : Fragment(), View.OnClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLotim.setOnClickListener(this)
        btnLobar.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val lotimWstFragment = LotimWstFragment()
        val lotengWstFragment = LotengWstFragment()
        val lobarWstFragment = LobarWstFragment()
        val lobutWstFragment = LobutWstFragment()

        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragmentContainer, lotimWstFragment, LotimWstFragment::class.java.simpleName)
                .addToBackStack(null)
                .commit()
        }

        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragmentContainer, lotengWstFragment, LotengWstFragment::class.java.simpleName)
                .addToBackStack(null)
                .commit()
        }

        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragmentContainer, lobarWstFragment, LobarWstFragment::class.java.simpleName)
                .addToBackStack(null)
                .commit()
        }

        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragmentContainer, lobutWstFragment, LobutWstFragment::class.java.simpleName)
                .addToBackStack(null)
                .commit()
        }
    }


}
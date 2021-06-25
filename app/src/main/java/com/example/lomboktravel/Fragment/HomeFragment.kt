package com.example.lomboktravel.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.lomboktravel.AdminActivity
import com.example.lomboktravel.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvbaru.setOnClickListener {
            startActivity(Intent(requireContext(),AdminActivity::class.java))
        }



      btnLotim.setOnClickListener {
          val listFragment = ListFragment()
          val lotimWstFragment = LotimWstFragment()
          fragmentManager?.beginTransaction()?.apply {
              replace(R.id.nav_host_fragment,lotimWstFragment, LotimWstFragment::class.java.simpleName)
                  .addToBackStack(null)
                  .commit()
          }
      }
      btnLobar.setOnClickListener {
          val lobarWstFragment = LobarWstFragment()
          fragmentManager?.beginTransaction()?.apply {
              replace(R.id.nav_host_fragment,lobarWstFragment, LobarWstFragment::class.java.simpleName)
                  .addToBackStack(null)
                  .commit()
          }

      }

      btnLobut.setOnClickListener {
          val lobutWstFragment = LobutWstFragment()
          fragmentManager?.beginTransaction()?.apply {
              replace(R.id.nav_host_fragment,lobutWstFragment, LobutWstFragment::class.java.simpleName)
                  .addToBackStack(null)
                  .commit()
          }

      }

      btnLoteng.setOnClickListener {
          val lotengWstFragment = LotengWstFragment()
          fragmentManager?.beginTransaction()?.apply {
              replace(R.id.nav_host_fragment,lotengWstFragment, LotengWstFragment::class.java.simpleName)
                  .addToBackStack(null)
                  .commit()
          }

      }
    }
}
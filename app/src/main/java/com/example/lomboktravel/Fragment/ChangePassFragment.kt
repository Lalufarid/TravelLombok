package com.example.lomboktravel.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lomboktravel.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class ChangePassFragment : Fragment(), View.OnClickListener {
    private lateinit var passLama: EditText
    private lateinit var passBaru: EditText
    private lateinit var konfirmasi: EditText
    private lateinit var reset: Button
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_pass, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        passLama = view.findViewById(R.id.etPasswordLama)
        passBaru = view.findViewById(R.id.etPasswordBaru)
        konfirmasi = view.findViewById(R.id.konfirmasi)
        reset = view.findViewById(R.id.resetPass)
        reset.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        val profileFragment = ProfileFragment()
        if (passLama.text.isNotEmpty() && passBaru.text.isNotEmpty() && konfirmasi.text.isNotEmpty()) {
            if (passBaru.text.toString().equals(konfirmasi.text.toString())) {
                val user = auth.currentUser
                if (user != null && user.email != null) {
                    val credential = EmailAuthProvider.getCredential(user.email!!, passLama.text.toString())
                    user?.reauthenticate(credential)
                        ?.addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(activity, "Re-Authentication success.", Toast.LENGTH_SHORT
                                ).show()
                                user?.updatePassword(passBaru.text.toString())
                                    ?.addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(activity, "Password changed successfully.", Toast.LENGTH_SHORT).show()
                                            fragmentManager?.beginTransaction()?.apply {
                                                replace(R.id.nav_host_fragment,profileFragment,ProfileFragment::class.java.simpleName)
                                                    .addToBackStack(null)
                                                    .commit()
                                            }

                                        }
                                    }

                            } else {
                                Toast.makeText(activity, "Re-Authentication failed.", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    fragmentManager?.beginTransaction()?.apply {
                        replace(R.id.nav_host_fragment,profileFragment,ProfileFragment::class.java.simpleName)
                            .addToBackStack(null)
                            .commit()
                }
            }
        }
    }

}}
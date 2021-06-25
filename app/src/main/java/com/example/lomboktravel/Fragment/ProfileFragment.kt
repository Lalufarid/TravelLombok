package com.example.lomboktravel.Fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.lomboktravel.CheckOutActivity
import com.example.lomboktravel.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.ByteArrayOutputStream
import java.nio.file.Files.find


@Suppress("DEPRECATION")
class ProfileFragment : Fragment() {

    companion object{
        const val REQUEST_CAMERA = 100
    }
    private lateinit var imageUri : Uri
    private lateinit var auth:FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if (user != null){
            if (user.photoUrl != null){
                Picasso.get().load(user.photoUrl).into(id_profile)
            }else{
                Picasso.get().load("https://picsum.photos/id/1/200/300")
            }
            ed_name.setText(user.displayName)
            ed_mail.setText(user.email)

            if (user.phoneNumber.isNullOrEmpty()){
                ed_phone.setText("masukkan nomor")
            }else{
                ed_phone.setText(user.phoneNumber)
            }

        }

        id_profile.setOnClickListener {
            intentCamera()
        }
        btnUpdate.setOnClickListener {
            val image = when {
                ::imageUri.isInitialized->imageUri
                user?.photoUrl== null -> Uri.parse("https://picsum.photos/id/1/200/300")
                else->user.photoUrl
            }
            val name = ed_name.text.toString().trim()
            if (name.isEmpty()){
                ed_name.error = "Nama Harus diisi"
                ed_name.requestFocus()
                return@setOnClickListener
            }
            val bundle = Bundle()


            UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(image)
                .build().also {
                    user?.updateProfile(it)?.addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(activity,"Profil Updated", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(activity,"${it.exception?.message}",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }

        ed_mail.setOnClickListener {
            val actionUpdateEmail = ProfileFragmentDirections.actionUpdatEmail()
            Navigation.findNavController(it).navigate(actionUpdateEmail)
        }
        changePasword.setOnClickListener {
            val changePassFragment = ChangePassFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.nav_host_fragment,changePassFragment,ChangePassFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun intentCamera() {
//        val intent = Intent()
//        intent.type = "/images"
//        intent.action = Intent.ACTION_GET_CONTENT
//        startActivityForResult(intent,100)
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {intent ->
            activity?.packageManager?.let {
                intent.resolveActivity(it).also {
                    startActivityForResult(intent, 100)
                }
            }
//            activity?.packageManager?.let {
//                intent.resolveActivity(it).also {
//                    startActivityForResult(intent,
//                        REQUEST_CAMERA
//                    )
                }
            }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == 100 && resultCode== RESULT_OK){
//
//            imageUri = data?.data!!
//
//        }

        if (requestCode == REQUEST_CAMERA && requestCode == RESULT_OK){
            val imgBitmap = data?.extras?.get("data") as Bitmap
            uploadImage(imgBitmap)
        }

    }

    private fun uploadImage(imgBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val ref = FirebaseStorage.getInstance().reference.child("img/${FirebaseAuth.getInstance().currentUser?.uid}")
        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100,baos)
        val image = baos.toByteArray()

        ref.putBytes(image)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    ref.downloadUrl.addOnCompleteListener {
                        it.result?.let {
                            imageUri = it
                            id_profile.setImageBitmap(imgBitmap)
                        }
                    }
                }
            }
    }
}
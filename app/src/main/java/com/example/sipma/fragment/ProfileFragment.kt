package com.example.sipma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.example.sipma.R

class ProfileFragment : Fragment() {

    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var ivProfileImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        tvName = view.findViewById(R.id.tvName)
        tvEmail = view.findViewById(R.id.tvEmail)
        ivProfileImage = view.findViewById(R.id.ivProfileImage)

        // Ambil akun Google yang sudah login
        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(requireContext())

        // Tampilkan data profil
        if (account != null) {
            tvName.text = account.displayName
            tvEmail.text = account.email
            Glide.with(this)
                .load(account.photoUrl)
                .into(ivProfileImage)
        }

        return view
    }
}



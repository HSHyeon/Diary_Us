package com.example.diary_us.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.diary_us.SwipeData
import com.example.diary_us.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class ProfileFragment : Fragment() {

    val datas = mutableListOf<SwipeData>()
    private val binding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(
            layoutInflater
        )
    }
    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        mAuth = FirebaseAuth.getInstance();
        var user: FirebaseUser? = mAuth!!.currentUser


        if(user!=null) {
            binding.nickname.setText(user?.displayName.toString())
            Glide.with(this).load(user?.photoUrl.toString()).into(binding.imgProfile)
        }//구글 프로필 설정



        return binding.root
    }



}


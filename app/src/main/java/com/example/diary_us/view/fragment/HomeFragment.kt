package com.example.diary_us.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diary_us.FriendAdapter

import com.example.diary_us.WriteData
import com.example.diary_us.databinding.FragmentHomeBinding
import com.example.diary_us.view.activity.WriteActivity

class HomeFragment : Fragment() {
    lateinit var swipeadapter: FriendAdapter
    val datas = mutableListOf<WriteData>()

    //val helper = SqliteHelper(this.context,"article",null,1)

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
        return binding.root
        }


    private fun initRecycler() {
        swipeadapter = FriendAdapter(requireContext())
        binding.rvProfile.adapter = swipeadapter
        binding.rvProfile.apply {
            layoutManager = LinearLayoutManager(context)

        }//initRecycler()//완료 버튼 누르면 데이터 바인딩

        //+버튼 누르면 WriteActivity 시작
        binding.floatingActionButton.setOnClickListener{
            activity?.let{
                Log.e("homeFrag.initRecycler", "1")
                //수정
                val writeActivity =  WriteActivity()
                val intent = Intent(context, writeActivity::class.java)
                /////////////////////
                startActivity(intent)
                Log.e("homeFrag.initRecycler", "2")
            }
        }


    }


    override fun onResume() {
        super.onResume()
        initRecycler()
        Log.e("I'm at HomeFragment", "1")

    }

}


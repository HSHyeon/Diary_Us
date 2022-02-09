package com.example.diary_us.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction

import com.example.diary_us.R
import com.example.diary_us.FriendAdapter
import com.example.diary_us.SwipeData
import com.example.diary_us.databinding.ActivityMainBinding
import com.example.diary_us.view.fragment.ChatFragment
import com.example.diary_us.view.fragment.HomeFragment
import com.example.diary_us.view.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener  {
    lateinit var swipeadapter: FriendAdapter
    val datas = mutableListOf<SwipeData>()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }
    //hello hihi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this)
        binding.bottomNavigationView.selectedItemId = R.id.menu_name
        // HOME as default tab
        supportFragmentManager.beginTransaction().add(
            R.id.frame_layout,
            HomeFragment(),"home"
        ).commit()
        // Launch app with HOME selected as default start tab
    }
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        when(p0.itemId){
            R.id.menu_name ->{
                val fragmentA = HomeFragment()
                transaction.replace(R.id.frame_layout,fragmentA, "HOME")
            }
            R.id.menu_tag -> {
                val fragmentB = ChatFragment()
                transaction.replace(R.id.frame_layout,fragmentB, "CHAT")
            }
            R.id.menu_cal -> {
                val fragmentC = ProfileFragment()
                transaction.replace(R.id.frame_layout,fragmentC, "MY")
            }


        }
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()

        return true
    }

}

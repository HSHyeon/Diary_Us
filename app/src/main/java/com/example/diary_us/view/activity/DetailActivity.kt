package com.example.diary_us.view.activity

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.diary_us.R
import com.example.diary_us.WriteData
import com.example.diary_us.databinding.ActivityDetailBinding
import com.example.diary_us.view.activity.ChatActivity


class DetailActivity : AppCompatActivity(){
    var data : WriteData?= null
    internal lateinit var preferences: SharedPreferences

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(
            layoutInflater
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
     //   supportActionBar!!.setHomeAsUpIndicator(R.drawable.menu)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.navigationIcon?.apply {
            setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN) //뒤로가기 아이콘 색 설정
        }

        preferences = getSharedPreferences("USERSIGN", Context.MODE_PRIVATE)
        val editor = preferences!!.edit()


        val idx = intent.getIntExtra("id",0)


        binding.tvTitle.setText(data?.title)
        binding.tvContent.setText(data?.content)
        binding.tvDate.setText(data?.datetime.toString())
        if(data?.img==null)
            Glide.with(this).load(R.drawable.placeholder).into(binding.imageView4)
        else Glide.with(this).load(data?.img).centerCrop().into(binding.imageView4)

        //채팅하기 버튼 누르면!
        binding.button2.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            editor.putString("name", binding.tvName.text.toString())//이름 가져오기
            startActivity(intent)
            Log.e("detailActivity name:", binding.tvName.text.toString())

        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.detail_menu, menu)
        menu?.apply {
            for(index in 0 until this.size()){
                val item = this.getItem(index)
                val s = SpannableString(item.title)
                s.setSpan(ForegroundColorSpan(Color.BLACK),0,s.length,0)
                item.title = s
            }
        }
        return true
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_modify -> {
           // msgShow("Search")
            true
        }
        android.R.id.home -> {
            finish()
            true
        }
        R.id.menu_delete -> {
          //  msgShow("Profile")
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

}
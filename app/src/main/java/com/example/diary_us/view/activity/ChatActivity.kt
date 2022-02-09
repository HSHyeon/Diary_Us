package com.example.diary_us.view.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diary_us.databinding.ActivityChatBinding

import org.json.JSONException
import org.json.JSONObject
import java.net.Socket
import java.net.URISyntaxException
import java.text.SimpleDateFormat
import java.util.*

class ChatActivity : AppCompatActivity(){
    internal lateinit var preferences: SharedPreferences//사용자 이름을 이걸로 저장할까..?
    private var hasConnection: Boolean = false
    private var thread2: Thread? = null
    private var startTyping = false
    private var time = 2
    private lateinit var mSocket: Socket

    private val binding: ActivityChatBinding by lazy {
        ActivityChatBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        preferences = getSharedPreferences("USERSIGN", Context.MODE_PRIVATE)

        //뭔가 설정..?
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.navigationIcon?.apply {
            setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_IN)
        }

        //전송버튼
        binding.messageActivityImageView.setOnClickListener{
            sendMessage()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }




    fun sendMessage() {//어댑터에 메시지 전송
        preferences = getSharedPreferences("USERSIGN", Context.MODE_PRIVATE)
        val now = System.currentTimeMillis()
        val date = Date(now)
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val getTime = sdf.format(date)

        val message = binding.messageActivityEditText.text.toString().trim({ it <= ' ' })//입력한 메시지 가져오기
        if (TextUtils.isEmpty(message)) {
            return
        }
        binding.messageActivityEditText.setText("")//메시지 입력창 비우기
        val jsonObject = JSONObject()
        try {
            jsonObject.put("name", preferences.getString("name", ""))
            jsonObject.put("script", message)
            jsonObject.put("profile_image", "example")
            jsonObject.put("date_time", getTime)
            jsonObject.put("roomName", "room_example")//룸이름
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        //chatAdapter.notifyDataSetChanged()
    }

}
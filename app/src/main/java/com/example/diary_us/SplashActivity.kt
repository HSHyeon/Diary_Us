package com.example.diary_us

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.diary_us.view.activity.GoogleLoginActivity
import com.example.diary_us.view.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }


    public override fun onStart() {
            super.onStart()
            var auth = FirebaseAuth.getInstance()
            moveMainPage(auth?.currentUser)
        }

    fun moveMainPage(user: FirebaseUser?){
            Handler().postDelayed({
            if( user!= null){
                startActivity(Intent(this, MainActivity::class.java))
                finish()}
            else
                {
                val intent = Intent(this, GoogleLoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                finish()}
        },DURATION)

    }
    companion object {
        private const val DURATION : Long = 3000
}
}
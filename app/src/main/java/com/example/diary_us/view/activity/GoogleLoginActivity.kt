package com.example.diary_us.view.activity

import android.R.id
import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.diary_us.R

import com.example.diary_us.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

import javax.security.auth.callback.Callback


class GoogleLoginActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null
    val GOOGLE_REQUEST_CODE = 99
    val TAG = "googleLogin"
    private lateinit var googleSignInClient: GoogleSignInClient
   // var server = retrofit.create(APIInterface::class.java)

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.google_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        binding.login.setOnClickListener {
            signIn()
        }

    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == GOOGLE_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.idToken)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = auth!!.currentUser
                    val input = HashMap<String?,Any?>()
                    input.put("email", user?.email!!)
                    input.put("nickname", user?.displayName!!)
                    input.put("token", idToken)

                    loginSuccess() //주석 지울때 지울 코드(중복)
                  /*  server.postSignUp(input).enqueue((object:retrofit2.Callback<SignUp> {


                        override fun onFailure(call: retrofit2.Call<SignUp>, t: Throwable?) {}

                        override fun onResponse(call: retrofit2.Call<SignUp>, response: retrofit2.Response<SignUp>){

                            if (response.isSuccessful()) {
                                val signup: SignUp? = response.body()
                                val flag = signup?.code
                                if (flag == 200) { //보내기 성공
                                    Toast.makeText(
                                        applicationContext,
                                        "회원가입에 성공했습니다",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                  loginSuccess()
                                } else if (flag == 308) { //이메일 중복
                                    Toast.makeText(
                                        applicationContext, /
                                        "이미 회원가입한 계정입니다", //로그인으로 넘어가기
                                        loginSuccess()
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else println(response.toString())
                        }


                    }))  */

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
             }
            }
    }

    private fun loginSuccess(){

        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    public override fun onStart() {
        super.onStart()
        moveMainPage(auth?.currentUser)
    }

    fun moveMainPage(user: FirebaseUser?){
        if( user!= null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }


}
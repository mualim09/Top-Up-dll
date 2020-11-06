package com.ronal.dinamakarya.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ronal.dinamakarya.R
import com.ronal.dinamakarya.api.RetrofitClient
import com.ronal.dinamakarya.model.token.TokenResponse
import com.ronal.dinamakarya.utils.SessionManager
import kotlinx.android.synthetic.main.activity_splash.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    private lateinit var retrofitClient: RetrofitClient
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        btn_mulai.setOnClickListener {
            btn_mulai()
        }
    }

    private fun btn_mulai() {

        retrofitClient = RetrofitClient()
        sessionManager = SessionManager(this)

        val id = "1QeDrKIZYe4gMAwoe53CenE4zF9rKkSG"
        val secret = "SVe6OoMXIIUzudrK"

        retrofitClient.getApiService(this).token(id,secret).enqueue(object : Callback<TokenResponse>{
            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                if (response.code() == 200 && sessionManager.ambilToken() != null){
                    sessionManager.saveToken(response.body()?.access_token.toString())
                    Log.v("PESAN", response.body()?.access_token.toString())
                }else{
                    Log.v("TOKEN SUDAH ADA", sessionManager.ambilToken().toString())
                }

                Intent(this@SplashActivity, HomeActivity::class.java).also {
                    startActivity(it)
                }
            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                Log.v("PESAN", t.message.toString())
                Intent(this@SplashActivity, SplashActivity::class.java).also {
                    startActivity(it)
                }
            }

        })

//        RetrofitClient.INSTANCE.token(id,secret).enqueue(object : Callback<TokenResponse>{
//            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
//                if (response.code() == 200 && sessionManager.ambilToken() == null){
//                    sessionManager.saveToken(response.body()?.access_token.toString())
//                    Log.v("PESAN", response.body()?.access_token.toString())
//                }else{
//                    Log.v("TOKEN SUDAH ADA", sessionManager.ambilToken().toString())
//                }
//
//                Intent(this@SplashActivity, HomeActivity::class.java).also {
//                    startActivity(it)
//                }
//            }
//
//            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
//                Log.v("PESAN", t.message.toString())
//                Intent(this@SplashActivity, SplashActivity::class.java).also {
//                    startActivity(it)
//                }
//            }
//        })
    }
}

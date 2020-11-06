package com.ronal.dinamakarya.activity.brizzi

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.ronal.dinamakarya.R
import com.ronal.dinamakarya.api.RetrofitClient
import com.ronal.dinamakarya.model.token.NomorResponse
import com.ronal.dinamakarya.utils.SessionManager
import com.ronal.dinamakarya.utils.Url.NOMOR
import kotlinx.android.synthetic.main.activity_brizzi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class BrizziActivity : AppCompatActivity() {

    private lateinit var retrofitClient: RetrofitClient
    private lateinit var sessionManager: SessionManager
    private lateinit var path: String
    private lateinit var verb: String
    private lateinit var token: String
    private lateinit var timestamp: String
    private lateinit var body: String
    private lateinit var key: String
    private lateinit var payload: String
    private lateinit var signature: String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brizzi)

        Log.v("SIGNATURE", getSignature())
        Log.v("TIMESTAMP", createTimestamp())
        Log.v ("PAYLOAD", payload)

        sessionManager = SessionManager(this)

        tv_token.text = sessionManager.ambilToken().toString()

        nomorRek()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun nomorRek() {
        retrofitClient = RetrofitClient()
        sessionManager = SessionManager(this)

        val signature = getSignature()
        val timestamp = createTimestamp()

        retrofitClient.getApiService(this).nomor(signature, timestamp).enqueue(object: Callback<NomorResponse>{
            override fun onResponse(call: Call<NomorResponse>, response: Response<NomorResponse>) {
                Log.v("PESAN", response.body()?.status?.code.toString())
            }

            override fun onFailure(call: Call<NomorResponse>, t: Throwable) {
                Log.v("PESAN", t.message.toString())
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getSignature() : String {
        sessionManager = SessionManager(this)
        path = NOMOR
        verb = "GET"
        token = "Bearer ${sessionManager.ambilToken()}"
        timestamp = createTimestamp()
        body = ""
        key = "JGkv8XjcYZQAZSyE"
        payload = "path=" + path + "&verb=" + verb + "&token=" + token +
                "&timestamp=" + timestamp + "&body=" + body
        signature = createSignature(payload, key)

        return signature
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createSignature(payload: String, key: String): String {
        val sha256 = Mac.getInstance("HmacSHA256")
        val secret = SecretKeySpec(key.toByteArray(charset("UTF-8")), "HmacSHA256")
        sha256.init(secret)
        val signatureByte = sha256.doFinal(payload.toByteArray(charset("UTF-8")))
        return Base64.getEncoder().encodeToString(signatureByte).toString()
    }

    @SuppressLint("SimpleDateFormat")
    private fun createTimestamp(): String {
        val tz = TimeZone.getTimeZone("UTC")
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        df.timeZone = tz
        return df.format(Date())
    }

}
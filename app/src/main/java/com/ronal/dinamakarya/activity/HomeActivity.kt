package com.ronal.dinamakarya.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ronal.dinamakarya.R
import com.ronal.dinamakarya.activity.brizzi.BrizziActivity
import com.ronal.dinamakarya.activity.emoney.EmoneyActivity
import com.ronal.dinamakarya.activity.tapcash.TapcashActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        cv_brizzi.setOnClickListener {
            Intent(this, BrizziActivity::class.java).also {
                startActivity(it)
            }
        }
        cv_tapcash.setOnClickListener {
            Intent(this, TapcashActivity::class.java).also {
                startActivity(it)
            }
        }
        cv_emoney.setOnClickListener {
            Intent(this, EmoneyActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}
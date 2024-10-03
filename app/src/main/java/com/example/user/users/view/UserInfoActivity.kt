package com.example.user.users.view
import com.example.user.databinding.ActivityUserInfoBinding

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.user.R
import com.example.user.users.model.data.Data

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
//        setContentView(R.layout.activity_user_info)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)

        var firstname = intent.getStringExtra("first") ?: ""
        var lastname = intent.getStringExtra("last") ?: ""
        var email = intent.getStringExtra("email") ?: ""
        var image = intent.getStringExtra("image") ?: ""
        var id = intent.getStringExtra("id") ?: ""
//
//        var firstview: TextView= findViewById(R.id.firstView)
//        var lastview: TextView= findViewById(R.id.lastView)
//        var emailview: TextView= findViewById(R.id.emailView2)
//        firstview.text=firstname
//        lastview.text=lastname
//        emailview.text=email

        val user =Data(image,email,firstname,id,lastname)

        binding.user= user

        var imageview: ImageView= findViewById(R.id.imageView)

        Glide.with(this).load(image).into(imageview)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
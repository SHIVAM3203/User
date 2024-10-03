package com.example.user.users.viewmodel

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.user.R
import com.example.user.users.model.data.Data
import com.example.user.users.view.UserInfoActivity
import com.squareup.picasso.Picasso
import gen._base._base_java__assetres.srcjar.R.id.text

class MyAdapter(val context: Activity,private  val userlist: List<Data>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.namebt)
        val email: TextView = itemView.findViewById(R.id.emailbt)
        val image: ImageView = itemView.findViewById(R.id.imagebt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData = userlist[position]

        holder.name.text = currentData.first_name
        holder.email.text = currentData.email
        Glide.with(context).load(currentData.avatar).into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context ,UserInfoActivity::class.java)
            intent.putExtra("first",currentData.first_name)
            intent.putExtra("last",currentData.last_name)
            intent.putExtra("email",currentData.email)
            intent.putExtra("image",currentData.avatar)
            intent.putExtra("id",currentData.id)
            holder.itemView.context.startActivity(intent)
        }

    }

    // Return the number of items in the list
    override fun getItemCount(): Int {
        return userlist.size
    }
}

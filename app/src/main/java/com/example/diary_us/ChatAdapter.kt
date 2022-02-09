package com.example.diary_us

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diary_us.view.activity.ChatActivity
import org.w3c.dom.Text


class ChatAdapter (private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var datas = mutableListOf<SwipeData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_chat,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
        holder.itemView.setOnClickListener{

            val intent = Intent(holder.itemView?.context, ChatActivity::class.java)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.tv_rv_name)
        private val txtAge: TextView = itemView.findViewById(R.id.tv_rv_age)
        private val imgProfile: ImageView = itemView.findViewById(R.id.img_rv_photo)

        fun bind(item: SwipeData) {
            txtName.text = item.name
            txtAge.text = item.age.toString()
            Glide.with(itemView).load(item.img).into(imgProfile)

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}
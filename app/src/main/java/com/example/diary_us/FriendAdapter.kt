package com.example.diary_us

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.diary_us.R
import com.example.diary_us.WriteData
import com.example.diary_us.view.activity.DetailActivity

class FriendAdapter (private val context: Context) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {
    var datas = mutableListOf<WriteData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_swipelist,parent,false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bind(datas[position])
        val article : WriteData = datas.get(position)
        holder.bind(article)


        //writeData 넣을거임이라고 정의
        Log.e("SwipeAdater.bindhold", "I'm here!2")


        //swipelayout.addSwipeListener(SwipeLayout.SwipeListener)

            holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView?.context, DetailActivity::class.java)
            intent.putExtra("id",position)
            ContextCompat.startActivity(holder.itemView.context, intent, null)

    }}

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtName: TextView = itemView.findViewById(R.id.tv_rv_name)
        private val txtAge: TextView = itemView.findViewById(R.id.tv_rv_age)
        private val imgProfile: ImageView = itemView.findViewById(R.id.img_rv_photo)

        /*fun bind(item: WriteData) {
            txtName.text = item.name
            txtAge.text = item.age.toString()
            Glide.with(itemView).load(item.img).into(imgProfile)

        }*/

        fun bind(article:WriteData){
            txtName.text = article.title
            txtAge.text = article.content
            if(article.img==null)
                Glide.with(itemView).load(R.drawable.placeholder).into(imgProfile)
            else Glide.with(itemView).load(article.img).centerCrop().into(imgProfile)
            Log.e("SwipeAdater.setArticle", "I'm here!3 " + txtAge.text+article.img)
        }

    }


}
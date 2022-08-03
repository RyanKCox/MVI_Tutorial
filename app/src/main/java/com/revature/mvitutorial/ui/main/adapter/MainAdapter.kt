package com.revature.mvitutorial.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.revature.mvitutorial.R
import com.revature.mvitutorial.data.model.User

class MainAdapter(
    private val users:ArrayList<User>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(user: User) {
            val tvName: TextView = itemView.findViewById(R.id.textViewUserName)
            tvName.text = user.name
            val tvEmail: TextView = itemView.findViewById(R.id.textViewUserEmail)
            tvEmail.text = user.email
            val ivAvatar: ImageView = itemView.findViewById(R.id.imageViewAvatar)
            Glide.with(ivAvatar.context)
                .load(user.avatar)
                .into(ivAvatar)
//        fun bind(user:User){
//            itemView.textViewUserName.text = user.name
//            itemView.textViewUserEmail.text = user.email
//            Glide.with(itemView.imageViewAvatar.context)
//                .load(user.avatar)
//                .into(itemView.imageViewAvatar)
//        }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,parent,false
            )
        )

    override fun getItemCount():Int = users.size
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }
    fun addData(list:List<User>){
        users.addAll(list)
    }


}
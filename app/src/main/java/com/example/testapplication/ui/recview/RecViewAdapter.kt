package com.example.testapplication.ui.recview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapplication.data.retrofitrequest.models.Article
import com.example.testapplication.databinding.RowLayoutBinding
import kotlinx.coroutines.Dispatchers

class RecViewAdapter(private val parentContext: Context) : RecyclerView.Adapter<RecViewAdapter.MyViewHolder>() {

    private var allData: List<Article> = emptyList()

    inner class MyViewHolder(private val itemBinding: RowLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(article: Article) {
            Glide.with(parentContext)
                .load(article.urlToImage)
                .into(itemBinding.imageView)

            itemBinding.author.text = article.author
            itemBinding.title.text = article.title
            itemBinding.published.text = article.publishedAt.toString()


            itemBinding.cardView.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                parentContext.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = allData[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return allData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setRequestsData(requestsPool: List<Article>) {
        allData = requestsPool
        notifyDataSetChanged()
    }
}
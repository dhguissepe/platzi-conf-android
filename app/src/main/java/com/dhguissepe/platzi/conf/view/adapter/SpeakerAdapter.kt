package com.dhguissepe.platzi.conf.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dhguissepe.platzi.conf.R
import com.dhguissepe.platzi.conf.model.Speaker

class SpeakerAdapter(private val speakerListener: SpeakerListener): RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {
    var speakerList = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speaker, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val speaker = speakerList[position] as Speaker

        holder.tvSpeakerName.text = speaker.name
        holder.tvSpeakerJobTitle.text = speaker.jobtitle

        Glide.with(holder.itemView.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.ivSpeakerPicture)

        holder.itemView.setOnClickListener {
            speakerListener.onSpeakerItemTap(speaker, position)
        }
    }

    override fun getItemCount() = speakerList.size

    fun updateData (data: List<Speaker>) {
        speakerList.clear()
        speakerList.addAll(data)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivSpeakerPicture = itemView.findViewById<ImageView>(R.id.ivItemSpeakerImage)
        val tvSpeakerName = itemView.findViewById<TextView>(R.id.tvItemSpeakerName)
        val tvSpeakerJobTitle = itemView.findViewById<TextView>(R.id.tvItemSpeakerJobTitle)
    }
}
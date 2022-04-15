package com.dhguissepe.platzi.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dhguissepe.platzi.conf.R
import com.dhguissepe.platzi.conf.model.Conference
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

// Estructura básica de un RecyclerView
class ScheduleAdapter(private val scheduleListener: ScheduleListener): RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    var conferenceList = ArrayList<Conference>()

    // Crear o decir cual va a ser el diseño que vamos a utilizar para la lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false))

    // Cuales son los datos que se añaden al view.
    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference = conferenceList[position] as Conference

        holder.tvConferenceName.text = conference.title
        holder.tvTag.text = conference.tag
        holder.tvSpeakerName.text = conference.speaker

        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")

        holder.tvHour.text = simpleDateFormat.format(conference.datetime)
        holder.tvAMPM.text = simpleDateFormatAMPM.format(conference.datetime).toUpperCase()

        holder.itemView.setOnClickListener {
            scheduleListener.onConferenceTap(conference, position)
        }
    }

    // Cuantos elementos tenemos para la vista
    // nota: lo que se ve abajo es una función en línea
    override fun getItemCount() = conferenceList.size

    fun updateData(data: List<Conference>) {
        conferenceList.clear()
        conferenceList.addAll(data)
        notifyDataSetChanged()
    }

    // Como enlazamos cada uno de los elementos visuales con la info
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // Aquí se declaran las referencias de cada uno de los elementos
        // visuales que se quieren utilizar para hidratar la ui
        val tvConferenceName = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceName)
        val tvSpeakerName = itemView.findViewById<TextView>(R.id.tvItemScheduleSpeakerName)
        val tvTag = itemView.findViewById<TextView>(R.id.tvScheduleTag)
        val tvHour = itemView.findViewById<TextView>(R.id.tvItemScheduleHour)
        val tvAMPM = itemView.findViewById<TextView>(R.id.tvItemScheduleAMPMIndicator)
    }
}
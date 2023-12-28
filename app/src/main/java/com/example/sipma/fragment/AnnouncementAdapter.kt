package com.example.sipma.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sipma.R


class AnnouncementAdapter(private val announcements: List<Announcement>) :
    RecyclerView.Adapter<AnnouncementAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val adminTextView: TextView = itemView.findViewById(R.id.adminTextView)
        val publishedAtTextView: TextView = itemView.findViewById(R.id.publishedAtTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_announcement, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val announcement = announcements[position]

        holder.titleTextView.text = announcement.judul
        holder.descriptionTextView.text = announcement.deskripsi
        holder.adminTextView.text = announcement.admin_name
        holder.publishedAtTextView.text = announcement.published_at
    }

    override fun getItemCount(): Int {
        return announcements.size
    }
}

package com.example.edvora.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.edvora.data.RidesItemX
import com.example.edvora.databinding.RideDetailBinding

class RideAdapter(var a:Int):RecyclerView.Adapter<RideAdapter.RideViewHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<RidesItemX>() {
        override fun areItemsTheSame(oldItem: RidesItemX, newItem: RidesItemX): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RidesItemX, newItem: RidesItemX): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    inner class RideViewHolder(private val binding:RideDetailBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(ride:RidesItemX){
            binding.apply {
                Glide.with(itemView).load(ride.mapUrl).into(img)
                date.text="Date : ${ride.date}"
                originStation.text="Origin Station : ${ride.originStationCode.toString()}"
                rideId.text="Ride ID : ${ride.id.toString()}"
                stationPath.text="station_path : ${ride.stationPath.toString()}"
                state.text=ride.state
                distance.text="Distance : ${(ride.destinationStationCode-a).toString()}"
            }
        }
    }
    override fun getItemCount(): Int=differ.currentList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        val binding=RideDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RideViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        val ride=differ.currentList[position]
        holder.bind(ride)
    }


}
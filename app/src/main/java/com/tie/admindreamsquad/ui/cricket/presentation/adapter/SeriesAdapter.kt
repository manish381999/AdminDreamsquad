package com.tie.admindreamsquad.ui.cricket.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tie.admindreamsquad.databinding.CricketseriesLayoutBinding
import com.tie.admindreamsquad.ui.cricket.data.models.SeriesResponse.Series

class SeriesAdapter(private val onItemClick: (Series) -> Unit) : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    private var seriesList: List<Series> = emptyList()

    // Method to set data in the adapter
    fun setSeriesData(data: List<Series>) {
        this.seriesList = data
        notifyDataSetChanged()
    }

    // Create the view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val binding = CricketseriesLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SeriesViewHolder(binding)
    }

    // Bind the data to the view
    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val series = seriesList[position]
        holder.bind(series)

        // Log the series ID to ensure it is being passed
        Log.d("SeriesAdapter", "Binding Series ID: ${series.id}")

        // Setting the item click listener
        holder.itemView.setOnClickListener {
            Log.d("SeriesAdapter", "Selected Series ID: ${series.id}") // Log the selected ID
            onItemClick(series)  // This will trigger the onItemClick lambda passed from the Activity
        }
    }

    // Return item count
    override fun getItemCount(): Int = seriesList.size

    // ViewHolder class to bind data
    class SeriesViewHolder(private val binding: CricketseriesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(series: Series) {
            binding.seriesName.text = series.name
            binding.tvStartDate.text = "Start: ${series.startDate}"
            binding.tvEndDate.text = "End: ${series.endDate}"
            binding.tvOdi.text = "ODI: ${series.odi}"
            binding.tvT20.text = "T20: ${series.t20}"
            binding.tvTest.text = "Test: ${series.test}"
            binding.tvSquads.text = "Squads: ${series.squads}"
            binding.tvMatches.text = "Matches: ${series.matches}"
        }
    }
}

package com.tie.admindreamsquad.ui.cricket.presentation.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tie.admindreamsquad.R
import com.tie.admindreamsquad.databinding.ActivityUploadSeriesBinding
import com.tie.admindreamsquad.ui.cricket.presentation.adapter.SeriesAdapter
import com.tie.admindreamsquad.ui.cricket.viewmodel.CricketViewModel
import com.tie.admindreamsquad.ui.cricket.data.models.SeriesResponse.Series

class UploadSeriesActivity : AppCompatActivity() {
    private val TAG = "UploadSeriesActivity"

    private lateinit var binding: ActivityUploadSeriesBinding
    private lateinit var mContext: Context
    private lateinit var cricketViewModel: CricketViewModel
    private lateinit var seriesAdapter: SeriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadSeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mContext = this
        cricketViewModel = ViewModelProvider(this)[CricketViewModel::class.java]

        setupRecyclerView()
        observeViewModel()

        cricketViewModel.fetchSeries(offset = 0)
    }

    private fun setupRecyclerView() {
        // Create the adapter with an onItemClick listener
        seriesAdapter = SeriesAdapter { selectedSeries ->
            // Log the selected series ID for debugging
            Log.d(TAG, "Selected Series ID: ${selectedSeries.id}")
            // Send the selected series data to the API
            sendDataToApi(selectedSeries)
        }

        binding.rvCricketSeriesInfo.apply {
            layoutManager = LinearLayoutManager(mContext)
            adapter = seriesAdapter
        }
    }

    private fun observeViewModel() {
        cricketViewModel.responseMessage.observe(this) { message ->
            Log.d(TAG, message)
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
        }

        cricketViewModel.seriesResponse.observe(this) { response ->
            if (response != null) {
                Log.d(TAG, "API Success: Filtered Series: ${response.map { it.name }}")
                seriesAdapter.setSeriesData(response)
            } else {
                Log.d(TAG, "No upcoming or ongoing series available.")
                Toast.makeText(mContext, "No upcoming or ongoing series available.", Toast.LENGTH_SHORT).show()
            }
        }

        cricketViewModel.errorMessage.observe(this) { message ->
            Log.e(TAG, "API Error: $message")
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendDataToApi(selectedSeries: Series) {
        // Log the series data before sending it
        Log.d(TAG, "Sending Series: ${selectedSeries.name}, ID: ${selectedSeries.id}")
        cricketViewModel.sendSeriesDataToApi(selectedSeries)
    }
}

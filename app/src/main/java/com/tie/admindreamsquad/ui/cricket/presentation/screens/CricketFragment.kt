package com.tie.admindreamsquad.ui.cricket.presentation.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tie.admindreamsquad.databinding.FragmentCricketBinding

class CricketFragment : Fragment() {

    private var _binding: FragmentCricketBinding? = null
    private val binding get() = _binding!! // Non-nullable property for safe access

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize view binding
        _binding = FragmentCricketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        onClickListener()
    }

    private fun initComponents() {
        binding.mcUploadSeries.setOnClickListener {
            val intent = Intent(requireContext(), UploadSeriesActivity::class.java)
            startActivity(intent)
        }
    }


    private fun onClickListener(){

    }

}
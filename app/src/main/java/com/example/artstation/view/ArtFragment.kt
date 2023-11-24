package com.example.artstation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.artstation.R
import com.example.artstation.databinding.FragmentArtsDetailsBinding
import com.example.artstation.databinding.FragmentsArtBinding
import com.example.artstation.view.ArtFragmentDirections.Companion.actionArtFragmentToArtDetailsFragment

class ArtFragment : Fragment(R.layout.fragments_art) {
    private var fragmentBinding : FragmentsArtBinding?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentsArtBinding.bind(view)
        fragmentBinding = binding
        binding.fab.setOnClickListener{
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }
    }
}
package com.example.artstation.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.artstation.R
import com.example.artstation.databinding.FragmentArtsDetailsBinding
import javax.inject.Inject
import javax.security.auth.callback.Callback

class ArtDetailsFragment @Inject constructor(
    val glide: RequestManager
): Fragment(R.layout.fragment_arts_details) {

    private var fragmentBinding : FragmentArtsDetailsBinding?= null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtsDetailsBinding.bind(view)
        fragmentBinding = binding

        binding.artImageView.setOnClickListener{
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
        }
        val callback= object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()   // for the back button
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }
}
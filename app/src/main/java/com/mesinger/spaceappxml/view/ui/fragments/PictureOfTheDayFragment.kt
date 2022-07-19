package com.mesinger.spaceappxml.view.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.databinding.FragmentPictureOfTheDayBinding
import com.mesinger.spaceappxml.service.repository.api.RetrofitInstance
import com.mesinger.spaceappxml.viewmodel.APIViewModel
import retrofit2.HttpException
import java.io.IOException


class PictureOfTheDayFragment : Fragment(R.layout.fragment_picture_of_the_day) {

    private lateinit var binding: FragmentPictureOfTheDayBinding;
    private val viewModel: APIViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)

        loadData()


        return binding.root
    }

    private fun loadData(){
        lifecycleScope.launchWhenCreated {
            val response = viewModel.getAPOD()
            if(response.isSuccessful && response.body() != null){
                binding.apply {
                    titleTextView.text = response.body()!!.title
                    authorTextView.text = response.body()!!.copyright
                    dateTextView.text = response.body()!!.date
                    actualDescriptionTextView.text = response.body()!!.explanation
                    Glide.with(requireContext())
                        .load(response.body()!!.hdurl)
                        .into(picOfTheDayImageView)
                }
            }else{
                Log.d("PictureOfTheDay", "Response not successful")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



}
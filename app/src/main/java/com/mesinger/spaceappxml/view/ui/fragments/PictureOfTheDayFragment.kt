package com.mesinger.spaceappxml.view.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.databinding.FragmentPictureOfTheDayBinding
import com.mesinger.spaceappxml.service.repository.api.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException


class PictureOfTheDayFragment : Fragment() {

    private lateinit var binding: FragmentPictureOfTheDayBinding;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)


        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.apodApi.getData()
            }catch (e: IOException){
                Log.d("PictureOfTheDay", "IOException, onCreate")
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.d("PictureOfTheDay", "HttpException, onCreate")
                return@launchWhenCreated
            }

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



}
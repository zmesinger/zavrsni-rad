package com.mesinger.spaceappxml.view.ui.fragments



import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import com.mesinger.spaceappxml.databinding.FragmentAddNewPhotoBinding


class AddNewPhotoFragment : Fragment() {

    private lateinit var binding: FragmentAddNewPhotoBinding

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission(),
        ActivityResultCallback {
                isGranted: Boolean ->
            if(isGranted){
                Log.d("AddNewPhotoFragment", "Granted")
            }else{
                Log.d("AddNewPhotoFragment", "Denied")
            }
        })




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermission()
    }


    private fun initListeners(){
        binding.selectPhotoButton.setOnClickListener(){
           // requestPermission()
            selectImageFromGallery()
        }
    }



    private fun requestPermission(){

        binding.selectPhotoButton.setOnClickListener() {

            when {
                checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    selectImageFromGallery()
                }
                ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) -> {
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    }


    private fun selectImageFromGallery() {
        val loadedImage = registerForActivityResult(ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                binding.cardImageView.setImageURI(it)
            })



    }

}

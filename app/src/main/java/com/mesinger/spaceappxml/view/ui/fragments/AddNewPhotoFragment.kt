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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.databinding.FragmentAddNewPhotoBinding
import com.mesinger.spaceappxml.viewmodel.AddNewPhotoViewModel


class AddNewPhotoFragment : Fragment() {

    private val viewModel: AddNewPhotoViewModel by viewModels()
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
        selectImageFromGallery()
        postImage()
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
                viewModel.setImageUri(it)

            })

        binding.selectPhotoButton.setOnClickListener(){
            loadedImage.launch("image/*")
        }



    }

    private fun postImage(){
        binding.postPhotoButton.setOnClickListener(){
            viewModel.uploadPost()
            navigateToHome()
        }
    }
    private fun navigateToHome(){
        findNavController().navigate(R.id.action_addNewPhotoFragment_to_homeFragment)
    }

}

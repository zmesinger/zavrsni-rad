package com.mesinger.spaceappxml.view.ui.fragments



import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.databinding.FragmentAddNewPhotoBinding
import com.mesinger.spaceappxml.utils.Constants


class AddNewPhotoFragment : Fragment() {

    private lateinit var binding: FragmentAddNewPhotoBinding





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()

    }


    private fun initListeners(){
        binding.selectPhotoButton.setOnClickListener(){
            requestPermission()
        }
    }

    private fun permissionLauncher(): ActivityResultLauncher<String> {
        val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission(),
            ActivityResultCallback {
                isGranted: Boolean ->
                    if(isGranted){
                        Log.d("AddNewPhotoFragment", "Granted")
                    }else{
                        Log.d("AddNewPhotoFragment", "Denied")
                    }
            })
        return requestPermissionLauncher
    }

    private fun requestPermission(){
        when {
            checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED ->{
                selectImageFromGallery().launch("image/*")
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) ->{
                permissionLauncher().launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            else ->{
                permissionLauncher().launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }


    private fun selectImageFromGallery(): ActivityResultLauncher<String> {
        val loadedImage = registerForActivityResult(ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                binding.cardImageView.setImageURI(it)
            })
            return loadedImage

    }

}

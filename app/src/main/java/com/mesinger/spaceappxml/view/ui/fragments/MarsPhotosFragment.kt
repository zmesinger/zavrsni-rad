package com.mesinger.spaceappxml.view.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mesinger.spaceappxml.databinding.FragmentMarsPhotosBinding
import com.mesinger.spaceappxml.service.model.marsrover.MarsPhoto
import com.mesinger.spaceappxml.service.model.marsrover.Photo
import com.mesinger.spaceappxml.view.adapter.marsphotosadapter.MarsPhotosAdapter
import com.mesinger.spaceappxml.viewmodel.APIViewModel
import java.util.*

private const val TAG = "MarsPhotosFragment"
class MarsPhotosFragment : Fragment() {


    private lateinit var binding: FragmentMarsPhotosBinding
    private val viewModel: APIViewModel by viewModels()
    private lateinit var adapter: MarsPhotosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMarsPhotosBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        pickDate()

    }

    private fun pickDate(){


        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        binding.dateEditText.setOnClickListener {
            val dpd = DatePickerDialog(requireActivity(), { _, year, monthOfYear, dayOfMonth ->
                binding.dateEditText.setText("$year-$monthOfYear-$dayOfMonth")

                loadData(binding.dateEditText.text.toString())


            }, year, month, day)
            dpd.show()

        }

    }

    private fun loadData(earthDate: String){

        lifecycleScope.launchWhenCreated {
            val response = viewModel.getMarsPhotos(earthDate)
            if(response.isSuccessful && response.body() != null){
                Log.d(TAG, "loadData: ${response.body()}")
                adapter.setPhotos(response!!.body()!!.photos)
            }

        }

    }

    private fun setupRecyclerView() {
        binding.marsRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = MarsPhotosAdapter()
        binding.marsRecyclerView.adapter = adapter
    }
}
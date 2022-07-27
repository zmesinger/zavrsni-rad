package com.mesinger.spaceappxml.view.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.databinding.FragmentMarsPhotosBinding
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS
private const val TAG = "MarsPhotosFragment"
class MarsPhotosFragment : Fragment() {


    private lateinit var binding: FragmentMarsPhotosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMarsPhotosBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

            }, year, month, day)
            dpd.show()

        }

    }
}
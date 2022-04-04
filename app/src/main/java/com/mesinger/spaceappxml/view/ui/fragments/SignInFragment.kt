package com.mesinger.spaceappxml.view.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.databinding.FragmentSignInBinding
import com.mesinger.spaceappxml.viewmodel.SignInViewModel

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToRegister()
        validateSignInInfo()
        getEmail()
        getPassword()
        singInUser()

    }

    private fun navigateToRegister(){
        binding.registerButton.setOnClickListener { findNavController().navigate(R.id.action_signInFragment_to_registerFragment2) }
    }

    private fun validateSignInInfo(){
        binding.signInButton.setOnClickListener {
            if(!viewModel.getEmail().contains('@')){
                Toast.makeText(requireContext(),
                    resources.getText(R.string.email_is_not_valid),Toast.LENGTH_SHORT).show()

            }else{

                if (viewModel.getPassword().length < 7 || viewModel.getPassword().isEmpty()){
                    Toast.makeText(requireContext(),
                        resources.getText(R.string.minimum_characters),Toast.LENGTH_SHORT).show()

                }else{
                    viewModel.singIn()
                }
            }
        }
    }

    private fun singInUser(){
        viewModel.user.observe(viewLifecycleOwner, Observer { newUser ->
            if(newUser){
                navigateToHome()
            }else{
                Log.d("SignInFragment", "singInUser")
            }
        })
    }


    private fun getEmail() = binding.signInEmailTextField.doOnTextChanged() {newEmail, _, _, _ -> viewModel.setEmail(newEmail.toString()) }

    private fun getPassword() = binding.signInPasswordEditText.doOnTextChanged() {newPassword, _, _, _ -> viewModel.setPassword(newPassword.toString())}

    private fun navigateToHome(){
        findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
    }
}
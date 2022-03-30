package com.mesinger.spaceappxml.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mesinger.spaceappxml.R
import com.mesinger.spaceappxml.databinding.FragmentRegisterBinding
import com.mesinger.spaceappxml.viewmodel.RegisterViewModel


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        validateRegisterInfo()
        registerNewUser()
        getName()
        getEmail()
        getPassword()
        viewModel.signOut()
    }



    private fun validateRegisterInfo(){
        validateName()
        validateEmail()
        validatePassword()
    }

    private fun registerNewUser(){
        binding.registerFragmentButton.setOnClickListener { viewModel.register() }
    }

    private fun validatePassword() {
        viewModel.password.observe(viewLifecycleOwner, Observer { newPassword ->
            if (newPassword == null){
                Toast.makeText(requireContext(), getString(R.string.no_empty_password), Toast.LENGTH_SHORT).show()
            }else if(newPassword.length < 7){
                Toast.makeText(requireContext(), getString(R.string.minimum_characters), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun validateEmail() {
        viewModel.email.observe(viewLifecycleOwner, Observer { newEmail ->
            if(newEmail.isEmpty() || !newEmail.contains("@")){
                Toast.makeText(requireContext(),getText(R.string.email_is_not_valid),Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun validateName() {
        viewModel.name.observe(viewLifecycleOwner, Observer { newName ->
            if(newName == null){
                Toast.makeText(requireContext(),getString(R.string.name_cannot_be_empty), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToSignIn(){
        viewModel.signOut()
        findNavController().navigate(R.id.action_registerFragment2_to_signInFragment)
    }


    private fun getName() = binding.nameSurnameEmailTextField.doOnTextChanged { newName, _, _, _ -> viewModel.setName(newName.toString()) }

    private fun getEmail() = binding.registerEmailTextField.doOnTextChanged { newEmail, _, _, _ -> viewModel.setEmail(newEmail.toString()) }

    private fun getPassword() = binding.registerPasswordEditText.doOnTextChanged { newPassword, _, _, _ -> viewModel.setPassword(newPassword.toString()) }

}
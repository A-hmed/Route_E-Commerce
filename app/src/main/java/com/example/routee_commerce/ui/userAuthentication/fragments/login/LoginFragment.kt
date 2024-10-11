package com.example.routee_commerce.ui.userAuthentication.fragments.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.routee_commerce.R
import com.example.routee_commerce.databinding.FragmentLoginBinding
import com.example.routee_commerce.ui.home.activity.MainActivity
import com.example.routee_commerce.utils.Resource
import com.example.routee_commerce.utils.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var viewBinding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.vm = loginViewModel
        viewBinding.lifecycleOwner = this
        observeLoginState()
        hideKeyboard()
        initViews()
    }

    private fun observeLoginState() {
        loginViewModel.loginState.observe(viewLifecycleOwner){
            when(it){
                is Resource.Error -> {
                   showErrorView(it.error.errorMessage)
                }
                Resource.Loading -> showLoadingView()
                is Resource.Success -> {
                    //todo: Navigate to home
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun initViews() {
        viewBinding.donTHaveAnAccountTv.setOnClickListener {
            // go to register
        }

    }


    private fun showSuccessView() {
        viewBinding.icNext.isVisible = true
        viewBinding.progressBar.isVisible = false
    }

    private fun showErrorView(message: String) {
        viewBinding.icNext.isVisible = true
        viewBinding.progressBar.isVisible = false
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .setBackgroundTint(resources.getColor(R.color.white))
            .setTextColor(resources.getColor(R.color.black))
            .show()
    }

    private fun showLoadingView() {
        viewBinding.icNext.isVisible = false
        viewBinding.progressBar.isVisible = true
    }


    private fun navigateToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun navigateToHome() {
        startActivity(Intent(activity, MainActivity::class.java))
        requireActivity().finish()
    }

    private fun hideKeyboard() {
        view?.hideKeyboard(activity as AppCompatActivity?)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding.unbind()
    }
}

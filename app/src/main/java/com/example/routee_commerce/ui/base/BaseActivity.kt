package com.example.routee_commerce.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.routee_commerce.R
import com.example.routee_commerce.domain.utils.Errors

abstract class BaseActivity<Binding> : AppCompatActivity() {
    var binding: Binding? = null
    var progressBar: AlertDialog? = null

    fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar =
                AlertDialog.Builder(this).setView(R.layout.layout_loading).show()
        } else {
            progressBar?.dismiss()
        }
    }

    fun showError(error: Errors) {
        Toast.makeText(this, error.errorMessage, Toast.LENGTH_LONG).show()
    }
    //abstract fun getLayoutId(): Int
}
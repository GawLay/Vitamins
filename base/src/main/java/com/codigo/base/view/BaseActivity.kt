package com.codigo.base.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity< T : ViewBinding> : AppCompatActivity() {
    protected abstract fun setBinding(inflater: LayoutInflater): T
    private lateinit var _binding: T
    protected val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = setBinding(layoutInflater)
        setContentView(_binding.root)
        initViews()
        onClickEvents()
        listener()
    }
    open fun listener(){}
    open fun initViews(){}

    open fun onClickEvents(){}
}
package com.crypto.currency.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.RequestManager
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel, VB : ViewBinding> : Fragment() {

    @Inject
    protected lateinit var requestManagerGlide: RequestManager

    protected lateinit var mViewBinding: VB

    protected abstract val mViewModel: VM

    protected var fragmentContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentContext = this.context
        setupView()
        startViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mViewBinding.root
    }

    abstract fun getViewBinding(): VB

    abstract fun startViewModel()

    abstract fun setupView()
}
package com.tistory.deque.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseKotlinFragment<T : ViewDataBinding, R : BaseKotlinViewModel> : Fragment(){

    lateinit var viewDataBinding: T

    abstract val layoutResourceId: Int

    abstract val viewModel: R

    abstract fun initDataBinding()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)

        snackbarObserving()
        initDataBinding()

        return viewDataBinding.root
    }

    private fun snackbarObserving() {
        viewModel.observeSnackbarMessage(this) {
            Snackbar.make(activity!!.findViewById(android.R.id.content), it, Snackbar.LENGTH_LONG).show()
        }
        viewModel.observeSnackbarMessageStr(this){
            Snackbar.make(activity!!.findViewById(android.R.id.content), it, Snackbar.LENGTH_LONG).show()
        }
    }
}
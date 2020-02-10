package com.arshak.core.view.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arshak.core.R

/**
 * Created by Arshak Avagyan on 1/24/20.
 * Project Name: FreeIPTV
 */

abstract class BaseFragment : Fragment() {

    lateinit var mFragmentView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFragmentView = inflater.inflate(onCreateView(), null)
        mFragmentView
        return mFragmentView
    }

    abstract fun initCompose()

    private fun onCreateView(): Int = R.layout.fragment_base_layout
}
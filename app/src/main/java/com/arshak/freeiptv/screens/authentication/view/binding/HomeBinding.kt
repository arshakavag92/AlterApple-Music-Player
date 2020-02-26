package com.arshak.freeiptv.screens.authentication.view.binding

import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.TextViewBindingAdapter
import com.arshak.freeiptv.screens.authentication.view.widget.listener.SearchQueryListener
import com.arshak.freeiptv.screens.authentication.view.widget.listener.TextChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView


/**
 * Created by Arshak Avagyan on 2/25/20.
 * Project Name: FreeIPTV
 */
object HomeBinding {

    @JvmStatic
    @BindingAdapter("onNavigationItemSelected")
    fun setOnNavigationItemSelected(
        view: BottomNavigationView, listener: BottomNavigationView.OnNavigationItemSelectedListener?
    ) {
        view.setOnNavigationItemSelectedListener(listener)
    }

    @JvmStatic
    @BindingAdapter("selectedItemPosition")
    fun setSelectedItemPosition(
        view: BottomNavigationView, position: Int
    ) {
        view.selectedItemId = position
    }

    @JvmStatic
    @BindingAdapter(value = ["afterTextChanged", "onTextSubmitted"])
    fun afterTextChanged(
        searchView: SearchView,
        textChangeListener: TextChangeListener,
        querySubmitListener: SearchQueryListener
    ) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                textChangeListener.onTextChanged(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                querySubmitListener.onTextSubmitted(newText)
                return true
            }
        })
    }
}
package com.arshak.freeiptv.screens.authentication.view.binding

import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.TextViewBindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arshak.freeiptv.screens.authentication.view.widget.listener.OnSearchClearListener
import com.arshak.freeiptv.screens.authentication.view.widget.listener.SearchQueryListener
import com.arshak.freeiptv.screens.authentication.view.widget.listener.TextChangeListener
import com.arshak.freeiptv.screens.home.view.adapter.SearchHintAdapter
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

    @JvmStatic
    @BindingAdapter("historyHintAdapter")
    fun historyHintAdapter(recyclerView: RecyclerView, adapter: SearchHintAdapter) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(recyclerView.context)
            this.adapter = adapter
        }
    }

    @JvmStatic
    @BindingAdapter("onClearClicked")
    fun onSearchCloseListener(
        searchView: SearchView,
        onSearchClearListener: OnSearchClearListener
    ) {
        searchView.setOnCloseListener {
            onSearchClearListener.onClearClicked()
            true
        }
    }

    @JvmStatic
    @BindingAdapter("itemDecoration")
    fun itemDecoration(recyclerView: RecyclerView, itemDecoration: RecyclerView.ItemDecoration) {
        while (recyclerView.itemDecorationCount > 0) {
            recyclerView.removeItemDecorationAt(0);
        }
        recyclerView.addItemDecoration(itemDecoration)
    }
}
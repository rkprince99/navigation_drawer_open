package com.prince.navigationdrawer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prince.navigationdrawer.R

class MainViewModel : ViewModel() {

    private val _selectedTitle = MutableLiveData("Home")
    val selectedTitle: LiveData<String> = _selectedTitle

    fun selectItem(itemId: Int): String {
        val title = when (itemId) {
            R.id.nav_home -> "Home"
            R.id.nav_cart -> "Cart"
            R.id.nav_favorites -> "Favorites"
            R.id.nav_calendar -> "Calendar"
            R.id.nav_bin -> "Bin"
            else -> "Home"
        }
        _selectedTitle.value = title
        return title
    }
}

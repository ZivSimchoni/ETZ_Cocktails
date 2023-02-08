package com.example.ETZcocktails.utils

import android.content.Context
import android.net.ConnectivityManager

class GlobalFunctions {
    //check if user as internet connection
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
               return true
            }
        }
        return false
    }

}
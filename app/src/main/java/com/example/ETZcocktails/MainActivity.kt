package com.example.ETZcocktails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.ETZcocktails.ui.SearchCocktails
import com.example.ETZcocktails.ui.SearchIngredient
import com.example.ETZcocktails.ui.MyCocktails
import com.example.ETZcocktails.ui.FavCocktails

class MainActivity : AppCompatActivity() {

    lateinit var binding: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        replaceFragment(SearchCocktails())

        val bottomNav = findViewById(R.id.bottomNavigationMenu) as BottomNavigationView

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.searchCocktails -> {
                    replaceFragment(SearchCocktails())
                true
                }
                R.id.searchIngredient -> {replaceFragment(SearchIngredient())
                    true
                }
                R.id.myCocktails -> {replaceFragment(MyCocktails())
                    true
                }
                R.id.FavCocktails -> {replaceFragment(FavCocktails())
                    true
                }
                else -> {
                    replaceFragment(SearchCocktails())
                    true
                }
            }
        }
    }

    // double click to exit -> the only downside that its over all the fragments
//    private var doubleBackToExitPressedOnce = false
//
//    override fun onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed()
//            return
//        }
//        this.doubleBackToExitPressedOnce = true
//        Toast.makeText(this,
//            R.string.bye_message,
//            Toast.LENGTH_SHORT
//        ).show()
//        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
//    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        //TODO addtobackstack works but dosent update the bottom nav menu #Eytan is the best#
        fragmentTransaction.commit()
    }
}


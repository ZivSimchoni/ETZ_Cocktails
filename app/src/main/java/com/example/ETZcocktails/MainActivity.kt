package com.example.ETZcocktails

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ETZcocktails.ui.FavCocktails
import com.example.ETZcocktails.ui.MyCocktails
import com.example.ETZcocktails.ui.SearchCocktails
import com.example.ETZcocktails.ui.SearchIngredient
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        //create an instance of each fragment
        val searchCocktails = SearchCocktails()
        val searchIngredient = SearchIngredient()
        val myCocktails = MyCocktails()
        val favCocktails = FavCocktails()

        //set the first fragment
        replaceFragment(favCocktails)

        val bottomNav = findViewById(R.id.bottomNavigationMenu) as BottomNavigationView

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.searchCocktails -> {
                    ClearBackStack()
                    replaceFragment(searchCocktails)
                    true
                }
                R.id.searchIngredient -> {
                    ClearBackStack()
                    replaceFragment(searchIngredient)
                    true
                }
                R.id.myCocktails -> {
                    ClearBackStack()
                    replaceFragment(myCocktails)
                    true
                }
                R.id.FavCocktails -> {
                    ClearBackStack()
                    replaceFragment(favCocktails)
                    true
                }
                else -> {
                    ClearBackStack()
                    replaceFragment(favCocktails)
                    true
                }
            }
        }
    }

    // double click to exit -> the only downside that its over all the fragments
    private var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this,
            R.string.bye_message,
            Toast.LENGTH_SHORT
        ).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    private fun ClearBackStack()
    {
        val count = supportFragmentManager.backStackEntryCount;
        for(i in 1..count)
        {
            supportFragmentManager.popBackStack();
        }
    }
}


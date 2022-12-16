package com.example.ETZcocktails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ETZcocktails.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    lateinit var binding: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        replaceFragment(SearchCocktails())

        val bottomNav = findViewById(R.id.bottomNavigationMenu) as BottomNavigationView

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.searchCocktails -> {replaceFragment(SearchCocktails())
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

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragnentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragnentTransaction.replace(R.id.frameLayout, fragment)
        fragnentTransaction.commit()
    }
}


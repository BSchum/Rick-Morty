package com.ynov.kotlin.rickmorty.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.episodeList.fragment.EpisodeListFragment
import com.ynov.kotlin.rickmorty.presentation.list.fragment.ListFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.main_activity_fragment_container, ListFragment()).commit()
        activity_main_bottom_navigation.setOnNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.action_android -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_activity_fragment_container, ListFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_logo -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_activity_fragment_container, EpisodeListFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_activity_fragment_container, ListFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }

        }

    }
}


package com.ynov.kotlin.rickmorty.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.detail.fragment.DetailFragment
import com.ynov.kotlin.rickmorty.presentation.list.fragment.ListFragment

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportFragmentManager.beginTransaction().replace(R.id.detail_activity_fragment_container, DetailFragment(intent.getIntExtra("id", 0))).commit()
    }
}

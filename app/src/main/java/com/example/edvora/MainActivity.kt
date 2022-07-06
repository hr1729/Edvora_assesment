package com.example.edvora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.edvora.databinding.ActivityMainBinding
import com.example.edvora.extra.Resource
import com.example.edvora.ui.Nearest.NearestFragment
import com.example.edvora.ui.rideviewModelProviderFactory
import com.example.edvora.ui.viewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewmodel: viewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = NearestFragment()
        val db=RideRepository()
        val viewModelprovider=rideviewModelProviderFactory(db)
        viewmodel=ViewModelProvider(this,viewModelprovider)[viewModel::class.java]
        nav_view.setupWithNavController(nav_host_fragment_activity_main.findNavController())

        viewmodel.user1.observe(this, Observer {
            when(it){
                is Resource.Success->{
                    it.data?.let {
                        binding.textView3.text=it.name.toString()
                        Glide.with(this).load(it.url).transform(CircleCrop()).into(binding.imageView)

                    }
                }
            }
        })
    }
}
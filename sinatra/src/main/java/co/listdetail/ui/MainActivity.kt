package co.listdetail.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import co.listdetail.R
import co.listdetail.Retrofit.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var  viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        observeLiveData()

    }

   private fun observeLiveData() {
        viewModel.getUsers().observe(this, {
            Log.d("USER", it.toString())
        })
   }
}

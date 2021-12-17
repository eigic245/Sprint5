package co.listdetail.ui

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import co.listdetail.R
import co.listdetail.Retrofit.MainViewModel
import co.listdetail.viewmodel.ContactViewModel

class MapsActivity: AppCompatActivity() {

    private lateinit var  viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(ContentValues.TAG,"entra al maps")

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        observeLiveData()
        setContentView(R.layout.maps_activity)
        val tv = findViewById<TextView>(R.id.textView)
        val bundle = intent.extras

        tv?.setText(bundle!!.getString("coordenadas", "No value from MainActivity :("))
        Log.d(ContentValues.TAG,tv.text.toString())

        if (tv.text == "central_park"){
            launchMap("40.782","-73.965")
        }else if (tv.text == "top_rock"){
            launchMap("40.759","-73.979")
        }
        else if (tv.text == "ferry_land"){
            launchMap("40.562","-74.139")
        }
        else if (tv.text == "high_land"){
            launchMap("40.747","-74.004")
        }
        else{ //vessel
            launchMap("40.753","-74.002")
        }
   }

    private fun observeLiveData() {
        viewModel.getUsers().observe(this, {
            Log.d("OBSERVE","entra al model observe")
            Log.d("USER", it.toString())
        })
    }


    private fun launchMap( lat: String, lon:String) {
        val geo = "geo: $lat,$lon"
        val gmmIntentUri = Uri.parse(geo)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

        Log.d(ContentValues.TAG,"entraallaunch")
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(packageManager)?.let {
            startActivity(mapIntent)
        }

    }
}
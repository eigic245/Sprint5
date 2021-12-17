package co.listdetail.Retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
   // enlaces de prueba
   // private const val BASE_URL = "https://my-json-server.typicode.com/sinatra-mintic/demo"
   // private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    private const val BASE_URL = "https://my-json-server.typicode.com"

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private fun retrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun apiService() : ApiService {
        return retrofit().create(ApiService::class.java)
    }
}
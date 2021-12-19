package co.listdetail.Retrofit

import co.listdetail.model.POI
import retrofit2.http.GET

interface ApiService {

    @GET("/sinatra-mintic/demo/posts")
    //suspend fun requestUsers() : List<User>
    suspend fun requestUsers() : ArrayList<POI>
}
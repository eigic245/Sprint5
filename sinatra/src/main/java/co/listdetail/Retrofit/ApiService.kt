package co.listdetail.Retrofit

import co.listdetail.model.Contact
import retrofit2.http.GET

interface ApiService {

    @GET("/sinatra-mintic/demo/posts")
    //suspend fun requestUsers() : List<User>
    suspend fun requestUsers() : ArrayList<Contact>
}
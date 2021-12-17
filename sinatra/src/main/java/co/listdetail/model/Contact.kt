package co.listdetail.model

import co.listdetail.R

data class Contact(
    var id: String,
    val name: String,
    var description1: String,
    var description2:String,
    var ubicacion: String,
    var temperatura: String,
    var latitud:String,
    var longitud:String)
{
    var imageUrl = R.drawable.ferry_land
}
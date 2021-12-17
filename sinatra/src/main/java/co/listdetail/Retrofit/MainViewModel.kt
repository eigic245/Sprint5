package co.listdetail.Retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.listdetail.R
import co.listdetail.model.Contact
import kotlinx.coroutines.launch

class MainViewModel: ViewModel (){

   private var apiService = RetrofitFactory.apiService()
   private var userList = MutableLiveData<java.util.ArrayList<Contact>>()
    init {
        //requestUsers() --test local
        requestUsersFromService()
    }

    fun getUsers(): LiveData<java.util.ArrayList<Contact>> = userList

    //para test local
    private fun requestUsers(): ArrayList<Contact> {
         return arrayListOf(
             Contact("1",
                  "Central Park",
                  "Senderos a pie",
                  "Recorre los senderos a pie o en bici, túmbate en el Sheep Meadow con un pícnic, rema por el lago, emociónate en la fuente de Bethesda.Las posibilidades son infinitas, pero algo está claro: una de las cosas que tienes que hacer es sí o sí perderte una o más veces",
                  "Distrito metropolitando de Manhattan",
                  "-2°C a 29°C",
                  "40.782",
                  "-73.965"),

             Contact( "2",
                   "Top of the rock",
                   "Panorámica de la gran manzana",
                   "Es un mirador que se encuentra situado en el Rockefeller Center, y es mi observatorio favorito en altura de Nueva York, Sin apenas colas, debido principalmente a una buena organización, te ofrece las mejores vistas aéreas de Nueva York y Manhattan, sobre todo de toda la zona de Central Park y Midtown",
                   "Rockefeller Center",
                   "-2°C a 29°C",
                   "40.759",
                   "-73.979"),
             Contact( "3",
                    "Ferry State Land",
                    "Ferry gratuito",
                    "Si te sitúas junto al agua en el sur de Manhattan, tanto de día como de noche verás un reguero de barcos de color naranja que llegan y zarpan de la isla, es muy popular entre los turistas porque, durante el trayecto, se ven la Estatua de la Libertad (desde lejos; el ferry no desembarca en la isla) y la silueta de Manhattan",
                    "Manhattan",
                    "-2°C a 29°C",
                    "40.562",
                   "-74.139"),
             Contact( "4",
                    "High Line",
                    "Parque elevado",
                    "Es un parque lineal de 1,45 millas de largo (2,33 km) en el distrito de Manhattan en Nueva York. Este parque se encuentra en una sección elevada de la línea East Side Line de la extinta compañía de ferrocarriles New York Central Railroad.Inspirados por el Coulée verte René-Dumont de París, un proyecto similar de 4,7 kilómetros completado en 1993",
                    "Nueva York, 10011, EE. UU.",
                    "-2°C a 29°C",
                    "40.747",
                    "-74.004"),
             Contact( "5",
                    "Vessel",
                    "Zona de rascacielos y lujos",
                    "Es una estructura y punto de atracción construida como parte del Plan de Desarrollo de Hudson Yards en Manhattan, Nueva York, Estados Unidos. Fue construida de acuerdo al diseño del británico Thomas Heatherwick, la estructura que asemeja un complejo patrón de panel de abejas se eleva 16 pisos",
                    "40°45′14″N 74°00′08″O",
                    "-2°C a 29°C",
                    "40.753",
                    "-74.002")
         )
    }

    private fun requestUsersFromService() {
        viewModelScope.launch {
            userList.value = apiService.requestUsers()
        }
    }

}
package co.listdetail.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import co.listdetail.R
import co.listdetail.Retrofit.MainViewModel
import co.listdetail.adapter.ContactsAdapter
import co.listdetail.model.Contact
import co.listdetail.viewmodel.ContactViewModel
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    private lateinit var mContacts: ArrayList<Contact>
    private lateinit var mAdapter: ContactsAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var model: ContactViewModel
    private lateinit var contexto:Context

    //con retrofit
    private lateinit var  viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(requireActivity()).get(ContactViewModel::class.java)

        //con retrofit
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        recycler = view.findViewById(R.id.contact_list)
        setupRecyclerView()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contexto = context
    }

    /**
     * Sets up the RecyclerView: empty data set, item dividers, swipe to delete.
     */
    private fun setupRecyclerView() {

        mContacts = createMockContacts()

        //utilizando retrofit
        viewModel.getUsers().observe(viewLifecycleOwner, {
            mContacts = it  //se asigna el gson de internet

            //se compara los dos vectores
            Log.d("CADENA_INTERNET", it.toString())
            Log.d("CADENA_MOCK",mContacts.toString())
        })


        recycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        mAdapter = ContactsAdapter( mContacts,contexto) { contact ->
            contactOnClick(contact)
        }


        recycler.adapter = mAdapter
    }

    /* RecyclerView item is clicked. */
    private fun contactOnClick(contact: Contact) {
        Log.d(TAG, "Click on: $contact")
        model.select(contact)


        findNavController().navigate(R.id.action_listFragment_to_detailFragment)

    }


  //para prueba local e inicializa el array
    private fun createMockContacts(): ArrayList<Contact> {
        return arrayListOf(
            Contact("1",
                    "Central Park",
                    "Senderos a pie",
                    "Recorre los senderos a pie o en bici, túmbate en el Sheep Meadow con un pícnic, rema por el lago, emociónate en la fuente de Bethesda.Las posibilidades son infinitas, pero algo está claro: una de las cosas que tienes que hacer es sí o sí perderte una o más veces",
                    "Distrito metropolitando de Manhattan",
                    "-2°C a 29°C",
                    "40.782",
                    "-73.965"),
            Contact("2",
                    "Top of the rock",
                    "Panorámica de la gran manzana",
                    "Es un mirador que se encuentra situado en el Rockefeller Center, y es mi observatorio favorito en altura de Nueva York, Sin apenas colas, debido principalmente a una buena organización, te ofrece las mejores vistas aéreas de Nueva York y Manhattan, sobre todo de toda la zona de Central Park y Midtown",
                    "Rockefeller Center",
                    "-2°C a 29°C",
                    "40.759",
                    "-73.979"),
            Contact("3",
                    "Ferry state land",
                    "Ferry gratuito",
                    "Si te sitúas junto al agua en el sur de Manhattan, tanto de día como de noche verás un reguero de barcos de color naranja que llegan y zarpan de la isla, es muy popular entre los turistas porque, durante el trayecto, se ven la Estatua de la Libertad (desde lejos; el ferry no desembarca en la isla) y la silueta de Manhattan",
                    "Manhattan",
                    "-2°C a 29°C",
                    "40.562",
                    "-74.139"),
            Contact("4",
                    "High line",
                    "Parque elevado" ,
                    "Es un parque lineal de 1,45 millas de largo (2,33 km) en el distrito de Manhattan en Nueva York. Este parque se encuentra en una sección elevada de la línea East Side Line de la extinta compañía de ferrocarriles New York Central Railroad.Inspirados por el Coulée verte René-Dumont de París, un proyecto similar de 4,7 kilómetros completado en 1993",
                    "Nueva York, 10011, EE. UU.",
                    "-2°C a 29°C",
                   "40.747",
                   "-74.004"),
            Contact("5",
                    "Vessel",
                    "Zona de rascacielos y lujos",
                    "Es una estructura y punto de atracción construida como parte del Plan de Desarrollo de Hudson Yards en Manhattan, Nueva York, Estados Unidos. Fue construida de acuerdo al diseño del británico Thomas Heatherwick, la estructura que asemeja un complejo patrón de panel de abejas se eleva 16 pisos",
                    "40°45′14″N 74°00′08″O",
                    "-2°C a 29°C",
                   "40.753",
                   "-74.002")
        )
    }

    companion object {
        private val TAG = ListFragment::class.java.simpleName
    }
}
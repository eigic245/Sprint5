package co.listdetail.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import co.listdetail.R
import co.listdetail.viewmodel.ContactViewModel
import com.bumptech.glide.Glide

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private lateinit var model: ContactViewModel
    private lateinit var nameView: TextView
    private lateinit var contexto: Context

    private lateinit var item_custom_text: TextView
    private lateinit var descripcion2: TextView
    private lateinit var ubicacion: TextView
    private lateinit var temperatura: TextView

    private lateinit var imageView2: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameView = view.findViewById(R.id.name_text)
        model = ViewModelProvider(requireActivity()).get(ContactViewModel::class.java)

        item_custom_text= view.findViewById(R.id.item_custom_text)
        descripcion2= view.findViewById(R.id.descripcion2)
        ubicacion= view.findViewById(R.id.ubicacion)
        temperatura= view.findViewById(R.id.temperatura)
        imageView2 = view.findViewById(R.id.imageView3)

        //mostrar los datos

        observeLiveData()

        val button = view.findViewById<Button>(R.id.ubicacion_central)
        button.setOnClickListener{
            //dispara un mapsactiviy

            var bundle = bundleOf("coordenadas" to "empty")
            if (item_custom_text.text =="Top of the rock"){
                bundle = bundleOf("coordenadas" to "top_rock")
            }else if (item_custom_text.text =="Ferry state land"){
                bundle = bundleOf("coordenadas" to "ferry_land")
            }else if (item_custom_text.text =="High line"){
                bundle = bundleOf("coordenadas" to "high_land")
            }else if (item_custom_text.text =="Vessel"){
                bundle = bundleOf("coordenadas" to "vessel")
            }else{
                bundle = bundleOf("coordenadas" to "central_park")
            }

            findNavController().navigate(R.id.action_detailFragment_to_mapsActivity,bundle)
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contexto = context
    }


    private fun observeLiveData() {
        model.getSelected().observe(viewLifecycleOwner, { contact ->
            val fullName = "Su nombre es: \n${contact.id} ${contact.name}"
            nameView.text = fullName
            item_custom_text.text= contact.name
            descripcion2.text= contact.description2
            ubicacion.text= contact.ubicacion
            temperatura.text= contact.temperatura

            var a: Int
            if (contact.id =="1") a=R.drawable.central_park2
            else if(contact.id == "2") a=R.drawable.top_rock2
            else if(contact.id == "3") a=R.drawable.ferry_land2
            else if(contact.id == "4" ) a=R.drawable.high_line2
            else a=R.drawable.vessel

            Glide.with(contexto).load(a).into(imageView2)
        })
    }
}

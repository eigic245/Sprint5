package co.listdetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.listdetail.model.Contact
import co.listdetail.R
import com.bumptech.glide.Glide
import java.util.ArrayList


class ContactsAdapter(
    private val mContacts: ArrayList<Contact>,
    private val context: Context,
    private val onClick: (Contact) -> Unit


) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_list_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holderContact: ContactViewHolder, position: Int) {
        val contact = mContacts[position]
        holderContact.bind(contact = contact)
    }

    override fun getItemCount(): Int {
        return mContacts.size
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var title: TextView = itemView.findViewById(R.id.textview_name)
        private var descriptionItemLabel: TextView = itemView.findViewById(R.id.textview_description)
        private var imageView: ImageView = itemView.findViewById(R.id.imageview_thumb)
        private var currentContact: Contact? = null

        init {
            itemView.setOnClickListener {
                currentContact?.let {
                    onClick(it)
                }
            }
        }

        /* Bind Contact name and image. */
        fun bind(contact: Contact) {
            currentContact = contact
            title.text = contact.name
            descriptionItemLabel.text = contact.description1
            if (contact.id =="1") contact.imageUrl=R.drawable.central_park
            else if(contact.id == "2") contact.imageUrl=R.drawable.top_rock
            else if(contact.id == "3") contact.imageUrl=R.drawable.ferry_land
            else if(contact.id == "4" ) contact.imageUrl=R.drawable.high_line
            else contact.imageUrl=R.drawable.vesel
            Glide.with(context).load(contact.imageUrl).into(imageView)

        }
    }
}
package co.listdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.listdetail.model.POI

class POIViewModel : ViewModel() {

    private val selected = MutableLiveData<POI>()

    fun getSelected() : LiveData<POI> = selected

    fun select(contact: POI) {
        selected.value = contact
    }

}
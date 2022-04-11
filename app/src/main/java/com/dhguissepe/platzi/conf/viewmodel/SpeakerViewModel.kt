package com.dhguissepe.platzi.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dhguissepe.platzi.conf.model.Speaker
import com.dhguissepe.platzi.conf.network.Callback
import com.dhguissepe.platzi.conf.network.FirestoreService
import java.lang.Exception

class SpeakerViewModel {
    val firestoreService = FirestoreService()
    val speakerList: MutableLiveData<List<Speaker>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    
    fun refresh() {
        getSpeakersFromFirestore()
    }
    
    fun getSpeakersFromFirestore() {
        firestoreService.getSpeakers(object: Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                speakerList.postValue(result)
                endProcess()
            }
            override fun onFailed(exception: Exception) {
                endProcess()
            }
        })
    }
    
    fun endProcess() {
        isLoading.value = false
    }
}
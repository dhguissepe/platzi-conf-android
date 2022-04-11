package com.dhguissepe.platzi.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dhguissepe.platzi.conf.model.Conference
import com.dhguissepe.platzi.conf.network.Callback
import com.dhguissepe.platzi.conf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel {
    val firestoreService = FirestoreService()
    val scheduleList: MutableLiveData<List<Conference>> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase() {
        firestoreService.getSchedule(object: Callback<List<Conference>> {
            override fun onSuccess(result: List<Conference>?) {
                scheduleList.postValue(result)
                endProcess()
            }

            override fun onFailed(exception: Exception) {
                endProcess()
            }
        })
    }

    fun endProcess() {
        isLoading.value = true
    }
}
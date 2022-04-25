package com.dhguissepe.platzi.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dhguissepe.platzi.conf.model.Conference
import com.dhguissepe.platzi.conf.network.Callback
import com.dhguissepe.platzi.conf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel: ViewModel() {
    private val firestoreService = FirestoreService()
    val scheduleList: MutableLiveData<List<Conference>> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getScheduleFromFirebase()
    }

    private fun getScheduleFromFirebase() {
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

    private fun endProcess() {
        isLoading.value = true
    }
}
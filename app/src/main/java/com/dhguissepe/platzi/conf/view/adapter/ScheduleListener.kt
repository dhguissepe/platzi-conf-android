package com.dhguissepe.platzi.conf.view.adapter

import com.dhguissepe.platzi.conf.model.Conference

interface ScheduleListener {
    fun onConferenceTap(conference: Conference, position: Int)
}
package com.dhguissepe.platzi.conf.view.adapter

import com.dhguissepe.platzi.conf.model.Speaker

interface SpeakerListener {
    fun onSpeakerItemTap(speaker: Speaker, position: Int)
}
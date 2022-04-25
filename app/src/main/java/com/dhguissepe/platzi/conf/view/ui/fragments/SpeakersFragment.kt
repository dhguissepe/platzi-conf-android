package com.dhguissepe.platzi.conf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dhguissepe.platzi.conf.R
import com.dhguissepe.platzi.conf.model.Speaker
import com.dhguissepe.platzi.conf.view.adapter.SpeakerAdapter
import com.dhguissepe.platzi.conf.view.adapter.SpeakerListener
import com.dhguissepe.platzi.conf.viewmodel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

/**
 * A simple [Fragment] subclass.
 * Use the [SpeakersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpeakersFragment : Fragment(), SpeakerListener {
    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var viewModel: SpeakerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        viewModel.refresh()

        // El adaptador recibe la instancia de la clase que funciona como contexto del fragmento ya que es la que se compone
        // de la interfaz del listener correspondiente y por eso cuenta con dicho método.
        speakerAdapter = SpeakerAdapter(this)

        rvSpeakers.apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = speakerAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.speakerList.observe(this, Observer<List<Speaker>> { speakers ->
            speakerAdapter.updateData(speakers)
        })

        viewModel.isLoading.observe(this, Observer<Boolean> {
            if (it != null)
                rlBaseSpeakers.visibility = View.INVISIBLE
        })
    }

    override fun onSpeakerItemTap(speaker: Speaker, position: Int) {
        val bundle = bundleOf("speaker" to speaker)

        findNavController().navigate(R.id.speakersDetailFragmentDialog, bundle)
    }
}
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhguissepe.platzi.conf.R
import com.dhguissepe.platzi.conf.model.Conference
import com.dhguissepe.platzi.conf.view.adapter.ScheduleAdapter
import com.dhguissepe.platzi.conf.view.adapter.ScheduleListener
import com.dhguissepe.platzi.conf.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*


/**
 * A simple [Fragment] subclass.
 * Use the [ScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScheduleFragment : Fragment(), ScheduleListener {
    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Se registra el viewModel una vez creadad la vista
        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()

        // Se registra el adaptador
        // El adaptador recibe la instancia de la clase que funciona como contexto del fragmento ya que es la que se compone
        // de la interfaz del listener correspondiente y por eso cuenta con dicho método.
        scheduleAdapter = ScheduleAdapter(this)

        // Se aplica al recyclerView las propiedades que necesita. LayoutManager y adapter.
        rvSchedule.apply {
            // El layout manager hace referencia a como queremos que se muestren las vistas en nuestro recycler view
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = scheduleAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.scheduleList.observe(this, Observer<List<Conference>> { schedule ->
            scheduleAdapter.updateData(schedule)
        })

        viewModel.isLoading.observe(this, Observer<Boolean> {
            if (it != null)
                rlBaseSchedule.visibility = View.INVISIBLE
        })
    }

    override fun onConferenceTap(conference: Conference, position: Int) {
        val bundle = bundleOf("conference" to conference)

        findNavController().navigate(R.id.scheduleDetailFragmentDialog, bundle)
    }
}
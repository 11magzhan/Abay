package com.example.abay.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.abay.databinding.FragmentTodayBinding

class TodayFragment: Fragment() {

    private lateinit var binding: FragmentTodayBinding
    private lateinit var viewModel: TodayViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val factory = TodayViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory).get(TodayViewModel::class.java)

        viewModel.randomQaraSoz.observe(viewLifecycleOwner) { qaraSoz ->
            binding.tvTitle.text = qaraSoz.title
            binding.tvText.text = qaraSoz.text
        }
    }
}
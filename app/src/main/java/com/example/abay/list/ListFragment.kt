package com.example.abay.list

import RecyclerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abay.database.QaraSoz
import com.example.abay.databinding.FragmentListBinding

class ListFragment: Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListViewModel
    private val adapter by lazy {
        RecyclerAdapter(
            onItemClick = ::navigateToDetails
        )
    }

    private fun navigateToDetails(qaraSoz: QaraSoz) {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        val application = requireNotNull(this.activity).application
        val factory = ListViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory).get(ListViewModel::class.java)

        // Set up RecyclerView
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        // Observe LiveData
        viewModel.qaraSozList.observe(viewLifecycleOwner, Observer { qaraSozList ->
            adapter.submitList(qaraSozList)
        })
    }
}
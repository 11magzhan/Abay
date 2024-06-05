package com.example.abay.favorites

import RecyclerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abay.database.QaraSoz
import com.example.abay.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment: Fragment() {

    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var binding: FragmentFavoritesBinding
    private val adapter by lazy {
        RecyclerAdapter(onItemClick = ::navigateToDetails)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favoritesRv.adapter = adapter
        binding.favoritesRv.layoutManager = LinearLayoutManager(context)

        viewModel.favoriteQaraSozList.observe(viewLifecycleOwner) { qaraSozList ->
            adapter.submitList(qaraSozList)
        }
    }

    private fun navigateToDetails(qaraSoz: QaraSoz) {
        val action = FavoritesFragmentDirections.actionFavoritesFragmentToTodayFragment(qaraSoz.id)
        findNavController().navigate(action)
    }
}

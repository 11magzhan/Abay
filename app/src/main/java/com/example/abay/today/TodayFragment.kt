package com.example.abay.today

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.abay.R
import com.example.abay.database.QaraSoz
import com.example.abay.databinding.FragmentTodayBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayFragment : Fragment() {
    private val viewModel: TodayViewModel by viewModels()
    private lateinit var binding: FragmentTodayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val qaraSozId = arguments?.getLong("qaraSozId")

        if (qaraSozId != null && qaraSozId != 0L) {
            viewModel.getQaraSozById(qaraSozId)
        } else {
            viewModel.getRandomQaraSoz()
        }

        viewModel.currentQaraSoz.observe(viewLifecycleOwner) { qaraSoz ->
            displayQaraSoz(qaraSoz)
        }

        binding.btnFavorite.setOnClickListener {
            viewModel.changeFavorite()
        }
    }

    override fun onResume() {
        super.onResume()
        updateLayoutVisibility()
    }

    private fun updateLayoutVisibility() {
        val sharedPrefs = requireActivity().getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)
        val showLayout = sharedPrefs.getBoolean("show_layout", false)
        binding.settingsLl.visibility = if (showLayout) View.VISIBLE else View.GONE
    }

    private fun displayQaraSoz(qaraSoz: QaraSoz?) {
        binding.tvText.text = qaraSoz?.text
        binding.tvTitle.text = qaraSoz?.title
        val favoriteIcon = if (qaraSoz?.favorite == 1) {
            R.drawable.ic_baseline_favorite_24
        } else {
            R.drawable.ic_baseline_favorite_border_24
        }
        binding.btnFavorite.setImageResource(favoriteIcon)
    }
}
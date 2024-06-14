package com.example.abay.settings

import android.content.Context
import android.os.Bundle
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.example.abay.databinding.SettingsActivityBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: SettingsActivityBinding
    private lateinit var checkbox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkbox = binding.checkboxShow
        val sharedPrefs = getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)
        val isChecked = sharedPrefs.getBoolean("show_layout", false)

        checkbox.isChecked = isChecked
        checkbox.setOnCheckedChangeListener { _, isChecked ->
            with(sharedPrefs.edit()) {
                putBoolean("show_layout", isChecked)
                apply()
            }
        }
    }
}
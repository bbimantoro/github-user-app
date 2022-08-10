package com.example.githubuser.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.R
import com.example.githubuser.data.repository.SettingPreferences
import com.example.githubuser.databinding.ActivitySettingThemeBinding
import com.example.githubuser.ui.viewmodel.SettingThemeViewModel

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingThemeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(dataStore)
        val settingThemeViewModel =
            ViewModelProvider(
                this,
                ViewModelFactory(pref)
            )[SettingThemeViewModel::class.java]

        settingThemeViewModel.getThemeSettings().observe(this) { isDarkModeActive ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.apply {
                    switchTheme.isChecked = true
                    imageView.setImageResource(R.drawable.ic_dark_mode)
                }
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.apply {
                    switchTheme.isChecked = false
                    imageView.setImageResource(R.drawable.ic_wb_sunny)
                }
            }
        }

        binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton, isChecked ->
            settingThemeViewModel.saveThemeSetting(isChecked)
        }

        supportActionBar?.title = getString(R.string.appbar_title_setting)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
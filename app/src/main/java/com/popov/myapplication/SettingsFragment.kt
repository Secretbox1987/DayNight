package com.popov.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val preference = findPreference<Preference>("key")
        preference?.setOnPreferenceClickListener {
            val sp = PreferenceManager.getDefaultSharedPreferences(requireActivity())
            sp.edit().putBoolean("isChanged", true).apply()

            val isChecked = (it as SwitchPreference).isChecked
            if(isChecked){
                updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
            }

            true
        }
    }

    private fun updateTheme(nightMode: Int): Boolean{
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }

}

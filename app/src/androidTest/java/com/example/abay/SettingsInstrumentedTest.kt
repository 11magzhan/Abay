package com.example.abay

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.abay.settings.SettingsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SettingsActivity::class.java)

    @Test
    fun testCheckboxShowLayout() {
        onView(withId(R.id.checkbox_show)).check(matches(isNotChecked()))
        onView(withId(R.id.checkbox_show)).perform(click()).check(matches(isChecked()))

        val sharedPrefs = ApplicationProvider.getApplicationContext<Context>()
            .getSharedPreferences("settings_prefs", Context.MODE_PRIVATE)
        assert(sharedPrefs.getBoolean("show_layout", false))
    }
}
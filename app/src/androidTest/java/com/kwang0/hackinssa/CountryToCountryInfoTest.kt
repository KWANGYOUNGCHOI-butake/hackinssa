package com.kwang0.hackinssa

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.ComponentNameMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import com.kwang0.hackinssa.presentation.ui.activities.main.MainActivity
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test

class CountryToCountryInfoTest {
    private val MESSAGE = "kor"
    private val PACKAGE_NAME = "com.kwang0.hackinssa"
    private val TAG = CountryToCountryInfoTest::class.simpleName


    /* Instantiate an IntentsTestRule object. */
    @get:Rule
    var intentsRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)


    @Test
    fun verifyMessageSentToCountryInfoActivity() {

        // Types a message into a EditText element.
        Espresso.onView(ViewMatchers.withId(R.id.searchbar_et))
                .perform(ViewActions.typeText(MESSAGE), ViewActions.closeSoftKeyboard())

        // Clicks a button to send the message to another
        // activity through an explicit intent.
        Espresso.onView(ViewMatchers.withId(R.id.country_rv_layout)).perform(ViewActions.click())

        // Verifies that the DisplayMessageActivity received an intent
        // with the correct package name and message.
        Intents.intended(AllOf.allOf(
                IntentMatchers.hasComponent(ComponentNameMatchers.hasShortClassName(".CountryInfoActivity")),
                IntentMatchers.toPackage(PACKAGE_NAME),
                IntentMatchers.hasExtraWithKey("country")))

    }
}
package com.kwang0.hackinssa

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.ComponentNameMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.kwang0.hackinssa.presentation.ui.activities.friendadd.FriendAddActivity
import com.kwang0.hackinssa.presentation.ui.activities.main.MainActivity
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class FriendAddTest {
    private val TAG = FriendAddTest::class.simpleName

    /* Instantiate an IntentsTestRule object. */
    @get:Rule
    var intentsRule: IntentsTestRule<FriendAddActivity> = IntentsTestRule(FriendAddActivity::class.java)


    @Test
    fun verifyMessageSentToCountryInfoActivity() {

        // Types a message into a EditText element.
        onView(withId(R.id.fa_name_et))
                .perform(ViewActions.typeText("kwnag0"))
        onView(withId(R.id.fa_phone_et))
                .perform(ViewActions.typeText("010-2589-4048"))
        onView(withId(R.id.fa_email_et))
                .perform(ViewActions.typeText("cky1203@gmail.com"))

        // Clicks a button to send the message to another
        // activity through an explicit intent.
//        onView(withId(R.id.menu_fa_ok)).perform(ViewActions.click())


    }
}
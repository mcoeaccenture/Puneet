package com.assignment.activity

import android.support.test.espresso.Espresso
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import android.view.View
import com.assignment.R
import org.hamcrest.CoreMatchers
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

/**
 * Created by psetia on 10/15/18.
 */
class UsersActivityTest {

    @Rule
    @JvmField
    val rule  = ActivityTestRule(UsersActivity::class.java)

    @Test
    fun checkIf10UsersLoadedIn5Sec() {
        Thread.sleep(5000)

        Espresso.
                onView(ViewMatchers.withId(R.id.recycleViewUsers)).
                check(CustomAssertions.hasItemCount(10))

    }


    class CustomAssertions {
        companion object {
            fun hasItemCount(count: Int): ViewAssertion {
                return RecyclerViewItemCountAssertion(count)
            }
        }

        private class RecyclerViewItemCountAssertion(private val count: Int) : ViewAssertion {

            override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }

                if (view !is RecyclerView) {
                    throw IllegalStateException("The asserted view is not RecyclerView")
                }

                if (view.adapter == null) {
                    throw IllegalStateException("No adapter is assigned to RecyclerView")
                }

                ViewMatchers.assertThat("RecyclerView item count", view.adapter.itemCount,
                        CoreMatchers.equalTo(count))
            }
        }
    }
}
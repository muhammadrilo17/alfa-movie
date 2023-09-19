package com.rilodev.alfamovies.core.helpers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.rilodev.alfamovies.R
import com.rilodev.alfamovies.core.helpers.Constants.EXTRA_BUNDLE_DATA
import java.text.SimpleDateFormat
import java.util.Locale

object Utils {
    fun Activity.movePage(
        destination: Class<*>,
        bundle: Bundle? = null,
        optionCompat: ActivityOptionsCompat? = null
    ) {
        val intent = Intent(this, destination)
        intent.putExtra(EXTRA_BUNDLE_DATA, bundle)

        if (optionCompat != null) {
            startActivity(intent, optionCompat.toBundle())
        } else startActivity(intent)
    }

    fun formatTimeDuration(minutes: Int): String {
        if (minutes < 0) {
            throw IllegalArgumentException("Input must be a non-negative integer.")
        }

        val hours = minutes / 60
        val remainingMinutes = minutes % 60

        return when {
            hours > 0 && remainingMinutes > 0 -> "${hours}h ${remainingMinutes}m"
            hours > 0 -> "${hours}h"
            remainingMinutes > 0 -> "${remainingMinutes}m"
            else -> "0m"
        }
    }

    fun generateFriendlyDateTime(inputDateTime: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.getDefault())
            val date = inputFormat.parse(inputDateTime)
            if (date != null) {
                outputFormat.format(date)
            }
            inputDateTime
        } catch (e: Exception) {
            e.printStackTrace()
            inputDateTime
        }
    }

    fun formatNumberWithSeparator(number: Long): String {
        val formatter = java.text.DecimalFormat("#,###")
        return formatter.format(number)
    }

    fun Activity.errorResponse(value: String): String {
        return if (value.lowercase().contains("unable to resolve")) {
            getString(R.string.internet_connection)
        } else value
    }
}
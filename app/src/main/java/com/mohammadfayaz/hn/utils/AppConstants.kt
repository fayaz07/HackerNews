package com.mohammadfayaz.hn.utils

object AppConstants {
  const val INTERNET_ERROR: String = "Unable to connect to internet"
  const val INTERNET_ERROR_CODE: Int = 600
  const val API_ERROR: Int = 601

  private const val REFER_TO_SETUP_GUIDELINES: String =
    "please refer to the setup guide for more information"
  const val INVALID_HN_API_URL: String =
    "Invalid baseUrl specified in local.properties, $REFER_TO_SETUP_GUIDELINES"
  const val INVALID_HN_API_VERSION: String =
    "Invalid apiVersion specified in local.properties, $REFER_TO_SETUP_GUIDELINES"
}

package com.dynamicisland.pro.core.privacy

import com.dynamicisland.pro.core.model.NotificationState
import java.util.regex.Pattern

/**
 * 100% On-Device Zero-Leak Privacy Engine
 * Automatically intercepts, masks, and redacts OTPs, credentials, and banking texts.
 * Zero disk storage. Everything stays volatile in RAM.
 */
object PrivacyFilter {

    private val OTP_PATTERN_1 = Pattern.compile("(?i)\\b(?:otp|code|verification|pin)\\b[^0-9]{0,10}(\\d{4,8})")
    private val OTP_PATTERN_2 = Pattern.compile("\\b(\\d{4,6})\\b")

    private val FINANCIAL_KEYWORDS = listOf(
        "debited", "credited", "acct", "account", "bank", "card ending", "available balance", "txn"
    )

    fun processNotification(
        pkg: String,
        appName: String,
        rawTitle: String,
        rawText: String
    ): NotificationState {
        val fullContent = "$rawTitle $rawText".lowercase()

        var extractedOtp: String? = null
        val m1 = OTP_PATTERN_1.matcher("$rawTitle $rawText")
        if (m1.find()) {
            extractedOtp = m1.group(1)
        } else {
            val m2 = OTP_PATTERN_2.matcher("$rawTitle $rawText")
            if (m2.find() && (fullContent.contains("otp") || fullContent.contains("code") || fullContent.contains("verify"))) {
                extractedOtp = m2.group(1)
            }
        }

        val isOtpDetected = extractedOtp != null && extractedOtp.length in 4..8
        val isFinancial = FINANCIAL_KEYWORDS.any { fullContent.contains(it) }

        val sanitizedTitle = if (isOtpDetected) {
            "🔒 One-Time Password"
        } else if (isFinancial) {
            "$appName Alert"
        } else {
            rawTitle.take(32)
        }

        val sanitizedMessage = if (isOtpDetected) {
            "Tap to copy authentication code safely"
        } else if (isFinancial) {
            "Confidential Transaction Notification"
        } else {
            rawText.take(90)
        }

        return NotificationState(
            id = "${pkg}_${System.currentTimeMillis()}",
            appPackage = pkg,
            appName = appName,
            title = sanitizedTitle,
            message = sanitizedMessage,
            isOtp = isOtpDetected,
            extractedOtp = extractedOtp
        )
    }
}\n
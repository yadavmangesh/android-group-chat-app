package com.vucko.cometchatdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.pro.core.AppSettings.AppSettingsBuilder
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException

class WelcomeActivity : AppCompatActivity() {
    val TAG = "WelcomeActivity"

    lateinit var loginButton: Button
    lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        loginButton = findViewById(R.id.logInButton)
        loginButton.isEnabled = false
        loginButton.setOnClickListener { redirectToLogin() }

        initCometChat()
    }

    private fun initCometChat() {
        // Initializes CometChat with the APP_ID from the dashboard

        val appSettings = AppSettingsBuilder().subscribePresenceForAllUsers()
            .setRegion(GeneralConstants.REGION).build()

        CometChat.init(this, GeneralConstants.APP_ID,appSettings, object : CometChat.CallbackListener<String>() {
            override fun onSuccess(p0: String?) {
                Log.d(TAG, "Initialization completed successfully")
                loginButton.isEnabled = true
            }

            override fun onError(p0: CometChatException?) {
                Log.d(TAG, "Initialization failed with exception: " + p0?.message)
            }
        })
    }


    private fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}

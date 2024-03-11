package com.mita.gamebuddymobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mita.gamebuddymobile.api.Login
import com.mita.gamebuddymobile.api.LoginResponse
import com.mita.gamebuddymobile.api.RetrofitClient
import com.mita.gamebuddymobile.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInPage : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in_page)

        emailEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.LogInbutton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            loginUser(email, password)
        }

        val signUpRed: TextView = findViewById(R.id.SignupRed)
        signUpRed.setOnClickListener {
            startActivity(Intent(this, SignUpPage::class.java))
        }
    }

    private fun loginUser(email: String, password: String) {
        val call = RetrofitClient.apiService.login(Login(email, password))
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    if (token != null) {
                        saveToken(token)
                        intent.putExtra("TOKEN", token)
                        Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LogInPage, HomePage::class.java)
                        intent.putExtra("TOKEN", token)
                        startActivity(intent)
                        finish()

                    }
                } else {
                    Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Failed to communicate with server",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    private fun saveToken(token: String) {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        Log.d("SharedPreferences", "User token saved: token=$token")
        editor.apply()
    }
}

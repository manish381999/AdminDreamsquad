package com.tie.admindreamsquad.ui.credentials

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.tie.admindreamsquad.R
import com.tie.admindreamsquad.api.ApiClient
import com.tie.admindreamsquad.databinding.ActivityLoginBinding
import com.tie.admindreamsquad.ui.credentials.model.LoginResponse
import com.tie.admindreamsquad.ui.dashboard.BaseActivity
import com.tie.dreamsquad.utils.SP
import com.tie.dreamsquad.utils.SP.savePreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()

        binding.btnContinue.setOnClickListener {
            performLogin()
        }
    }

    private fun setupListeners() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateInputs()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        // Add TextWatcher to both input fields
        binding.tilEmpId.editText?.addTextChangedListener(textWatcher)
        binding.tilPassword.editText?.addTextChangedListener(textWatcher)
    }

    private fun validateInputs() {
        val empId = binding.tilEmpId.editText?.text.toString().trim()
        val password = binding.tilPassword.editText?.text.toString().trim()

        if (empId.isNotEmpty() && password.isNotEmpty()) {
            // Enable the button and set the enabled background
            binding.btnContinue.isEnabled = true
            binding.btnContinue.background = ContextCompat.getDrawable(this, R.drawable.bg_enable_continue)
            binding.btnContinue.setTextColor(ContextCompat.getColor(this, R.color.white))
        } else {
            // Disable the button and set the disabled background
            binding.btnContinue.isEnabled = false
            binding.btnContinue.background = ContextCompat.getDrawable(this, R.drawable.bg_disable_continue)
            binding.btnContinue.setTextColor(ContextCompat.getColor(this, R.color.gray2))
        }
    }

    private fun performLogin() {
        val empId = binding.tilEmpId.editText?.text.toString().trim()
        val password = binding.tilPassword.editText?.text.toString().trim()

        // Call the API
        val call = ApiClient.api.admin_login(empId, password)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    Log.d("LoginActivity", "Response: $loginResponse")

                    if (loginResponse != null && loginResponse.status == "success") {
                        val user = loginResponse.user
                        if (user != null) {
                            // Save user details in SharedPreferences
                            SP.savePreferences(this@LoginActivity, SP.USER_ID, user.id)
                            SP.savePreferences(this@LoginActivity, SP.USER_NAME, user.name)
                            SP.savePreferences(this@LoginActivity, SP.USER_EMP_ID, user.emp_id)
                            SP.savePreferences(this@LoginActivity, SP.USER_MOBILE, user.mobile_no)

                            savePreferences(this@LoginActivity, SP.LOGIN_STATUS, SP.SP_TRUE)


                            // Navigate to the next screen
                            val intent = Intent(this@LoginActivity, BaseActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "User details missing!", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, loginResponse?.message ?: "Login failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("LoginActivity", "Error Response: ${response.message()}")
                    Toast.makeText(this@LoginActivity, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("LoginActivity", "API Call Failed: ${t.message}", t)
                Toast.makeText(this@LoginActivity, "API Call Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}

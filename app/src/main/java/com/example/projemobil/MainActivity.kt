package com.example.projemobil

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projemobil.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth=Firebase.auth
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.texthere1.setOnClickListener {
            val intent=Intent(this,kayitoll::class.java)
            startActivity(intent)

        }


        val usernameEditText = findViewById<EditText>(R.id.emailhere)
        val passwordEditText = findViewById<EditText>(R.id.passhere)
        val loginButton = findViewById<Button>(R.id.login_btn)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Lütfen kullanıcı adı ve şifre giriniz", Toast.LENGTH_SHORT).show()
            } else {
                if (username == "admin" && password == "1234") {
                    Toast.makeText(this, "Giriş başarılı", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Kullanıcı adı veya şifre hatalı", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun girisyap(view: View){
        val email=binding.emailhere.text.toString()
        val password=binding.passhere.text.toString()
        if(email.equals("")||password.equals("")){
            Toast.makeText(this,"Doğru bir şekilde email ve şifreyi giriniz",Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent=Intent(this,AnaSayfa::class.java)
                startActivity(intent)
                finish()

            }.addOnFailureListener{
                Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }
}
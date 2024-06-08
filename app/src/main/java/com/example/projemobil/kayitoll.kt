package com.example.projemobil

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projemobil.databinding.ActivityKayitollBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.rpc.context.AttributeContext.Auth

import android.util.Log
import android.widget.Button
import android.widget.EditText


class kayitoll : AppCompatActivity() {



    private lateinit var binding: ActivityKayitollBinding

    private lateinit var auth:FirebaseAuth

    private lateinit var firestore: FirebaseFirestore

    private lateinit var kullaniciadi: EditText
    private lateinit var emailhere: EditText
    private lateinit var passhere: EditText
    private lateinit var kaydol_btn: Button

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayitoll)
        binding= ActivityKayitollBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        var database=FirebaseDatabase.getInstance().reference



        auth=Firebase.auth
        firestore= Firebase.firestore


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets}

        kullaniciadi = findViewById(R.id.kullaniciadi)
        emailhere = findViewById(R.id.emailhere)
        passhere = findViewById(R.id.passhere)
        kaydol_btn = findViewById(R.id.kaydol_btn)

        db = FirebaseFirestore.getInstance()

        kaydol_btn.setOnClickListener {
            val username = kullaniciadi.text.toString()
            val email = emailhere.text.toString()
            val password = passhere.text.toString()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = hashMapOf(
                "username" to username,
                "email" to email,
                "password" to password
            )

            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "Kayıt başarılı", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Kayıt başarısız", Toast.LENGTH_SHORT).show()
                    Log.w(TAG, "Error adding document", e)
                }
        }
    }

    companion object {
        private const val TAG = "RegisterActivity"
    }
    fun kayitol(view:View) {
        val email = binding.emailhere.text.toString()
        val password = binding.passhere.text.toString()
        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Doğru bir şekilde email ve şifreyi griniz", Toast.LENGTH_LONG)
                .show()
        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
        val postMap = hashMapOf<String, Any>()
        postMap.put("name", binding.kullaniciadi.text.toString())
        firestore.collection("names").add(postMap).addOnSuccessListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
}





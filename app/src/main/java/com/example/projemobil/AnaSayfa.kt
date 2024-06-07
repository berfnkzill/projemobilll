package com.example.projemobil

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projemobil.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AnaSayfa : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)


        firestore=Firebase.firestore

        // GetData()


        enableEdgeToEdge()
        setContentView(R.layout.activity_ana_sayfa)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
/*
    fun GetData(){
        firestore.collection("name").addSnapshotListener { value, error ->
            if(error!=null){
                Toast.makeText(this,error.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if(value!=null)
                {
                    if(!value.isEmpty)
                    {
                        val documents=value.documents

                        for(i in documents){
                            val name= i.get("name") as String
                            val email=i.get("email")as String

                            val auth = null
                            if(email==auth.currentUser?.email)
                            {
                                binding.textView.setText(name)
                            }
                        }
                    }
                }


            }
        }

    }

    */
}
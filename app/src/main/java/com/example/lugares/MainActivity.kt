package com.example.lugares

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lugares.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.btLogin.setOnClickListener(){
            haceLogin()
        }

        binding.btRegister.setOnClickListener(){
            haceRegister()
        }


}

    private fun haceRegister() {
        val email= binding.etCorreo.text.toString()
        val clave = binding.etClave.text.toString()

        auth.createUserWithEmailAndPassword(email,clave).
                addOnCompleteListener(this){ task->
                    if(task.isSuccessful){
                        Log.d("Creando usuario","Registrado")
                        val user=auth.currentUser
                        actualiza(user)
                    }else{
                        Log.d("Creando usuario","Fallo")
                        Toast.makeText(baseContext,"Fallo",Toast.LENGTH_LONG).show()
                        actualiza(null)
                    }

                }
    }

    private fun haceLogin() {
        val email= binding.etCorreo.text.toString()
        val clave = binding.etClave.text.toString()

        auth.signInWithEmailAndPassword(email,clave).
        addOnCompleteListener(this){ task->
            if(task.isSuccessful){
                Log.d("Autenticando","Auntenticado")
                val user=auth.currentUser
                actualiza(user)
            }else{
                Log.d("Autenticando","Fallo")
                Toast.makeText(baseContext,"Fallo",Toast.LENGTH_LONG).show()
                actualiza(null)
            }

        }
    }
    private fun actualiza(user: FirebaseUser?) {
        if(user!=null){
            val intent = Intent(this,Principal::class.java)
            startActivity(intent)
        }

    }

    //Esto hara que una vez auntenticado...
    public override fun onStart(){
        super.onStart()
        val usuario= auth.currentUser
        actualiza(usuario)
    }

}
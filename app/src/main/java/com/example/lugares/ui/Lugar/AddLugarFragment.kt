package com.example.lugares.ui.Lugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.lugares.R
import com.example.lugares.databinding.FragmentAddLugarBinding
import com.example.lugares.databinding.FragmentLugarBinding
import com.example.lugares.model.Lugar
import com.example.lugares.viewmodel.LugarViewModel

class AddLugarFragment : Fragment() {

    private var _binding: FragmentAddLugarBinding? = null
    private val binding get() = _binding!!
    private lateinit var lugarViewModel : LugarViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddLugarBinding.inflate(inflater, container, false)

        lugarViewModel = ViewModelProvider(this).get(LugarViewModel::class.java)

        binding.btAgregar.setOnClickListener{addLugar()}
        return binding.root
    }

    private fun addLugar(){
        var nombre=binding.etNombre.text.toString()
        if(nombre.isNotEmpty()){
            var correo = binding.etCorreo.text.toString()
            var telefono = binding.etTelefono.text.toString()
            var web = binding.etWeb.text.toString()
            if(validate(nombre,correo, telefono, web)){
                val lugar = Lugar(0,nombre,correo,telefono,web,0.0,0.0,0.0,"","")
                lugarViewModel.addLugar(lugar)
                Toast.makeText(requireContext(),getString(R.string.msg_LugarAgregado),Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(requireContext(),getString(R.string.msg_FaltanDatos),Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun validate(nombre: String,correo: String,telefono: String,web: String): Boolean{
        return !(nombre.isEmpty()||correo.isEmpty()||telefono.isEmpty()||web.isEmpty())
    }

}
package com.example.scrambled

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.scrambled.databinding.FragmentSettingsBinding
import com.google.android.material.transition.MaterialFadeThrough


class Settings : Fragment(R.layout.fragment_settings) {
    private var _binding: FragmentSettingsBinding?=null
    private val binding get()=_binding!!
    private val viewModel:GameViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        //exitTransition = MaterialFadeThrough()
        _binding=FragmentSettingsBinding.inflate(inflater,container,false)
        binding.maxcount.setText(numbers.MaxVal.value.toString())
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navoptions= NavOptions.Builder()
            .setEnterAnim(R.anim.pop)

            .setPopEnterAnim(R.anim.slide_out_right)
            .setPopExitAnim(R.anim.slide_out_left)
            .build()
        binding.SAVENO.setOnClickListener {
            if (binding.maxcount.text.toString().toInt()<10 || binding.maxcount.text.toString().toInt()>50)
            {
                Toast.makeText(requireContext(), "choose val between 10-50", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                numbers.MaxVal.value=binding.maxcount.text.toString().toInt()
                findNavController().popBackStack()
            }
        }
        binding.CANCELNO.setOnClickListener {
            numbers.MaxVal.value=10
            binding.maxcount.setText(numbers.MaxVal.value.toString())
        }
        binding.button3.setOnClickListener {
            viewModel.reinitializeData()
            numbers.MaxVal.value=10
            binding.maxcount.setText(numbers.MaxVal.value.toString())

            findNavController().popBackStack()
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}

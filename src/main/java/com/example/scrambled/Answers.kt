package com.example.scrambled

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scrambled.databinding.FragmentAnswersBinding
import com.example.scrambled.databinding.FragmentSettingsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Answers : Fragment() {
    private var _binding: FragmentAnswersBinding?=null
    private val binding get()=_binding!!
    private val viewModel:GameViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentAnswersBinding.inflate(inflater,container,false)
        val arrayfinal:Array<String>
        arrayfinal=viewModel.listTOarray()
        val listview=binding.listviewwww
        val arrayAdapter=ArrayAdapter(requireContext(),R.layout.support_simple_spinner_dropdown_item,arrayfinal)
        listview.adapter=arrayAdapter

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onDestroy() {
        viewModel.reinitializeData()

        super.onDestroy()
        _binding=null
    }
}

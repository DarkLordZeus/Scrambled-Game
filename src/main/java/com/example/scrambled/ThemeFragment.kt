package com.example.scrambled

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import com.example.scrambled.databinding.FragmentAnswersBinding
import com.example.scrambled.databinding.FragmentThemeBinding


class ThemeFragment : Fragment(R.layout.fragment_theme) {
    private var _binding: FragmentThemeBinding?=null
    private val binding get()=_binding!!
    private val viewModel:GameViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentThemeBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.GroupofRADIO.setOnCheckedChangeListener { _, optionId ->
            kotlin.run {
            when (optionId) {
                R.id.radioButton->{ activity?.findViewById<ConstraintLayout>(R.id.nav_host)?.setBackgroundResource(R.drawable.forest)}
                R.id.radioButton2->{ activity?.findViewById<ConstraintLayout>(R.id.nav_host)?.setBackgroundResource(R.drawable.ferriswheel)}
                R.id.radioButton3->{ activity?.findViewById<ConstraintLayout>(R.id.nav_host)?.setBackgroundResource(R.drawable.birds)}
                R.id.radioButton4->{ activity?.findViewById<ConstraintLayout>(R.id.nav_host)?.setBackgroundResource(R.drawable.leaf)}
                R.id.radioButton5->{ activity?.findViewById<ConstraintLayout>(R.id.nav_host)?.setBackgroundResource(R.drawable.mountain)}
                R.id.radioButton6->{ activity?.findViewById<ConstraintLayout>(R.id.nav_host)?.setBackgroundResource(R.drawable.skyscraped)}

                R.id.radioButton8->{ activity?.findViewById<ConstraintLayout>(R.id.nav_host)?.setBackgroundResource(R.drawable.tree_ice)}

            }
        }
        }
    }
    override fun onDestroy() {


        super.onDestroy()
        _binding=null
    }
}
// binding.themechange.setOnClickListener {
//        activity?.findViewById<ConstraintLayout>(R.id.nav_host)?.setBackgroundResource(R.drawable.birds)
//        }
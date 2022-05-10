package com.example.scrambled

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.scrambled.databinding.GameFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.transition.MaterialFadeThrough

class GameFragment : Fragment(R.layout.game_fragment) {
    private var _binding: GameFragmentBinding?=null
    private val binding get()=_binding!!

   private val viewModel:GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //exitTransition = MaterialFadeThrough()
        setHasOptionsMenu(true)
        _binding=GameFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.scrambleword.text=viewModel.currentscrambledword
        numbers.currentnum.observe(viewLifecycleOwner)
        {
            binding.count.text="${numbers.currentnum.value}/${numbers.MaxVal.value} Words"
        }
        numbers.scorecard.observe(viewLifecycleOwner)
        {
            binding.score.text="Score:${numbers.scorecard.value}"
        }

        binding.skip.setOnClickListener { skipnext() }
        binding.submit.setOnClickListener { Onsubmitword() }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


    fun Onsubmitword() {
        viewModel.addtolist()
        if (numbers.currentnum.value?.equals(numbers.MaxVal.value) == true)
        {
            binding.submit.isClickable = false
            binding.skip.isClickable=false

            showdialogbox()
        }
        else
        {

            viewModel.checkright(binding.textedited?.text.toString())

            viewModel.nextword()
            binding.textedited?.setText("")
            binding.scrambleword.text=viewModel.currentscrambledword
        }
    }



    private fun showdialogbox() {


   MaterialAlertDialogBuilder(requireContext())

       .setTitle(R.string.congratulations)
       .setMessage("You Scored ${numbers.scorecard.value}/${numbers.MaxVal.value.toString().toInt()*4}")
       .setPositiveButton(getString(R.string.exit)) { _, _ ->
           activity?.finish()
       }
       .setNegativeButton(getString(R.string.retry)) { _, _ ->
           viewModel.reinitializeData()
           binding.submit.isClickable = true
           binding.skip.isClickable=true
           binding.scrambleword.text = viewModel.currentscrambledword
       }
       .setNeutralButton("ANSWERS"){ _, _ ->
           val action=GameFragmentDirections.actionGameFragmentToAnswers()
           binding.submit.isClickable = true
           binding.skip.isClickable=true
           findNavController().navigate(action)

       }
       .setCancelable(false)
       .show()
    }


    private fun skipnext() {

        viewModel.addtolist()
        if (numbers.currentnum.value?.equals(numbers.MaxVal.value) == true)
        {
            binding.submit.isClickable = false
            binding.skip.isClickable=false
            showdialogbox()}
        else {

            numbers.currentnum.value = numbers.currentnum.value?.plus(1)
            viewModel.nextword()
            binding.scrambleword.text = viewModel.currentscrambledword
        }
        }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu);

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== R.id.restart )
            {
                viewModel.reinitializeData()

            }


        return super.onOptionsItemSelected(item)
    }



}
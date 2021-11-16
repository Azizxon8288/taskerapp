package com.example.taskerapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.akexorcist.snaptimepicker.SnapTimePickerDialog
import com.andrewjapar.rangedatepicker.CalendarPicker
import com.example.taskerapp.databinding.FragmentAddTaskBinding

class AddTaskFragment : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.edTv.windowToken, 0)
        binding.apply {
            cancelBtn.setOnClickListener {
                findNavController().popBackStack()
            }
            doneBtn.setOnClickListener {
                Toast.makeText(requireContext(), "Umarxon aka qalesiz!!!", Toast.LENGTH_SHORT)
                    .show()
            }
            date.setOnClickListener {
                CalendarPicker(requireContext()).apply {
                    showDayOfWeekTitle(false)
                    setMode(CalendarPicker.SelectionMode.SINGLE)
                }
            }

            time.setOnClickListener {
                SnapTimePickerDialog.Builder().apply {
                    setPositiveButtonText(R.string.yes)
                    setNegativeButtonText(R.string.no)
                }.build().apply {

                }.show(parentFragmentManager, "")
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
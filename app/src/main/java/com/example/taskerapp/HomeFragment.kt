package com.example.taskerapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.navigation.fragment.findNavController
import com.example.taskerapp.databinding.FragmentHomeBinding
import java.lang.reflect.Method

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.apply {
            add.setOnClickListener {
                val popupMenu = PopupMenu(requireContext(), binding.add)
                popupMenu.apply {
                    inflate(R.menu.popup)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.task ->
                                findNavController().navigate(R.id.addTaskFragment)
                            R.id.list ->
                                Toast.makeText(requireContext(), "List", Toast.LENGTH_SHORT).show()
                        }
                        false
                    }

                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    popupMenu.setForceShowIcon(true)
                } else {
                    try {
                        val fields = popupMenu.javaClass.declaredFields
                        for (field in fields) {
                            if ("mPopup" == field.name) {
                                field.isAccessible = true
                                val menuPopupHelper = field[popupMenu]
                                val classPopupHelper =
                                    Class.forName(menuPopupHelper.javaClass.name)
                                val setForceIcons: Method = classPopupHelper.getMethod(
                                    "setForceShowIcon",
                                    Boolean::class.javaPrimitiveType
                                )
                                setForceIcons.invoke(menuPopupHelper, true)
                                break
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                popupMenu.show()
            }
        }

        return binding.root
    }
}
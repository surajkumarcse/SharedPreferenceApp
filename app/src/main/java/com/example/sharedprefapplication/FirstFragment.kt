package com.example.sharedprefapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sharedprefapplication.Model.KeyValue
import com.example.sharedprefapplication.Model.KeyValueHelper
import com.example.sharedprefapplication.databinding.FragmentFirstBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.insert.setOnClickListener {

            val key = binding.keyEditText.text.toString()
            val value = binding.valueEditText.text.toString()
            val keyValue = KeyValue(key = key, value = value)
            KeyValueHelper.createKeyValuePair(requireActivity().applicationContext,
                    keyValue
                )
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.delete.setOnClickListener {
            val key = binding.keyEditText.text.toString()
            val keyValue = KeyValue(key = key, value = "")
            KeyValueHelper.deleteKeyValuePair(
                requireActivity().applicationContext, keyValue
            )
        }

        binding.retrieve.setOnClickListener {
            val key = binding.keyEditText.text.toString()
            val keyValue = KeyValue(key = key, value = "")

            GlobalScope.launch {
                val retrieved = KeyValueHelper.retrieveKeyValuePair(
                    requireActivity().applicationContext, key
                )

                binding.textView.text = retrieved;
            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
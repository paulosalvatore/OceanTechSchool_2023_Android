package com.oceanbrasil.oceantechschool_2023_android.ui.learn_assistant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.oceanbrasil.oceantechschool_2023_android.databinding.FragmentLearnAssistantBinding

class LearnAssistantFragment : Fragment() {

    private var _binding: FragmentLearnAssistantBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(LearnAssistantViewModel::class.java)

        _binding = FragmentLearnAssistantBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textLearnAssistant
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

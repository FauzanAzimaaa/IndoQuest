package com.example.indoquest.ui.questshare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.indoquest.R
import com.example.indoquest.databinding.FragmentHomeBinding
import com.example.indoquest.databinding.FragmentQuestShareBinding
import com.example.indoquest.ui.home.HomeViewModel

class QuestShareFragment : Fragment() {

    private var _binding: FragmentQuestShareBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val questShareViewModel =
            ViewModelProvider(this).get(QuestShareViewModel::class.java)

        _binding = FragmentQuestShareBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textQuestShare
        questShareViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
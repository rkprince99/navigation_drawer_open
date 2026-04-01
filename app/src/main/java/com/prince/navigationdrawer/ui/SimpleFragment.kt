package com.prince.navigationdrawer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prince.navigationdrawer.databinding.FragmentSimpleBinding

class SimpleFragment : Fragment() {

    private var _binding: FragmentSimpleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimpleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString(ARG_TITLE) ?: "Home"
        binding.contentTitle.text = title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TITLE = "arg_title"

        fun newInstance(title: String): SimpleFragment {
            val fragment = SimpleFragment()
            fragment.arguments = Bundle().apply {
                putString(ARG_TITLE, title)
            }
            return fragment
        }
    }
}

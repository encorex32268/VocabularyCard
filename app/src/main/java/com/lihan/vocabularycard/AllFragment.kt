package com.lihan.vocabularycard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.lihan.vocabularycard.databinding.FragmentAllBinding

class AllFragment : Fragment() {

    private lateinit var binding : FragmentAllBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            allRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(requireContext(),2)

            }
        }

    }



}
package com.lihan.vocabularycard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lihan.vocabularycard.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val TAG = HomeFragment::class.java.simpleName

    private lateinit var binding : FragmentHomeBinding
    private lateinit var mAdapter : HomeVocabularyCardAdapter
    private val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            Log.d(TAG, "onMove: ")
            return false;
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            mAdapter.vocabularys.removeAt(position)
            mAdapter.notifyDataSetChanged()

        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            homeFloatingActionButton.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToCreateFragment()
                view.findNavController().navigate(action)
            }

            homeRecyclerView.apply {

                mAdapter = HomeVocabularyCardAdapter(getVocabularyListSharedPreferences(SHAREDPREFERENCES_VOCABULARYLIST))
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = mAdapter
            }
            val mItemTouchHelper = ItemTouchHelper(itemTouchHelper)
            mItemTouchHelper.attachToRecyclerView(homeRecyclerView)
        }

    }






}
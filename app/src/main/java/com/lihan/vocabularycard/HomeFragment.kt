package com.lihan.vocabularycard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lihan.vocabularycard.databinding.FragmentHomeBinding
import java.util.*


class HomeFragment : Fragment() {

    private val TAG = HomeFragment::class.java.simpleName

    private lateinit var binding : FragmentHomeBinding
    private lateinit var mAdapter : HomeVocabularyCardAdapter
    private lateinit var viewModel : HomeFragmentViewModel
    private val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false;
        }

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            AlertDialog.Builder(requireContext())
                .setTitle("Move to Storage")
                .setMessage("You sure ?")
                .setPositiveButton("Yes") { _, _ ->
                    val position = viewHolder.adapterPosition
                    val nowList = getVocabularyListSharedPreferences(SHAREDPREFERENCES_NOWLIST)
                    nowList.removeAt(position)
                    saveVocabularyListSharedPreferences(nowList, SHAREDPREFERENCES_NOWLIST)
                    mAdapter.vocabularys.removeAt(position)
                    mAdapter.notifyDataSetChanged()
                }.setNegativeButton("No",null)
                .setOnDismissListener {
                    mAdapter.notifyDataSetChanged()
                }
                .show()


        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        Log.d(TAG, "onCreateView: ")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            homeFloatingActionButton.setOnClickListener {
                startActivity(Intent(requireContext(),CreateActivity::class.java))
            }
            homeRecyclerView.apply {

                mAdapter = HomeVocabularyCardAdapter(arrayListOf())
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = mAdapter
            }
            val mItemTouchHelper = ItemTouchHelper(itemTouchHelper)
            mItemTouchHelper.attachToRecyclerView(homeRecyclerView)

            viewModel = ViewModelProvider(requireActivity()).get(HomeFragmentViewModel::class.java)
            viewModel.getVocabularys().observe(requireActivity(),
                Observer<ArrayList<Vocabulary>> {
                    mAdapter.vocabularys = it
                    mAdapter.notifyDataSetChanged()
                    Log.d(TAG, "onViewCreated: ${it.size}")
                })
        }

    }






}
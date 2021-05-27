package com.lihan.vocabularycard.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
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
import com.lihan.vocabularycard.activity.CreateActivity
import com.lihan.vocabularycard.model.Vocabulary
import com.lihan.vocabularycard.databinding.FragmentHomeBinding
import com.lihan.vocabularycard.model.SHAREDPREFERENCES_NOWLIST
import com.lihan.vocabularycard.model.getVocabularyListSharedPreferences
import com.lihan.vocabularycard.model.saveVocabularyListSharedPreferences
import com.lihan.vocabularycard.view.HomeVocabularyCardAdapter
import com.lihan.vocabularycard.viewmodel.HomeFragmentViewModel
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

    private val CREATEACTIVITY_CODE = 101
    private val CREATEACTIVITY_REQEUST_CODE = 1101

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
                startActivityForResult(Intent(requireContext(), CreateActivity::class.java),CREATEACTIVITY_REQEUST_CODE)
            }
            homeRecyclerView.apply {

                mAdapter = HomeVocabularyCardAdapter(arrayListOf())
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = mAdapter
            }
            val mItemTouchHelper = ItemTouchHelper(itemTouchHelper)
            mItemTouchHelper.attachToRecyclerView(homeRecyclerView)

            viewModel = ViewModelProvider(
            requireActivity()).get(HomeFragmentViewModel::class.java)
            viewModel.getVocabularys().observe(requireActivity(),
                Observer<ArrayList<Vocabulary>> {
                    mAdapter.vocabularys = it
                    mAdapter.notifyDataSetChanged()
                })
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATEACTIVITY_REQEUST_CODE && resultCode == RESULT_OK){
            viewModel.refresh()
        }
    }




}
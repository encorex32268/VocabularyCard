package com.lihan.vocabularycard

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.lihan.vocabularycard.databinding.FragmentCreateBinding
import com.pes.androidmaterialcolorpickerdialog.ColorPicker
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback

class CreateFragment : Fragment(R.layout.fragment_create) {
    private val TAG = CreateFragment::class.java.simpleName
    private lateinit var binding : FragmentCreateBinding
    private var mColor =-1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentCreateBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mColor = getIntSharedPreferences(SHAREDPREFERENCES_COLOR)
        binding.apply {

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_colorPicker->{
                val colorPicker = ColorPicker(requireActivity())
                colorPicker.color = mColor
                colorPicker.show()
                colorPicker.enableAutoClose()
                colorPicker.setCallback { selectedColor ->
                    mColor = selectedColor
                    setIntSharedPreferences(SHAREDPREFERENCES_COLOR,mColor)
                    binding.apply {
                        createCardFront.setCardBackgroundColor(mColor)
                        createCardBack.setCardBackgroundColor(mColor)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
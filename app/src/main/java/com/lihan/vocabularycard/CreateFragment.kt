package com.lihan.vocabularycard

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.lihan.vocabularycard.databinding.FragmentCreateBinding
import com.pes.androidmaterialcolorpickerdialog.ColorPicker

class CreateFragment : Fragment(R.layout.fragment_create) {
    private val TAG = CreateFragment::class.java.simpleName
    private lateinit var binding : FragmentCreateBinding

    private lateinit var tag : Tag

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
        tag = getTagObject()

        binding.apply {
            tag.apply {
                createColorView.setBackgroundColor(color)
                createCardBack.setCardBackgroundColor(color)
                createCardFront.setCardBackgroundColor(color)
                createColorTagNameTextView.setText(name)

            }


            createFloatingActionButton.setOnClickListener {
                if(createCardFrontVocabulary.text.toString().isNullOrEmpty() || createCardBackVocabularyExplain.text.toString().isNullOrEmpty())return@setOnClickListener
                val tagList = getTagListSharedPreferences(SHAREDPREFERENCES_TAGLIST)
                tag.name = createColorTagNameTextView.text.toString()?:"Unkown"
                tagList.add(tag)
                val vocabulary = Vocabulary().apply {
                    front = createCardFrontVocabulary.text.toString()
                    back = createCardBackVocabularyExplain.text.toString()
                    tag = tag
                }
                val vocabularyList = getVocabularyListSharedPreferences(SHAREDPREFERENCES_VOCABULARYLIST)
                vocabularyList.add(vocabulary)
                saveVocabularyListSharedPreferences(vocabularyList, SHAREDPREFERENCES_VOCABULARYLIST)

                view.findNavController().popBackStack()
            }
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
                colorPicker.color = tag.color
                colorPicker.show()
                colorPicker.enableAutoClose()
                colorPicker.setCallback { selectedColor ->
                    tag.color = selectedColor
                    saveTagObject(tag)
                    binding.apply {
                        tag.apply {
                            createCardFront.setCardBackgroundColor(color)
                            createCardBack.setCardBackgroundColor(color)
                            createColorView.setBackgroundColor(color)
                        }

                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
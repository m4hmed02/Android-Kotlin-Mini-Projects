package com.example.fragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fragments.R

private const val ARGS_TEXT = "argsText"
private const val ARGS_NUMBER = "argsNumber"
class ExampleFragment: Fragment() {

    private var name = ""
    private var age = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.example_fragment, container, false)
        val textView: TextView = view.findViewById(R.id.textview)

        arguments?.let {
            name = it.getString(ARGS_TEXT, "DEFAULT VALUE")
            age = it.getInt(ARGS_NUMBER, -1)
        }

        textView.text = name + "\n" + age + "\n"

        return view
    }


    companion object {

//        fun newInstance(text: String, number: Int) : ExampleFragment {
//
//            val fragment = ExampleFragment()
//            val bundle = Bundle()
//            bundle.putString(ARGS_TEXT, text)
//            bundle.putInt(ARGS_NUMBER, number)
//
//            fragment.arguments = bundle
//            return fragment
//        }


        //another way for doing same thing
        fun newInstance(text: String, number: Int) = ExampleFragment().apply {
            arguments = Bundle().apply {
                putString(ARGS_TEXT, text)
                putInt(ARGS_NUMBER, number)
            }
        }
    }
}
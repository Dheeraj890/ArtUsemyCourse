package com.example.artudemydevelopment.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.example.artudemydevelopment.R
import com.example.artudemydevelopment.model.Animal
import com.example.artudemydevelopment.util.getProgresDrawable
import com.example.artudemydevelopment.util.loadImage
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {


    var animal:Animal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        arguments?.let {

            animal=DetailFragmentArgs.fromBundle(it).animal
        }

context?.let {
    animalImage.loadImage(animal?.imageUrl, getProgresDrawable(it))
}

        animalName.text=animal?.name
        animalLocation.text=animal?.location
        animalLifeSpan.text=animal?.lifeSpan
        animalDiet.text=animal?.diet

//        buttonList.setOnClickListener {
//
//            val actions=DetailFragmentDirections.actionDummyFragment()
//            Navigation.findNavController(it).navigate(actions)
//        }
    }




}

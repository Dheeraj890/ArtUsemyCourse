package com.example.artudemydevelopment.view


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

import com.example.artudemydevelopment.R
import com.example.artudemydevelopment.databinding.FragmentDetailBinding
import com.example.artudemydevelopment.model.Animal
import com.example.artudemydevelopment.util.getProgresDrawable
import com.example.artudemydevelopment.util.loadImage


/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {


    var animal:Animal? = null

    private lateinit var databinding:FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        databinding=DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)
        return databinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        arguments?.let {

            animal=DetailFragmentArgs.fromBundle(it).animal
        }

//context?.let {
//    databinding.animalImage.loadImage(animal?.imageUrl, getProgresDrawable(it))
//
//}
//
//        animalName.text=animal?.name
//        animalLocation.text=animal?.location
//        animalLifeSpan.text=animal?.lifeSpan
//        animalDiet.text=animal?.diet

//        buttonList.setOnClickListener {
//
//            val actions=DetailFragmentDirections.actionDummyFragment()
//            Navigation.findNavController(it).navigate(actions)
//        }

        animal?.imageUrl.let {

            setupBackgroundColor(it)
        }
        databinding.animal=animal

    }

    private fun setupBackgroundColor(imageUrl: String?) {


        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .into(object:CustomTarget<Bitmap>(){
                override fun onLoadCleared(placeholder: Drawable?) {


                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {

                    Palette.from(resource)
                        .generate(){palette ->


                            val intColor=palette?.lightMutedSwatch?.rgb?:0
                          //  databinding.animalLayout.setBackgroundColor(intColor)

                            databinding.color=intColor

                        }

                }

            })

    }


}

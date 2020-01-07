package com.example.artudemydevelopment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.artudemydevelopment.R
import com.example.artudemydevelopment.model.Animal
import com.example.artudemydevelopment.util.getProgresDrawable
import com.example.artudemydevelopment.util.loadImage
import kotlinx.android.synthetic.main.item_animal.view.*



class AnimalListAdapter(private val animalList: ArrayList<Animal>):RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {

        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.item_animal,parent,false)
        return AnimalViewHolder(view)
    }

    override fun getItemCount()=animalList.size

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {


        holder.view.animalName.text=animalList[position].name
        holder.view.animalImage.loadImage(animalList[position].imageUrl,
            getProgresDrawable(holder.view.context))




    }


    class AnimalViewHolder(var view: View):RecyclerView.ViewHolder(view){


    }



    fun updateAnimalList(newAnimalList: List<Animal>){

        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()


    }
}
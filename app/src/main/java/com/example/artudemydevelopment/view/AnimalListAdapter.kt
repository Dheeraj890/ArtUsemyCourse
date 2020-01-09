package com.example.artudemydevelopment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.artudemydevelopment.R
import com.example.artudemydevelopment.databinding.ItemAnimalBinding
import com.example.artudemydevelopment.model.Animal
import com.example.artudemydevelopment.util.getProgresDrawable
import com.example.artudemydevelopment.util.loadImage
import kotlinx.android.synthetic.main.item_animal.view.*



class AnimalListAdapter(private val animalList: ArrayList<Animal>):RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>(),AnimalCLickListener {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {

        val inflater=LayoutInflater.from(parent.context)
       // val view=inflater.inflate(R.layout.item_animal,parent,false)

        val view=DataBindingUtil.inflate<ItemAnimalBinding>(inflater,R.layout.item_animal,parent,false)
        return AnimalViewHolder(view)
    }

    override fun getItemCount()=animalList.size

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {

        holder.view.animal=animalList[position]
        holder.view.listener=this

//        holder.view.animalName.text=animalList[position].name
//        holder.view.animalImage.loadImage(animalList[position].imageUrl,
//            getProgresDrawable(holder.view.context))
//        holder.view.animalLayout.setOnClickListener{
//
//            val action=ListFragmentDirections.actionDetail(animalList[position],null)
//            Navigation.findNavController(holder.view).navigate(action)
//
//        }




    }

    override fun onClick(v: View) {

        for(animal in animalList){

            if(v.tag==animal.name){

                val action=ListFragmentDirections.actionDetail(animal,null)
            Navigation.findNavController(v).navigate(action)
            }
        }
    }


    class AnimalViewHolder(var view: ItemAnimalBinding):RecyclerView.ViewHolder(view.root){


    }



    fun updateAnimalList(newAnimalList: List<Animal>){

        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()


    }
}
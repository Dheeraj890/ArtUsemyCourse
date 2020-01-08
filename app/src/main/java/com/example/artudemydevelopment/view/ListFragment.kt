package com.example.artudemydevelopment.view


import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager

import com.example.artudemydevelopment.R
import com.example.artudemydevelopment.model.Animal
import com.example.artudemydevelopment.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    private lateinit var viewModel:ListViewModel
    private val listAdapter=AnimalListAdapter(arrayListOf())
    private val animalListDataObserver=Observer<List<Animal>>{ list->


        list?.let{

            animalList.visibility=View.VISIBLE
            listAdapter.updateAnimalList(list)

    }
    }


    private val loadingLiveDataObserver= Observer<Boolean> {isLoading ->


        loadingView.visibility=if(isLoading) View.VISIBLE else View.GONE
        if(isLoading){

            listError.visibility=View.GONE
            animalList.visibility=View.GONE
        }
    }


    private val errorLiveDataObserver= Observer<Boolean> {isError ->



        listError.visibility=if(isError) View.VISIBLE else View.GONE

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel=ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.animals.observe(this,animalListDataObserver)


        viewModel.loading.observe(this,loadingLiveDataObserver)

        viewModel.loadError.observe(this,errorLiveDataObserver)

        viewModel.refresh()

        animalList.apply {


            layoutManager=GridLayoutManager(context,2)
            adapter=listAdapter
        }



        refreshLayout.setOnRefreshListener{


            animalList.visibility=View.GONE
            listError.visibility=View.GONE
            loadingView.visibility=View.VISIBLE
            viewModel.hardRefreshKey()
            refreshLayout.isRefreshing=false
        }




//        buttonDetail.setOnClickListener {
//
//            val actions=ListFragmentDirections.actionDetail()
//            Navigation.findNavController(it).navigate(actions)
//        }
    }


}

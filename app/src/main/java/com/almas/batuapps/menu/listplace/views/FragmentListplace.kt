package com.almas.batuapps.menu.listplace.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.almas.batuapps.R
import com.almas.batuapps.databinding.FragmentListplaceBinding
import com.almas.batuapps.menu.listplace.adapters.ListplaceAdapter
import com.almas.batuapps.menu.listplace.models.ListplaceModel
import com.almas.batuapps.menu.listplace.viewmodels.FragmentListplaceViewModel

class FragmentListplace: Fragment() {
    companion object {
        fun getInstance(): Fragment {
            return FragmentListplace()
        }
    }

    private lateinit var viewModel: FragmentListplaceViewModel
    private lateinit var binding: FragmentListplaceBinding
    private lateinit var adapter: ListplaceAdapter
   private var listPlace : MutableList<ListplaceModel> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listplace, container, false)
        viewModel = ViewModelProviders.of(this).get(FragmentListplaceViewModel::class.java)
        binding.listplace = viewModel

        setupRecyclerView()
        observeLiveData()

        viewModel.getListplace()
        viewModel.listPlaceResponse.observe(this, Observer {
            onListDataChange(it)
        })
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.destroy()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerViewPlace.layoutManager = layoutManager
        adapter = ListplaceAdapter(activity!!, listPlace)
        binding.recyclerViewPlace.adapter = adapter
    }


    private fun observeLiveData() {
        viewModel.listPlaceResponse.observe(this, Observer {
            //adapter.setData(it?.data!!)
            adapter.notifyDataSetChanged()
        })
        viewModel.error.observe(this, Observer {

        })
    }

    private fun onListDataChange(listplace: MutableList<ListplaceModel>?){
        if(listplace?.isNotEmpty()!!){
            this.listPlace.clear()
            this.listPlace.addAll(listplace)
            binding.recyclerViewPlace.post{
                adapter.notifyDataSetChanged()
                binding.recyclerViewPlace.scrollToPosition(0)
            }
        }
    }
}

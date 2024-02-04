package com.example.restoranapp.ui.order

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.realestateapp.ui.main.OrderAdapter
import com.example.restoranapp.R
import com.example.restoranapp.databinding.FragmentOrderBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding
    private val viewModel by viewModel<OrderViewModel>()
    private lateinit var adapter: OrderAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getItem()
        initial()
        viewModel.resultsListLiveData.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }
    private fun initial() {
        val recyclerView = binding.recyclerViewHome
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = OrderAdapter(clickListener = { item ->
            val bundle = Bundle()
            bundle.putParcelable("item", item)
            findNavController().navigate(R.id.action_navigation_dashboard_to_itemFragment,bundle)
        })
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
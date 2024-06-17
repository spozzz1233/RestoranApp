package com.example.restoranapp.ui.cart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.autorus.ui.cart.CartAdapter
import com.example.autorus.util.CartSave
import com.example.restoranapp.R
import com.example.restoranapp.databinding.FragmentHomeBinding
import com.example.restoranapp.databinding.FragmentOrderBinding
import com.example.restoranapp.domain.model.cart
import com.example.restoranapp.domain.model.item

class CartFragment : Fragment() {

    private lateinit var cartHistory: CartSave
    lateinit var binding: FragmentHomeBinding
    private lateinit var cartAdapter: CartAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initial()
        checkFavorite()
        cartHistory = CartSave(requireContext())
        binding.toOrder.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_dashboard)
        }
        binding.clear.setOnClickListener {
            cartHistory.clearSharedPreferences()
            cartAdapter.updateData()
            checkFavorite()
        }
        var a = 0
        for(c in cart){
            a += c.price

        }
        binding.price.text = a.toString()
    }

    private fun initial() {
        cartAdapter = CartAdapter(clickListener = { item ->
            val bundle = Bundle()
            bundle.putParcelable("item", item)
            findNavController().navigate(R.id.action_navigation_home_to_itemFragment,bundle)
        })
        binding.recyclerViewHome.adapter = cartAdapter

    }
    private fun checkFavorite(){
        if(cart.isEmpty()){
            binding.toOrder.visibility = View.VISIBLE
            binding.placeholder.visibility = View.VISIBLE
            binding.price.visibility = View.GONE
            binding.clear.visibility = View.GONE
            binding.recyclerViewHome.visibility = View.GONE
        }else{
            binding.toOrder.visibility = View.GONE
            binding.placeholder.visibility = View.GONE
            binding.price.visibility = View.VISIBLE
            binding.clear.visibility = View.VISIBLE
            binding.recyclerViewHome.visibility = View.VISIBLE
        }
    }
}
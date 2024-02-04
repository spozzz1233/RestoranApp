package com.example.restoranapp.ui.order.item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.autorus.util.CartSave
import com.example.restoranapp.databinding.FragmentItemBinding
import com.example.restoranapp.databinding.FragmentOrderBinding
import com.example.restoranapp.domain.model.MenuItem


class ItemFragment : Fragment() {

    private lateinit var cartSave: CartSave
    lateinit var binding: FragmentItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartSave = CartSave(requireContext())
        val item = arguments?.getParcelable<MenuItem>("item")

        binding.addCart.setOnClickListener {
            if (item != null) {
                cartSave.saveCartHistory(item)
            }
            if (item != null) {
                Toast.makeText(requireContext(), "${item.itemName} добавлен в корзину", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        if (item != null) {
            Glide.with(requireContext())
                .load(item.image)
                .transform(RoundedCorners(8))
                .into(binding.cover)
            binding.priceInt.text = item.price.toString()
            binding.partName.text = item.itemName
            binding.descriptionName.text = item.description
        }

    }
}
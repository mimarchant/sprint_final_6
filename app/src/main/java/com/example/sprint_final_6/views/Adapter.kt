package com.example.sprint_final_6.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sprint_final_6.R
import com.example.sprint_final_6.data.local.list.PhoneListEntity
import com.example.sprint_final_6.databinding.FragmentPhoneItemBinding

class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>() {
    lateinit var binding: FragmentPhoneItemBinding
    private val listOfPhones = mutableListOf<PhoneListEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        binding = FragmentPhoneItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val cellPhone = listOfPhones[position]
        holder.bind(cellPhone)
    }

    override fun getItemCount(): Int {
        return listOfPhones.size
    }

    fun setData(cellPhones: List<PhoneListEntity>) {
        this.listOfPhones.clear()
        this.listOfPhones.addAll(cellPhones)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: FragmentPhoneItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(phone: PhoneListEntity) {
            binding.phoneItemImg.load(phone.image)
            binding.phoneItemName.text = phone.name
            binding.phonePrice.text = "$ ${phone.price}"
            binding.cardContainer.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("id", phone.id.toString())
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_phoneListFragment2_to_phoneDetail, bundle)
            }
        }
    }
}
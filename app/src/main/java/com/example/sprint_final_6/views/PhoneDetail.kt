package com.example.sprint_final_6.views

import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.sprint_final_6.R
import com.example.sprint_final_6.databinding.FragmentPhoneDetailBinding
import java.text.NumberFormat
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PhoneDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class PhoneDetail : Fragment() {
    lateinit var binding: FragmentPhoneDetailBinding
    private val viewModel: PhoneViewModel by activityViewModels()

    private var id: String? = null
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            id = it.getString("id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhoneDetailBinding.inflate(layoutInflater)
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        viewModel.phoneDetailsData(id.toString().toInt())
            .observe(viewLifecycleOwner) {
                if (it != null) {
                    val subject = "Consulta sobre producto ${it.name}, id ${it.id}"
                    val message = "Hola, me gustaría tener más información sobre el producto ${it.name}, id ${it.id}. Pueden contactarme a mi correo o a mi número +56935332478, saludos."

                    binding.phoneDetailBtn.setOnClickListener {
                        val mail = "mimarchttgmail.com"
                        val mailIntent = Intent(ACTION_SEND, Uri.parse(mail))
                        mailIntent.type = "text/plain"
                        mailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))
                        mailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
                        mailIntent.putExtra(Intent.EXTRA_TEXT, message)
                        startActivity(Intent.createChooser(mailIntent, "Send Mail"))
                    }
                }
            }

        viewModel.getPhoneDetails(id.toString().toInt())
        viewModel.phoneDetailsData(id.toString().toInt())
            .observe(viewLifecycleOwner) {
                if (it != null) {
                    binding.phoneDetailImg.load(it.image)
                    binding.phoneDetailLastPrice.text = "ANTES ${formatAsChileanPeso(it.lastPrice.toDouble())}"
                    binding.phoneDetailCurrentPrice.text = "AHORA ${formatAsChileanPeso(it.price.toDouble())}"
                    binding.phoneDetailCredit.text = if (it.credit) "Aceptamos crédito" else "Sólo efectivo"
                    binding.phoneDetailCreditImg.visibility = if (it.credit) View.VISIBLE else View.GONE
                    binding.phoneDetailCardDescription.text = it.description
                    binding.phoneDetailCardTitle.text = it.name
                    binding.phoneDetailCardPrice.text = formatAsChileanPeso(it.price.toDouble())
                    binding.phoneDetailLastPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
            }
    }

    fun formatAsChileanPeso(amount: Double): String {
        val chileanFormat = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        return chileanFormat.format(amount)
    }


}
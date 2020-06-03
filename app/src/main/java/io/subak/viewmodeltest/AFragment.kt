package io.subak.viewmodeltest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import io.subak.viewmodeltest.databinding.FragmentABinding

class AFragment : Fragment() {

    private lateinit var viewModel: UDPViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(activity!!).get(UDPViewModel::class.java)

        val binding: FragmentABinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_a, container, false)
        binding.gotoBBtn.setOnClickListener {
            findNavController().navigate(AFragmentDirections.actionAFragmentToBFragment())
        }


        viewModel._dataSize.observe(this, Observer { remaining ->
            binding.inputDataSizeA.text = remaining.toString() })

        return binding.root
    }



}
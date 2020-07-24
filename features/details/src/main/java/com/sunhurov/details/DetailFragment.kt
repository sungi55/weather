package com.sunhurov.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.sunhurov.common.base.BaseFragment
import com.sunhurov.common.base.BaseViewModel
import com.sunhurov.model.DailyForecast
import com.sunhurov.model.HourlyForecast
import com.sunhurov.model.CurrentCondition
import com.sunhurov.detail.databinding.FragmentDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * A simple [BaseFragment] subclass
 * that will show the [CurrentCondition], [HourlyForecast], [DailyForecast] details.
 */
class DetailFragment : BaseFragment() {

    // FOR DATA
    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onResume() {
        super.onResume()
//        viewModel.loadDetailsWhenActivityStarts(args.key)
    }

    override fun getViewModel(): BaseViewModel = viewModel

}

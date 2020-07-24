package com.sunhurov.search

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.sunhurov.common.base.BaseFragment
import com.sunhurov.common.base.BaseViewModel
import com.sunhurov.search.databinding.FragmentSearchBinding
import com.sunhurov.search.views.DebouncingQueryTextListener
import com.sunhurov.search.views.SearchAdapter
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * A simple [BaseFragment] subclass
 * that will show a list of top [Movie] from The Movie BD API.
 */
class SearchFragment : BaseFragment() {

    // FOR DATA
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var binding: FragmentSearchBinding

    private var searchView: SearchView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureRecyclerView()
    }

    override fun onStop() {
        super.onStop()
        searchView?.clearFocus()
    }


    override fun getViewModel(): BaseViewModel = viewModel


    private fun configureRecyclerView() {
        binding.fragmentSearchRv.also {
            it.adapter = SearchAdapter(viewModel)
            it.addItemDecoration(DividerItemDecoration(it.context, DividerItemDecoration.VERTICAL))
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        searchView = menu.findItem(R.id.action_search)?.actionView as SearchView
        configureSearchView()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun configureSearchView() {
        searchView?.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView?.inputType = InputType.TYPE_CLASS_TEXT
        val searchAutoComplete: SearchView.SearchAutoComplete =
            searchView!!.findViewById(androidx.appcompat.R.id.search_src_text)
        searchAutoComplete.setTextColor(Color.WHITE)
        searchAutoComplete.setHintTextColor(Color.WHITE)

        searchView?.setOnQueryTextListener(
            DebouncingQueryTextListener(
                this@SearchFragment.lifecycle
            ) { newText ->
                newText?.let {
                    viewModel.searchText = it
                }
            }
        )
        searchView?.isIconified = false
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.action_search).isVisible = true
    }

}

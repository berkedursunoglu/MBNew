package com.berkedursunoglu.mbnews.news

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkedursunoglu.mbnews.Constants
import com.berkedursunoglu.mbnews.R
import com.berkedursunoglu.mbnews.databinding.FragmentCategoryNewsBinding
import com.berkedursunoglu.mbnews.news.viewmodels.CategoryViewModel
import com.berkedursunoglu.mbnews.recyclerviews.CategoryRecyclerView


class CategoryNews : Fragment() {

    private lateinit var dataBinding: FragmentCategoryNewsBinding
    private lateinit var viewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val back = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val action = CategoryNewsDirections.actionCategoryNewsToNewsPage()
                view?.findNavController()?.navigate(action)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, back)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_category_news, container, false)
        val supportbar = activity as AppCompatActivity
        supportbar.setSupportActionBar(dataBinding.categoryNewsToolbar)
        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        val args: CategoryNewsArgs by navArgs()
        viewModel.getNewsbyCategoryId(args.categoryId)
        dataBinding.categoryNewsRecyclerview.layoutManager = LinearLayoutManager(context)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.categoryNews.observe(viewLifecycleOwner, Observer {
            val rv = CategoryRecyclerView(it.news)
            dataBinding.categoryNewsRecyclerview.adapter = rv
        })
    }
}



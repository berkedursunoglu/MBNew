package com.berkedursunoglu.mbnews.news

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkedursunoglu.mbnews.Constants
import com.berkedursunoglu.mbnews.R
import com.berkedursunoglu.mbnews.databinding.FragmentNewsPageBinding
import com.berkedursunoglu.mbnews.news.viewmodels.NewsPageViewModel
import com.berkedursunoglu.mbnews.profile.ProfileActivity
import com.berkedursunoglu.mbnews.recyclerviews.EconomyRecyclerView
import com.berkedursunoglu.mbnews.recyclerviews.PublicNewsRecyclerView
import com.berkedursunoglu.mbnews.recyclerviews.SportNewsRecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel


class NewsPage : Fragment() {

    private lateinit var dataBinding: FragmentNewsPageBinding
    private lateinit var viewModel: NewsPageViewModel
    private lateinit var toogle: ActionBarDrawerToggle
    var sliderArray = ArrayList<SlideModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        dataBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_news_page, container, false
        )
        viewModel = ViewModelProvider(this)[NewsPageViewModel::class.java]
        viewModel.getFourNews()
        viewModel.getLasttenNewsbyCategoryId(Constants.PUBLIC)
        viewModel.getLasttenNewsbyCategoryId(Constants.SPORT)
        viewModel.getLasttenNewsbyCategoryId(Constants.ECONOMY)
        toogle = ActionBarDrawerToggle(
            requireActivity(), dataBinding.drawerLayout, dataBinding.toolbar,
            R.string.open,
            R.string.close
        )
        dataBinding.drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
        val actionBar = (activity as AppCompatActivity?)!!.supportActionBar
        val actionToolbar = (activity as AppCompatActivity?)?.setSupportActionBar(dataBinding.toolbar)
        setHasOptionsMenu(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.fournews.observe(viewLifecycleOwner, Observer {
            it.forEach {
                val slide = SlideModel(it.imageUrl, ScaleTypes.CENTER_INSIDE)
                sliderArray.add(slide)
                dataBinding.imageSlider.setImageList(sliderArray)
            }

            dataBinding.imageSlider.setItemClickListener(object : ItemClickListener {
                override fun onItemSelected(position: Int) {
                    val action =
                        NewsPageDirections.actionNewsPageToNewsPageDetail(it[position].infoId,1)
                    view.findNavController().navigate(action)
                }
            })


        })





        viewModel.lastTenNewsPublic.observe(viewLifecycleOwner, Observer {
            if (it.size == 0) {
                dataBinding.sportNewsHeader.visibility = View.GONE
                dataBinding.sportNewsRecyclerview.visibility = View.GONE
            } else {
                dataBinding.newsPageScrollview.visibility = View.VISIBLE
                dataBinding.newsPageProgressbar.visibility = View.GONE
                val rv1 = PublicNewsRecyclerView(it)
                dataBinding.publicNewsRecyclerview.layoutManager =
                    LinearLayoutManager(this.context, LinearLayout.HORIZONTAL, false)
                dataBinding.publicNewsRecyclerview.adapter = rv1
            }
        })

        viewModel.lastTenNewsSport.observe(viewLifecycleOwner, Observer {
            if (it.size == 0) {
                dataBinding.sportNewsHeader.visibility = View.GONE
                dataBinding.sportNewsRecyclerview.visibility = View.GONE
            } else {
                dataBinding.newsPageScrollview.visibility = View.VISIBLE
                dataBinding.newsPageProgressbar.visibility = View.GONE
                val rv1 = SportNewsRecyclerView(it)
                dataBinding.sportNewsRecyclerview.layoutManager =
                    LinearLayoutManager(this.context, LinearLayout.HORIZONTAL, false)
                dataBinding.sportNewsRecyclerview.adapter = rv1
            }

        })

        viewModel.lastTenNewsEconomy.observe(viewLifecycleOwner, Observer {
            if (it.size == 0) {
                dataBinding.economyNewsHeader.visibility = View.GONE
                dataBinding.economyNewsRecyclerview.visibility = View.GONE

            } else {
                dataBinding.newsPageScrollview.visibility = View.VISIBLE
                dataBinding.newsPageProgressbar.visibility = View.GONE
                val rv1 = EconomyRecyclerView(it)
                dataBinding.economyNewsRecyclerview.layoutManager =
                    LinearLayoutManager(this.context, LinearLayout.HORIZONTAL, false)
                dataBinding.economyNewsRecyclerview.adapter = rv1
            }
        })

        dataBinding.publicNewsHeader.setOnClickListener {
            val action = NewsPageDirections.actionNewsPageToCategoryNews(Constants.PUBLIC,1)
            it.findNavController().navigate(action)
        }
        dataBinding.sportNewsHeader.setOnClickListener {
            val action = NewsPageDirections.actionNewsPageToCategoryNews(Constants.SPORT,1)
            it.findNavController().navigate(action)
        }
        dataBinding.economyNewsHeader.setOnClickListener {
            val action = NewsPageDirections.actionNewsPageToCategoryNews(Constants.ECONOMY,1)
            it.findNavController().navigate(action)
        }

        dataBinding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.journal_action -> navigate(Constants.NATIONAL)

                R.id.public_action -> navigate(Constants.PUBLIC)

                R.id.sport_action -> navigate(Constants.SPORT)

                R.id.economi_action ->  navigate(Constants.ECONOMY)

                R.id.politics_action -> navigate(Constants.POLITICS)

            }
                true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.profile_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.profile_action -> {
                startActivity(Intent(requireContext(), ProfileActivity::class.java))
                requireActivity().finish()
            }
        }
       return super.onOptionsItemSelected(item)
    }



    fun navigate(id:Int){
        val action = NewsPageDirections.actionNewsPageToCategoryNews(id,1)
        view?.findNavController()?.navigate(action)
    }

}
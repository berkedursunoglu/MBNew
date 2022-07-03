package com.berkedursunoglu.mbnews.news

import android.app.AlertDialog
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkedursunoglu.mbnews.Constants
import com.berkedursunoglu.mbnews.R
import com.berkedursunoglu.mbnews.databinding.FragmentNewsPageDetailBinding
import com.berkedursunoglu.mbnews.databinding.NewsCommentAlertdialogBinding
import com.berkedursunoglu.mbnews.model.Comment
import com.berkedursunoglu.mbnews.model.Post
import com.berkedursunoglu.mbnews.news.viewmodels.NewsPageDetailViewModel
import com.berkedursunoglu.mbnews.recyclerviews.CommentRecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class NewsPageDetail : Fragment() {

    private lateinit var dataBinding: FragmentNewsPageDetailBinding

    private lateinit var viewModel: NewsPageDetailViewModel

    private lateinit var alertDialogBinding: NewsCommentAlertdialogBinding

    private lateinit var rv: CommentRecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val back = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val args: NewsPageDetailArgs by navArgs()
                if (args.howToCame == 1) {
                    val action = NewsPageDetailDirections.actionNewsPageDetailToNewsPage()
                    view?.findNavController()?.navigate(action)
                } else {
                    viewModel.newsDetail.observe(viewLifecycleOwner, Observer {
                        val action =
                            NewsPageDetailDirections.actionNewsPageDetailToCategoryNews(it.categoryId,
                                2)
                        view?.findNavController()?.navigate(action)
                    })
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, back)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        dataBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_news_page_detail, container, false
        )
        viewModel = ViewModelProvider(this)[NewsPageDetailViewModel::class.java]
        val args: NewsPageDetailArgs by navArgs()
        viewModel.getNewsDetail(args.newsID)
        dataBinding.newsCommentDetailRecyclerview.layoutManager = LinearLayoutManager(context)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.newsDetail.observe(viewLifecycleOwner, Observer { a ->
            Picasso.get().load(a.imageUrl).into(dataBinding.newsDetailImage)
            dataBinding.newsDetailProgressbar.visibility = View.GONE
            dataBinding.newsDetailImage.visibility = View.VISIBLE
            dataBinding.newsDetailHeader.text = a.newsName
            dataBinding.newsDetailContent.text = a.content
            rv = CommentRecyclerView(a.comments)
            dataBinding.newsCommentDetailRecyclerview.adapter = rv
            dataBinding.commentButton.setOnClickListener {
                val alertdialog = AlertDialog.Builder(requireContext())
                alertDialogBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(requireContext()),
                    R.layout.news_comment_alertdialog,
                    null,
                    false
                )
                alertdialog.setView(alertDialogBinding.root)
                val create = alertdialog.create()
                alertDialogBinding.commentOk.setOnClickListener {
                    var userName = alertDialogBinding.commentUsername.text.toString()
                    var comment = alertDialogBinding.commentContent.text.toString()
                    val args: NewsPageDetailArgs by navArgs()
                    var infoId = args.newsID
                    if (userName == "") {
                        userName = "Misafir"
                    }
                    if (comment == "") {
                        Toast.makeText(requireContext(),
                            "Yorum boş bırakılamaz",
                            Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        runBlocking {
                            var comment = Post(userName, comment, infoId)
                            viewModel.commentPost(comment)
                            delay(100)
                            viewModel.getNewsDetail(infoId)
                        }
                        rv.notifyItemInserted(a.comments.size + 1)
                        create.dismiss()
                    }
                }
                alertDialogBinding.commentDecline.setOnClickListener {
                    create.dismiss()
                }
                create.show()
            }
        })
    }
}

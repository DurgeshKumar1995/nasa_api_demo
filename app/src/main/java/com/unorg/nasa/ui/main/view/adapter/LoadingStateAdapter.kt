package com.unorg.nasa.ui.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.unorg.nasa.databinding.ProgressBinding


class LoadingStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadingStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {

        val progress = holder.binding.progressBarItem
        val retryBtn = holder.binding.btnRetry
        val txtErrorMessage = holder.binding.errorMsgItem

        if (loadState is LoadState.Loading) {
            progress.isVisible = true
            txtErrorMessage.isVisible = false
            retryBtn.isVisible = false

        } else {
            progress.isVisible = false
        }

        if (loadState is LoadState.Error) {
            txtErrorMessage.isVisible = true
            retryBtn.isVisible = true
            txtErrorMessage.text = loadState.error.localizedMessage
        }


        retryBtn.setOnClickListener {
            retry.invoke()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            ProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class LoadStateViewHolder(val binding: ProgressBinding) :
        RecyclerView.ViewHolder(binding.root)
}
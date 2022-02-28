package com.example.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.base.BaseDiffCallBack
import com.example.base.BaseViewHolder
import com.example.data.models.ActivityEntity
import com.example.databinding.ItemActivityBinding
import javax.inject.Inject

class ActivityAdapter @Inject constructor() :
    ListAdapter<ActivityEntity, ActivityViewHolder>(ActivityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val itemViewBinding =
            ItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActivityViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.displayData(getItem(position))
    }
}

class ActivityViewHolder(private val itemViewBinding: ItemActivityBinding) :
    BaseViewHolder<ActivityEntity>(itemViewBinding) {

    override fun displayData(entity: ActivityEntity) {
        itemViewBinding.textActivityName.text =
            "${absoluteAdapterPosition + 1}. ${entity.activity.toString()}"
    }
}

class ActivityDiffCallback : BaseDiffCallBack<ActivityEntity>() {
    override fun areContentsTheSame(oldItem: ActivityEntity, newItem: ActivityEntity) =
        oldItem.activity == newItem.activity && oldItem.key == newItem.key && oldItem.id == newItem.id
}

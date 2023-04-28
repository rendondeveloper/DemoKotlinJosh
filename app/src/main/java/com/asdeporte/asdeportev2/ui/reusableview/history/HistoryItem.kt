package com.asdeporte.asdeportev2.ui.reusableview.history

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.HistoryItemBinding

class HistoryItem @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: HistoryItemBinding
    private var title: String? = null
    private var calories: String? = null
    private var average: String? = null
    private var time: String? = null

    init {
        binding = HistoryItemBinding.inflate(LayoutInflater.from(context), this, true)
        attrs?.let {
            val typeArray = getContext().obtainStyledAttributes(attrs, R.styleable.HistoryItem)

            val maxLength = typeArray.indexCount
            for (counter in 0..maxLength) {
                when (typeArray.getIndex(counter)) {
                    R.styleable.HistoryItem_history_item_title -> {
                        title = typeArray.getString(R.styleable.HistoryItem_history_item_title)
                    }

                    R.styleable.HistoryItem_history_item_calories -> {
                        calories = typeArray.getString(R.styleable.HistoryItem_history_item_calories)
                    }

                    R.styleable.HistoryItem_history_item_average -> {
                        average = typeArray.getString(R.styleable.HistoryItem_history_item_average)
                    }

                    R.styleable.HistoryItem_history_item_time -> {
                        time = typeArray.getString(R.styleable.HistoryItem_history_item_time)
                    }
                }
            }
            setTexts(title, calories, average, time)
        }
    }

    private fun setTexts(title: String?, calories: String?, average: String?, time: String?) {
        binding.apply {
            tvDateLabelData.text = title
            tvCaloriesData.text = calories
            tvMidLabelData.text = average
            tvTimeLabelData.text = time
        }
    }
}
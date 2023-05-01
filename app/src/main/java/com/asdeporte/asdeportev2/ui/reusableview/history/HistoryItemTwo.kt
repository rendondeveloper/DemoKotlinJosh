package com.asdeporte.asdeportev2.ui.reusableview.history

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.databinding.HistoryItemTwoBinding

class HistoryItemTwo @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var binding: HistoryItemTwoBinding
    private var date: String? = null
    private var score: String? = null
    private var time: String? = null

    init {
        binding = HistoryItemTwoBinding.inflate(LayoutInflater.from(context), this, true)
        attrs?.let {
            val typeArray = getContext().obtainStyledAttributes(attrs, R.styleable.HistoryItemTwo)

            val maxLength = typeArray.indexCount
            for (counter in 0..maxLength) {
                when (typeArray.getIndex(counter)) {
                    R.styleable.HistoryItemTwo_history_item_two_date -> {
                        date = typeArray.getString(R.styleable.HistoryItemTwo_history_item_two_date)
                    }

                    R.styleable.HistoryItemTwo_history_item_two_score -> {
                        score = typeArray.getString(R.styleable.HistoryItemTwo_history_item_two_score)
                    }

                    R.styleable.HistoryItemTwo_history_item_two_time -> {
                        time = typeArray.getString(R.styleable.HistoryItemTwo_history_item_two_time)
                    }

                }
            }
            setTexts(date, score, time)
        }
    }

    private fun setTexts(date: String?, score: String?, time: String?) {
        binding.apply {
            tvDate.text = date
            tvScore.text = score
            tvTime.text = time
        }
    }
}
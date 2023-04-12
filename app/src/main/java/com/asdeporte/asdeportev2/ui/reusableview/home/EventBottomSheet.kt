package com.asdeporte.asdeportev2.ui.reusableview.home

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asdeporte.asdeportev2.data.responses.events.EventData
import com.asdeporte.asdeportev2.databinding.EventBottomSheetBinding
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EventBottomSheet : BottomSheetDialogFragment() {

    interface EventBottomSheetListener {
        fun onOpenEvent(event: String)
        fun onOpenInscription(event: String)
    }

    private lateinit var binding: EventBottomSheetBinding

    lateinit var listener: EventBottomSheetListener
    lateinit var event: EventData

    private lateinit var player: ExoPlayer
    companion object {
        fun create(listener: EventBottomSheetListener, event: EventData) = EventBottomSheet().apply {
            this.listener = listener
            this.event = event
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = EventBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Glide.with(this)
            //.load("https://file-examples.com/storage/fe0358100863d05afed02d2/2017/04/file_example_MP4_480_1_5MG.mp4")
            //.load("https://giphy.com/gifs/video-games-retro-classic-YWUpVw86AtIbe")
            .load(event.cover)
            .centerCrop()
            .into(binding.eventCover)

        // Video
        player = ExoPlayer.Builder(requireContext()).build()
        binding.videoPlayerView.player = player
        val mediaItem = MediaItem.fromUri("https://storage.googleapis.com/wvmedia/clear/h264/tears/tears.mpd")
        //val mediaItem = MediaItem.fromUri("https://file-examples.com/storage/fe0358100863d05afed02d2/2017/04/file_example_MP4_480_1_5MG.mp4")
        player.addMediaItem(mediaItem)
        player.prepare()
        player.play()
        player.volume = 0f


        binding.eventName.text = event.official_name

        Glide.with(this)
            .load(event.logo)
            .centerCrop()
            .into(binding.eventImage)

        binding.strikedPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG


        binding.eventInfo.setOnClickListener {
            this.dismiss()
            listener.onOpenEvent("")
        }

        binding.inscriptionButton.setOnClickListener {
            this.dismiss()
            listener.onOpenInscription("")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        player.stop()
    }

}
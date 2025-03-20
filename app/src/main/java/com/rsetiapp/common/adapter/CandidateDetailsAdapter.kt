package com.rsetiapp.common.adapter

import FollowUpStatusAdapter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rsetiapp.R
import com.rsetiapp.common.fragments.FollowUpCandidateFragmentDirections
import com.rsetiapp.common.model.response.CandidateDetail
import com.rsetiapp.common.model.response.FollowUpStatus
import com.rsetiapp.databinding.ItemCandidateDetailsBinding


class CandidateDetailsAdapter(
    private val candidateList: List<CandidateDetail>
) : RecyclerView.Adapter<CandidateDetailsAdapter.CandidateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateViewHolder {
        val binding =
            ItemCandidateDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CandidateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CandidateViewHolder, position: Int) {
        val candidate = candidateList[position]
        holder.bind(candidate)
    }

    override fun getItemCount(): Int = candidateList.size

    inner class CandidateViewHolder(private val binding: ItemCandidateDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(candidate: CandidateDetail?) {
            if (candidate == null) {
                return // Prevents NullPointerException
            }

            val context = binding.root.context

            // Handle Profile Picture
            val profilePic = candidate.candidateProfilePic
            if (profilePic.isNullOrEmpty() || profilePic == "NA") {
                Glide.with(context)
                    .load(R.drawable.person)
                    .into(binding.candidateImage)
            } else {
                try {
                    val decodedString: ByteArray = Base64.decode(profilePic, Base64.DEFAULT)
                    val profileBitmap: Bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

                    Glide.with(context)
                        .load(profileBitmap)
                        .into(binding.candidateImage)
                } catch (e: Exception) {
                    Glide.with(context)
                        .load(R.drawable.person) // Load default image if decoding fails
                        .into(binding.candidateImage)
                }
            }

            // Ensure non-null values for UI fields
            binding.tvCandidateName.text = candidate.candidateName ?: "Unknown"
            binding.tvRollNumberValue.text = candidate.rollNo?.toString() ?: "N/A"
            binding.tvContactNumber.text = candidate.mobileNo ?: "N/A"

            // Handle Follow-Up Status Adapter
            binding.rvStatus.adapter = FollowUpStatusAdapter(
                candidate.followUpStatus ?: listOf(
                    FollowUpStatus("S1", "Settled"),
                    FollowUpStatus("S2", "Settled"),
                    FollowUpStatus("S3", "Settled"),
                    FollowUpStatus("S4", "Settled"),
                    FollowUpStatus("S5", "Not Settled"),
                    FollowUpStatus("S6", "Not Settled"),
                    FollowUpStatus("S7", "Not Settled"),
                    FollowUpStatus("S8", "Not Settled")
                )
            )

            // Handle Click Navigation (Ensure safe `adapterPosition`)
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION && position < candidateList.size) {
                    val data = candidateList[position]

                    val action = FollowUpCandidateFragmentDirections
                        .actionFollowUpCandidateFragmentToFollowUpFormFragment(data)

                    binding.root.findNavController().navigate(action)
                }
            }
        }
    }
}

package com.example.foodapplication.presentation.screen.profile

import com.example.foodapplication.databinding.FragmentProfileBinding
import com.example.foodapplication.domain.remote.model.UserInfo
import com.example.foodapplication.presentation.common.base.BaseFragment
import com.example.foodapplication.presentation.common.helper.Listener
import com.example.foodapplication.presentation.extension.showSnackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate),
    Listener {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("users")

    override fun init() {
        showData()
        listeners()
    }

    private fun showData() {
        val currentUser = auth.currentUser
        currentUser?.let { user ->
            databaseReference.child(user.uid)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val userInfo = snapshot.getValue(UserInfo::class.java)
                        binding.tvUsername.text = userInfo?.username
                        binding.tvEmail.text = userInfo?.email
                    }

                    override fun onCancelled(error: DatabaseError) {
                        binding.root.showSnackbar(error.message)
                    }
                })
        }
    }

    override fun listeners() {
        binding.buttonLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
        }
    }
}

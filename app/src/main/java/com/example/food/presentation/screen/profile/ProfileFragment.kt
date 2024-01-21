package com.example.food.presentation.screen.profile

import android.view.View
import androidx.navigation.findNavController
import com.example.food.R
import com.example.food.databinding.FragmentProfileBinding
import com.example.food.domain.remote.model.UserInfo
import com.example.food.presentation.common.base.BaseFragment
import com.example.food.presentation.common.helper.Listener
import com.example.food.presentation.extension.showSnackbar
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

    private fun showData() = with(binding) {
        progressBar.visibility = View.VISIBLE
        val currentUser = auth.currentUser
        currentUser?.let { user ->
            databaseReference.child(user.uid)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val userInfo = snapshot.getValue(UserInfo::class.java)
                        binding.tvUsername.text = userInfo!!.username
                        binding.tvEmail.text = userInfo.email
                        progressBar.visibility = View.GONE
                    }

                    override fun onCancelled(error: DatabaseError) {
                        binding.root.showSnackbar(error.message)
                        binding.progressBar.visibility = View.GONE
                    }
                })
        }
    }

    override fun listeners() {
        binding.buttonLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val navController = requireActivity().findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.action_hostFragment_to_loginFragment)
        }
    }
}

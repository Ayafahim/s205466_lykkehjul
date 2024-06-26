package com.example.hotspotf3.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hotspotf3.LoginActivity
import com.example.hotspotf3.R
import com.example.hotspotf3.ViewActivity
import com.example.hotspotf3.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null


    var modalList = ArrayList<Modal>()
    var names = arrayOf(
        "img1",
        "img2",
        "img3",
        "img4"
    )

    var images = intArrayOf(
        R.drawable.sample_img1,
        R.drawable.sample_img2,
        R.drawable.sample_img3,
        R.drawable.sample_img4
    )


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        for (i in names.indices) {
            modalList.add(Modal(names[i], images[i]))
        }

        val imageView1: ImageView = binding.imageView1

        imageView1.setOnClickListener(View.OnClickListener {
            var intent = Intent(this.requireContext(), ViewActivity::class.java)
            intent.putExtra("data", modalList[0])
            startActivity(intent)
        })

        val imageView2: ImageView = binding.imageView2

        imageView2.setOnClickListener(View.OnClickListener {
            var intent = Intent(this.requireContext(), ViewActivity::class.java)
            intent.putExtra("data", modalList[1])
            startActivity(intent)
        })

        val imageView3: ImageView = binding.imageView3

        imageView3.setOnClickListener(View.OnClickListener {
            var intent = Intent(this.requireContext(), ViewActivity::class.java)
            intent.putExtra("data", modalList[2])
            startActivity(intent)
        })

        val imageView4: ImageView = binding.imageView4

        imageView4.setOnClickListener(View.OnClickListener {
            var intent = Intent(this.requireContext(), ViewActivity::class.java)
            intent.putExtra("data", modalList[3])
            startActivity(intent)
        })

        val imageView5: ImageView = binding.imageView5

        imageView5.setOnClickListener(View.OnClickListener {
            var intent = Intent(this.requireContext(), ViewActivity::class.java)
            intent.putExtra("data", modalList[2])
            startActivity(intent)
        })

        val imageView6: ImageView = binding.imageView6

        imageView6.setOnClickListener(View.OnClickListener {
            var intent = Intent(this.requireContext(), ViewActivity::class.java)
            intent.putExtra("data", modalList[1])
            startActivity(intent)
        })

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logout: ImageView = binding.logoutIcon
        logout.display

        logout.setOnClickListener {
            val popupMenu = PopupMenu(activity,logout)
            popupMenu.inflate(R.menu.logout_popup)
            popupMenu.setOnMenuItemClickListener{

                var logoutItem = onOptionsItemSelected(popupMenu.menu.getItem(0))
                if (logoutItem) {
                    requireActivity().run {
                        FirebaseAuth.getInstance().signOut()
                        Toast.makeText(activity, "Logged Out", Toast.LENGTH_SHORT).show()
                        var intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()

                    }
                }

                var cancelItem = onOptionsItemSelected(popupMenu.menu.getItem(1))
                if (cancelItem) {
                    Toast.makeText(activity, "Action cancled", Toast.LENGTH_SHORT).show()
                }
                true
            }
            popupMenu.show()
        }
    }

//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setHasOptionsMenu(true)
//
//        val logout: ImageView = binding.logoutIcon
//        logout.display
//
//        logout.setOnClickListener {
//
//        }
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.logout_popup, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
//        val id = item!!.itemId
//        if (id == R.id.nav_logout) {
//            requireActivity().run {
//                FirebaseAuth.getInstance().signOut()
//                Toast.makeText(activity, "Logged Out", Toast.LENGTH_SHORT).show()
//                var intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }
//        if (id == R.id.nav_cancel) {
//            Toast.makeText(activity, "Action cancled", Toast.LENGTH_SHORT).show()
//        }
//        return super.onOptionsItemSelected(item)
//
//    }
}


                                    
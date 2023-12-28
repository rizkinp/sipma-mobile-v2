import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sipma.R
import com.example.sipma.fragment.Announcement
import com.example.sipma.fragment.AnnouncementAdapter
import com.google.firebase.database.*

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var announcementAdapter: AnnouncementAdapter
    private lateinit var announcements: MutableList<Announcement>

    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.list_pengumuman)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        announcements = mutableListOf()
        announcementAdapter = AnnouncementAdapter(announcements)
        recyclerView.adapter = announcementAdapter

        databaseReference = FirebaseDatabase.getInstance().getReference("pengumuman")

        // Read data from Firebase
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                announcements.clear()
                for (childSnapshot in snapshot.children) {
                    val announcement = childSnapshot.getValue(Announcement::class.java)
                    announcement?.let { announcements.add(it) }
                }
                announcementAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        return view
    }
}

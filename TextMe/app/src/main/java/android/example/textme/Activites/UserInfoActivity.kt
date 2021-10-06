package android.example.textme.Activites
 
import android.example.textme.UserModel
import android.example.textme.Utile.AppUtil
import android.example.textme.databinding.ActivityUserInfoBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class UserInfoActivity : AppCompatActivity() {
    private lateinit var activityUserInfoBinding: ActivityUserInfoBinding
    private var userId: String? = null
    private lateinit var appUtil: AppUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserInfoBinding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(activityUserInfoBinding.root)
        appUtil = AppUtil()

        supportActionBar!!.title = "User Info"
        userId = intent.getStringExtra("userId")
        getUserData(userId)
    }

    private fun getUserData(userId: String?) {

        val databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId!!)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val userModel = snapshot.getValue(UserModel::class.java)
                    activityUserInfoBinding.userModel = userModel

                    if (userModel!!.name.contains(" ")) {
                        val split = userModel.name.split(" ")
                        activityUserInfoBinding.txtProfileFName.text = split[0]
                        activityUserInfoBinding.txtProfileLName.text = split[1]
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onPause() {
        super.onPause()
        appUtil.updateOnlineStatus("offline")
    }

    override fun onResume() {
        super.onResume()
        appUtil.updateOnlineStatus("online")
    }

}
package android.example.textme.ViewModels
 
import android.example.textme.Repository.AppRepo
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import android.example.textme.UserModel

class ProfileViewModel : ViewModel() {

    private var appRepo = AppRepo.StaticFunction.getInstance()

    fun getUser(): LiveData<UserModel> {
        return appRepo.getUser()
    }

    fun updateStatus(status: String) {
        appRepo.updateStatus(status)

    }

    fun updateName(userName: String?) {
        appRepo.updateName(userName!!)
    }

    fun updateImage(imagePath: String) {
        appRepo.updateImage(imagePath)
    }


}
package id.hamasah.rxkotlinketigaformlogin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        validEdit(ed_username)
        validEdit(ed_password)
    }

    private fun validEdit(edit: EditText?) {
        RxTextView.textChanges(edit!!)
                .map { t: CharSequence -> t.length < 5 && t.length > 0 }
                .debounce (1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { t: Boolean? ->
                    if (t!!){
                        edit.setError("karakter harus lebih dari 5")
                    }
                }
    }
}

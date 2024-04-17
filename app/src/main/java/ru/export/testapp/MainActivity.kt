package ru.export.testapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.export.testapp.flow.EquipmentFlowFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        supportFragmentManager.beginTransaction()
            .add(R.id.container, EquipmentFlowFragment())
            .commitNow()
    }
}
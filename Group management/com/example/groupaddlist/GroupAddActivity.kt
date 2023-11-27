package com.example.groupaddlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.groupaddlist.ui.theme.GroupAddListTheme



class MainActivity : ComponentActivity() {
    private Button buttonConfirm;
    val id:Int = 10
    val groupName:String ="it works in hardcoding!"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.group_add_layout)
        buttonConfirm = (Button)findViewById(R.id.buttonConfirm);

        buttonConfirm.setOnClickListener(){}


    }
}




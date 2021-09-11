package com.bicubictwice.composedemoapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.bicubictwice.composedemoapp.classTag
import com.bicubictwice.composedemoapp.ui.search.SearchScreen
import com.bicubictwice.composedemoapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(classTag, "onCreate")
        setContent {
            AppTheme {
                SearchScreen(Modifier.fillMaxSize())
            }
        }
    }
}

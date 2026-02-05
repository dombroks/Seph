package com.younesbelouche.seph

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.younesbelouche.seph.core.theme.SephTheme
import com.younesbelouche.seph.features.products.presentation.ProductsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SephTheme {
                ProductsScreen(
                    onSearch = {},
                    productsViewModel = hiltViewModel()
                )
            }
        }
    }
}

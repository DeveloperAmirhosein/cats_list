package com.nocompany.catslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nocompany.catslist.presentation.features.bookmarkedCats.bookmarkedCatsScreen
import com.nocompany.catslist.presentation.features.bookmarkedCats.navigateToBookmarkedCats
import com.nocompany.catslist.presentation.features.catsList.CATS_LIST_ROUTE
import com.nocompany.catslist.presentation.features.catsList.catsListScreen
import com.nocompany.catslist.ui.theme.CatsListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatsListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = CATS_LIST_ROUTE
                    ) {
                        catsListScreen {
                            navController.navigateToBookmarkedCats()
                        }
                        bookmarkedCatsScreen()
                    }
                }
            }
        }
    }
}


package com.example.androiddevchallenge.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.androiddevchallenge.data.DogViewModel
import com.example.androiddevchallenge.ui.detail.DetailScreen
import com.example.androiddevchallenge.ui.home.HomeScreen
import com.example.androiddevchallenge.ui.theme.DogAdoptionTheme

@ExperimentalFoundationApi
@Composable
fun DogAdoptionApp(dogViewModel: DogViewModel, navigationViewModel: NavigationViewModel) {
  Surface(color = MaterialTheme.colors.background) {
    DogAdoptionTheme {
      AppContent(dogViewModel, navigationViewModel)
    }
  }
}

@ExperimentalFoundationApi
@Composable
private fun AppContent(
  dogViewModel: DogViewModel,
  navigationViewModel: NavigationViewModel
) {
  Crossfade(navigationViewModel.currentScreen) { screen ->
    Surface(color = MaterialTheme.colors.background) {
      when (screen) {
        is Screen.Home -> HomeScreen(
          dogs = dogViewModel.dogs,
          navigateTo = navigationViewModel::navigateTo
        )
        is Screen.Detail -> DetailScreen(
          dog = screen.dog,
          onBack = { navigationViewModel.onBack() }
        )
      }
    }
  }
}

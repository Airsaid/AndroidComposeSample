package com.example.androiddevchallenge.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Dog
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.theme.typography

@ExperimentalFoundationApi
@Composable
fun HomeScreen(dogs: List<Dog>, navigateTo: (Screen) -> Unit) {
  Column {
    TopAppBar(title = { Text(stringResource(R.string.app_name)) })
    DogList(dogs, navigateTo)
  }
}

@ExperimentalFoundationApi
@Composable
fun DogList(dogs: List<Dog>, navigateTo: (Screen) -> Unit) {
  LazyVerticalGrid(cells = GridCells.Fixed(2)) {
    itemsIndexed(dogs) { _, dog ->
      DogItem(dog, navigateTo)
    }
  }
}

@Composable
fun DogItem(dog: Dog, navigateTo: (Screen) -> Unit) {
  Card(modifier = Modifier
    .fillMaxWidth(0.5f)
    .padding(10.dp)
    .clickable { navigateTo(Screen.Detail(dog)) }
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Image(
        painter = painterResource(dog.picture),
        contentDescription = dog.name,
        modifier = Modifier
          .height(180.dp)
          .fillMaxWidth(),
        contentScale = ContentScale.Crop
      )
      Text(
        text = dog.name,
        style = typography.h6,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(10.dp)
      )
    }
  }
}

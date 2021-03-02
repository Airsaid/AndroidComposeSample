package com.example.androiddevchallenge.ui.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Dog
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun DetailScreen(dog: Dog, onBack: () -> Unit) {
  Column {
    TopAppBar(
      title = { Text(dog.name) },
      navigationIcon = {
        Image(
          painter = painterResource(R.drawable.ic_back),
          contentDescription = "back",
          modifier = Modifier
            .clickable { onBack() }
            .padding(16.dp)
        )
      }
    )
    DogDetail(dog)
  }
}

@Composable
fun DogDetail(dog: Dog) {
  var isShowAdoptionPopup by remember { mutableStateOf(false) }
  Column {
    Image(
      painter = painterResource(dog.picture),
      contentDescription = dog.name,
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.4f),
      contentScale = ContentScale.Crop
    )
    Column(modifier = Modifier.padding(16.dp)) {
      DogIconItem(R.drawable.ic_breed, dog.breed, "breed")
      DogIconItem(R.drawable.ic_location, dog.location, "location")
      DogIconItem(R.drawable.ic_grass, dog.age, "age")
      DogIconItem(R.drawable.ic_face, dog.gender, "gender")
      DogIconItem(R.drawable.ic_straighten, dog.size, "size")
    }
    Row(
      horizontalArrangement = Arrangement.End,
      modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
    ) {
      Button(onClick = { isShowAdoptionPopup = true }) {
        Text(stringResource(R.string.adoption))
      }
    }
  }
  if (isShowAdoptionPopup) {
    AdoptionPopup {
      isShowAdoptionPopup = false
    }
  }
}

@Composable
fun DogIconItem(@DrawableRes id: Int, text: String, description: String) {
  Row(modifier = Modifier.padding(top = 10.dp)) {
    Image(
      painter = painterResource(id),
      contentDescription = description,
      modifier = Modifier.padding(end = 10.dp)
    )
    Text(
      text = text,
      style = typography.body1
    )
  }
}

@Composable
fun AdoptionPopup(onDismiss: () -> Unit) {
  AlertDialog(
    onDismissRequest = onDismiss,
    text = {
      Text(
        text = "Thank you for your adoption!",
        style = MaterialTheme.typography.body2
      )
    },
    confirmButton = {
      TextButton(onClick = onDismiss) {
        Text(text = "CLOSE")
      }
    }
  )
}
/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val isShowAdoptionPopup = remember { mutableStateOf(false) }
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
            Button(onClick = { isShowAdoptionPopup.value = true }) {
                Text(stringResource(R.string.adoption))
            }
        }
    }
    if (isShowAdoptionPopup.value) {
        AdoptionPopup {
            isShowAdoptionPopup.value = false
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

package com.example.eventexplorerapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eventexplorerapp.data.Event
import com.example.eventexplorerapp.data.eventCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonCategoryUi(
    event: Event,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .size(100.dp),
    navController: NavController
){
    Card(
        onClick = {
            eventCategory = event.category
            navController.navigate("RealEvent")
        },
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
    ) {
        Box{
            Image(
                painter = painterResource(id = event.category.imageId),
                contentDescription = event.title,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = event.title,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
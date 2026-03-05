package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceApp()
        }
    }
}

data class Artwork(
    val image: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtSpaceApp() {

    val artworks = listOf(
        Artwork(R.drawable.art1, "Valley in the mountains", "Thomas Cole", "1840"),
        Artwork(R.drawable.art2, "Golden Sunset", "John Smith", "2000"),
        Artwork(R.drawable.art3, "Mountain View", "Emily Brown", "1950")
    )

    var currentIndex by remember { mutableStateOf(0) }

    val currentArtwork = artworks[currentIndex]

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF2F2F2)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // Image Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {

                Image(
                    painter = painterResource(id = currentArtwork.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(20.dp),
                    contentScale = ContentScale.Crop
                )
            }

            // Artwork Description Box
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE6E6EB)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = currentArtwork.title,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "${currentArtwork.artist} (${currentArtwork.year})",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(
                    onClick = {
                        currentIndex =
                            if (currentIndex == 0) artworks.lastIndex
                            else currentIndex - 1
                    },
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.width(140.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5C6BC0),
                        contentColor = Color.White
                    )
                ) {
                    Text("Previous")
                }

                Button(
                    onClick = {
                        currentIndex =
                            if (currentIndex == artworks.lastIndex) 0
                            else currentIndex + 1
                    },
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.width(140.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5C6BC0),
                        contentColor = Color.White
                    )
                ) {
                    Text("Next")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
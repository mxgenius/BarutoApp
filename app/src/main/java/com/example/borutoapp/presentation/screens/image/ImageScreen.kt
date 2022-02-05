package com.example.borutoapp.presentation.screens.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.borutoapp.R
import com.example.borutoapp.presentation.screens.details.DetailsViewModel
import com.example.borutoapp.ui.theme.INFO_ICON_SIZE
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.borutoapp.util.Constants

@Composable
fun ImageScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
){
    val selectedHero by detailsViewModel.selectedHero.collectAsState()

    selectedHero?.let{ hero ->
        ShowHeroImage(
            heroImage = hero.image,
            onCloseClicked = {
                navController.popBackStack()
            }
        )
    }
}

@Composable
fun ShowHeroImage(
    heroImage: String,
    onCloseClicked: () -> Unit

) {
    val imageUrl = "${Constants.BASE_URL}${heroImage}"
    val painter = rememberImagePainter(imageUrl) {
        error(R.drawable.ic_placeholder)
    }

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            painter = painter,
            contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_icon),
                    tint = Color.White
                )
            }
        }
    }
}
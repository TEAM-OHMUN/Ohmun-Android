package com.bongyang.ohmun.presentation.screens.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.bongyang.ohmun.util.Constants.BASE_URL
import com.bongyang.ohmun.util.PaletteGenerator.convertImageUrlToBitmap
import com.bongyang.ohmun.util.PaletteGenerator.extractColorsFromBitmap
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedMovie by detailsViewModel.selectedMovie.collectAsState()
    val colorPalette by detailsViewModel.colorPalette

    if (colorPalette.isNotEmpty()) {
        DetailsContent(
            navController = navController,
            selectedMovie = selectedMovie,
            colors = colorPalette
        )
    } else {
        detailsViewModel.generateColorPalette()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        detailsViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.GenerateColorPalette -> {
                    val bitmap = convertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedMovie?.image}",
                        context = context
                    )
                    if (bitmap != null) {
                        detailsViewModel.setColorPalette(
                            colors = extractColorsFromBitmap(
                                bitmap = bitmap
                            )
                        )
                    }
                }
            }
        }
    }


}
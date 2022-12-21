package com.bongyang.ohmun.presentation.screens.details

import android.graphics.Color.parseColor
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.BottomSheetValue.Expanded
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bongyang.ohmun.domain.model.Movie
import com.bongyang.ohmun.R
import com.bongyang.ohmun.domain.model.Actor
import com.bongyang.ohmun.presentation.screens.components.RatingWidget
import com.bongyang.ohmun.presentation.screens.components.TextBox
import com.bongyang.ohmun.ui.theme.*
import com.bongyang.ohmun.util.Constants.BASE_URL
import com.bongyang.ohmun.util.Constants.MIN_BACKGROUND_IMAGE_HEIGHT
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.math.round

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedMovie: Movie?,
    colors: Map<String, String>
) {
    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    LaunchedEffect(key1 = selectedMovie) {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(parseColor(darkVibrant))
        )
    }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = Expanded)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue =
        if (currentSheetFraction == 1f)
            EXTRA_LARGE_PADDING
        else
            EXPANDED_RADIUS_LEVEL
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedMovie?.let {
                BottomSheetContent(
                    selectedMovie = it,
                    categoryColor = Color(parseColor(vibrant)),
                    sheetBackgroundColor = Color(parseColor(darkVibrant)),
                    contentColor = Color(parseColor(onDarkVibrant))
                )
            }
        },
        content = {
            selectedMovie?.let { movie ->
                BackgroundContent(
                    movieImage = movie.image,
                    imageFraction = currentSheetFraction,
                    backgroundColor = Color(parseColor(darkVibrant)),
                    onCloseClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }
    )
}

@Composable
fun BottomSheetContent(
    selectedMovie: Movie,
    categoryColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(INFO_ICON_SIZE)
                    .weight(1f),
                painter = painterResource(
                    id = platformImage(selectedMovie.platform) ?: R.drawable.ic_placeholder
                ),
                contentDescription = stringResource(id = R.string.app_logo)
            )
            Spacer(modifier = Modifier.width(MEDIUM_PADDING))
            Text(
                modifier = Modifier
                    .weight(8f),
                text = selectedMovie.title,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val ratings = selectedMovie.reviews.map {
                it.rating
            }
            val averageRating = round(ratings.average() * 10) / 10
            RatingWidget(rating = averageRating)
            Spacer(modifier = Modifier.width(MEDIUM_PADDING))
            Text(
                text = averageRating.toString(),
                color = contentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(MEDIUM_PADDING))
            Text(
                text = "리뷰 ${selectedMovie.reviews.size}개",
                color = contentColor.copy(alpha = 0.5f),
                fontSize = MaterialTheme.typography.subtitle2.fontSize,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = contentColor.copy(alpha = 0.5f)
            )
        }
        Spacer(modifier = Modifier.height(MEDIUM_PADDING))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedMovie.openingDate,
                color = contentColor,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(MEDIUM_PADDING))
            Image(
                modifier = Modifier
                    .size(INFO_ICON_SIZE),
                painter = painterResource(
                    id = filmRatingImage(selectedMovie.filmRating) ?: R.drawable.ic_placeholder
                ),
                contentDescription = stringResource(id = R.string.app_logo)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            selectedMovie.genre.forEach { genre ->
                TextBox(
                    text = genre,
                    borderColor = categoryColor,
                    textColor = contentColor
                )
                Spacer(modifier = Modifier.width(SMALL_PADDING))
            }
        }
        Spacer(modifier = Modifier.height(SMALL_PADDING))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "출연 배우",
                color = contentColor,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = contentColor
            )
        }
        ActorsGrid(actors = selectedMovie.actors, color = contentColor)
    }
}

@ExperimentalCoilApi
@Composable
fun BackgroundContent(
    movieImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit
) {
    val imageUrl = "$BASE_URL${movieImage}"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(imageFraction + MIN_BACKGROUND_IMAGE_HEIGHT)
                .align(Alignment.TopStart),
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = imageUrl)
                .error(drawableResId = R.drawable.ic_placeholder)
                .build(),
            contentDescription = stringResource(id = R.string.movie_image),
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

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == Collapsed && targetValue == Collapsed -> 1f
            currentValue == Expanded && targetValue == Expanded -> 0f
            currentValue == Collapsed && targetValue == Expanded -> 1f - fraction
            currentValue == Expanded && targetValue == Collapsed -> 0f + fraction
            else -> fraction
        }
    }

@Composable
fun ActorsGrid(
    modifier: Modifier = Modifier,
    actors: List<Actor>,
    color: Color
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(120.dp)
    ) {
        items(actors) { actor ->
            ActorElement(
                actorImage = actor.image,
                actorName = actor.name,
                contentColor = color
            )
        }
    }
}

@Composable
fun ActorElement(
    actorImage: String,
    actorName: String,
    modifier: Modifier = Modifier,
    contentColor: Color
) {
    val imageUrl = "$BASE_URL${actorImage}"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = imageUrl)
                .error(drawableResId = R.drawable.ic_placeholder)
                .build(),
            contentDescription = stringResource(id = R.string.actor_image),
            contentScale = ContentScale.Crop
        )
        Text(
            text = actorName,
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle2.fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
            )
        )
    }
}

@Composable
@Preview
fun BottomSheetContentPreview() {
    val movie = Movie(
        id = 1,
        title = "웬즈데이",
        image = "",
        openingDate = "2022",
        filmRating = "19",
        genre = listOf(
            "드라마",
            "판타지",
            "코미디"
        ),
        platform = listOf(
            "netflix"
        ),
        actors = listOf(
            Actor(
                id = 1,
                name = "가나다",
                image = "",
                age = 20,
                birthDate = ""
            ),
            Actor(
                id = 2,
                name = "라마바",
                image = "",
                age = 21,
                birthDate = ""
            ),
            Actor(
                id = 3,
                name = "사아자",
                image = "",
                age = 22,
                birthDate = ""
            ),
            Actor(
                id = 4,
                name = "차카타",
                image = "",
                age = 23,
                birthDate = ""
            ),
        ),
        reviews = listOf()
    )
    BottomSheetContent(
        selectedMovie = movie
    )
}

private fun platformImage(platforms: List<String>): Int? {
    return when (platforms.first()) {
        "netflix" -> R.drawable.ic_netflix
        "wavve" -> R.drawable.ic_wavve
        "disney_plus" -> R.drawable.ic_diseny_plus
        "tving" -> R.drawable.ic_tving
        "watcha" -> R.drawable.ic_watcha
        else -> null
    }
}

private fun filmRatingImage(filmRating: String): Int? {
    return when (filmRating) {
        "all" -> R.drawable.ic_all
        "12" -> R.drawable.ic_12
        "15" -> R.drawable.ic_15
        "19" -> R.drawable.ic_19
        else -> null
    }
}














package com.bongyang.ohmun.presentation.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import com.bongyang.ohmun.R
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bongyang.ohmun.domain.model.Actor
import com.bongyang.ohmun.domain.model.Movie
import com.bongyang.ohmun.navigation.Screen
import com.bongyang.ohmun.presentation.screens.components.ShimmerEffect
import com.bongyang.ohmun.ui.theme.*
import com.bongyang.ohmun.util.Constants.BASE_URL

@Composable
fun ListContent(
    movies: LazyPagingItems<Movie>,
    navController: NavHostController
) {
    val result = handlePagingResult(movies = movies)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
        ) {
            items(
                items = movies,
                key = { movie ->
                    movie.id
                }
            ) { movie ->
                movie?.let {
                    MovieItem(movie = it, navController = navController)
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    movies: LazyPagingItems<Movie>
): Boolean {
    movies.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                ShimmerEffect()
                false
            }
            movies.itemCount < 1 -> {
                ShimmerEffect()
                false
            }
            else -> true
        }
    }
}

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    navController: NavHostController
) {
    Box(
        modifier = modifier
            .height(MOVIE_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Details.passMovieId(movieId = movie.id))
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = "$BASE_URL${movie.image}")
                    .placeholder(drawableResId = R.drawable.ic_placeholder)
                    .error(drawableResId = R.drawable.ic_placeholder)
                    .build(),
                contentDescription = stringResource(id = R.string.movie_image),
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = movie.title,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movie.genre.joinToString(", "),
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(SMALL_PADDING))
                ActorsRow(movie = movie)
            }
        }
    }
}

@Composable
fun ActorsRow(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING),
        contentPadding = PaddingValues(horizontal = EXTRA_SMALL_PADDING),
        modifier = modifier
    ) {
        val actorImages = movie.actors.map {
            it.image
        }
        items(actorImages) { actorImage ->
            ActorElement(actorImage = actorImage)
        }
    }
}

@Composable
fun ActorElement(
    actorImage: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = "$BASE_URL${actorImage}")
                .placeholder(drawableResId = R.drawable.ic_placeholder)
                .error(drawableResId = R.drawable.ic_placeholder)
                .build(),
            contentDescription = stringResource(id = R.string.actor_image),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview()
@Composable
fun MovieItemPreview() {
    val movie = Movie(
        id = 1,
        title = "웬즈데이",
        image = "",
        openingDate = "",
        filmRating = "12",
        genre = listOf(
            "드라마",
            "판타지",
            "코미디"
        ),
        platform = listOf(),
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
    MovieItem(
        movie = movie,
        navController = rememberNavController()
    )
}

package com.nocompany.catslist.presentation.features.bookmarkedCats

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nocompany.catslist.R
import com.nocompany.catslist.domain.model.Cat

@Composable
fun BookmarkedCatsRoute(
    viewModel: BookmarkedCatsViewModel = hiltViewModel()
) {
    val bookmarkedCatsUiState by viewModel.bookmarkedCats.collectAsState()
    BookmarkedCatsScreen(bookmarkedCatsUiState)
}


@Composable
fun BookmarkedCatsScreen(bookmarkedCatsUiState: BookmarkedCatsUiState) {
    Column {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            text = stringResource(id = R.string.bookmarks),
            style = MaterialTheme.typography.headlineMedium
        )
        Box(modifier = Modifier.fillMaxSize()) {
            when (bookmarkedCatsUiState) {
                is BookmarkedCatsUiState.Success -> {
                    if (bookmarkedCatsUiState.list.isNotEmpty())
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            contentPadding = PaddingValues(
                                start = 8.dp,
                                end = 8.dp
                            ),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 8.dp)
                        ) {
                            items(bookmarkedCatsUiState.list.size) {
                                CatCard(
                                    bookmarkedCatsUiState.list[it],
                                )
                            }

                        }
                    else
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = stringResource(id = R.string.empty_bookmark_list),
                            style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center)
                        )

                }

                is BookmarkedCatsUiState.LoadFailed -> {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(id = R.string.error),
                        style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center)
                    )
                }

                is BookmarkedCatsUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .size(32.dp)
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

        }
    }

}

@Composable
fun CatCard(
    cat: Cat,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = cat.url,
        contentDescription = stringResource(id = R.string.cat_image),
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .aspectRatio(0.8f)
            .clip(
                RoundedCornerShape(10.dp)
            ),
        placeholder = ColorPainter(MaterialTheme.colorScheme.secondaryContainer),
        contentScale = ContentScale.Crop,
        error = ColorPainter(MaterialTheme.colorScheme.secondaryContainer),
    )

}

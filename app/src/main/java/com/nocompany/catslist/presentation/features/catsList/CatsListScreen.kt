package com.nocompany.catslist.presentation.features.catsList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.nocompany.catslist.R
import com.nocompany.catslist.presentation.model.CatModel

@Composable
fun CatsListRoute(
    onGoToBookmarksButtonClick: () -> Unit,
    viewModel: CatsViewModel = hiltViewModel(),
) {
    val cats = viewModel.cats.collectAsLazyPagingItems()
    CatsListScreen(
        cats = cats,
        onBookMarkItemClick = viewModel::bookmark,
        onGoToBookmarkedCatsButtonClick = onGoToBookmarksButtonClick
    )
}

@Composable
fun CatsListScreen(
    cats: LazyPagingItems<CatModel>,
    onBookMarkItemClick: (CatModel, Boolean) -> Unit,
    onGoToBookmarkedCatsButtonClick: () -> Unit,
) {
    val loadState = cats.loadState
    val refreshLoadState = loadState.refresh
    val loadMoreState = loadState.source.append
    var selectedCat: CatModel? by rememberSaveable {
        mutableStateOf(null)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (refreshLoadState) {
            is LoadState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .size(32.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            is LoadState.NotLoading -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(
                        start = 8.dp,
                        end = 8.dp
                    ),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(cats.itemCount) {
                        cats[it]?.let { cat ->
                            CatCard(
                                cat,
                                onClick = {
                                    selectedCat = cat
                                },
                                onBookMarkClick = onBookMarkItemClick
                            )
                        }
                    }
                }
            }

            is LoadState.Error -> {
                MainError(
                    Modifier.align(Alignment.Center),
                    cats::retry,
                )
            }
        }

        AnimatedVisibility(
            visible = loadMoreState !is LoadState.NotLoading,
            modifier = Modifier.align(
                Alignment.BottomCenter
            ),
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            when (loadMoreState) {
                is LoadState.Loading -> {
                    BottomBarLoading(modifier = Modifier.align(Alignment.BottomCenter))
                }

                is LoadState.Error -> {
                    LoadMoreError(
                        Modifier.align(Alignment.BottomCenter),
                        cats::retry,
                    )
                }

                else -> {}

            }

        }

        selectedCat?.let {
            FullScreenImageDialog(cat = it) { selectedCat = null }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_bookmarks),
            contentScale = ContentScale.Inside,
            contentDescription = stringResource(
                id = R.string.bookmarks
            ),
            modifier = Modifier
                .size(48.dp)
                .offset(x = (-16).dp, y = -80.dp)
                .align(Alignment.BottomEnd)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .clickable(onClick = onGoToBookmarkedCatsButtonClick)

        )
    }
}


@Composable
fun LoadMoreError(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = stringResource(id = R.string.general_error_message)
        )
        OutlinedButton(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(4.dp)),
            shape = RoundedCornerShape(4.dp),

            onClick = onClick
        ) {
            Text(
                text = stringResource(id = R.string.try_again),
            )
        }

    }
}

@Composable
private fun BottomBarLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(MaterialTheme.colorScheme.background),
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .size(32.dp)
                .align(Alignment.BottomCenter),
        )

    }
}

@Composable
fun FullScreenImageDialog(
    modifier: Modifier = Modifier,
    cat: CatModel,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        AsyncImage(
            model = cat.url,
            contentDescription = stringResource(id = R.string.cat_image),
            modifier = modifier
                .aspectRatio(cat.width / cat.height.toFloat())
                .clip(
                    RoundedCornerShape(16.dp)
                ),
            placeholder = ColorPainter(MaterialTheme.colorScheme.secondaryContainer),
            contentScale = ContentScale.Crop,
        )

    }
}

@Composable
fun CatCard(
    cat: CatModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onBookMarkClick: (CatModel, Boolean) -> Unit,
) {
    Box(modifier = modifier.wrapContentSize()) {
        AsyncImage(
            model = cat.url,
            contentDescription = stringResource(id = R.string.cat_image),
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .aspectRatio(0.8f)
                .clip(
                    RoundedCornerShape(10.dp)
                )
                .clickable(onClick = onClick),
            placeholder = ColorPainter(MaterialTheme.colorScheme.secondaryContainer),
            contentScale = ContentScale.Crop,
        )
        Image(
            painter = painterResource(
                id = if (cat.isBookmarked)
                    R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark_empty
            ),
            contentDescription = stringResource(id = R.string.bookmark_button),
            modifier = Modifier
                .size(32.dp)
                .padding(start = 8.dp, top = 8.dp)
                .align(Alignment.TopStart)
                .clickable {
                    onBookMarkClick(cat, cat.isBookmarked.not())
                }
                .clip(CircleShape)
                .background(White)
        )
    }

}

@Composable
private fun MainError(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .width(96.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_error_sad),
            contentDescription = stringResource(id = R.string.error)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium,
            text = stringResource(id = R.string.connection_glitch)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.internet_connection_error),
            style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = onClick
        ) {
            Text(
                text = stringResource(id = R.string.retry)
            )
        }
    }

}

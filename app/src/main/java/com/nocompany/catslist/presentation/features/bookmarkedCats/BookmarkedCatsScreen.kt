package com.nocompany.catslist.presentation.features.bookmarkedCats

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nocompany.catslist.R
import com.nocompany.catslist.domain.model.Cat

@Composable
fun BookmarkedCatsRoute(

) {
    BookmarkedCatsScreen(ArrayList())
}


@Composable
fun BookmarkedCatsScreen(cats: List<Cat>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp
        ),
        modifier = Modifier.fillMaxSize()
    ) {
        items(cats.size) {
            CatCard(
                cats[it],
                onClick = {
                }
            )
        }

    }

}

@Composable
fun CatCard(
    cat: Cat,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    AsyncImage(
        model = cat.url,
        contentDescription = stringResource(id = R.string.cat_image),
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .aspectRatio(0.8f)
            .clip(
                RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onClick),
        placeholder = ColorPainter(MaterialTheme.colorScheme.secondaryContainer),
        contentScale = ContentScale.Crop,
    )

}

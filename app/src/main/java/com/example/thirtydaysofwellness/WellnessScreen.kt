package com.example.thirtydaysofwellness

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirtydaysofwellness.model.Wellness

@Composable
fun WellnessList(
    wellnesses: List<Wellness>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(wellnesses) {
            WellnessCard(
                wellness = it, modifier = Modifier.padding(
                    top = 10.dp,
                    start = 18.dp,
                    bottom = 10.dp,
                    end = 18.dp
                )
            )
        }
    }

}

@Composable
fun WellnessCard(wellness: Wellness, modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    val density = LocalDensity.current

    Card(modifier = modifier, onClick = { isExpanded = !isExpanded }) {
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary)
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    )
            ) {
                WellnessDay(wellness.dayRes)
                WellnessTitle(wellness.titleRes)
                Spacer(modifier = Modifier.height(8.dp))
                WellnessImage(wellness.imageRes)
                AnimatedVisibility(
                    visible = isExpanded,
                    enter = slideInVertically {
                        with(density) { -20.dp.roundToPx() }

                    } + expandVertically(
                        expandFrom = Alignment.Top
                    ),
                    exit = slideOutVertically() + shrinkVertically() + fadeOut()
                ) {
                    WellnessDescription(wellness.descriptionRes)
                }
            }
        }
    }

}

@Composable
fun WellnessImage(
    @DrawableRes wellnessImage: Int
) {
    Image(
        painter = painterResource(wellnessImage),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
    )
}

@Composable
fun WellnessDay(@StringRes dayRes: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(dayRes),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = modifier
    )
}

@Composable
fun WellnessTitle(
    @StringRes titleRes: Int, modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(titleRes),
        style = MaterialTheme.typography.displayMedium,
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = modifier
    )
}

@Composable
fun WellnessDescription(
    @StringRes descriptionRes: Int,
) {
    Text(
        text = stringResource(descriptionRes),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
    )
}

@Preview
@Composable
fun CardPreview() {
    WellnessCard(
        wellness = Wellness(
            R.string.title1,
            R.string.description1,
            R.string.day1,
            R.drawable.wellness1
        )
    )
}
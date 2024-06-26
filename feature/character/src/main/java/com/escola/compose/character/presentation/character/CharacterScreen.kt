package com.escola.compose.character.presentation.character

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.escola.compose.character.presentation.character.component.EpisodeComponent
import com.escola.compose.character.presentation.character.component.OtherCharacterComponent
import com.escola.compose.resource.ui.component.PullToRefreshComponent
import com.escola.compose.resource.ui.component.ShimmerText
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CharacterScreen(
    state: CharacterState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    characterEvent: (CharacterEvent) -> Unit,
    navigationEvent: (CharacterEffect) -> Unit,
) {

    val interactionSource = remember { MutableInteractionSource() }

    var maxDescriptionLines by remember {
        mutableIntStateOf(5)
    }

    var isDescriptionExpanded by remember {
        mutableStateOf(false)
    }

    var descriptionText by remember {
        mutableStateOf("Pokaż więcej")
    }

    var currentRotation by remember { mutableFloatStateOf(90f) }

    val rotation = remember { Animatable(currentRotation) }


    fun toggleDescriptionState() {
        if (isDescriptionExpanded) {
            maxDescriptionLines = 5
            descriptionText = "Pokaż więcej"
        } else {
            maxDescriptionLines = 999
            descriptionText = "Pokaż mniej"
        }
        isDescriptionExpanded = !isDescriptionExpanded
    }

    LaunchedEffect(isDescriptionExpanded) {
        if (isDescriptionExpanded) {
            rotation.animateTo(
                targetValue = currentRotation + 180f,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearEasing
                )
            ) {
                currentRotation = value
            }
        } else {
            rotation.animateTo(
                targetValue = currentRotation - 180f,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearEasing
                )
            ) {
                currentRotation = value
            }
        }
    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            PullToRefreshComponent(
                isRefreshing = state.isRefreshing,
                onRefresh = {
                    characterEvent.invoke(CharacterEvent.RefreshData)
                },
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        IconButton(onClick = {
                            navigationEvent.invoke(CharacterEffect.OnBackClick)
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "back"
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))


                        Text(
                            text = state.name,
                            modifier = Modifier
                                .sharedBounds(
                                    sharedContentState = rememberSharedContentState(key = "text-${state.characterId}"),
                                    animatedVisibilityScope = animatedVisibilityScope
                                )
                                .fillMaxWidth(),
                            style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {


                        Box(
                            modifier = Modifier
                                .width(450.dp)
                                .height(450.dp)
                                .padding(horizontal = 10.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .sharedElement(
                                        state = rememberSharedContentState(key = "image-${state.characterId}"),
                                        animatedVisibilityScope = animatedVisibilityScope
                                    )
                                    .skipToLookaheadSize(),
                                model = state.imageUrl,
                                contentDescription = "Text",
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Opis",
                            style = TextStyle(fontSize = 36.sp, fontFamily = FontFamily.Cursive),
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)) {
                            ShimmerText(
                                isLoading = state.isLoadingCharacter,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(shape = RoundedCornerShape(25.dp, 25.dp, 25.dp, 25.dp))
                                    .height(140.dp),
                                contentAfterLoading = {
                                    Column(
                                        modifier = Modifier.padding(horizontal = 10.dp)
                                    ) {
                                        Text(
                                            fontSize = 12.sp,
                                            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = maxDescriptionLines,
                                            modifier = Modifier.clickable(
                                                interactionSource = interactionSource,
                                                indication = null
                                            ) {
                                                toggleDescriptionState()
                                            }
                                        )



                                        Spacer(modifier = Modifier.height(5.dp))

                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable(
                                                    interactionSource = interactionSource,
                                                    indication = null
                                                ) {
                                                    toggleDescriptionState()
                                                }, horizontalArrangement = Arrangement.Center
                                        ) {

                                            Icon(
                                                modifier = Modifier.rotate(currentRotation),
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = "back"
                                            )

                                            Text(text = descriptionText)

                                        }
                                    }
                                })
                        }



                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Episody",
                            style = TextStyle(fontSize = 36.sp, fontFamily = FontFamily.Cursive),
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)) {
                            ShimmerText(
                                isLoading = state.isLoadingEpisode,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(shape = RoundedCornerShape(25.dp, 25.dp, 25.dp, 25.dp))
                                    .height(200.dp),
                                contentAfterLoading = {
                                    Column {
                                        state.characterEpisodeDetails?.let { dataList ->
                                            LazyRow(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(200.dp)
                                            ) {
                                                item {
                                                    Spacer(modifier = Modifier.width(10.dp))
                                                }

                                                itemsIndexed(dataList) { index, episode ->
                                                    EpisodeComponent(episode)
                                                    Spacer(modifier = Modifier.width(10.dp))

                                                }
                                            }
                                        }
                                    }
                                })
                        }




                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Inne postacie",
                            style = TextStyle(fontSize = 36.sp, fontFamily = FontFamily.Cursive),
                        )

                        Spacer(modifier = Modifier.height(10.dp))


                        val realSize = 5
                        val pageCount = realSize * 400
                        val middlePage = pageCount / 2
                        val pagerState =
                            rememberPagerState(
                                pageCount = { pageCount },
                                initialPage = middlePage
                            )

                        val isDraggedState =
                            pagerState.interactionSource.collectIsDraggedAsState()


                        ShimmerText(isLoading = state.isLoadingCharacter,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(25.dp, 25.dp, 25.dp, 25.dp))
                                .height(100.dp)
                                .padding(horizontal = 10.dp),
                            contentAfterLoading = {

                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    HorizontalPager(
                                        state = pagerState,
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(300.dp),
                                            contentAlignment = Alignment.Center
                                        ) {

                                            OtherCharacterComponent(
                                                modifier = Modifier
                                                    .fillMaxSize(),
                                                imageUrl = prepareUrl(it, state.imageUrl)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))

                                    HorizontalPagerIndicator(
                                        modifier = Modifier
                                            .padding(bottom = 10.dp),
                                        pageCount = realSize,
                                        pagerState = pagerState,
                                        pageIndexMapping = { page -> page % realSize }
                                    )
                                }


                            })
                        LaunchedEffect(isDraggedState) {
                            snapshotFlow { isDraggedState.value }
                                .collectLatest { isDragged ->
                                    if (!isDragged) {
                                        while (true) {
                                            delay(5_000L)
                                            runCatching {
                                                pagerState.animateScrollToPage(pagerState.currentPage.inc() % pagerState.pageCount)
                                            }
                                        }
                                    }
                                }
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}

fun prepareUrl(index: Int, url: String): String {
    val urlId = (url.split("/").last().split(".").first().toInt() + (index % 5) + 1).toString()
    val list = url.split("/").toMutableList()
    list[list.size - 1] = "$urlId.jpeg"
    return list.joinToString(separator = "/")
}
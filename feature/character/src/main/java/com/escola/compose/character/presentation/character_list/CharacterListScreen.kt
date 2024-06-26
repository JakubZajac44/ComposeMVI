package com.escola.compose.character.presentation.character_list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.escola.compose.character.domain.model.CharacterModel
import com.escola.compose.character.presentation.character_list.component.CharacterItemCard
import com.escola.compose.resource.ui.component.ShimmerText
import com.escola.compose.resource.ui.component.dot_progress_bar.ProgressDotIndicator

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterialApi::class)
@Composable
fun SharedTransitionScope.CharacterListScreen(
    state: CharacterListState,
    list: LazyPagingItems<CharacterModel>,
    animatedVisibilityScope: AnimatedVisibilityScope,
    characterListEvent: (CharacterListEvent) -> Unit,
    navigationEvent: (CharacterListEffect) -> Unit,
) {

    val pullRefreshState = rememberPullRefreshState(state.isRefreshing, {
        list.refresh()

    })

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val maxItemLoad = (screenHeight - 10.dp - 20.dp - 12.dp) / 120.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 10.dp, end = 10.dp)
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Rick and Morty",
                    style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
                )
            }
            if (list.loadState.refresh is LoadState.Loading) {
                items(maxItemLoad.toInt()) {
                    ShimmerText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(25.dp, 25.dp, 25.dp, 25.dp))
                            .height(120.dp),
                        isLoading = ( list.loadState.refresh is LoadState.Loading),
                        contentAfterLoading = {
                            Box(modifier = Modifier.fillMaxSize())
                        }
                    )
                }
            } else {
                items(list.itemCount) { index ->
                    list[index]?.let { model ->
                        CharacterItemCard(
                            characterModel = model,
                            animatedVisibilityScope = animatedVisibilityScope,
                            backgroundColor = Color(0xFFEBEBF4),
                            characterItemClick = { id, name, image ->
                                navigationEvent.invoke(
                                    CharacterListEffect.OnCharacterDetailsClick(
                                        id.toString(),
                                        name,
                                        image
                                    )
                                )
                            }
                        )
                    }

                }

                item {
                    if (list.loadState.append is LoadState.Loading) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(bottom = 20.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ProgressDotIndicator(
                                modifier = Modifier
                                    .size(80.dp)
                            )
                        }

                    }
                }
            }


        }


        PullRefreshIndicator(
            state.isRefreshing,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )


    }


}
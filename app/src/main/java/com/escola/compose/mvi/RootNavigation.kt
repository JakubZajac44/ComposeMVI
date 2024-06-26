package com.escola.compose.mvi

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.escola.compose.authorization.AUTHORIZATION_GRAPH_ROUTE
import com.escola.compose.authorization.authorizationGraph
import com.escola.compose.character.characterGraph
import com.escola.compose.character.navigateToCharacterGraph

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RootNavigation(
) {
    val navController = rememberNavController()

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = AUTHORIZATION_GRAPH_ROUTE
        ) {
            authorizationGraph(
                navController = navController,
                userLogged = {
                    navController.navigateToCharacterGraph()
                }
            )



            characterGraph(
                navController = navController,
                sharedTransitionScope = this@SharedTransitionLayout
            )
        }
    }


}
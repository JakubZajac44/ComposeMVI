package com.escola.compose.authorization.presentation.regulation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.escola.compose.authorization.R

@Composable
fun RegulationScreen(
    navigationEvent: (RegulationNavigationEvent) -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {



        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically){
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = { navigationEvent.invoke(RegulationNavigationEvent.OnBackClick) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }

                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.regulation_title),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center

                )

                Spacer(modifier = Modifier.size(32.dp))
            }



            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus viverra vitae mi vitae pulvinar. Sed faucibus mauris vulputate, pulvinar dolor sit amet, iaculis sem. Donec ac quam at nulla dignissim venenatis sit amet eu ipsum. Integer tortor neque, feugiat in eleifend ac, blandit vitae felis. Proin sit amet condimentum ante. Sed ornare erat massa, at elementum tortor condimentum ac. Vivamus accumsan interdum magna, eget elementum libero gravida at. Aliquam orci orci, consequat sed vehicula non, commodo quis turpis. Integer dui mi, tincidunt at ligula at, elementum dignissim ligula. Nunc dapibus massa vel turpis lacinia pharetra."
                        + "\n" +
                        "Nunc sodales magna non scelerisque vestibulum. Curabitur blandit sed sapien a auctor. Proin bibendum erat sed sem aliquet suscipit. Quisque et tellus nec orci gravida condimentum. Phasellus maximus rutrum dolor quis fringilla. Quisque eu aliquet neque. Sed nibh erat, eleifend a quam non, maximus feugiat odio. Nunc eu accumsan odio. In facilisis aliquam quam laoreet molestie. Praesent quis tempor libero. Donec scelerisque feugiat sem. Nunc dui velit, fermentum in ante et, laoreet commodo ligula. Phasellus mattis sapien vel urna facilisis, sed porta nunc feugiat."
                        + "\n" +
                        "Praesent bibendum hendrerit ligula, id blandit quam. Mauris fermentum in nunc a sagittis. Nullam massa augue, volutpat vitae ipsum sit amet, ultricies placerat massa. Ut eu iaculis lectus. Aenean fermentum non nulla in molestie. Aliquam eleifend consectetur velit id finibus. Ut aliquet, orci quis blandit sodales, libero nisi pretium augue, a mattis enim dolor nec nibh. Nunc mattis hendrerit facilisis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nulla facilisi. Mauris bibendum rhoncus viverra."
                        + "\n" +
                        "Pellentesque sed nisl et risus hendrerit fringilla. Suspendisse id dolor ligula. Maecenas et aliquet arcu. Nulla quis mi quis odio rutrum fringilla. In ultricies aliquet enim, quis posuere nisl auctor ut. Nulla laoreet massa et arcu vestibulum aliquet. Vestibulum consequat mauris ligula, molestie cursus odio feugiat a. Morbi imperdiet urna ullamcorper rhoncus facilisis. Cras at orci auctor, cursus risus at, vestibulum massa. Donec sed nunc accumsan, luctus lorem eu, pretium nulla. Phasellus lorem ex, dictum a mauris eget, maximus aliquam mi. Fusce ultricies risus at dignissim feugiat. Praesent ac dolor vehicula nibh dapibus sollicitudin quis sed velit. Duis semper tempor est, eu imperdiet nulla placerat eu. Donec odio quam, pellentesque non ex at, ornare tempus ex. Nullam condimentum sem viverra ligula placerat, non fermentum justo egestas."
                        + "\n" +
                        "Morbi faucibus metus nec est laoreet, in efficitur enim porta. Morbi ullamcorper ac metus dapibus rhoncus. Phasellus augue libero, scelerisque vitae vulputate sed, sodales id velit. Etiam pellentesque augue at arcu ullamcorper, eget ornare felis ultrices. Vivamus dictum, velit a vestibulum tincidunt, dolor enim euismod ex, eleifend malesuada sem ante vel neque. Aenean vestibulum, lectus sit amet posuere euismod, libero quam placerat leo, quis ultrices libero magna a lacus. Nunc mattis sapien nec ullamcorper euismod. Sed sem mi, ullamcorper nec blandit non, vehicula et enim. Phasellus semper tellus at sem sollicitudin eleifend."
            )

        }


    }
}


@Preview
@Composable
fun RegulationScreenPreview() {
    RegulationScreen(
        navigationEvent = {}
    )
}
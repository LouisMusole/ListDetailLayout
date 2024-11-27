package com.drcmind.myapp

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.drcmind.myapp.utils.Discussion
import com.drcmind.myapp.utils.MyData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListDetailsScreen(){

    val scope = rememberCoroutineScope()

    val navigator = rememberListDetailPaneScaffoldNavigator<Discussion>()

    BackHandler(enabled = navigator.canNavigateBack()) {
        scope.launch{
            navigator.navigateBack()
        }
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                ListContent(
                    discussions = MyData.discussions,
                    onNavigateToDetailPane = { discussion->
                        scope.launch{
                            navigator.navigateTo(
                                ListDetailPaneScaffoldRole.Detail, discussion
                            )
                        }
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {

                val discussion = navigator.currentDestination?.contentKey
                if(discussion == null){
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Aucun élément n'est selectionné")
                    }
                }else{
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(modifier = Modifier
                                            .size(60.dp)
                                            .clip(CircleShape)
                                            .background(MaterialTheme.colorScheme.surfaceContainerHighest),
                                            contentAlignment = Alignment.Center
                                        ){
                                            Text(
                                                text = discussion.participants.second.first().uppercase(),
                                                fontSize = 22.sp
                                            )
                                        }
                                        Text(discussion.participants.second)
                                    }
                                },
                                actions = {
                                    IconButton(onClick = {}) {
                                        Icon(
                                            imageVector = Icons.Default.MoreVert,
                                            contentDescription = "Plus d'options"
                                        )
                                    }
                                }
                            )
                        }
                    ) {paddingValue->
                        Box(modifier = Modifier.padding(paddingValue).padding(16.dp).fillMaxSize()){
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                modifier = Modifier.fillMaxSize().padding(bottom = 70.dp)
                            ) {
                                items(discussion.messages){message->
                                    Box(modifier = Modifier.fillMaxWidth()) {
                                        Box(
                                            modifier = Modifier.fillMaxWidth(0.6f)
                                                .align(
                                                    if(message.sender == "Me") Alignment.CenterEnd else Alignment.CenterStart
                                                )
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .align(if(message.sender == "Me") Alignment.CenterEnd else Alignment.CenterStart)
                                                    .clip(CircleShape)
                                                    .background(if(message.sender == "Me") MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.secondaryContainer)
                                                    .padding(16.dp)
                                            ) {
                                                Text(
                                                    text = message.content,
                                                    color = if(message.sender == "Me") MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onSecondaryContainer
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.background)
                            ) {
                                OutlinedTextField(
                                    value = "",
                                    onValueChange = {},
                                    modifier = Modifier.fillMaxWidth(),
                                    placeholder = {
                                        Text("Votre message ici")
                                    },
                                    trailingIcon = {
                                        IconButton(onClick = {}) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Default.Send,
                                                contentDescription = "Envoyer message"
                                            )
                                        }
                                    },
                                    leadingIcon = {
                                        IconButton(onClick = {}) {
                                            Icon(
                                                imageVector = Icons.Default.Face,
                                                contentDescription = "Emoji"
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListContent(
    discussions : List<Discussion>,
    onNavigateToDetailPane : (Discussion)->Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Discussions")
                },
                actions = {
                    OutlinedIconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = "Action créer discussion"
                        )
                    }
                }
            )
        }
    ) { paddingValues->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(discussions){discussion->
                ListItem(
                    headlineContent = {
                        Text(discussion.participants.second)
                    },
                    leadingContent = {
                        Box(modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceContainerHighest),
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = discussion.participants.second.first().uppercase(),
                                fontSize = 22.sp
                            )
                        }
                    },
                    trailingContent = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier.clickable{
                        onNavigateToDetailPane(discussion)
                    }
                )
            }
            WindowWidthSizeClass.COMPACT
        }
    }
}

@Preview
@Composable
fun ListDetailsScreenPreview(){
    ListDetailsScreen()
}
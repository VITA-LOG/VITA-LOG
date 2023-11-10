package com.daineey.vita_log

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
<<<<<<< HEAD
import androidx.activity.ComponentActivity
=======
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
>>>>>>> ec56cb6 (update 0.22)
import androidx.activity.compose.BackHandler
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.daineey.vita_log.ui.common.AppBar
import com.daineey.vita_log.ui.common.AppScaffold
import com.daineey.vita_log.ui.conversations.Conversation
import com.daineey.vita_log.ui.theme.ChatGPTLiteTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatActivity : ComponentActivity() {
    private val chatViewModel: ChatViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(
            ComposeView(this).apply {
                consumeWindowInsets = false
                setContent {
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val drawerOpen by chatViewModel.drawerShouldBeOpened.collectAsState()

                    if (drawerOpen) {
                        // Open drawer and reset state in VM.
                        LaunchedEffect(Unit) {
                            // wrap in try-finally to handle interruption whiles opening drawer
                            try {
                                drawerState.open()
                            } finally {
                                chatViewModel.resetOpenDrawerAction()
                            }
                        }
                    }

                    // Intercepts back navigation when the drawer is open
                    val scope = rememberCoroutineScope()
                    val focusManager = LocalFocusManager.current

<<<<<<< HEAD
                    BackHandler {
                        if (drawerState.isOpen) {
                            scope.launch {
                                drawerState.close()
                            }
                        } else {
                            focusManager.clearFocus()
                        }
                    }
=======
//                    BackHandler {
//                        if (drawerState.isOpen) {
//                            scope.launch {
//                                drawerState.close()
//                            }
//                        } else {
//                            focusManager.clearFocus()
//                        }
//                    }
>>>>>>> ec56cb6 (update 0.22)
                    val darkTheme = remember(key1 = "darkTheme") {
                        mutableStateOf(true)
                    }
                    ChatGPTLiteTheme(darkTheme.value) {
                        Surface(
                            color = MaterialTheme.colorScheme.background,
                        ) {
                            AppScaffold(
                                drawerState = drawerState,
                                onChatClicked = {
                                    scope.launch {
                                        drawerState.close()
                                    }
                                },
                                onNewChatClicked = {
                                    scope.launch {
                                        drawerState.close()
                                    }
                                },
                                onIconClicked = {
                                    darkTheme.value = !darkTheme.value
                                }
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    AppBar(onClickMenu = {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    })
                                    Divider()
                                    Conversation()
                                }
                            }
                        }
                    }
                }
            }
        )
<<<<<<< HEAD
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
=======

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.i("ChatActivity.kt", "뒤로가기 클릭")
                isEnabled = false
                onBackPressedDispatcher.onBackPressed()
            }
        }

        onBackPressedDispatcher.addCallback(this, callback)
>>>>>>> ec56cb6 (update 0.22)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChatGPTLiteTheme {
    }
}
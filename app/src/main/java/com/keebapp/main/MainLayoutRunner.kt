package com.keebapp.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.keebapp.R
import com.keebapp.compose.composeViewFactory

val MainLayoutRunner = composeViewFactory<MainScreen> { rendering, _ ->
  setContent {
    Scaffold(
      topBar = {
        TopAppBar(
          title = {
            Text(text = stringResource(R.string.app_name))
          }
        )
      },
      content = {
        Text(
          modifier = Modifier.padding(16.dp),
          text = rendering.text
        )
      }
    )
  }
}

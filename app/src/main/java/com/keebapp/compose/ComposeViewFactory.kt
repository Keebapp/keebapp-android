package com.keebapp.compose

import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.ui.platform.ComposeView
import com.squareup.workflow1.ui.BuilderViewFactory
import com.squareup.workflow1.ui.ViewEnvironment
import com.squareup.workflow1.ui.ViewShowRendering
import com.squareup.workflow1.ui.bindShowRendering

/**
 * Returns a [BuilderViewFactory] specifically for binding the rendering to a [ComposeView].
 */
inline fun <reified RenderingT : Any> composeViewFactory(
  crossinline showRendering: ComposeViewShowRendering<RenderingT>
) = BuilderViewFactory(
  type = RenderingT::class,
  viewConstructor = { initialRendering, initialViewEnv, context, _ ->
    ComposeView(context)
      .apply {
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        bindShowRendering(initialRendering, initialViewEnv) { rendering, env ->
          showRendering(rendering, env)
        }
      }
  }
)

/**
 * A variant of [ViewShowRendering] that specifies [ComposeView] as the receiver scope.
 */
typealias ComposeViewShowRendering<RenderingT> = ComposeView.(RenderingT, ViewEnvironment) -> Unit

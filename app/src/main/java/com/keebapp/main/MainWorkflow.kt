package com.keebapp.main

import com.squareup.workflow1.StatelessWorkflow

class MainWorkflow : StatelessWorkflow<Unit, Unit, MainScreen>() {
  override fun render(renderProps: Unit, context: RenderContext): MainScreen {
    return MainScreen("Hello, world!")
  }
}

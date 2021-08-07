package com.keebapp.main

import com.squareup.workflow1.ui.ViewRegistry

class MainViewRegistry: ViewRegistry by ViewRegistry(
  MainLayoutRunner
)
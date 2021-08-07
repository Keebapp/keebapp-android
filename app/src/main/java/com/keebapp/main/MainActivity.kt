package com.keebapp.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.squareup.workflow1.ui.WorkflowLayout
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

  private val mainWorkflow = MainWorkflow()

  private val viewModel: MainViewModel by viewModels {
    MainViewModelFactory(this, mainWorkflow, intent.extras)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launch {
      setContentView(
        WorkflowLayout(this@MainActivity).apply {
          // Launches a job to begin running the workflow on the main thread.
          start(viewModel.render(), MainViewRegistry())
        }
      )

      // Suspends until the MainWorkflow emits an output, and then finishes the Activity.
      viewModel.waitForExit()
      finishAfterTransition()
    }
  }
}

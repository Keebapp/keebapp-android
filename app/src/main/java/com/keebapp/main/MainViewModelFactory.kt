package com.keebapp.main

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.squareup.workflow1.ui.renderWorkflowIn
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

class MainViewModelFactory(
  owner: SavedStateRegistryOwner,
  private val workflow: MainWorkflow,
  defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(
    key: String,
    modelClass: Class<T>,
    handle: SavedStateHandle
  ): T = MainViewModel(handle, workflow) as T
}

class MainViewModel(
  private val savedState: SavedStateHandle,
  private val workflow: MainWorkflow
) : ViewModel() {
  private val running = Job()

  fun render(): Flow<MainScreen> {
    return renderWorkflowIn(
      workflow = workflow,
      prop = Unit,
      scope = viewModelScope,
      savedStateHandle = savedState,
      onOutput = { running.complete() }
    )
  }

  suspend fun waitForExit() = running.join()
}

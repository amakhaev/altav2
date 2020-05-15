package com.alta_v2.game.gamelogic.interaction.executor

import com.alta_v2.game.gamelogic.data.interaction.DialogModel
import com.alta_v2.game.gamelogic.domain.dialog.DialogProcessor
import com.google.inject.Inject
import mu.KotlinLogging

class DialogExecutor @Inject constructor(private val dialogProcessor: DialogProcessor) : Executor<DialogModel> {

    private val log = KotlinLogging.logger {  }

    private var executionResult: ExecutionResult = ExecutionResult()
    private var currentDialog: DialogModel? = null
    private var sectionIndex = 0

    override fun prepare(data: DialogModel): ExecutionResult {
        if (!canExecute()) {
            log.error("Previous dialog effect still not completed yet")
            return executionResult
        }

        currentDialog = data
        executionResult = ExecutionResult()
        sectionIndex = 0
        return executionResult
    }

    override fun execute() {
        if (currentDialog!!.sections.isEmpty()) {
            executionResult.complete()
            return
        }

        sectionIndex = 0
        dialogProcessor.setDialogText(currentDialog!!.sections[sectionIndex].text)
    }

    override fun makeNextStep() {
        if (executionResult.isDone) {
            log.warn("Dialog effect already completed.")
            return
        }

        if (dialogProcessor.isDialogTextAnimationCompleted()) {
            handleNextSection()
        } else {
            dialogProcessor.completeDialogTextAnimationImmediately()
        }
    }

    private fun canExecute() = currentDialog == null

    private fun handleNextSection() {
        sectionIndex++
        if (sectionIndex < currentDialog!!.sections.size) {
            dialogProcessor.setDialogText(currentDialog!!.sections[sectionIndex].text)
        } else {
            dialogProcessor.hideDialog()
            currentDialog = null
            executionResult.complete()
        }
    }
}
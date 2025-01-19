package com.ex.transferscreen.ui.compose_elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ex.transferscreen.ui.screens.model.TransferScreenViewModel
import com.ex.transferscreen.ui.theme.Paddings
import io.github.vinceglb.filekit.compose.rememberFilePickerLauncher
import io.github.vinceglb.filekit.core.PickerMode
import io.github.vinceglb.filekit.core.PickerType
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import transferscreen.composeapp.generated.resources.*

@Composable
fun UploadDocumentCard(viewModel: TransferScreenViewModel) {

    val launcher = rememberFilePickerLauncher(
        type = PickerType.File(extensions = listOf("jpeg", "jpg", "png", "pdf", "doc", "docx")),
        mode = PickerMode.Multiple(),
        onResult = viewModel::filesPicked
    )
    val state by viewModel.state.collectAsState()
    val (painter, label) = if (state.uploadedFile == 0) {
        painterResource(Res.drawable.icon_doc) to stringResource(Res.string.upload_documents)
    } else {
        painterResource(Res.drawable.ic_check) to stringResource(Res.string.uploaded_documents, state.uploadedFile)
    }

    Card(
        modifier = Modifier.fillMaxWidth().clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = { launcher.launch() }
        ),
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(
            width = Paddings.border,
            color = colorScheme.tertiary
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(Paddings.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpaceVertical(Paddings.small)
            Image(
                modifier = Modifier.size(25.dp),
                painter = painter,
                contentDescription = null,
            )
            SpaceVertical(Paddings.small)
            TextSubHeader(label)
            SpaceVertical(Paddings.small)
            TextSecondary(stringResource(Res.string.file_types))

        }
    }
}
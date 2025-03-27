package app.playground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable internal fun MainScreen(
  navHostController: NavHostController,
) = Column {
  var value by remember { mutableStateOf("") }

  OutlinedTextField(
    value = value,
    modifier = Modifier.fillMaxWidth().padding(all = 16.dp),
    singleLine = false,
    minLines = 5, // Change this to 1 to avoid jumping.
    maxLines = 10,
    keyboardOptions = KeyboardOptions(
      capitalization = KeyboardCapitalization.Sentences,
      keyboardType = KeyboardType.Text,
      imeAction = ImeAction.Unspecified,
      autoCorrectEnabled = true,
      showKeyboardOnFocus = false,
    ),
    placeholder = { Text("Placeholder") }, // This placeholder is jumping when minLines is set to 5.
    onValueChange = {
      value = it
    },
  )
}

package app.playground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

enum class DragonTextType {
  TITLE,
  NORMAL_BOLD,
  NORMAL,
  FOOTER,
  ;

  @Composable fun textStyle() = when (this) {
    TITLE -> MaterialTheme.typography.titleLarge
    NORMAL_BOLD -> MaterialTheme.typography.bodyLarge
    NORMAL -> MaterialTheme.typography.bodyMedium
    FOOTER -> MaterialTheme.typography.bodySmall
  }

  fun fontWeight() = when (this) {
    TITLE -> FontWeight.Bold
    NORMAL_BOLD -> FontWeight.Bold
    NORMAL -> FontWeight.Normal
    FOOTER -> FontWeight.Light
  }
}

enum class DragonTextStyle {
  PRIMARY,
  SECONDARY,
  ON_PRIMARY,
  ON_SECONDARY,
}

@Composable fun DragonRawText(
  text: String,
  type: DragonTextType,
  modifier: Modifier,
  style: DragonTextStyle = DragonTextStyle.PRIMARY,
  maxLines: Int? = null,
  textAlign: TextAlign? = null,
) = Text(
  modifier = modifier,
  text = text,
  textAlign = textAlign,
  color = when (style) {
    DragonTextStyle.PRIMARY -> LocalContentColor.current
    DragonTextStyle.SECONDARY -> LocalContentColor.current.copy(alpha = 0.6f)
    DragonTextStyle.ON_PRIMARY -> MaterialTheme.colorScheme.onPrimaryContainer
    DragonTextStyle.ON_SECONDARY -> MaterialTheme.colorScheme.onSecondary
  },
  style = type.textStyle(),
  fontWeight = type.fontWeight(),
  maxLines = maxLines ?: Int.MAX_VALUE,
  overflow = TextOverflow.Ellipsis,
)

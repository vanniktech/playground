package app.playground

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.RangeSliderState
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable internal fun MainScreen(
  navHostController: NavHostController,
  currentMin: Int = 10,
  currentMax: Int = 50,
  range: IntRange = 1..100,
) = Column {
  Text("Hello World")

  val rangeSliderState = remember {
    RangeSliderState(
      currentMin.toFloat(),
      currentMax.toFloat(),
      steps = range.last - range.first,
      valueRange = range.first.toFloat()..range.last.toFloat(),
    )
  }

  rangeSliderState.onValueChangeFinished = {
    // onChanged(rangeSliderState.activeRangeStart.toInt(), rangeSliderState.activeRangeEnd.toInt())
  }

  val startInteractionSource = remember { MutableInteractionSource() }
  val endInteractionSource = remember { MutableInteractionSource() }

  Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
    val colors = SliderDefaults.colors(
      activeTrackColor = Color.Yellow, // This does not change the color.
    )
    RangeSlider(
      state = rangeSliderState,
      modifier = Modifier.semantics { contentDescription = "Foo" },
      startInteractionSource = startInteractionSource,
      endInteractionSource = endInteractionSource,
      colors = colors,
      startThumb = {
        Label(
          label = {
            PlainTooltip(modifier = Modifier.sizeIn(45.dp, 25.dp).wrapContentWidth()) {
              Text(
                text = rangeSliderState.activeRangeStart.toInt().toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally),
              )
            }
          },
          interactionSource = startInteractionSource,
        ) {
          SliderDefaults.Thumb(
            colors = colors,
            interactionSource = startInteractionSource,
          )
        }
      },
      endThumb = {
        Label(
          label = {
            PlainTooltip(modifier = Modifier.sizeIn(45.dp, 25.dp).wrapContentWidth()) {
              Text(
                text = rangeSliderState.activeRangeEnd.toInt().toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally),
              )
            }
          },
          interactionSource = endInteractionSource,
        ) {
          SliderDefaults.Thumb(
            colors = colors,
            interactionSource = endInteractionSource,
          )
        }
      },
      track = { rangeSliderState ->
        SliderDefaults.Track(
          rangeSliderState = rangeSliderState,
          drawStopIndicator = null,
          drawTick = { _, _ -> },
        )
      },
    )
  }
}

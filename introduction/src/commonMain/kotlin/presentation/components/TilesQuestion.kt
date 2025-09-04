package presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.QuestionName
import domain.model.Tile
import org.jetbrains.compose.resources.stringResource

@Composable
fun TilesQuestion(
    questionName: QuestionName,
    currentItem: Tile,
    onItemClick: (Tile) -> Unit,
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        FlowRow(
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.align(Alignment.Center),
        ) {
            questionName.getQuestionTiles().forEachIndexed { index, tile ->
                Tile(
                    modifier = Modifier.padding(
                        end = if (index == questionName.getQuestionTiles().lastIndex) 0.dp else 12.dp,
                    ),
                    content = stringResource(tile.getDisplayValue()),
                    isSelected = tile == currentItem,
                    onItemClick = {
                        onItemClick(tile)
                    }
                )
            }
        }
    }
} 
package yo.ane.linked

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import yo.ane.linked.ui.theme.LinkedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LinkedTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /* keep */ }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_keep),
                            contentDescription = "Theme"
                        )
                    }
                    IconButton(onClick = { /* search */ }) {
                        Icon(Icons.Default.Search, contentDescription = "search")
                    }
                    Spacer(Modifier.weight(1f, true))
                    AddButton(onClick = { /* add action */ })
                    Spacer(Modifier.weight(1f, true))
                    IconButton(onClick = { /* edit */ }) {
                        Icon(Icons.Default.Edit, contentDescription = "edit")
                    }
                    IconButton(onClick = { /* delete */ }) {
                        Icon(Icons.Default.Delete, contentDescription = "delete")
                    }
                },
            )
        }
    ) { padding ->
        Text(
            text = "Main content",
            fontSize = 26.sp,
            modifier = Modifier.padding(padding)
        )
    }
}

@SuppressLint("Range")
@Composable
fun AddButton(onClick: () -> Unit) {
    val primaryColor = MaterialTheme.colorScheme.primary
    Box(
        modifier = Modifier
            .size(56.dp)
            .clickable { onClick() }
            .drawWithCache {
                val radius = size.minDimension / 2
                val centerX = size.width / 2
                val centerY = size.height / 2

                val roundedPolygon = RoundedPolygon(
                    numVertices = 5,
                    radius = radius,
                    centerX = centerX,
                    centerY = centerY,
                    rounding = CornerRounding(
                        size.minDimension / 3f,
                        smoothing = 0.2f
                    )
                )

                val path = roundedPolygon.toPath().asComposePath()

                onDrawBehind {
                    drawPath(path, color = primaryColor)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(24.dp)
        )
    }
}
package br.com.insertkoin.airsoldier.ui.user


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import br.com.insertkoin.airsoldier.R
import br.com.insertkoin.airsoldier.data.models.User
import coil.compose.rememberAsyncImagePainter

@Composable
fun UserDetail(
    modifier: Modifier = Modifier,
    user: User
) {
    Row(modifier = modifier.padding(all = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        UserProfilePicture(user.picture, 75.dp)
        Spacer(modifier = modifier.width(16.dp))
        Column {
            Text(text = user.tag, style = MaterialTheme.typography.body1)
            Spacer(modifier = modifier.height(4.dp))
            Text(text = user.name, style = MaterialTheme.typography.h5)
        }
    }
}

@Composable
fun EditUserProfilePicture(updateUserPicture: (String) -> Unit) {
    val imageUri = rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { newUri: Uri? ->
        if (newUri == null) return@rememberLauncherForActivityResult

        val input = context.contentResolver.openInputStream(newUri)
            ?: return@rememberLauncherForActivityResult
        val outputFile = context.filesDir.resolve("${System.currentTimeMillis()}profilePic.jpg")
        input.copyTo(outputFile.outputStream())
        imageUri.value = outputFile.toUri().toString()
        updateUserPicture(imageUri.value)
    }
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        UserProfilePicture(imageUri.value, 120.dp)

        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = stringResource(id = R.string.edit_profile),
            modifier = Modifier
                .size(48.dp)
                .clickable { launcher.launch("image/*") }
        )
    }
}

@Composable
fun UserProfilePicture(uri: String, size: Dp) {
    val painter = rememberAsyncImagePainter(
        if (uri.isEmpty()) {
            R.drawable.ic_airsoldier
        } else {
            uri
        }
    )

    Image(
        painter = painter,
        contentDescription = stringResource(R.string.app_name),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .border(4.dp, MaterialTheme.colors.primary, CircleShape)
    )
}

@Composable
fun CircularLevelBar(
    percentage: Float,
    level: Int,
    fontSize: TextUnit,
    radius: Dp,
    color: Color = Color.LightGray,
    strokeWidth: Dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Box(
        modifier = Modifier.size(radius * 2f),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(radius * 2f)
        ) {
            drawArc(
                color = color,
                -90f,
                360 * currentPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = level.toString(),
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun UserDetailPreview() {
    Surface {
        UserDetail(modifier = Modifier, User(1, "Tief", 0, 3, "", "Soldado"))
    }
}
package br.com.insertkoin.airsoldier.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.insertkoin.airsoldier.R
import br.com.insertkoin.airsoldier.data.models.User
import br.com.insertkoin.airsoldier.ui.theme.AirSoldierTheme
import br.com.insertkoin.airsoldier.ui.user.CircularLevelBar
import br.com.insertkoin.airsoldier.ui.user.UserDetail

@Composable
fun HomeDrawer(
    modifier: Modifier = Modifier,
    user: User,
    editProfile: () -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp)
    ) {
        UserDetail(
            modifier = modifier,
            user
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            text = stringResource(R.string.title_nivel)
        )
        Box(
            modifier = modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.barbedwire),
                contentDescription = null,
                modifier = Modifier.size(110.dp)
            )
            CircularLevelBar(
                percentage = 0.85f,
                level = user.level,
                fontSize = 28.sp,
                radius = 30.dp,
                strokeWidth = 8.dp
            )
        }
        Spacer(modifier = modifier.height(16.dp))
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            text = stringResource(R.string.title_loadout)
        )
        LoadOutList(
            items = listOf(
                LoadOutItem(
                    1,
                    "ASSALT",
                    1,
                    0f
                ),
                LoadOutItem(
                    2,
                    "SUPORT",
                    1,
                    0.13f
                ),
                LoadOutItem(
                    3,
                    "DMR",
                    1,
                    0.44f
                ),
                LoadOutItem(
                    4,
                    "sniper",
                    3,
                    0.89f
                )
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = editProfile,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp
                ),
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            {
                Image(
                    painterResource(id = R.drawable.ic_baseline_edit_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 8.dp)
                )
                Text(text = stringResource(R.string.edit_profile))
            }
        }
    }
}

@Composable
fun LoadOutList(
    items: List<LoadOutItem>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            LoadOutCard(
                id = item.id,
                title = item.title,
                level = item.level,
                percentage = item.percentage
            )
        }
    }
}

@Composable
fun LoadOutCard(
    id: Int,
    title: String,
    level: Int,
    percentage: Float,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .width(192.dp)
                .padding(10.dp)
        ) {
            Text(
                text = title.uppercase(),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Row {
                Text(
                    text = stringResource(id = R.string.title_nivel),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                CircularLevelBar(
                    percentage = percentage,
                    level = level,
                    fontSize = 14.sp,
                    radius = 10.dp,
                    strokeWidth = 2.dp
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeDrawerPreview() {
    AirSoldierTheme {
        Surface {
            HomeDrawer(
                modifier = Modifier,
                User(
                    id = 1,
                    name = "Tief",
                    experience = 0,
                    level = 3,
                    picture = "",
                    tag = "Soldado"
                ),
                editProfile = {}
            )
        }
    }
}
package br.com.insertkoin.airsoldier.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.insertkoin.airsoldier.R
import br.com.insertkoin.airsoldier.ui.theme.AirSoldierTheme

@Composable
fun HomeDrawer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
        UserDetail()
        LoadOutList(
            items = listOf(
                LoadOutItem(
                    1,
                    "ASSALT",
                    1
                ),
                LoadOutItem(
                    2,
                    "SUPORT",
                    1
                ),
                LoadOutItem(
                    3,
                    "DMR",
                    1
                ),
                LoadOutItem(
                    4,
                    "SNIPER",
                    3
                )
            ),
            onItemClick = {
                println(it.title)
            }
        )
    }
}

@Composable
fun UserDetail(
    modifier: Modifier = Modifier
) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_airsoldier),
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = "Recruta", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Usuário", style = MaterialTheme.typography.h4)
        }
    }
}

@Composable
fun LoadOutList(
    items: List<LoadOutItem>,
    modifier: Modifier = Modifier,
    onItemClick: (LoadOutItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item) }
                    .padding(16.dp)
            ) {
                Text(text = "1")
                Text(text = "assalt")
                Column {
                    Text(text = "Nível")
                    Text(text = "1")
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeDrawerPreview() {
    AirSoldierTheme {
        HomeDrawer()
    }
}
package br.com.insertkoin.airsoldier.ui.navigation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.insertkoin.airsoldier.R
import br.com.insertkoin.airsoldier.ui.theme.AirSoldierTheme
import br.com.insertkoin.airsoldier.ui.user.EditUserProfilePicture

@Composable
fun ProfileScreen(
    saveButton: () -> Unit,
    userPicture: String,
    updateUserPicture: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 48.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.edit_profile_title),
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        EditUserProfilePicture(updateUserPicture = updateUserPicture)
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Button(onClick = saveButton) {

        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    AirSoldierTheme() {
        ProfileScreen(saveButton = {}, "", updateUserPicture = {})
    }
}
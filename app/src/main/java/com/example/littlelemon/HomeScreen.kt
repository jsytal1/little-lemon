package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.littlelemon.ui.theme.LittleLemonColors

@Composable
fun HomeScreen(navController: NavController, database: AppDatabase) {
    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

    Column {
        TopBarLoggedIn(navController = navController)
        HeroBanner()
        MenuItems(menuItems = databaseMenuItems)
    }
}

@Composable
fun MenuItems(menuItems: List<MenuItemRoom>) {
    Column {
        menuItems.forEach { menuItem ->
            MenuItem(menuItem = menuItem)
        }
    }
}

@Composable
fun MenuItem(menuItem: MenuItemRoom) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column {
            Text(
                text = menuItem.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(.7f)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = menuItem.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth(.7f)
                    .padding(bottom = 8.dp)

            )
            Text(
                text = "$" + String.format("%.2f", menuItem.price),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth(.7f)

            )
        }
        Column(
            modifier = Modifier

        ) {
            AsyncImage(
                model = menuItem.image,
                contentDescription = menuItem.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(8.dp))

            )
        }

    }
}

@Composable
fun HeroBanner() {
    Column(
        modifier = Modifier
            .background(LittleLemonColors.DarkGreen)
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = "Little Lemon",
            style = MaterialTheme.typography.titleLarge,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Chicago",
                    modifier = Modifier.padding(bottom = 28.dp),
                    style = MaterialTheme.typography.titleMedium,
                )

                Text(
                    text = stringResource(id = R.string.home_description),
                    style = MaterialTheme.typography.bodyMedium,
                    color = LittleLemonColors.White,
                    modifier = Modifier
                        .padding(bottom = 28.dp, end = 12.dp)
                        .fillMaxWidth(0.6f)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.hero),
                modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                contentDescription = "Hero"
            )
        }
    }
}

@Composable
private fun TopBarLoggedIn(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*TODO*/ }) {}
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .fillMaxWidth(.5f)
                .align(Alignment.CenterVertically)
                .height(48.dp)
        )
        IconButton(onClick = {
            navController.navigate(Profile.route)
        }) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Image",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TopBarLoggedInPreview() {
    TopBarLoggedIn(navController = rememberNavController())
}


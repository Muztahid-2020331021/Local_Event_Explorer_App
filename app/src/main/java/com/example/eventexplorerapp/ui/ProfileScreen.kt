package com.example.eventexplorerapp.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eventexplorerapp.BottomBar
import com.example.eventexplorerapp.MyTopBar
import com.example.eventexplorerapp.R
import com.example.eventexplorerapp.data.signedIn
import com.google.firebase.auth.FirebaseAuth


@Composable
fun ProfileScreen(
    navController: NavController
){
    val context = LocalContext.current
    val currentUser = FirebaseAuth.getInstance().currentUser

    Scaffold (
        topBar = {
            MyTopBar()
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ){contentPadding ->
        Column (
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ){

            val upSpace = contentPadding.calculateTopPadding()
            Spacer(modifier = Modifier.height(upSpace))

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(R.drawable.mehedi),
                    contentDescription = "cat",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(80.dp))

                Button(
                    onClick = {
                        signOut(navController, context)
                        signedIn = false
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .height(60.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.baseline_logout_24),
                            contentDescription = "log out",
                            modifier = Modifier
                                .size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Sign Out",
                            fontSize = 15.sp
                        )
                    }
                }
            }



            Text(
                text = "Mehedi Hasan",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )


            Spacer(modifier = Modifier.height(10.dp))
            ProfileMenu(
                icon = ImageVector.vectorResource(R.drawable.baseline_note_alt_24),
                text = "Scheduled\nevents",
                space = 95.dp,
                onClick = {
                    navController.navigate("ScheduledEvents")
                }
            )
            ProfileMenu(
                icon = Icons.Filled.Settings,
                text = "Settings",
                space = 120.dp,
                onClick = {}
            )
            ProfileMenu(
                icon = ImageVector.vectorResource(R.drawable.baseline_event_available_24),
                text = "My Events",
                space = 100.dp,
                onClick = {
                    navController.navigate("MyEvents")
                }
            )
            Spacer(modifier = Modifier.height(20.dp))

            val downSpace = contentPadding.calculateBottomPadding()
            Spacer(modifier = Modifier.height(downSpace))

        }
    }


}

@Composable
fun ProfileMenu(
    icon : ImageVector,
    text : String,
    space : Dp,
    onClick: () -> Unit
){
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = icon, 
            contentDescription = text,
            modifier = Modifier
                .size(50.dp)
        )
        Spacer(modifier = Modifier
            .width(20.dp)
        )
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.width(space))
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .background(Color.LightGray, RoundedCornerShape(10.dp))

        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "Null"
            )
        }
    }
}

private fun signOut(navController: NavController?, context: Context) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    if (currentUser != null) {
        FirebaseAuth.getInstance().signOut()
        navController?.navigate("login") // Navigate to login screen after sign out
    } else {
        showToast(context, "No user is currently signed in")
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun ProfilePreview(){
//    ProfileScreen()
//}


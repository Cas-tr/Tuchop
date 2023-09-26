package com.example.tuchop.ui.theme.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tuchop.R
import com.example.tuchop.navigation.ROUTE_VIEW_STUDENTS_SCREEN
import com.example.tuchop.navigation.ROUTE_VIEW_TEACHERS_SCREEN
import com.example.tuchop.ui.theme.TuchopTheme

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
//            .background(Castro),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "TUCHOP",
            color = Color.Red,
            fontFamily = FontFamily.Monospace,
            fontSize = 40.sp,
            modifier = Modifier.padding(0.dp,30.dp)

        )
        Spacer(modifier = Modifier.height(15.dp))


//        @Composable
//        fun CircleImageView(){
        Image(
            painter = painterResource(id = R.drawable.books),
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .size(128.dp)
                .clip(CircleShape)
                .background(Color.Blue)
        )
//        }

        Spacer(modifier = Modifier.height(15.dp))

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Button(onClick = {navController.navigate(ROUTE_VIEW_TEACHERS_SCREEN) }) {
                Text(text = "Teacher")
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(onClick = {navController.navigate(ROUTE_VIEW_STUDENTS_SCREEN) }) {
                Text(text = "Student")

            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "KULA MBUKU NA TUCHOP!!Links you with quality education",
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
                color = Color.Blue
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "HELPS BOTH THE TEACHER AND THE STUDENT!! Are you a teacher? If so,you're in the right place in that we can link you with a student ",
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
                color = Color.Blue)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Are you a student? We do the same!!  ",
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            color = Color.Blue)
    }

}

@Preview
@Composable
fun HomeScreenPreview(){
    TuchopTheme {
        HomeScreen(rememberNavController())
    }
}
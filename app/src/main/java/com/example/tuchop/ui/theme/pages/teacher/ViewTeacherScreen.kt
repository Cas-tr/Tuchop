package com.example.tuchop.ui.theme.pages.teacher

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tuchop.data.TeacherRepository
import com.example.tuchop.models.Teacher
import com.example.tuchop.navigation.ROUTE_ADD_TEACHERS_SCREEN
import com.example.tuchop.navigation.ROUTE_HOME
import com.example.tuchop.navigation.ROUTE_UPDATE_TEACHERS_SCREEN
import com.example.tuchop.ui.theme.TuchopTheme

@Composable
fun ViewTeacherScreen(navController: NavHostController) {
    var context = LocalContext.current
    var teacherRepository = TeacherRepository(navController, context)

    val emptyTeacherState = remember { mutableStateOf(Teacher("","","","","","","")) }
    var emptyTeacherListState = remember { mutableStateListOf<Teacher>() }

    var teachers = teacherRepository.viewTeacher(emptyTeacherState, emptyTeacherListState)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "TEACHERS",
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(teachers) {
                TeacherItem(
                    name = it.name,
                    email = it.email,
                    phoneNumber = it.phoneNumber,
                    levelOfEducation = it.levelOfEducation,
                    school =it.school,
                    subject = it.subject,
                    teacherId =it.teacherId ,
                    navController = navController,
                    teacherRepository = teacherRepository
                )
            }
        }
    }
    Column {
        Spacer(modifier = Modifier.weight(1f))

        Row {
            IconButton(onClick = { navController.navigate(ROUTE_HOME) },
                colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Red, contentColor = Color.White)) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "")
            }
            Spacer(modifier = Modifier.weight(1f))
            FloatingActionButton(
                onClick = {
                    navController.navigate(ROUTE_ADD_TEACHERS_SCREEN)
                },
                containerColor = Color.Red,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherItem(name:String, email:String, phoneNumber:String, levelOfEducation:String, school:String,subject:String,teacherId:String,
                navController: NavHostController, teacherRepository: TeacherRepository
) {
    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var context = LocalContext.current
        Card(onClick = {
            val uri = Uri.parse("smsto:+254$phoneNumber")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Hello\n" +
                    "My name is $name. I got your contact from Tuchop  and I would like you to take me through some classes.\n" +
                    "What do you say?")
            context.startActivity(intent)
        }, colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            modifier = Modifier.width(240.dp)) {
            Row {
                Text(text = "Name: ")
                Text(text = name)
            }
            Row {
                Text(text = "Email: ")
                Text(text = email)
            }
            Row {
                Text(text = "Phone Number: ")
                Text(text = phoneNumber)
            }
            Row {
                Text(text = "Level of Education: ")
                Text(text = levelOfEducation)
            }
            Row {
                Text(text = "School: ")
                Text(text = school)
            }
            Row {
                Text(text = "Subject: ")
                Text(text = subject)
            }
            Button(onClick = {
                teacherRepository.deleteTeacher(teacherId)
            },modifier = Modifier.align(CenterHorizontally)) {
                Text(text = "Delete")
            }
            Button(onClick = {
                navController.navigate(ROUTE_UPDATE_TEACHERS_SCREEN + "/$teacherId")
            },modifier = Modifier.align(CenterHorizontally)) {
                Text(text = "Update")
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Preview
@Composable
fun ViewTeacherScreenPreview(){
    TuchopTheme {
        ViewTeacherScreen(rememberNavController())
    }
}
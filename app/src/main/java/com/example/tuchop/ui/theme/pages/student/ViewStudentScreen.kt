package com.example.tuchop.ui.theme.pages.student

import android.content.Intent
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tuchop.data.StudentRepository
import com.example.tuchop.models.Student
import com.example.tuchop.navigation.ROUTE_ADD_STUDENTS_SCREEN
import com.example.tuchop.navigation.ROUTE_ADD_TEACHERS_SCREEN
import com.example.tuchop.navigation.ROUTE_HOME
import com.example.tuchop.navigation.ROUTE_UPDATE_STUDENTS_SCREEN
import com.example.tuchop.navigation.ROUTE_UPDATE_TEACHERS_SCREEN
import com.example.tuchop.ui.theme.TuchopTheme

@Composable
fun ViewStudentScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var studentRepository = StudentRepository(navController, context)


        val emptyStudentState = remember { mutableStateOf(Student("","","","","")) }
        var emptyStudentListState = remember { mutableStateListOf<Student>() }

        var students = studentRepository.viewStudent(emptyStudentState, emptyStudentListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { navController.navigate(ROUTE_HOME) },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.Red
                    )
                ) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "")
                }
            }
            Text(text = "All STUDENTS",
                color = Color.Red,
                fontFamily = FontFamily.Monospace,
                fontSize = 30.sp,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp))

            Text(text = "To contact a student, tap on them!", color = Color.Gray)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(students){
                    StudentItem(
                        name = it.name,
                        email = it.email,
                        phoneNumber = it.phoneNumber,
                        levelOfEducation = it.levelOfEducation,
                        studentId = it.userId,
                        navController = navController,
                        studentRepository = studentRepository
                    )
                }
            }
            Column {
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(ROUTE_ADD_STUDENTS_SCREEN)
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
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentItem( name:String,email:String,phoneNumber:String,levelOfEducation:String,studentId:String,
                 navController:NavHostController, studentRepository:StudentRepository) {

    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var context = LocalContext.current
        Card(onClick = {
            val uri = Uri.parse("smsto:+254$phoneNumber")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Hello,\n" +
                    "My name is Teacher $name. I got your contact from Tuchop and I would like to take you through some classes.\n" +
                    "What do you say?")
            context.startActivity(intent)
        }, colors = CardDefaults.cardColors(
//            containerColor = Color.White
        ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            modifier = Modifier.width(300.dp)
        ) {
            Row  (modifier = Modifier.align(Alignment.CenterHorizontally)){
                Text(text = "Name: ")
                Text(text = name)
            }
            Row  (modifier = Modifier.align(Alignment.CenterHorizontally)){
                Text(text = "Email: ")
                Text(text = email)
            }
            Row (modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = "Phone Number: ")
                Text(text = phoneNumber)
            }
            Row  (modifier = Modifier.align(Alignment.CenterHorizontally)){
                Text(text = "Level of Education: ")
                Text(text = levelOfEducation)
            }
            Row {
                IconButton(onClick = {studentRepository.deleteStudent(studentId) },
                    colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Red)) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                }
                IconButton(onClick = { navController.navigate("$ROUTE_UPDATE_STUDENTS_SCREEN/$studentId")},
                    colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Red)) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                }
            }
//            Button(onClick = {
//                studentRepository.deleteStudent(studentId)
//            },modifier = Modifier.align(Alignment.CenterHorizontally)) {
//                Text(text = "Delete")
//            }
//            Button(onClick = {
//                navController.navigate("$ROUTE_UPDATE_STUDENTS_SCREEN/$studentId")
//            },modifier = Modifier.align(Alignment.CenterHorizontally)) {
//                Text(text = "Update")
//            }
        }

    }
}


@Preview
@Composable
fun ViewStudentScreenPreview(){
    TuchopTheme {
        ViewStudentScreen(rememberNavController())
    }
}
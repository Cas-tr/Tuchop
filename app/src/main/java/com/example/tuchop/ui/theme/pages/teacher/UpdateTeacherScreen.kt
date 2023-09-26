package com.example.tuchop.ui.theme.pages.teacher

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tuchop.data.TeacherRepository
import com.example.tuchop.models.Teacher
import com.example.tuchop.navigation.ROUTE_HOME
import com.example.tuchop.navigation.ROUTE_VIEW_TEACHERS_SCREEN
import com.example.tuchop.ui.theme.TuchopTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTeacherScreen(navController: NavHostController,id:String) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { navController.navigate(ROUTE_HOME) }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "")
            }
        }
        var context = LocalContext.current
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var levelOfEducation by remember { mutableStateOf("") }
        var school by remember { mutableStateOf("")}
        var subject by remember { mutableStateOf("") }

        var currentDataRef = FirebaseDatabase.getInstance().getReference()
            .child("Teacher/$id")
        currentDataRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var teacher = snapshot.getValue(Teacher::class.java)
                name = teacher!!.name
                email = teacher!!.email
                phoneNumber= teacher!!.phoneNumber
                levelOfEducation = teacher!!.levelOfEducation
                school = teacher!!.school
                subject = teacher!!.subject
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })

        Text(
            text = "Add teacher",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Red,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )

        var teacherName by remember { mutableStateOf(TextFieldValue(name)) }
        var teacherEmail by remember { mutableStateOf(TextFieldValue(email)) }
        var teacherPhoneNumber by remember { mutableStateOf(TextFieldValue(phoneNumber)) }
        var teacherLevelOfEducation by remember { mutableStateOf(TextFieldValue(levelOfEducation)) }
        var teacherSchool by remember { mutableStateOf(TextFieldValue(school)) }
        var teacherSubject by remember { mutableStateOf(TextFieldValue(subject)) }

        OutlinedTextField(
            value = teacherName,
            onValueChange = { teacherName = it },
            label = { Text(text = "Teacher name *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = teacherEmail,
            onValueChange = { teacherEmail = it },
            label = { Text(text = "Teacher Email *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = teacherPhoneNumber,
            onValueChange = { teacherPhoneNumber = it },
            label = { Text(text = "Teacher PhoneNumber* start with 7..0r 1..") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = teacherLevelOfEducation,
            onValueChange = { teacherLevelOfEducation = it },
            label = { Text(text = "Teacher Level Of Education *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = teacherSchool,
            onValueChange = { teacherSchool = it },
            label = { Text(text = "Teacher's school *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = teacherSubject,
            onValueChange = { teacherSubject = it },
            label = { Text(text = "Teacher's school *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )


        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-----------WRITE THE UPDATE LOGIC HERE---------------//
            var teacherRepository = TeacherRepository(navController, context)
            teacherRepository.updateTeacher(teacherName.text.trim(),teacherEmail.text.trim(),
                teacherPhoneNumber.text.trim(),teacherLevelOfEducation.text.trim(),teacherSchool.text.trim(),
                teacherSubject.text.trim(),id)
            navController.navigate(ROUTE_VIEW_TEACHERS_SCREEN)


        }) {
            Text(text = "Update")
        }

    }
}

@Preview
@Composable
fun UpdateTeacherScreenPreview(){
    TuchopTheme {
        UpdateTeacherScreen(rememberNavController(),id="")
    }
}
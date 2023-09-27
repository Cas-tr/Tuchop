package com.example.tuchop.ui.theme.pages.teacher

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.tuchop.navigation.ROUTE_HOME
import com.example.tuchop.navigation.ROUTE_VIEW_TEACHERS_SCREEN
import com.example.tuchop.ui.theme.TuchopTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTeacherScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
//            .background(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { navController.navigate(ROUTE_HOME) },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.Red
                )) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "")
            }
        }
        var context = LocalContext.current
        Text(
            text = "ADD TEACHER",
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            color = Color.Red,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline

        )
        var name by remember { mutableStateOf(TextFieldValue("")) }
        var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
        var email by remember { mutableStateOf(TextFieldValue("")) }
        var levelOfEducation by remember { mutableStateOf("") }
        var isExpanded by remember { mutableStateOf(false) }
        var school by remember { mutableStateOf(TextFieldValue("")) }
        var subject by remember { mutableStateOf("") }
        var ifExpanded by remember { mutableStateOf(false) }




        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name*") },
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text(text = "Phone Number* start with 7..0r 1..") },
            leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            label = { Text(text = "Email*") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(20.dp))
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }
        ) {
            OutlinedTextField(
                value = levelOfEducation,
                onValueChange = {levelOfEducation=it},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
//                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor(),
                label = { Text(text = "Level of Education") }
            )
            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Primary") },
                    onClick = {
                        levelOfEducation = "Primary"
                        isExpanded = false
//                        navController.navigate(ROUTE_PRIMARY_SUBJECTS)
                    })
                DropdownMenuItem(
                    text = { Text(text = "Secondary") },
                    onClick = {
                        levelOfEducation = "Secondary"
                        isExpanded = false
//                        navController.navigate(ROUTE_SECONDARY_SUBJECTS)
                    }
                )


            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        ExposedDropdownMenuBox(expanded = ifExpanded, onExpandedChange = { ifExpanded = it }
        ) {
            OutlinedTextField(
                value = subject,
                onValueChange = {subject=it},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = ifExpanded)
                },
//                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor(),
                label = { Text(text = "Subjects*") }
            )
            ExposedDropdownMenu(expanded = ifExpanded, onDismissRequest = { ifExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Math") },
                    onClick = {
                        subject = "Math"
                        ifExpanded = false
//                        navController.navigate(ROUTE_PRIMARY_SUBJECTS)
                    })
                DropdownMenuItem(
                    text = { Text(text = "English") },
                    onClick = {
                        subject = "English"
                        ifExpanded = false
//                        navController.navigate(ROUTE_PRIMARY_SUBJECTS)
                    })

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = school,
            onValueChange = { school = it },
            label = { Text(text = "School*") },
            leadingIcon = { Icon(imageVector = Icons.Default.Create, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                var teacherRepository = TeacherRepository(navController, context)
                teacherRepository.saveTeacher(
                    name.text.trim(), email.text.trim(),
                    phoneNumber.text.trim(), levelOfEducation.trim(),
                    school.text.trim(),subject.trim()
                )
                navController.navigate(ROUTE_VIEW_TEACHERS_SCREEN)
            },colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White)
        ) {
            Text(text = "Submit")

        }


//}
    }
}

@Preview
@Composable
fun AddTeacherScreenPreview(){
    TuchopTheme {
        AddTeacherScreen(rememberNavController())
    }
}
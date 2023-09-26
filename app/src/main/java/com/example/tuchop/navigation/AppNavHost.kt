package com.example.tuchop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tuchop.ui.theme.pages.home.HomeScreen
import com.example.tuchop.ui.theme.pages.login.LoginScreen
import com.example.tuchop.ui.theme.pages.signup.SignUpScreen
import com.example.tuchop.ui.theme.pages.student.AddStudentScreen
import com.example.tuchop.ui.theme.pages.student.UpdateStudentScreen
import com.example.tuchop.ui.theme.pages.student.ViewStudentScreen
import com.example.tuchop.ui.theme.pages.teacher.AddTeacherScreen
import com.example.tuchop.ui.theme.pages.teacher.UpdateTeacherScreen
import com.example.tuchop.ui.theme.pages.teacher.ViewTeacherScreen

@Composable
fun AppNavHost( navController: NavHostController = rememberNavController(),
                startDestination:String = ROUTE_HOME) {
    NavHost(navController = navController,
        startDestination = startDestination){
        composable(ROUTE_HOME){
            HomeScreen(navController)
        }
        composable(route = ROUTE_ADD_STUDENTS_SCREEN){
            AddStudentScreen(navController)
        }
        composable(route = ROUTE_VIEW_STUDENTS_SCREEN){
            ViewStudentScreen(navController)
        }
        composable(route = ROUTE_UPDATE_STUDENTS_SCREEN+"/{id}"){passedData->
            UpdateStudentScreen(navController,passedData.arguments?.getString("id")!!)
        }
        composable(route = ROUTE_ADD_TEACHERS_SCREEN){
            AddTeacherScreen(navController)
        }
        composable(route = ROUTE_VIEW_TEACHERS_SCREEN){
            ViewTeacherScreen(navController)
        }
        composable(route = ROUTE_UPDATE_TEACHERS_SCREEN+"/{id}"){passedData->
            UpdateTeacherScreen(navController,passedData.arguments?.getString("id")!!)
        }
        composable(route = ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable(route = ROUTE_SIGNUP){
            SignUpScreen(navController)
        }
    }
}
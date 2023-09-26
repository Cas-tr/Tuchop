package com.example.tuchop.ui.theme.pages.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tuchop.ui.theme.TuchopTheme

@Composable
fun LoginScreen(navController: NavHostController) {

}

@Preview
@Composable
fun LoginScreenPreview(){
    TuchopTheme {
        LoginScreen(rememberNavController())
    }
}
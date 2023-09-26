package com.example.tuchop.ui.theme.pages.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tuchop.ui.theme.TuchopTheme

@Composable
fun SignUpScreen(navController: NavHostController) {

}

@Preview
@Composable
fun SignUpScreenPreview(){
    TuchopTheme {
        SignUpScreen(rememberNavController())
    }
}
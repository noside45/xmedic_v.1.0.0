package com.example.xmedic_v100.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xmedic_v100.R

/**
 * Isotipo representativo de Xmedic (Usado en el Login y Selección de Rol)
 */
@Composable
fun XmedicEmblem(
    modifier: Modifier = Modifier
) {
    Image(
        // Aquí puedes cambiar a R.drawable.mi_logo_xmedic_2 o 3 según cuál sea el icono
        painter = painterResource(id = R.drawable.mi_logo_xmedic_2),
        contentDescription = "Icono de Xmedic",
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}

/**
 * Logo completo de Xmedic (Usado en el Splash Screen)
 */
@Composable
fun XmedicBrandLogo(
    modifier: Modifier = Modifier,
    showText: Boolean = true, // Mantenemos el parámetro por compatibilidad
    fontSize: TextUnit = 32.sp
) {
    Image(
        // Aquí puedes cambiar a R.drawable.mi_logo_xmedic_1 o 3 según cuál sea el logo completo
        painter = painterResource(id = R.drawable.mi_logo_xmedic_1),
        contentDescription = "Logo Completo de Xmedic",
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}

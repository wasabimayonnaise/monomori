package com.monomori.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Material 3 Expressive shapes with playful, rounded corners
 */
val MonomoriShapes = Shapes(
    // Extra small shapes (chips, small buttons)
    extraSmall = RoundedCornerShape(8.dp),
    
    // Small shapes (buttons, text fields)
    small = RoundedCornerShape(12.dp),
    
    // Medium shapes (cards, dialogs)
    medium = RoundedCornerShape(16.dp),
    
    // Large shapes (bottom sheets, nav drawers)
    large = RoundedCornerShape(24.dp),
    
    // Extra large shapes (full-screen components)
    extraLarge = RoundedCornerShape(32.dp)
)

/**
 * Alternative playful shapes for special components
 */
object PlayfulShapes {
    val Button = RoundedCornerShape(20.dp)
    val Card = RoundedCornerShape(20.dp)
    val Chip = RoundedCornerShape(16.dp)
    val Dialog = RoundedCornerShape(28.dp)
    val FAB = RoundedCornerShape(16.dp)
    val BottomSheet = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
}

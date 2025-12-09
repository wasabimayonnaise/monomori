package com.monomori.data.model

/**
 * Represents a custom field definition that can be added to any category
 */
data class CustomField(
    val id: String,
    val name: String,
    val type: CustomFieldType,
    val value: String, // Stored as string, parsed based on type
    val options: List<String>? = null // For select fields
)

/**
 * Container for multiple custom fields
 */
data class CustomFieldsData(
    val fields: List<CustomField> = emptyList()
)

package com.monomori.data.model

/**
 * Main collection categories supported by Monomori
 */
enum class CollectionCategory {
    BOOKS,
    FIGURES,
    MUSIC,
    MOVIES_TV,
    VIDEO_GAMES,
    TRADING_CARDS,
    MODEL_KITS,
    MAGAZINES,
    ART_PRINTS,
    CUSTOM
}

/**
 * Subcategories for Books
 */
enum class BookSubcategory {
    FICTION,
    NON_FICTION,
    MANGA,
    ART_BOOKS,
    COMICS,
    GRAPHIC_NOVELS
}

/**
 * Subcategories for Figures & Statues
 */
enum class FigureSubcategory {
    SCALE_1_4,
    SCALE_1_6,
    SCALE_1_7,
    SCALE_1_8,
    SCALE_OTHER,
    NENDOROID,
    PRIZE_FIGURE,
    FIGMA,
    ACTION_FIGURE,
    FUNKO_POP
}

/**
 * Subcategories for Music
 */
enum class MusicSubcategory {
    VINYL,
    CD,
    CASSETTE
}

/**
 * Subcategories for Movies & TV
 */
enum class MovieSubcategory {
    DVD,
    BLU_RAY,
    UHD_4K,
    VHS
}

/**
 * Subcategories for Video Games
 */
enum class GameSubcategory {
    GAME,
    CONSOLE,
    ACCESSORY,
    LIMITED_EDITION
}

/**
 * Subcategories for Trading Cards
 */
enum class CardSubcategory {
    POKEMON,
    MAGIC_THE_GATHERING,
    YU_GI_OH,
    SPORTS,
    OTHER_TCG
}

/**
 * Subcategories for Model Kits
 */
enum class ModelSubcategory {
    GUNPLA,
    SCALE_MODEL_CAR,
    SCALE_MODEL_PLANE,
    SCALE_MODEL_TANK,
    MINIATURE_WARHAMMER
}

/**
 * Subcategories for Art & Prints
 */
enum class ArtSubcategory {
    POSTER,
    LIMITED_EDITION_PRINT,
    ORIGINAL_ART,
    DOUJINSHI
}

/**
 * Condition ratings for items
 */
enum class ItemCondition {
    MINT,
    NEAR_MINT,
    EXCELLENT,
    GOOD,
    FAIR,
    POOR
}

/**
 * Build status for model kits
 */
enum class BuildStatus {
    UNBUILT,
    IN_PROGRESS,
    COMPLETED
}

/**
 * Type definitions for custom fields
 */
enum class CustomFieldType {
    TEXT_SINGLE,
    TEXT_MULTI,
    NUMBER,
    DECIMAL,
    DATE,
    BOOLEAN,
    RATING,
    SINGLE_SELECT,
    MULTI_SELECT,
    URL,
    IMAGE
}

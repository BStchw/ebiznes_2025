package models

import "gorm.io/gorm"

type Category struct {
	gorm.Model
	Name     string    `json:"name"`
	Products []Product `gorm:"foreignKey:CategoryID"`
}

type Product struct {
	gorm.Model
	Name       string  `json:"name"`
	Price      float64 `json:"price"`
	CategoryID uint
}

type User struct {
	gorm.Model
	Name  string `json:"name"`
	Email string `json:"email"`
}

type Order struct {
	gorm.Model
	UserID uint
	Total  float64
}

type Review struct {
	gorm.Model
	UserID    uint
	ProductID uint
	Content   string
}

type Cart struct {
	gorm.Model
	UserID    uint
	ProductID uint
	Quantity  int
}

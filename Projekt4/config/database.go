package config

import (
	"Projekt4/models"
	"fmt"
	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
)

func InitDB() *gorm.DB {
	fmt.Println("Próba połączenia z bazą danych...")

	db, err := gorm.Open(sqlite.Open("app.db"), &gorm.Config{})
	if err != nil {
		fmt.Println("‼️ Błąd połączenia z bazą danych:", err)
		panic("failed to connect database")
	}

	err = db.AutoMigrate(
		&models.Category{},
		&models.Product{},
		&models.User{},
		&models.Order{},
		&models.Review{},
		&models.Cart{},
		&models.Payment{},
	)
	if err != nil {
		fmt.Println("Błąd migracji:", err)
		panic("failed to migrate database")
	}

	fmt.Println("Połączenie z bazą danych OK!")
	return db
}

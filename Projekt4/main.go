package main

import (
	"Projekt4/config"
	"Projekt4/routes"
	"fmt"
	"github.com/labstack/echo/v4"
)

func main() {
	fmt.Println("👉 Uruchamiam serwer Echo...") // DODAJ TO
	db := config.InitDB()
	e := echo.New()

	routes.SetupRoutes(e, db)

	e.Logger.Fatal(e.Start(":8080"))
}

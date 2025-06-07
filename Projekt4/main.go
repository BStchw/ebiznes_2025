package main

import (
	"Projekt4/config"
	"Projekt4/routes"
	"fmt"

	"github.com/labstack/echo/v4"
	"github.com/labstack/echo/v4/middleware"
)

func main() {
	fmt.Println("👉 Uruchamiam serwer Echo...")
	db := config.InitDB()

	e := echo.New()

	e.Use(middleware.CORSWithConfig(middleware.CORSConfig{
		AllowOrigins: []string{"http://localhost:5173"},
		AllowMethods: []string{"GET", "POST", "PUT", "DELETE", "OPTIONS"},
	}))

	routes.SetupRoutes(e, db)

	e.Logger.Fatal(e.Start(":8080"))
}

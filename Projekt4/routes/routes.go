package routes

import (
	"Projekt4/handlers"
	"github.com/labstack/echo/v4"
	"gorm.io/gorm"
)

func SetupRoutes(e *echo.Echo, db *gorm.DB) {
	h := &handlers.Handler{DB: db}

	p := e.Group("/products")
	p.POST("", h.CreateProduct)
	p.GET("", h.GetProducts)
	p.GET("/:id", h.GetProduct)
	p.PUT("/:id", h.UpdateProduct)
	p.DELETE("/:id", h.DeleteProduct)

	e.POST("/cart", handlers.AddToCart(db))
	e.GET("/cart", handlers.GetCartItems(db))

	e.POST("/payments", handlers.CreatePayment(db))
	e.GET("/payments", handlers.GetPayments(db))
}

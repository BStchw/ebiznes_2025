package handlers

import (
	"Projekt4/models"
	"github.com/labstack/echo/v4"
	"gorm.io/gorm"
	"net/http"
)

type Handler struct {
	DB *gorm.DB
}

func (h *Handler) CreateProduct(c echo.Context) error {
	var product models.Product
	if err := c.Bind(&product); err != nil {
		return err
	}
	h.DB.Create(&product)
	return c.JSON(http.StatusCreated, product)
}

func (h *Handler) GetProducts(c echo.Context) error {
	var products []models.Product
	h.DB.Preload("Category").Find(&products)
	return c.JSON(http.StatusOK, products)
}

func (h *Handler) GetProduct(c echo.Context) error {
	id := c.Param("id")
	var product models.Product
	if err := h.DB.First(&product, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"message": "Product not found"})
	}
	return c.JSON(http.StatusOK, product)
}

func (h *Handler) UpdateProduct(c echo.Context) error {
	id := c.Param("id")
	var product models.Product
	if err := h.DB.First(&product, id).Error; err != nil {
		return c.JSON(http.StatusNotFound, echo.Map{"message": "Product not found"})
	}

	if err := c.Bind(&product); err != nil {
		return err
	}
	h.DB.Save(&product)
	return c.JSON(http.StatusOK, product)
}

func (h *Handler) DeleteProduct(c echo.Context) error {
	id := c.Param("id")
	var product models.Product
	if err := h.DB.Delete(&product, id).Error; err != nil {
		return err
	}
	return c.NoContent(http.StatusNoContent)
}

func AddToCart(db *gorm.DB) echo.HandlerFunc {
	return func(c echo.Context) error {
		var cart models.Cart
		if err := c.Bind(&cart); err != nil {
			return err
		}
		db.Create(&cart)
		return c.JSON(http.StatusCreated, cart)
	}
}

func GetCartItems(db *gorm.DB) echo.HandlerFunc {
	return func(c echo.Context) error {
		var cart []models.Cart
		db.Find(&cart)
		return c.JSON(http.StatusOK, cart)
	}
}

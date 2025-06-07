package handlers

import (
	"Projekt4/models"
	"github.com/labstack/echo/v4"
	"gorm.io/gorm"
	"net/http"
)

func CreatePayment(db *gorm.DB) echo.HandlerFunc {
	return func(c echo.Context) error {
		var payment models.Payment
		if err := c.Bind(&payment); err != nil {
			return c.JSON(http.StatusBadRequest, map[string]string{"error": "Błędne dane"})
		}

		if err := db.Create(&payment).Error; err != nil {
			return c.JSON(http.StatusInternalServerError, map[string]string{"error": "Nie udało się zapisać płatności"})
		}

		return c.JSON(http.StatusCreated, payment)
	}
}

func GetPayments(db *gorm.DB) echo.HandlerFunc {
	return func(c echo.Context) error {
		var payments []models.Payment
		if err := db.Find(&payments).Error; err != nil {
			return c.JSON(http.StatusInternalServerError, map[string]string{"error": "Nie udało się pobrać płatności"})
		}
		return c.JSON(http.StatusOK, payments)
	}
}

package models

import "gorm.io/gorm"

type Payment struct {
	gorm.Model
	Name   string  `json:"name"`
	Amount float64 `json:"amount"`
}

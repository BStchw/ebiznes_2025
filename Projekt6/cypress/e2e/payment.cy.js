describe('Płatność - testy UI', () => {
    beforeEach(() => {
        cy.visit('/payment');
    });

    it('Powinno załadować widok płatności', () => {
        cy.contains('Płatność');
        cy.get('input[placeholder="Imię"]').should('exist');
        cy.get('input[placeholder="Kwota"]').should('exist');
        cy.get('button').contains('Zapłać').should('exist');
    });

    it('Powinno umożliwić wysłanie płatności z poprawnymi danymi', () => {
        cy.get('input[placeholder="Imię"]').type('Jan');
        cy.get('input[placeholder="Kwota"]').type('99.99');
        cy.intercept('POST', '/payments').as('postPayment');
        cy.get('button').contains('Zapłać').click();
        cy.wait('@postPayment').its('response.statusCode').should('eq', 201);
    });

    it('Nie powinno wysyłać formularza bez danych', () => {
        cy.get('button').contains('Zapłać').click();
    });

    it('Nie powinno akceptować niepoprawnej kwoty', () => {
        cy.get('input[placeholder="Imię"]').type('Jan');
        cy.get('input[placeholder="Kwota"]').type('abc');
        cy.get('button').contains('Zapłać').click();
    });

    it('Nie powinno pozwolić na płatność bez danych', () => {
        cy.visit('/payment');
        cy.get('button').contains('Zapłać').click();
        cy.on('window:alert', (text) => {
            expect(text).to.include('Błąd');
        });
    });

    it('Płatność z danymi powinna zakończyć się sukcesem', () => {
        cy.visit('/payment');
        cy.get('input[placeholder="Imię"]').type('Jan');
        cy.get('input[placeholder="Kwota"]').type('123');
        cy.get('button').contains('Zapłać').click();
        cy.on('window:alert', (text) => {
            expect(text).to.include('Płatność wysłana');
        });
    });
    
    it('Powinno umożliwić powrót z płatności do listy produktów', () => {
        cy.visit('/payment');
        cy.contains('Produkty').click();
        cy.url().should('include', '/');
        cy.contains('Produkty');
    });


});

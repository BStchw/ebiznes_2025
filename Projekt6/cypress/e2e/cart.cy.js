describe('Koszyk - testy UI', () => {
    it('Powinno pokazać komunikat, gdy koszyk jest pusty', () => {
        cy.visit('/cart');
        cy.contains('Koszyk');
        cy.contains('Koszyk jest pusty.');
    });

    it('Powinno wyświetlić produkt w koszyku po dodaniu', () => {
        cy.visit('/');
        cy.get('button').contains('Dodaj do koszyka').first().click();
        cy.get('a').contains('Koszyk').click();
        cy.get('li').should('have.length.at.least', 1);
    });

    it('Powinno umożliwić usunięcie produktu z koszyka', () => {
        cy.visit('/');
        cy.get('button').contains('Dodaj do koszyka').first().click();
        cy.get('a').contains('Koszyk').click();
        cy.get('button').contains('Usuń').first().click();
        cy.contains('Koszyk jest pusty.');
    });

    it('Po dodaniu wielu produktów, powinny być widoczne w koszyku', () => {
        cy.visit('/');
        cy.wait(1000);

        cy.get('li').eq(0).find('button').click();
        cy.get('li').eq(1).find('button').click();
        cy.get('li').eq(2).find('button').click();

        cy.contains('Koszyk').click();
        cy.get('li').should('have.length.at.least', 3);
    });

    it('Powinien umożliwić usunięcie produktu z koszyka', () => {
        cy.visit('/');
        cy.get('button').contains('Dodaj do koszyka').first().click();
        cy.contains('Koszyk').click();
        cy.get('button').contains('Usuń').click();
        cy.contains('Koszyk jest pusty.').should('exist');
    });

    it('Produkty w koszyku powinny być widoczne po powrocie z płatności', () => {
        cy.visit('/');
        cy.get('button').contains('Dodaj do koszyka').first().click();
        cy.contains('Płatność').click();
        cy.contains('Koszyk').click();
        cy.get('li').should('have.length.at.least', 1);
    });

    it('Każdy produkt ma przycisk "Dodaj do koszyka"', () => {
        cy.visit('/');
        cy.get('li').each(($li) => {
            cy.wrap($li).find('button').should('contain', 'Dodaj do koszyka');
        });
    });


});

describe('Produkty - testy UI', () => {
    beforeEach(() => {
        cy.visit('/');
    });

    it('Powinno załadować listę produktów', () => {
        cy.contains('Produkty');
        cy.get('button').should('contain', 'Dodaj do koszyka');
    });

    it('Każdy produkt powinien mieć nazwę i cenę', () => {
        cy.get('li').each(($el) => {
            cy.wrap($el).should('contain.text', 'zł');
        });
    });

    it('Powinno dodać produkt do koszyka i wyświetlić go w koszyku', () => {
        cy.get('button').contains('Dodaj do koszyka').first().click();
        cy.get('a').contains('Koszyk').click();
        cy.contains('Koszyk');
        cy.get('li').should('have.length.at.least', 1);
    });

    it('Powinno umożliwić dodanie tego samego produktu kilka razy', () => {
        cy.get('button').contains('Dodaj do koszyka').first().click();
        cy.get('button').contains('Dodaj do koszyka').first().click();
        cy.get('button').contains('Dodaj do koszyka').first().click();

        cy.get('a').contains('Koszyk').click();
        cy.get('li').should('have.length.at.least', 3);
    });


    it('Po dodaniu produktu, link do koszyka powinien być dostępny', () => {
        cy.get('button').contains('Dodaj do koszyka').first().click();
        cy.get('a').contains('Koszyk').should('exist');
    });

    it('Nie powinien dodać produktu jeśli brak danych', () => {
        cy.visit('/');
        cy.get('button').contains('Dodaj nieistniejący').should('not.exist');
    });

    it('Powinien umożliwiać powrót z koszyka do produktów', () => {
        cy.visit('/');
        cy.contains('Koszyk').click();
        cy.contains('Produkty').click();
        cy.url().should('include', '/');
    });


});

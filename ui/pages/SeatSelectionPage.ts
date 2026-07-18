import { expect, Page } from '@playwright/test';
import { BasePage } from './BasePage';


export class SeatSelectionPage extends BasePage {


    constructor(page: Page) {
        super(page);
    }



    // Locators

    private cabin = () =>
        this.page.locator('[data-layout="cabin"]');



    private availableSeats = () =>
        this.page.locator('.seat.available');



    private continueButton = () =>
        this.page.locator('#continue-btn');





    // Verify Seat Map

    async verifySeatMap() {

        await expect(
            this.cabin()
        ).toBeVisible({
            timeout:15000
        });

    }







    // Select Random Seat

    async selectRandomSeat() {


        await this.verifySeatMap();



        const seatCount =
            await this.availableSeats()
                .count();



        expect(seatCount)
            .toBeGreaterThan(0);




        const randomIndex =
            Math.floor(
                Math.random() * seatCount
            );



        const seat =
            this.availableSeats()
                .nth(randomIndex);



        console.log(
            "Available seats:",
            seatCount
        );


        console.log(
            "Selected seat index:",
            randomIndex
        );




        await seat.scrollIntoViewIfNeeded();



        await seat.click({
            force:true
        });




        // Allow UI state update

        await this.page.waitForTimeout(
            3000
        );


    }







    // Continue Booking

    async continueBooking() {



        await expect(
            this.continueButton()
        ).toBeEnabled({
            timeout:15000
        });



        await this.continueButton()
            .click();

    }







    // Complete Seat Selection

    async selectSeatAndContinue() {


        await this.selectRandomSeat();



        await this.continueBooking();



        await expect(
            this.page
        ).toHaveURL(
            /book\/passenger/,
            {
                timeout:15000
            }
        );

    }


}
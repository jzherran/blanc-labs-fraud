package com.blanc_labs.fraud;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
public class GeneratorController {

    private static ArrayList<Long> sumOfCreditCardsPerDay = new ArrayList<>();

    /**
     * 
     * @return
     */
    @RequestMapping("/")
    public String home() {
        return "Hello Blanc Labs - Fraud Card Control";
    }

    /**
     * 
     * @param day
     * @return
     */
    @GetMapping(value = "/cc/generator", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getCCGeneratedOnDay(@RequestParam("day") int day) {
        // Builder object to build the answer

        if (day < 1) {
            return new ResponseEntity<String>("Invalid day", HttpStatus.BAD_REQUEST);
        } else {
            ResponseGenerator rg = new ResponseGenerator(day, calculateCCPerDay(day));
            return new ResponseEntity<>(rg, HttpStatus.OK);
        }
    }

    /**
     * 
     * @param day
     * @return
     */
    private long calculateCCPerDay(int day) {
        int sizeInitial = sumOfCreditCardsPerDay.size();

        for (; sizeInitial < day; sizeInitial++) {
            if (sizeInitial == 0) {
                sumOfCreditCardsPerDay.add(1L);
            } else {
                long currentDay = sizeInitial + 1;
                long value = sumOfCreditCardsPerDay.get(sizeInitial - 1)
                        + (sumOfCreditCardsPerDay.get(sizeInitial - 1) * currentDay) + currentDay;
                sumOfCreditCardsPerDay.add(value);
            }
        }

        if (day == 1) {
            return 1;
        } else {
            long value = (sumOfCreditCardsPerDay.get(day - 2) * day) + day;
            sumOfCreditCardsPerDay.add(value);
            return value;
        }
    }

    class ResponseGenerator {

        int day;

        long numberOfCreditCards;

        public ResponseGenerator(int day, long numberOfCreditCards) {
            this.day = day;
            this.numberOfCreditCards = numberOfCreditCards;
        }

        /**
         * @return the day
         */
        public int getDay() {
            return day;
        }

        /**
         * @param day the day to set
         */
        public void setDay(int day) {
            this.day = day;
        }

        /**
         * @return the numberOfCreditCards
         */
        public long getNumberOfCreditCards() {
            return numberOfCreditCards;
        }

        /**
         * @param numberOfCreditCards the numberOfCreditCards to set
         */
        public void setNumberOfCreditCards(long numberOfCreditCards) {
            this.numberOfCreditCards = numberOfCreditCards;
        }
    }
}
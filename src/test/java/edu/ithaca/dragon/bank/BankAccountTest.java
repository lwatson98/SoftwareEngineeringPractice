package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void isEmailValidTest(){
        /**This tests for the initial requirement for an @ symbol.
         *Considering an email can either have one, none, or more @ symbols.
         *This is technically at a boundary, seeing as we can't have infinite
         * @ symbols and we can't have negative. We either have one of don't.
         */
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        /**
         * This is similar to the previous test; however
         * this may serve as a test for three different cases:
         * A lack of an @ symbol, and not having a period nor a suffix
         * a least over two characters in length. The first two cases,
         * not having an @ symbol nor . might count as border cases,
         *  from a similar binary present/not present distinction.
         *  However with the suffix length, while it does test an invalid
         *  partition, it is technically not a border case, since that would
         *  need to be a suffix length of one, a case adjacent to a valid example
         *  (A suffix of equal to length two).
         */
        assertFalse( BankAccount.isEmailValid(""));
        /**
         * This solely tests for a lack of a period, therefore testing for a lack
         * of a suffix. I would say this is a border case for a binary has/has not check.
         */
        assertFalse(BankAccount.isEmailValid("a@bcom"));
        /**
         * This checks to see if a non-alphanumeric character is adjacent to
         * an alphanumeric character. Considering this can either simply be true or false,
         * yet another binary decision, then this is a border case.
         */
        assertFalse(BankAccount.isEmailValid("a@b..com"));
        /**
         * This technically checks for a valid suffix, but since we already have
         * a valid example with .com and we only care about the minimum character limit of
         * two, this test is redundant. I would say this may count as a valid border case,
         * but unneeded in this current set of tests.
         */
        assertTrue(BankAccount.isEmailValid("a@b.org"));
        /**
         * This checks to make sure a nonalphanumeric character other than .
         * is adjacent to valid characters (alphanumeric). Since this test is seperate
         * from testing suffixes, and simply testing valid uses of nonalpha characters,
         * then this is a border case in the valid range.
         */
        assertTrue(BankAccount.isEmailValid("a@b.mail-archive.com"));

        /**
         * Honestly, speaking these aren't needed persay since they fall under the same rules
         * of using alphanumeric characters, but having tests using numbers as well as alphabetic
         * characters. Perhaps using numbers and having them adjacent to nonalphanumeric characters,
         * or just checking their general use.
         */
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }
    /**Takes a double and returns true if the amount is positive and has two decimal points or less, and false otherwise.
     *
     * */
    @Test
    void isAmountValidTest(){
        /**Tests to return false if the inputted string parameter is a negative number
         *
         * */
        BankAccount bankAccount2 = new BankAccount("lcole@gmail.com",-9.00);
        assertFalse(bankAccount2.isAmountValid(-9.00));
/**Tests to return false if the inputted string parameter is a negative number
 *
 * */
        /**Tests to return false if the inputted double parameter is number more than 2 decimals
         *
         * */
        assertFalse(bankAccount2.isAmountValid(9.050));

/**Tests to return true if the inputted double parameter is a positive number
 *
 * */
        assertTrue(bankAccount2.isAmountValid(900));

    }

    void DepositTest(){
        BankAccount bankAccount3 = new BankAccount("jcole@gmail.com",20);
        assertFalse(bankAccount3.deposit(-9.00,bankAccount3));
/**Tests to return false if the inputted string parameter is a negative number
 *
 * */
        /**Tests to return false if the inputted double parameter is number more than 2 decimals
         *
         * */
        assertThrows(bankAccount3.deposit(9.050,bankAccount3));

/**Tests to return true if the inputted double parameter is a positive number
 *
 * */
        assertTrue(bankAccount3.deposit(900,bankAccount3));


    }

    void transferTest(){
        BankAccount bankAccount4 = new BankAccount("jcole@gmail.com",14);
        BankAccount bankAccount5 = new BankAccount("dcole@gmail.com",14);
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("acole@gmail", 100).transfer(9.050,bankAccount4, bankAccount5));
/**Tests to return false if the inputted string parameter is a negative number
 *
 * */
        /**Tests to return false if the inputted double parameter is number more than 2 decimals
         *
         * */
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("acole@gmail", 100).transfer(9.050,bankAccount4, bankAccount5));

/**Tests to return true if the inputted double parameter is a positive number
 *
 * */
        assertTrue(bankAccount4.transfer(900,bankAccount4, bankAccount5));


    }
}


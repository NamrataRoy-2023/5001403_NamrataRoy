CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    sourceAccountID IN NUMBER,
    destinationAccountID IN NUMBER,
    amount IN NUMBER
)
IS
    sourceBalance NUMBER;
    destinationBalance NUMBER;
    insufficient_funds EXCEPTION;
BEGIN
    SELECT Balance INTO sourceBalance FROM Accounts WHERE AccountID = sourceAccountID FOR UPDATE;

    IF sourceBalance < amount THEN
        RAISE insufficient_funds;
    END IF;

    SELECT Balance INTO destinationBalance FROM Accounts WHERE AccountID = destinationAccountID FOR UPDATE;

    UPDATE Accounts
    SET Balance = Balance - amount
    WHERE AccountID = sourceAccountID;

    UPDATE Accounts
    SET Balance = Balance + amount
    WHERE AccountID = destinationAccountID;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Transfer completed successfully.');
EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in the source account.');
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: One of the accounts does not exist.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END SafeTransferFunds;
/

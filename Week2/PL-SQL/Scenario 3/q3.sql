CREATE OR REPLACE PROCEDURE TransferFunds(
    p_SourceAccountID IN NUMBER,
    p_DestinationAccountID IN NUMBER,
    p_Amount IN NUMBER
)
IS
    v_SourceBalance NUMBER;
    v_MaxTransactionID NUMBER;
BEGIN
    SELECT Balance INTO v_SourceBalance
    FROM Accounts
    WHERE AccountID = p_SourceAccountID
    FOR UPDATE;
 
    IF v_SourceBalance < p_Amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance in the source account.');
    END IF;
 
    UPDATE Accounts
    SET Balance = Balance - p_Amount, LastModified = SYSDATE
    WHERE AccountID = p_SourceAccountID;
 
    UPDATE Accounts
    SET Balance = Balance + p_Amount, LastModified = SYSDATE
    WHERE AccountID = p_DestinationAccountID;
 
    SELECT NVL(MAX(TransactionID), 0) INTO v_MaxTransactionID
    FROM Transactions;
 
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (v_MaxTransactionID + 1, p_SourceAccountID, SYSDATE, p_Amount, 'Withdrawal');
 
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (v_MaxTransactionID + 2, p_DestinationAccountID, SYSDATE, p_Amount, 'Deposit');
 
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END TransferFunds;
/

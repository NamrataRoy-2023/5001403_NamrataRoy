DECLARE
    TYPE TransactionRecord IS RECORD (
        CustomerID       NUMBER,
        CustomerName     VARCHAR2(100),
        AccountID        NUMBER,
        TransactionID    NUMBER,
        TransactionDate  DATE,
        Amount           NUMBER,
        TransactionType  VARCHAR2(10)
    );

    CURSOR TransactionsCursor IS
        SELECT c.CustomerID, c.Name AS CustomerName, t.AccountID, t.TransactionID, 
               t.TransactionDate, t.Amount, t.TransactionType
        FROM Customers c
        JOIN Accounts a ON c.CustomerID = a.CustomerID
        JOIN Transactions t ON a.AccountID = t.AccountID
        WHERE t.TransactionDate >= TRUNC(SYSDATE, 'MM') AND t.TransactionDate < ADD_MONTHS(TRUNC(SYSDATE, 'MM'), 1)
        ORDER BY c.CustomerID, t.TransactionDate;

    transaction_rec TransactionRecord;

    current_customer_id NUMBER := NULL;
    current_account_id  NUMBER := NULL;

BEGIN
    OPEN TransactionsCursor;

    LOOP
        FETCH TransactionsCursor INTO transaction_rec;
        EXIT WHEN TransactionsCursor%NOTFOUND;

        IF current_customer_id IS NULL OR current_customer_id != transaction_rec.CustomerID THEN
            IF current_customer_id IS NOT NULL THEN
                DBMS_OUTPUT.PUT_LINE('----------------------');
            END IF;
            DBMS_OUTPUT.PUT_LINE('Customer ID: ' || transaction_rec.CustomerID);
            DBMS_OUTPUT.PUT_LINE('Customer Name: ' || transaction_rec.CustomerName);
            DBMS_OUTPUT.PUT_LINE('----------------------');
            current_customer_id := transaction_rec.CustomerID;
            current_account_id  := NULL;
        END IF;

        IF current_account_id IS NULL OR current_account_id != transaction_rec.AccountID THEN
            DBMS_OUTPUT.PUT_LINE('Account ID: ' || transaction_rec.AccountID);
            current_account_id := transaction_rec.AccountID;
        END IF;

        DBMS_OUTPUT.PUT_LINE('Transaction ID: ' || transaction_rec.TransactionID);
        DBMS_OUTPUT.PUT_LINE('Transaction Date: ' || TO_CHAR(transaction_rec.TransactionDate, 'YYYY-MM-DD'));
        DBMS_OUTPUT.PUT_LINE('Transaction Type: ' || transaction_rec.TransactionType);
        DBMS_OUTPUT.PUT_LINE('Amount: ' || transaction_rec.Amount);
        DBMS_OUTPUT.PUT_LINE('----------------------');
    END LOOP;

    CLOSE TransactionsCursor;
END;
/

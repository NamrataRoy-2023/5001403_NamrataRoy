DECLARE
    annual_fee CONSTANT NUMBER := 50;

    TYPE AccountRecord IS RECORD (
        AccountID   NUMBER,
        Balance     NUMBER
    );

    CURSOR AccountsCursor IS
        SELECT AccountID, Balance
        FROM Accounts
        FOR UPDATE OF Balance;

    account_rec AccountRecord;

BEGIN
    OPEN AccountsCursor;

    LOOP
        FETCH AccountsCursor INTO account_rec;
        EXIT WHEN AccountsCursor%NOTFOUND;

        UPDATE Accounts
        SET Balance = Balance - annual_fee
        WHERE CURRENT OF AccountsCursor;
    END LOOP;

    CLOSE AccountsCursor;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Annual maintenance fee applied to all accounts.');
END;
/

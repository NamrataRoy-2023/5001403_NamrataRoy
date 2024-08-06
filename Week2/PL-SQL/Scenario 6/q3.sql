DECLARE
    interest_rate_increase CONSTANT NUMBER := 1;

    TYPE LoanRecord IS RECORD (
        LoanID        NUMBER,
        InterestRate  NUMBER
    );

    CURSOR LoansCursor IS
        SELECT LoanID, InterestRate
        FROM Loans
        FOR UPDATE OF InterestRate;

    loan_rec LoanRecord;

BEGIN
    OPEN LoansCursor;

    LOOP
        FETCH LoansCursor INTO loan_rec;
        EXIT WHEN LoansCursor%NOTFOUND;

        UPDATE Loans
        SET InterestRate = loan_rec.InterestRate + interest_rate_increase
        WHERE CURRENT OF LoansCursor;
    END LOOP;

    CLOSE LoansCursor;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Interest rates updated for all loans.');
END;
/

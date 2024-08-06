DECLARE
    CURSOR c_due_loans IS
        SELECT l.LoanID, l.CustomerID, c.Name, l.LoanAmount, l.EndDate
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
    
    v_reminder_count NUMBER := 0;

BEGIN
    FOR loan_rec IN c_due_loans LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder for Loan ID: ' || loan_rec.LoanID);
        DBMS_OUTPUT.PUT_LINE('Dear ' || loan_rec.Name || ',');
        DBMS_OUTPUT.PUT_LINE('This is a reminder that your loan of $' || 
                             TO_CHAR(loan_rec.LoanAmount, '999,999.99') || 
                             ' is due on ' || TO_CHAR(loan_rec.EndDate, 'YYYY-MM-DD') || '.');
        DBMS_OUTPUT.PUT_LINE('Please ensure timely repayment to avoid any late fees.');
        DBMS_OUTPUT.PUT_LINE('-------------------------------------------');
        
        v_reminder_count := v_reminder_count + 1;
    END LOOP;
    
    IF v_reminder_count > 0 THEN
        DBMS_OUTPUT.PUT_LINE('Total reminders generated: ' || v_reminder_count);
    ELSE
        DBMS_OUTPUT.PUT_LINE('No loans are due within the next 30 days.');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
END;
/

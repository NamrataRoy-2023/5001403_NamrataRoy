DECLARE
    v_customer_id Customers.CustomerID%TYPE;
    v_dob Customers.DOB%TYPE;
    v_age NUMBER;
    v_loan_id Loans.LoanID%TYPE;
    v_interest_rate Loans.InterestRate%TYPE;
    
    CURSOR c_customers IS
        SELECT CustomerID, DOB
        FROM Customers;
    
    CURSOR c_loans(p_customer_id NUMBER) IS
        SELECT LoanID, InterestRate
        FROM Loans
        WHERE CustomerID = p_customer_id;

BEGIN
    FOR customer_rec IN c_customers LOOP
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, customer_rec.DOB) / 12);
        
        IF v_age > 60 THEN
            FOR loan_rec IN c_loans(customer_rec.CustomerID) LOOP
                v_interest_rate := loan_rec.InterestRate - 1;
                
                v_interest_rate := GREATEST(v_interest_rate, 0);
                
                UPDATE Loans
                SET InterestRate = v_interest_rate
                WHERE LoanID = loan_rec.LoanID;
                
                DBMS_OUTPUT.PUT_LINE('Updated loan ' || loan_rec.LoanID || 
                                     ' for customer ' || customer_rec.CustomerID || 
                                     '. New interest rate: ' || v_interest_rate || '%');
            END LOOP;
        END IF;
    END LOOP;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Process completed successfully.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
END;
/

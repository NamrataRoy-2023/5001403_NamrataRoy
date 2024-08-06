ALTER TABLE Customers ADD IsVIP NUMBER(1) DEFAULT 0;

DECLARE
    CURSOR c_customers IS
        SELECT CustomerID, Balance
        FROM Customers;
    
    v_vip_threshold NUMBER := 10000;
    v_updated_count NUMBER := 0;

BEGIN
    FOR customer_rec IN c_customers LOOP
        IF customer_rec.Balance > v_vip_threshold THEN
            UPDATE Customers
            SET IsVIP = 1
            WHERE CustomerID = customer_rec.CustomerID;
            
            v_updated_count := v_updated_count + 1;
        ELSE
            UPDATE Customers
            SET IsVIP = 0
            WHERE CustomerID = customer_rec.CustomerID;
        END IF;
    END LOOP;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Process completed. ' || v_updated_count || ' customers updated to VIP status.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
END;
/

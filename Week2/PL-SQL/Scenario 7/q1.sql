CREATE SEQUENCE CustomerID_seq
    START WITH 4
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
/



CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(
        p_Name IN VARCHAR2,
        p_DOB IN DATE,
        p_Balance IN NUMBER
    );

    PROCEDURE UpdateCustomer(
        p_CustomerID IN NUMBER,
        p_Name IN VARCHAR2,
        p_DOB IN DATE,
        p_Balance IN NUMBER
    );

    FUNCTION GetCustomerBalance(
        p_CustomerID IN NUMBER
    ) RETURN NUMBER;
END CustomerManagement;
/




CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(
        p_Name IN VARCHAR2,
        p_DOB IN DATE,
        p_Balance IN NUMBER
    ) IS
        v_CustomerID NUMBER;
    BEGIN
        SELECT CustomerID_seq.NEXTVAL INTO v_CustomerID FROM DUAL;

        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (v_CustomerID, p_Name, p_DOB, p_Balance, SYSDATE);
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE_APPLICATION_ERROR(-20001, 'Error adding customer: ' || SQLERRM);
    END AddCustomer;

    PROCEDURE UpdateCustomer(
        p_CustomerID IN NUMBER,
        p_Name IN VARCHAR2,
        p_DOB IN DATE,
        p_Balance IN NUMBER
    ) IS
    BEGIN
        UPDATE Customers
        SET Name = p_Name, DOB = p_DOB, Balance = p_Balance, LastModified = SYSDATE
        WHERE CustomerID = p_CustomerID;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE_APPLICATION_ERROR(-20002, 'Error updating customer: ' || SQLERRM);
    END UpdateCustomer;

    FUNCTION GetCustomerBalance(
        p_CustomerID IN NUMBER
    ) RETURN NUMBER IS
        v_Balance NUMBER;
    BEGIN
        SELECT Balance INTO v_Balance
        FROM Customers
        WHERE CustomerID = p_CustomerID;
        RETURN v_Balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20003, 'Customer not found.');
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20004, 'Error retrieving balance: ' || SQLERRM);
    END GetCustomerBalance;

END CustomerManagement;
/

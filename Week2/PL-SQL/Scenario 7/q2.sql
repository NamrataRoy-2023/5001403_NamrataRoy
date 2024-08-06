CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_EmployeeID NUMBER,
        p_Name VARCHAR2,
        p_Position VARCHAR2,
        p_Salary NUMBER,
        p_Department VARCHAR2,
        p_HireDate DATE
    );

    PROCEDURE UpdateEmployeeDetails(
        p_EmployeeID NUMBER,
        p_Name VARCHAR2 := NULL,
        p_Position VARCHAR2 := NULL,
        p_Salary NUMBER := NULL,
        p_Department VARCHAR2 := NULL
    );

    FUNCTION CalculateAnnualSalary(
        p_EmployeeID NUMBER
    ) RETURN NUMBER;
END EmployeeManagement;
/







CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_EmployeeID NUMBER,
        p_Name VARCHAR2,
        p_Position VARCHAR2,
        p_Salary NUMBER,
        p_Department VARCHAR2,
        p_HireDate DATE
    ) IS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_EmployeeID, p_Name, p_Position, p_Salary, p_Department, p_HireDate);
    END HireEmployee;

    PROCEDURE UpdateEmployeeDetails(
        p_EmployeeID NUMBER,
        p_Name VARCHAR2 := NULL,
        p_Position VARCHAR2 := NULL,
        p_Salary NUMBER := NULL,
        p_Department VARCHAR2 := NULL
    ) IS
    BEGIN
        UPDATE Employees
        SET 
            Name = NVL(p_Name, Name),
            Position = NVL(p_Position, Position),
            Salary = NVL(p_Salary, Salary),
            Department = NVL(p_Department, Department)
        WHERE EmployeeID = p_EmployeeID;
    END UpdateEmployeeDetails;

    FUNCTION CalculateAnnualSalary(
        p_EmployeeID NUMBER
    ) RETURN NUMBER IS
        v_Salary NUMBER;
    BEGIN
        SELECT Salary INTO v_Salary
        FROM Employees
        WHERE EmployeeID = p_EmployeeID;

        RETURN v_Salary * 12;
    END CalculateAnnualSalary;

END EmployeeManagement;
/

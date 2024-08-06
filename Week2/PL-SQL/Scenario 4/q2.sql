CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_LoanAmount NUMBER,
    p_InterestRate NUMBER,
    p_DurationYears NUMBER
)
RETURN NUMBER
IS
    v_MonthlyRate NUMBER;
    v_NumberOfMonths NUMBER;
    v_EMI NUMBER;
BEGIN
    v_MonthlyRate := p_InterestRate / 1200;

    v_NumberOfMonths := p_DurationYears * 12;

    IF v_MonthlyRate != 0 THEN
        v_EMI := p_LoanAmount * v_MonthlyRate * POWER(1 + v_MonthlyRate, v_NumberOfMonths) /
                 (POWER(1 + v_MonthlyRate, v_NumberOfMonths) - 1);
    ELSE
        v_EMI := p_LoanAmount / v_NumberOfMonths;
    END IF;
    RETURN v_EMI;
END CalculateMonthlyInstallment;
/

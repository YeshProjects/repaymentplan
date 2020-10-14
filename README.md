# repaymentplan
In order to inform borrowers about the final repayment schedule, we need to have pre-calculated repayment plans throughout the lifetime of a loan. To be able to calculate a repayment plan specific input parameters are necessary: duration (number of installments in months) nominal rate (annual interest rate) loan amount (principal amount) Date of Disbursement/Payout ("startDate") These four parameters need to be input parameters. The goal is to calculate a repayment plan for an annuity loan. Therefore the amount that the borrower has to pay back every month, consisting of principal and interest repayments, does not change (the last installment might be an exception). The annuity amount has to be derived from three of the input parameters (duration, nominal interest rate, total loan amount) before starting the plan calculation. (use http://financeformulas.net/Annuity_Payment_Formula.html as reference)Note: the nominal interest rate is an annual rate and must be converted to monthly before using in the annuity formula.

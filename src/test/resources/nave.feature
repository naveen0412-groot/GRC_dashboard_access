Feature: Upload Document Flow


#  @ChangeLLPObjStepDefs
#  Scenario: Validate the Change the Objectives of Your LLP service
#    Given the user redirect to LLP Obj service
#    When the user update the LLP Objectives Details
#    When the user update the Object details
#
#  @ChequeBounceNoticeStepDefs
#  Scenario: Validate the Cheque Bounce Notice service flow
#    Given the user redirect to cheque bounce notice service
#    When the user update the Party details
#    When the user update the Notice details


  @EmployeeContractstepdef
  Scenario: Validate the Employee Contract service flow
  Given the user redirect to Employee Contract service
  When the user updates the Details of Employer Your Details in employee
  When the user updates the Details of Employee Your Details in employee
  When the user updates the Employee Details in employee
  When the user updates the Description of business in employee
  When the user updates the Probation in employee


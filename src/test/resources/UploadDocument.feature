Feature: Upload Document Flow

  @LoginStepDefinitions
  Scenario: Validate the helpdesk login flow
    Given the user redirect to helpdesk site
    When the user enter Username
    When the user enter Password
    Then the user clicks SignIn CTA

#  @SearchTicketStepdefs
#  Scenario: Validate to search the ticket
#    Given the user redirect to ticket list page
#    When the user enter the ticket
#    When the user click the search CTA
#    Then the user view the ticket page by clicking on the link


  @AppDirStepDefinitions
  Scenario: Validate the appointment of a director service
    Given the user open upload document link
    When the user update company details
    When the user update director details
    Then the user upload the documents

  @RemoveDirStepDefinitions
  Scenario: Validate the remove of directors service
    Given the user redirect to upload document page of Remove director service
    When the user update company details for Remove director service
    When the user update director details for Remove director service
#   Then the user upload the documents for Remove director service

  @ChangeCompanyObjectivesStepDefinitions
  Scenario: Validate the Change the Objectives of Your Company service
    Given the user redirect to upload document page of Change the Objectives of Your Company service
    When the user update company details for Change Objectives of Your Company service
    Then the user update objective details for Change Objectives of Your Company service

  @ApplyForPatentStepDefinitions
  Scenario: Validate the Apply for a patent service
    Given the user redirect to upload document page of Apply for a patent service
    When the user update the Applicant details and its type
    When the user update the Inventor Details
    When the user update the Type of patent application
    Then the user update the Publication and Examination preference

  @CopyrightStepDefinitions
  Scenario: Validate the Copyright Registration
    Given the user redirect to upload document page of Copyright Registration service
    When the user update the Applicant details
    When the user update the Author details
    When the user update the Copyright work
    Then the user update the Publisher details

  @AddingPartnerStepDefs
  Scenario: Validate the Adding Partner service
    Given the user redirect to upload document page of Adding Partner service
    When the user update the LLP Details
    When the user update the No of Partners being appointed
    When the user upload documents

  @ApplyPANStepDefs
  Scenario: Validate the Apply for PAN Number service
    Given the user redirect to upload document page of Apply for a PAN
    When the user update the Details

  @NameChangeStepDefs
  Scenario: Validate the Apply for Name Change service
    Given the user redirect to Name Changes Service page
    When the user update Name Change details
    When the user update the Witness details
    When the user upload the documents for Name change service

  @NameChangeForMinorStepDefs
  Scenario: Validate the Apply for Name Change For Minor service
    Given the user redirect to Name Changes for Minor Service page
    When the user update Name Change details for Minor
    When the user update the Witness details for Name Change Minor
    When the user upload the documents for Name change Minor

  @ProvisionalPatentStepDefs
  Scenario: Validate the Provisional Patent service
    Given the user redirect to Provisional Patent Service page
    When the user enter Applicant details for Provisional Patent
    When the user enter the No of inventor and its details
    When the user enter the Type of application
    When the user enter the Disclosure of the Invention
    When the user enter the Other Details for Provisional Patent

  @PVTStepDefs
  Scenario: Validate the Private Limited Company Service
    Given the user redirect to Private Limited Company Registration Page
    When the user enter the Company Name details
    When the user enter the Director Details
    When the user enter the Company Details

  @ApplyTANStepDefs
  Scenario: Validate the Apply for a TAN service
    Given the user redirect to Apply for a TAN service
    When the user enter the Details

  @USTrademarkStepDefs
  Scenario: Validate the US Trademark service
    Given the user redirect to US Trademark service flow
    When the user update US Trademark details
    When the user update US Applicant details
    When the user claim details
    When the user update specification of service

  @CriticalMarkStepDefs
  Scenario: validate the Apply for Critical Mark service
    Given the user redirect to Critical Mark service
    When the user update the critical mark applicant details

  @TMAssignorStepDefs
  Scenario: Validate the Assign your Trademark service
    Given the user redirect to Assign your Trademark service
    When the user update the Trademark details
    When the user update the Details of Assignor
    When the user uploads the documents

  @CompanyAuditStepDefs
  Scenario: Validate the Company Audit service
    Given the user redirect to the Company Audit service page
    When the user enter the Company Audit Details

  @GSTCancellationStepDefs
  Scenario: Validate the GST Cancellation service flow
    Given the user redirect to the GST Cancellation service
    When the user update the GST Basic Details
    When the user update the GST Other Details

  @BusinessCommencementStepDefs
  Scenario: Validate the Certificate for Commencement of Business service
    Given the user redirect to Business Commencement service flow
    When the user update the Business Details
    When the user update the Business Other Info

  @ChangeLLPObjStepDefs
  Scenario: Validate the Change the Objectives of Your LLP service
    Given the user redirect to LLP Obj service
    When the user update the LLP Objectives Details
    When the user update the Object details

  @OutsideAddressChangeStepDefs
  Scenario: Validate the Official Address Change of Your Company Outside the City
    Given the user redirect to Outside Address Change service flow
    When the user update the company info details
    When the user update the address details

  @AddressChangeWithinCityStepDefs
  Scenario: Validate the Official Address change of Your Company Within the City
    Given the user redirect to Address change within city service flow
    When the user update the company address within city
    When the user upload the documents for address change

  @AddressChangeWithinStateStepDefs
  Scenario: Validate the Official Address change of Your Company Within the State
    Given the user redirect to LLP Address change within state service flow
    When the user update the LLP details within state
    When the user upload the office details change within state

  @ChangeCompanyNameStepDefs
  Scenario: Validate the Change your company name service flow
    Given the user redirect to change your company name service
    When the user update the company details
    When the user update the new company name details
    When the user update the other information

  @ChequeBounceNoticeStepDefs
  Scenario: Validate the Cheque Bounce Notice service flow
    Given the user redirect to cheque bounce notice service
    When the user update the Party details
    When the user update the Notice details

  @CloseLLPStepDefs
  Scenario: Validate the close your Limited Liability Partnership
    Given the user redirect to close your LLP service
    When the user update the LLP Info Details
    When the user update the LLP Closure Information

  @OPCStepDefs
  Scenario: Validate the One Person Company Registration service
    Given the user redirect to OPC service
    When the user update the OPC Company name
    When the user update the OPC Member details
    When the user update the OPC Company details

  @StateFoodStepDefs
  Scenario: Validate the State Food License service
    Given the user redirect to State Food License Service
#    When the user update the Company State Food Details
    #When the user update the Applicant State Food Details
    #When the user update the Office State Food Document

  @CentralFoodStepDefs
  Scenario: Validate the Central Food License service
    Given the user redirect to Central Food License Service
#    When the user update the Company Central Food Details
    #When the user update the Applicant Central Food Details
    #When the user update the Office Central Food Document
    #When the user update the OPC Company details

  @MSMEservice
  Scenario: Validate the Msme services
    Given the user redirect to Company details page of msme service
    When the user selecting the company type
    When the user updates the bank and address in msme
    When the user updates the second subtab and address in msme
    When  The user adding the director details in msme
    Then The user uploading director documents in msme


  @eFir
  Scenario: Validate the eFir services
    Given the user redirect to eFIR Details page of eFir service
    When the user update the common Details

  @PTregservice
  Scenario: Validate the PT services
    Given the user redirect to Company details page of PT service
   When the user selecting the new company type
    When the user updates the bank and address in PT
    When the user updates the second subtab and address in PT
    When  The user adding the director details in PT
    When The user uploading director documents in PT
    Then The user uploading company documents for PT

  @CloseyourPrivateLimitedCompany
  Scenario: Validate the close your pvt company services
    Given the user redirect to Company details page of Close your pvt company
    When The user enters the details in closure form
    When The user enters the company details
   When the user enter the Director Details in close your pvt
    When the user enter the Second Director Details in close your pvt
    When the user enter the first shareholder in close your pvt
    Then the user enter second shareholder and board date

  @Commercialrentalandlease
  Scenario: Validate the Commercial rental and lease services
    Given the user redirect to Commercial rental and lease services
    When the user update the Details of Parties in commercial rental service
    When the user updates the Term of Lease
    Then the user updating other details in commercial rental service

  @MOUdef
  Scenario: Validate the Memorandum of Understanding (MoU) services
    Given the user redirect to Memorandum of Understanding services
    When The user enters the Basic Details of Parties
    When Updating the background of Mou service
    When Updating the Scope of Mou service
    When Updating the Financial Terms of Mou service
    Then user updates the term and termination

  @IncreaseinAuthorizedCapital
  Scenario: Validate the Authorized capital services
    Given the user redirect to Increase in Auth captial services
    When the user enters the Company Information
    Then the user enters the auth captial information

  @Patentsearch
  Scenario: Validate the Patent search services
    Given The user navigating to the Patent search page
    When the user entering the details in Disclosure of the Invention
    When the user enters the other information
    Then the user uploads the documents for patent search

  @LegalMetrelogy
  Scenario: Validate the Legal Metrology services
    Given The user navigating to the Legal Metrology page
    When the user selecting the entity type for legal metrology
    When the user updates the address and office details for legal metrology
    Then the user uploading docs in the Legal metrology

  @Ipinfringement
  Scenario: Validate the Ip Infringement services
    Given The user navigating to the Ip Infringement page
    When the user entering the Infringer Details
    When the user entering the client and upload

  @Lutsteps
  Scenario: Validate the LUT application services
    Given the user redirect to LUT application service
    When the user enters the entity details in LUT application
    When the user enters the gst login credential
    Then the user adding the witness details in Lut

  @Psarastep
  Scenario: Validate the Psara license services
    Given the user redirect to Psara license service
    When the user entering the company details for Psara service
    When the user entering the Authorized person details
    Then the user uploading docs for psara

  @Provisionalcertificate
  Scenario: Validate the Provisional certificate services
    Given the user redirect to Provisional certificate services
    When the user entering the Details of NGO
    When the user entering the Details of Auth Person
    Then the user uploading docs for Provisional certificate

  @NGOCompliance
  Scenario: Validate the NGO Compliance services
    Given the user redirect to NGO Compliance services
    When the user navigate to ngo registration
    Then the user navigates to upload document page for Ngo compliance page

  @Ngodiscloseagreementstep
  Scenario: Validate the NGO Disclose Agreement
    Given the user redirect to NGO Disclose Agreement
    When the user entering the Party Details in NGO Disclose Agreement
    When the user entering the receiver details and NDA in NGO Disclose Agreement
    When the user enters the confidential destruction
    Then the user enters the Term Termination

  @Section12gstep
  Scenario: Validate the Section 12A and 80G Registration for NGOs
    Given the user redirect to Section 12A and 80G
    When the user entering the Details of NGO in Section 12
    When the user enters the member details in Section 12

  @PowerofAttroneystep
  Scenario: Validate the Power of Attroney services
    Given the user redirect to Power of Attroney
    When the user enters the Details of Principal in Power of Attroney
    When the user enters the Details of Agent in Power of Attroney
    When the user enters the Purpose in Power of Attroney
    Then the user enters the Term in Power of Attroney

  @Propertyregstep
  Scenario: Validate the Property Registration services
    Given the user redirect to Property Registration
    When the user enters the Basic Details of Property Registration
  Then the user enters the Property Address of Property Registration

  @PropertyVerificationstep
  Scenario: Validate the Property Verification (Due Diligence) services
    Given the user redirect to Property Verification
    When the user enters the Basic Details of Property Verification

  @Oppositioninfringestep
  Scenario: Validate the File an Opposition for Brand Infringement services
    Given the user redirect to File an Opposition for Brand Infringement
    When the user enters the Client Application number of File an Opposition for Brand Infringement
    When the user enters the Application no. to be opposed of File an Opposition
    When the user enters the First Date of use of the mark of File an Opposition
    Then the user enters the Any prior disputes with the other party of File an Opposition

  @Firearmstep
  Scenario: Validate the Firearm/Gun license services
    Given the user redirect to Firearm license services
    When the user enters the authorised person Firearm license services
    Then the user upload documents Firearm license services

  @Firelicensestep
  Scenario: Validate the Fire and Factory License services
    Given the user redirect to Fire and Factory License
    When the user enters the Company details of Fire and Factory License
    When the user enters the authorised person details for Fire and Factory License
    Then the user upload documents for Fire and Factory License

  @GiftDeedstep
  Scenario: Validate the Gift Deed services
    Given the user redirect to Gift Deed services
    When the user enters the Basic Details of Gift Deed

  @Incometaxnoticestep
  Scenario: Validate the Income Tax Notice
    Given the user redirect to Income Tax Notice
    When the user update the Bank Details

  @Fundraisetermstep
  Scenario: Validate the Fundraise-Term Sheet drafting
    Given the user redirect to Fundraise-Term Sheet drafting
    When the user update Investement Information the Fundraise-Term Sheet drafting
    When the user update Share details the Fundraise-Term Sheet drafting


  @Genderchangestep
  Scenario: Validate to Gender change service
    Given the user redirect to Gender change service page
    When the user validate the Personal Details in gender change
  When the user entering the Witness details
  Then The user uploading the documents for gender change





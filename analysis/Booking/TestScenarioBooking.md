# Booking - Test Scenarios

This document covers the main test scenarios for the Booking use case.  
The scenarios are presented in a clear and organized way to aid testing and future maintenance.

---

## 1. Successful Booking (Happy Path)

|          |                                                 |
|---------|---------|
| **Test ID** | TC_BOOK_001 |
| **Title** | Sales Employee Successfully Books Trip for Customer |
| **Priority** | High |
| **Pre-conditions** | - User is authenticated (Sales Employee)<br>- Trip is available<br>- Flight seats are available for booking |
  
### Test Data:

| Field | Values |
|---------|---------|
| Customer Name | "john doe" |
| Customer Email | "john.doe@email.com" |
| Flight ID | 1 |
| Seat Number | "3B" |
| Class Type | "Economy" |
| Luggage | false |
| Food | true |
| Payment | true |
  
### Test Steps:

1. Open booking form.
2. Enter customer details.
3. Select seat, class, meal, and luggage.
4. Confirm booking.

### Expected Result:

- Customer name is capitalized to "John Doe"
- Email format validation passes
- Seat format validation (matches `^([1-9]|10)[A-F]$`) and availability pass
- Booking is successfully saved in the database
- Booking ID is generated and displayed in UI

---

## 2. Invalid Email Format

|          |                                                 |
|---------|---------|
| **Test ID** | TC_BOOK_002 |
| **Title** | Booking Fails Due to Invalid Customer Email |
| **Priority** | High |
| **Pre-conditions** | Same as TC_BOOK_001 |
  
### Test Data:

| Field | Values |
|---------|---------|
| Customer Email | "invalid-email-format" |
| Other fields | Same as TC_BOOK_001 |
  
### Test Steps:

1. Open booking form.
2. Enter invalid email.
3. Attempt booking.

### Expected Result:

- Validation fails immediately.
- Exception `InvalidCustomerDataException` is thrown.
- Response: 400 Bad Request with message "Invalid email format"
- Database unaffected.

---

## 3. Invalid Seat Format

|          |                                                 |
|---------|---------|
| **Test ID** | TC_BOOK_003 |
| **Title** | Booking Fails Due to Invalid Seat Number |
| **Priority** | High |
| **Pre-conditions** | Same as TC_BOOK_001 |
  
### Test Data:

| Field | Values |
|---------|---------|
| Seat Number | "15Z" |
| Other fields | Same as TC_BOOK_001 |
  
### Test Steps:

1. Open booking form.
2. Enter invalid seat number.
3. Attempt booking.

### Expected Result:

- Validation fails against seat format `^([1-9]|10)[A-F]$`.
- Exception `InvalidBookingDataException` is thrown.
- Response: 400 Bad Request with message "Invalid seat format"
- Database unaffected.

---

## 4. Seat Already Taken

|          |                                                 |
|---------|---------|
| **Test ID** | TC_BOOK_004 |
| **Title** | Booking Fails Due to Seat Unavailability |
| **Priority** | High |
| **Pre-conditions** | - User is authenticated<br>- Trip is available<br>- Seat "2A" is already taken |
  
### Test Data:

| Field | Values |
|---------|---------|
| Seat Number | "2A" |
| Other fields | Same as TC_BOOK_001 |
  
### Test Steps:

1. Open booking form.
2. Select seat "2A" (unavailable).
3. Attempt booking.

### Expected Result:

- Database finds seat "2A" is already taken.
- Exception `SeatNotAvailableException` is thrown.
- Response: 409 Conflict with message "Seat already taken"

---

## 5. Boundary Value - Maximum Seat Number

|          |                                                 |
|---------|---------|
| **Test ID** | TC_BOOK_005 |
| **Title** | Booking with Maximum Valid Seat Number |
| **Priority** | Medium |
| **Pre-conditions** | Same as TC_BOOK_001 |
  
### Test Data:

| Field | Values |
|---------|---------|
| Seat Number | "10F" |
| Other fields | Same as TC_BOOK_001 |
  
### Expected Result:

- Validation passes (matches `^([1-9]|10)[A-F]$`)
- Booking successfully saved
- Booking confirmation displayed

---

## 6. Name Capitalization

|          |                                                 |
|---------|---------|
| **Test ID** | TC_BOOK_006 |
| **Title** | Customer Name is Properly Capitalized |
| **Priority** | Low |
| **Pre-conditions** | Same as TC_BOOK_001 |
  
### Test Data:

| Field | Values |
|---------|---------|
| Customer Name | "mary jane smith" |
| Other fields | Same as TC_BOOK_001 |
  
### Expected Result:

- Customer name is capitalized to "Mary Jane Smith"
- Booking successfully saves with proper name format

---

## 7. Multiple Validation Failures

|          |                                                 |
|---------|---------|
| **Test ID** | TC_BOOK_007 |
| **Title** | Multiple Validation Failures Handled Properly |
| **Priority** | Medium |
| **Pre-conditions** | Same as TC_BOOK_001 |
  
### Test Data:

| Field | Values |
|---------|---------|
| Customer Email | "bad-email" |
| Seat Number | "99X" |
| Other fields | Same as TC_BOOK_001 |
  
### Expected Result:

- Validation fails immediately upon first invalid field.
- Exception thrown with clear error message.
- Database unaffected.

---


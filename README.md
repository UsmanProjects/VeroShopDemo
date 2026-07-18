# VeroShopDemo
sample of "food dilvery app" with some basic feature and damy backand



"

Demo Android Application for VeroFlow
Purpose

This application is built solely for testing an AI-powered automated mobile testing system.

It should contain realistic workflows and common Android UI components while remaining simple enough to understand and automate.

The app should not require any backend server. All data can be stored locally using mock data.

Application Name

VeroShop Demo

Main Workflow
Splash
↓

Welcome
↓

Register
↓

Login
↓

Home
↓

Categories
↓

Product Details
↓

Cart
↓

Checkout
↓

Order Success
↓

Profile
↓

Settings
↓

Logout
Screen 1 — Splash Screen
Purpose

Display the application logo.

Features
Logo
App name
Loading indicator
Behavior

After 2 seconds automatically navigate to the Welcome screen.

Screen 2 — Welcome Screen
Components
Login button
Register button
Continue as Guest button
Test Actions
Click Login
Click Register
Click Guest
Screen 3 — Register Screen
Fields
First Name
Last Name
Email
Phone Number
Password
Confirm Password
Controls
Register button
Already have an account
Validation
Empty fields
Invalid email
Weak password
Password mismatch
Success

Navigate to Login.

Screen 4 — Login Screen
Fields
Email
Password
Buttons
Login
Forgot Password
Back
Register
Success

Navigate to Home.

Screen 5 — Home Screen
Components
Search bar
Categories
Featured Products
Bottom Navigation
Home
Cart
Orders
Profile
User Actions
Search
Scroll
Open Category
Open Product
Open Cart
Open Profile
Screen 6 — Category Screen

Categories such as:

Pizza
Burger
Drinks
Dessert

Selecting one opens a product list.

Screen 7 — Product List

Each product displays:

Image
Name
Price
Rating

Clicking a product opens Product Details.

Screen 8 — Product Details

Displays:

Product image
Description
Quantity selector (+/-)
Add to Cart button
Favorite button
Test Actions
Increase quantity
Decrease quantity
Add to cart
Screen 9 — Cart

Displays:

Products
Quantity
Total price

Buttons:

Remove Item
Continue Shopping
Checkout
Screen 10 — Checkout

Fields:

Name
Phone
Address
City
ZIP Code

Payment Methods:

Cash
Credit Card
Wallet

Button:

Place Order
Screen 11 — Order Success

Displays:

Success icon
Order ID
Continue Shopping button
Screen 12 — Orders

Shows previous mock orders.

Clicking an order opens Order Details.

Screen 13 — Profile

Fields:

Name
Email
Phone

Buttons:

Edit Profile
Change Password
Settings
Logout
Screen 14 — Edit Profile

Editable fields:

Name
Phone
Address

Button:

Save
Screen 15 — Settings

Components:

Dark Mode switch
Notifications switch
Language dropdown
About
Privacy Policy
Screen 16 — About

Displays:

App Version
Build Number
Developer Information
Screen 17 — Logout Dialog

Buttons:

Yes
No
UI Components Included

This app should include a wide variety of controls so your automation system can practice interacting with them:

Text fields
Password fields
Buttons
Image buttons
Checkboxes
Radio buttons
Switches
Dropdowns (Spinners)
Lists (RecyclerView)
Search bar
Tabs
Bottom navigation
Navigation drawer (optional)
Dialogs
Snackbar messages
Toast messages
Date picker
Time picker
Progress indicator
Loading screen
Scrollable content
Confirmation dialogs
Test Scenarios

The app should support many common test cases, such as:

Valid registration
Invalid registration
Valid login
Invalid login
Search for a product
Open a product
Add items to the cart
Change item quantity
Remove items from the cart
Complete checkout
Edit profile
Toggle dark mode
Change language
View order history
Log out
Why this app is ideal for VeroFlow

This design gives you a controlled environment with predictable behavior while still covering most of the UI patterns your system will encounter in real applications. It exercises navigation, form filling, validation, lists, dialogs, settings, and multi-screen workflows, making it an excellent foundation for developing and validating VeroFlow before moving on to complex production apps."

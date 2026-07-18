# VeroShop Demo 🛍️

A complete demo Android application built for **VeroFlow** automated mobile testing.

## Overview

VeroShop Demo is a self-contained food delivery/shop app with **17 screens** and **zero backend dependency**. All data is stored locally using mock data and SharedPreferences.

## Screens

| # | Screen | Class |
|---|---|---|
| 1 | Splash (2s auto-nav) | `SplashActivity` |
| 2 | Welcome | `WelcomeActivity` |
| 3 | Register (with validation) | `RegisterActivity` |
| 4 | Login | `LoginActivity` |
| 5 | Home (search + categories) | `HomeActivity` + `HomeFragment` |
| 6 | Categories grid | `CategoriesFragment` |
| 7 | Product List | `ProductListActivity` |
| 8 | Product Detail (qty selector) | `ProductDetailActivity` |
| 9 | Cart (swipe-remove + undo) | `CartFragment` |
| 10 | Checkout (form + payment) | `CheckoutActivity` |
| 11 | Order Success | `OrderSuccessActivity` |
| 12 | Orders history | `OrdersFragment` |
| 13 | Profile | `ProfileFragment` |
| 14 | Edit Profile | `EditProfileActivity` |
| 15 | Settings (dark/notif/lang) | `SettingsActivity` |
| 16 | About | `AboutActivity` |
| 17 | Logout Dialog | In `ProfileFragment` |

## UI Components (for VeroFlow automation)

- TextInputLayout / EditText (text & password)
- MaterialButton (primary, outlined, text)
- ImageButton (favorite toggle)
- CheckBox (register terms)
- RadioGroup / RadioButton (payment method)
- SwitchMaterial (dark mode, notifications)
- Spinner (language selector)
- RecyclerView + CardView (products, cart, orders)
- SearchView (home search)
- BottomNavigationView
- AlertDialog (logout confirmation)
- Snackbar (add-to-cart, remove with UNDO)
- Toast (validation, guest mode)
- CircularProgressIndicator (splash)
- SwipeRefreshLayout (orders)

## Building

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or newer
- JDK 8+
- Android SDK 34

### Steps
1. Open Android Studio
2. File → Open → select `VeroShopDemo/` folder
3. Wait for Gradle sync to complete
4. Run → Run 'app' (or press Shift+F10)

### Command Line Build
```bash
cd "f:\SOFTWARE ENGINERING\apk\VeroShopDemo"
gradlew.bat assembleDebug
```
APK output: `app/build/outputs/apk/debug/app-debug.apk`

## Test Credentials

Any valid-format email + 6+ character password works for login.

**Example:**
- Email: `test@veroshop.com`
- Password: `test123`

## Package Name
`com.veroshop.demo`

## Element IDs (for Appium/UIAutomator2)

All interactive elements have descriptive `android:id` and `contentDescription`:

| Element | ID |
|---|---|
| Login button | `btn_login` |
| Email field | `et_login_email` |
| Password field | `et_login_password` |
| Register button | `btn_register` |
| Search bar | `sv_search` |
| Add to cart | `btn_add_to_cart` |
| Increase qty | `btn_increase_qty` |
| Decrease qty | `btn_decrease_qty` |
| Checkout | `btn_checkout` |
| Place order | `btn_place_order` |
| Dark mode switch | `switch_dark_mode` |
| Logout | `btn_logout` |
| Save profile | `btn_save_profile` |

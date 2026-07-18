package com.veroshop.demo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.veroshop.demo.data.MockDataManager;
import com.veroshop.demo.data.SessionManager;

public class CheckoutActivity extends AppCompatActivity {

    private TextInputLayout tilName, tilPhone, tilAddress, tilCity, tilZip;
    private TextInputEditText etName, etPhone, etAddress, etCity, etZip;
    private RadioGroup rgPayment;
    private MockDataManager data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        data = MockDataManager.getInstance();
        SessionManager session = new SessionManager(this);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_checkout);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tilName = findViewById(R.id.til_checkout_name);
        tilPhone = findViewById(R.id.til_checkout_phone);
        tilAddress = findViewById(R.id.til_checkout_address);
        tilCity = findViewById(R.id.til_checkout_city);
        tilZip = findViewById(R.id.til_checkout_zip);
        etName = findViewById(R.id.et_checkout_name);
        etPhone = findViewById(R.id.et_checkout_phone);
        etAddress = findViewById(R.id.et_checkout_address);
        etCity = findViewById(R.id.et_checkout_city);
        etZip = findViewById(R.id.et_checkout_zip);
        rgPayment = findViewById(R.id.rg_payment);

        // Pre-fill from session
        etName.setText(session.getName());
        etPhone.setText(session.getPhone());
        etAddress.setText(session.getAddress());

        ((TextView) findViewById(R.id.tv_checkout_total))
                .setText(String.format("$%.2f", data.getCartTotal()));

        findViewById(R.id.btn_place_order).setOnClickListener(v -> placeOrder());
    }

    private void placeOrder() {
        tilName.setError(null); tilPhone.setError(null);
        tilAddress.setError(null); tilCity.setError(null); tilZip.setError(null);
        boolean valid = true;

        if (TextUtils.isEmpty(etName.getText())) { tilName.setError(getString(R.string.error_empty_field)); valid = false; }
        if (TextUtils.isEmpty(etPhone.getText())) { tilPhone.setError(getString(R.string.error_empty_field)); valid = false; }
        if (TextUtils.isEmpty(etAddress.getText())) { tilAddress.setError(getString(R.string.error_empty_field)); valid = false; }
        if (TextUtils.isEmpty(etCity.getText())) { tilCity.setError(getString(R.string.error_empty_field)); valid = false; }
        if (TextUtils.isEmpty(etZip.getText())) { tilZip.setError(getString(R.string.error_empty_field)); valid = false; }

        if (valid) {
            String payment = "Cash";
            int selectedId = rgPayment.getCheckedRadioButtonId();
            if (selectedId == R.id.rb_credit_card) payment = "Credit Card";
            else if (selectedId == R.id.rb_wallet) payment = "Wallet";

            data.placeOrder(data.getCartItems(), data.getCartTotal(), payment);
            data.clearCart();

            Toast.makeText(this, "Order placed!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, OrderSuccessActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) { onBackPressed(); return true; }
        return super.onOptionsItemSelected(item);
    }
}

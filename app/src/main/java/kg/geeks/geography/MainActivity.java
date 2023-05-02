package kg.geeks.geography;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import kg.geeks.geography.Continent.ContinentFragment;
import kg.geeks.geography.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // if (savedInstanceState != null) {
        navigateFragments();
        //}
    }

    private void navigateFragments() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_cv_main, new ContinentFragment())
                .commit();
    }
}
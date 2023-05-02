package kg.geeks.geography;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import kg.geeks.geography.Countries.Country;
import kg.geeks.geography.databinding.FragmentInfoBinding;

public class InfoFragment extends Fragment {

    private FragmentInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBundleCountry();
    }

    private void getBundleCountry() {
        Bundle bundle = getArguments();
        if (bundle != null){
            Country country = (Country) bundle.getSerializable("country");
            Glide.with(binding.imageViewInfoFlag).load(country.getFlag()).into(binding.imageViewInfoFlag);
            binding.textViewInfoCountry.setText(country.getCountry());
            binding.textViewInfoCapital.setText(country.getCapital());
            binding.textViewInfoLanguage.append(country.getLanguage());
        }
    }

}
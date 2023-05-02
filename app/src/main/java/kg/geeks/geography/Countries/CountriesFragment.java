package kg.geeks.geography.Countries;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kg.geeks.geography.Continent.Continent;
import kg.geeks.geography.InfoFragment;
import kg.geeks.geography.OnItemClick;
import kg.geeks.geography.R;
import kg.geeks.geography.databinding.FragmentContinentBinding;
import kg.geeks.geography.databinding.FragmentCountriesBinding;
import kg.geeks.geography.databinding.ItemCountryBinding;

public class CountriesFragment extends Fragment implements OnItemClick {

    private ArrayList<Country> arrayList;
    private FragmentCountriesBinding binding;
    private String continentName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCountriesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBundleContinent();
        lodListData();
        initRecycler();
    }

    private void getBundleContinent() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Continent continent = (Continent) bundle.getSerializable("continent");
            if (continent != null) {
                continentName = continent.getContinent();
            }
        }
    }

    private void lodListData() {
        arrayList = new ArrayList<>();
        switch (continentName) {
            case "Asia":
                arrayList.add(new Country("Azerbaijan", "Baku", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Flag_of_Azerbaijan.svg/1200px-Flag_of_Azerbaijan.svg.png", "Azerbaijan"));
                arrayList.add(new Country("Armenia", "Erevan", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Flag_of_Armenia_%281918%E2%80%931922%29.svg/200px-Flag_of_Armenia_%281918%E2%80%931922%29.svg.png", "Armenian"));
                arrayList.add(new Country("Afghanistan", "Kabul", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Flag_of_Afghanistan_%282013%E2%80%932021%29.svg/800px-Flag_of_Afghanistan_%282013%E2%80%932021%29.svg.png", "Afghan"));
                arrayList.add(new Country("Bangladesh", "Dakka", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/Flag_of_Bangladesh.svg/800px-Flag_of_Bangladesh.svg.png", "Bengal"));
                arrayList.add(new Country("Bahrain", "Manama", "https://us.123rf.com/450wm/zloyel/zloyel1608/zloyel160800225/61282167-bandiera-del-bahrain-ufficialmente-regno-del-bahrain-%C3%A8-uno-stato-insulare-situato-vicino-alle-rive.jpg?ver=6", "Arabian"));
                arrayList.add(new Country("Brunei", "Bandar-Seri-Begavan", "https://upload.wikimedia.org/wikipedia/commons/9/9c/Flag_of_Brunei.svg", "Malaysian"));
                arrayList.add(new Country("Butan", "Thimphu", "https://upload.wikimedia.org/wikipedia/commons/9/91/Flag_of_Bhutan.svg", "Dzong-Ka"));
                arrayList.add(new Country("", "", "", ""));
                arrayList.add(new Country("", "", "", ""));
                arrayList.add(new Country("", "", "", ""));
                arrayList.add(new Country("", "", "", ""));
                arrayList.add(new Country("", "", "", ""));
                arrayList.add(new Country("", "", "", ""));
                arrayList.add(new Country("", "", "", ""));
                break;

            case "Africa":
                arrayList.add(new Country("Algeria", "Algeria", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/77/Flag_of_Algeria.svg/800px-Flag_of_Algeria.svg.png", "Arabian"));
                arrayList.add(new Country("", "", "", ""));
                break;

            case "Europe":
                arrayList.add(new Country("Austria", "Vena", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/Flag_of_Austria.svg/1200px-Flag_of_Austria.svg.png", "German"));
                arrayList.add(new Country("", "", "", ""));
                break;

            case "North America":
                arrayList.add(new Country("Antigua and Barbuda", "Saint-Jones", "https://upload.wikimedia.org/wikipedia/commons/8/89/Flag_of_Antigua_and_Barbuda.svg", "English"));
                arrayList.add(new Country("", "", "", ""));
                break;

            case "South America":
                arrayList.add(new Country("Argentina", "Buenos-Aires", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Flag_of_Argentina.svg/1200px-Flag_of_Argentina.svg.png", "Spanish"));
                arrayList.add(new Country("", "", "", ""));
                break;

            case "Australia/Oceania":
                arrayList.add(new Country("Australia", "Canberra", "https://upload.wikimedia.org/wikipedia/commons/8/88/Flag_of_Australia_%28converted%29.svg", "English"));
                arrayList.add(new Country("", "", "", ""));
                break;

            case "Antarctica":
                arrayList.add(new Country("", "", "", ""));
                arrayList.add(new Country("", "", "", ""));
                break;
        }
    }

    private void initRecycler() {
        CountryAdapter adapter = new CountryAdapter(arrayList, this);
        binding.recyclerViewCountry.setAdapter(adapter);
    }

    @Override
    public void onClick(int position) {
        Country country = arrayList.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("country", country);

        Fragment fragment = new InfoFragment();
        fragment.setArguments(bundle);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_cv_main, fragment)
                .addToBackStack(null)
                .commit();
    }
}

class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private ArrayList<Country> arrayList;
    private OnItemClick onItemClick;

    public CountryAdapter(ArrayList<Country> arrayList, OnItemClick onItemClick) {
        this.arrayList = arrayList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryViewHolder(ItemCountryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.onBind(arrayList.get(position));
        holder.itemView.setOnClickListener(view ->
                onItemClick.onClick(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {

        private ItemCountryBinding binding;

        public CountryViewHolder(@NonNull ItemCountryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Country country) {
            Glide.with(binding.imageViewCountry).load(country.getFlag()).into(binding.imageViewCountry);
            binding.textViewCountry.setText(country.getCountry());
            binding.textViewCapital.setText(country.getCapital());
        }
    }
}
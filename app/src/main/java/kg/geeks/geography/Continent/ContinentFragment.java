package kg.geeks.geography.Continent;

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

import kg.geeks.geography.Countries.CountriesFragment;
import kg.geeks.geography.OnItemClick;
import kg.geeks.geography.R;
import kg.geeks.geography.databinding.FragmentContinentBinding;
import kg.geeks.geography.databinding.ItemContinentBinding;

public class ContinentFragment extends Fragment implements OnItemClick {

    private FragmentContinentBinding binding;
    private ArrayList<Continent> arrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContinentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadListData();
        initRecycler();
    }

    private void loadListData() {
        arrayList = new ArrayList<>();
        arrayList.add(new Continent("https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Asia_%28orthographic_projection%29.svg/1200px-Asia_%28orthographic_projection%29.svg.png",
                "Asia"));
        arrayList.add(new Continent("https://upload.wikimedia.org/wikipedia/commons/thumb/8/86/Africa_%28orthographic_projection%29.svg/640px-Africa_%28orthographic_projection%29.svg.png",
                "Africa"));
        arrayList.add(new Continent("https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Europe_orthographic_Caucasus_Urals_boundary_%28with_borders%29.svg/1200px-Europe_orthographic_Caucasus_Urals_boundary_%28with_borders%29.svg.png",
                "Europe"));
        arrayList.add(new Continent("https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Location_North_America.svg/1200px-Location_North_America.svg.png",
                "North America"));
        arrayList.add(new Continent("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/South_America_%28orthographic_projection%29.svg/1200px-South_America_%28orthographic_projection%29.svg.png",
                "South America"));
        arrayList.add(new Continent("https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Australia_with_AAT_%28orthographic_projection%29.svg/250px-Australia_with_AAT_%28orthographic_projection%29.svg.png",
                "Australia/Oceania"));
        arrayList.add(new Continent("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Antarctica_%28orthographic_projection%29.svg/1200px-Antarctica_%28orthographic_projection%29.svg.png",
                "Antarctica"));
    }

    private void initRecycler() {
        ContinentAdapter adapter = new ContinentAdapter(arrayList, this);
        binding.recyclerViewContinent.setAdapter(adapter);
    }

    @Override
    public void onClick(int position) {
        Continent continent = arrayList.get(position);
        System.out.println("Continent "+ continent.getContinent());
        Bundle bundle = new Bundle();
        bundle.putSerializable("continent",continent);

        Fragment fragment = new CountriesFragment();
        fragment.setArguments(bundle);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_cv_main, fragment)
                .addToBackStack(null)
                .commit();
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//ADAPTER
class ContinentAdapter extends RecyclerView.Adapter<ContinentAdapter.ContinentViewHolder> {

    private ArrayList<Continent> arrayList;
    private OnItemClick onItemClick;

    public ContinentAdapter(ArrayList<Continent> arrayList, OnItemClick onItemClick) {
        this.arrayList = arrayList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ContinentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContinentViewHolder(ItemContinentBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContinentViewHolder holder, int position) {
        holder.onBind(arrayList.get(position));
        holder.itemView.setOnClickListener(view -> {
            onItemClick.onClick(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    //VIEW HOLDER
    public class ContinentViewHolder extends RecyclerView.ViewHolder {

        private ItemContinentBinding binding;

        public ContinentViewHolder(@NonNull ItemContinentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Continent continent) {
            Glide.with(binding.imageViewContinent).load(continent.getImage()).into(binding.imageViewContinent);
            binding.tvTitleContinent.setText(continent.getContinent());
        }
    }
}
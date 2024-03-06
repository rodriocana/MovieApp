package com.example.movieapp.Activities.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieapp.Activities.Adaptadores.CategoriaAdaptador;
import com.example.movieapp.Activities.Adaptadores.ListaPeliculasAdaptador;
import com.example.movieapp.Activities.Adaptadores.SliderAdaptador;
import com.example.movieapp.Activities.Domain.ListaPeliculas;
import com.example.movieapp.Activities.Domain.SliderItems;
import com.example.movieapp.Activities.Domain.itemGeneros;
import com.example.movieapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private Handler slideHandler = new Handler();
    private final int NUM_PAGES = 3;
    private int currentPage = 0;
    private Runnable sliderRunnable;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest,mStringRequest2,mStringRequest3;

    private ProgressBar loading1,loading2,loading3;

    RecyclerView.Adapter adaptadorMejoresPeliculas,adaptadorEstrenos,adaptadorCategorias;
    private RecyclerView RecyclerViewMejoresPeliculas, RecyclerViewEstrenos, RecyclerViewCategorias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        initView();
        Banners();
        //SendRequestMejoresPeliculas();
        SendRequestProximosEstrenos();
        SendRequestProximosCategorias();
        SendRequestMejoresPeliculas2();


    }

    public void Banners(){

        List<SliderItems> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItems(R.drawable.napoleon2));
        sliderItems.add(new SliderItems(R.drawable.society));
        sliderItems.add(new SliderItems(R.drawable.dune2));

        viewPager2.setAdapter(new SliderAdaptador(sliderItems, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipChildren(false);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        // Configurar una animación personalizada para el desplazamiento del ViewPager2
        viewPager2.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float alpha = 1 - Math.abs(position); // Cambiar la opacidad en función de la posición
                page.setAlpha(alpha);
                page.setScaleY(0.85f + (1 - Math.abs(position)) * 0.15f); // Cambiar la escala en función de la posición
            }
        });

        // Lógica para el desplazamiento automático
        sliderRunnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == NUM_PAGES - 1) {
                    currentPage = 0;
                } else {
                    currentPage++;
                }
                viewPager2.setCurrentItem(currentPage, true);
            }
        };

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    slideHandler.removeCallbacks(sliderRunnable);
                } else if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    slideHandler.postDelayed(sliderRunnable, 6000);
                }
            }
        });


    }

    public void SendRequestMejoresPeliculas(){

        mRequestQueue = Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // aqui es donde pido los datos a la api y los datos descargados se guardan en la lista pelicula y se muestran en el recycle view
                Gson gson = new Gson();
                loading1.setVisibility(View.GONE);
                ListaPeliculas items = gson.fromJson(response, ListaPeliculas.class);
                adaptadorMejoresPeliculas = new ListaPeliculasAdaptador(items);
                RecyclerViewMejoresPeliculas.setAdapter(adaptadorMejoresPeliculas);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loading1.setVisibility(View.GONE);
                Log.i("Fallos", "OnError response " + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest); // aqui finalizo el pedido de datos añadiendolo a mRequestQueue

    }





    public void SendRequestProximosCategorias(){

        mRequestQueue = Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        mStringRequest2 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/genres", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // aqui es donde pido los datos a la api y los datos descargados se guardan en la lista pelicula y se muestran en el recycle view
                Gson gson = new Gson();
                loading2.setVisibility(View.GONE);
                ArrayList<itemGeneros> listaCategoria = gson.fromJson(response, new TypeToken<ArrayList<itemGeneros>>(){}.getType());

                adaptadorCategorias = new CategoriaAdaptador(listaCategoria);
                RecyclerViewCategorias.setAdapter(adaptadorCategorias);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loading2.setVisibility(View.GONE);
                Log.i("Fallos", "OnError response " + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest2);  // aqui finalizo el pedido de datos añadiendolo a mRequestQueue

    }

    public void SendRequestProximosEstrenos(){

        mRequestQueue = Volley.newRequestQueue(this);
        loading3.setVisibility(View.VISIBLE);
        mStringRequest3 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=3", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // aqui es donde pido los datos a la api y los datos descargados se guardan en la lista pelicula y se muestran en el recycle view
                Gson gson = new Gson();
                loading3.setVisibility(View.GONE);
                ListaPeliculas items = gson.fromJson(response, ListaPeliculas.class);
                adaptadorEstrenos = new ListaPeliculasAdaptador(items);
                RecyclerViewEstrenos.setAdapter(adaptadorEstrenos);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loading3.setVisibility(View.GONE);
                Log.i("Fallos", "OnError response " + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest3);  // aqui finalizo el pedido de datos añadiendolo a mRequestQueue

    }



    public void SendRequestMejoresPeliculas2() {
        mRequestQueue = Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);

        List<ListaPeliculas> peliculasList = new ArrayList<>(); // Lista para almacenar todas las películas

        // Definir las URLs de las solicitudes
        String[] urls = {"https://moviesapi.ir/api/v1/movies?page=1",
                "https://moviesapi.ir/api/v1/movies?page=2",
                "https://moviesapi.ir/api/v1/movies?page=3"};

        for (String url : urls) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    loading1.setVisibility(View.GONE);
                    ListaPeliculas items = gson.fromJson(response, ListaPeliculas.class);
                    peliculasList.add(items);

                    // Verificar si todas las solicitudes se han completado
                    if (peliculasList.size() == urls.length) {
                        // Todas las solicitudes se han completado, mostrar las películas
                        showMovies(peliculasList);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    loading1.setVisibility(View.GONE);
                    Log.i("Fallos", "OnError response " + error.toString());
                }
            });

            mRequestQueue.add(stringRequest); // Agregar la solicitud a la cola de solicitudes
        }
    }

    private void showMovies(List<ListaPeliculas> peliculasList) {
        // Combinar todas las listas de películas en una sola lista
        List<com.example.movieapp.Domain.Datum> combinedMovies = new ArrayList<>();

        // Agregar las películas de cada página al principio de la lista combinada
        for (int i = peliculasList.size() - 1; i >= 0; i--) {
            combinedMovies.addAll(0, peliculasList.get(i).getData());
        }

        // Crear una instancia de ListaPeliculas y establecer la lista combinada de películas
        ListaPeliculas combinedListaPeliculas = new ListaPeliculas();
        combinedListaPeliculas.setData(combinedMovies);

        // Crear un adaptador con la lista combinada de películas
        adaptadorMejoresPeliculas = new ListaPeliculasAdaptador(combinedListaPeliculas);
        RecyclerViewMejoresPeliculas.setAdapter(adaptadorMejoresPeliculas);
    }


    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable, 6000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    private void initView(){

        viewPager2 = findViewById(R.id.viewPagerSlider);

        RecyclerViewMejoresPeliculas = findViewById(R.id.recyclerViewMejoresPeliculasId);
        RecyclerViewMejoresPeliculas.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RecyclerViewEstrenos = findViewById(R.id.recycleviewProxEstrenos);
        RecyclerViewEstrenos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        RecyclerViewCategorias = findViewById(R.id.recyclerViewCategorias);
        RecyclerViewCategorias.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        loading1 = findViewById(R.id.progressBar1);
        loading2 = findViewById(R.id.progressBar2);
        loading3 = findViewById(R.id.progressBar3);

    }
}
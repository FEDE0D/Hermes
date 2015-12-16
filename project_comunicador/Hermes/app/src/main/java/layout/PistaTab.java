package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.grupo03.hermes.adaptors.GridAdaptor;
import com.grupo03.hermes.Pictograma;
import com.grupo03.hermes.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PistaTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PistaTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PistaTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PistaTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PistaTab.
     */
    // TODO: Rename and change types and number of parameters
    public static PistaTab newInstance(String param1, String param2) {
        PistaTab fragment = new PistaTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_pista_tab, container, false);

        ArrayList<Pictograma> pictogramas = new ArrayList<Pictograma>();
        pictogramas.add(new Pictograma("Aro", "pista", "aro.png", "Aro.m4a"));
        pictogramas.add(new Pictograma("Broches", "pista", "broches.png", "broches.m4a"));
        pictogramas.add(new Pictograma("Burbujas", "pista", "burbujas.png", "Burbujas.m4a"));
        pictogramas.add(new Pictograma("Caballo", "pista", "caballo_1.png", "Caballo.m4a"));

        pictogramas.add(new Pictograma("Casco", "pista", "casco.png", "Casco.m4a"));
        pictogramas.add(new Pictograma("Chapas", "pista", "chapas.png", "Chapas.m4a"));
        pictogramas.add(new Pictograma("Cubos", "pista", "cubos.png", "Cubos.m4a"));
        pictogramas.add(new Pictograma("Letras", "pista", "letras.png", "Letras.m4a"));
        pictogramas.add(new Pictograma("Maracas", "pista", "maracas.png", "Maracas.m4a"));

        pictogramas.add(new Pictograma("Palos", "pista", "palos.png", "Palos.m4a"));
        pictogramas.add(new Pictograma("Pato", "pista", "pato.png", "Pato.m4a"));
        pictogramas.add(new Pictograma("Pelota", "pista", "pelota.png", "Pelota.m4a"));
        pictogramas.add(new Pictograma("Riendas", "pista", "riendas.png", "Riendas.m4a"));
        pictogramas.add(new Pictograma("Tarima", "pista", "tarima.png", "Tarima.m4a"));


        GridAdaptor adaptor = new GridAdaptor(pictogramas);
        GridView grid = (GridView) rootView.findViewById(R.id.gridView);
        grid.setAdapter(adaptor);


        // Inflate the layout for this fragment
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

package com.example.kisileruygulamasi.data.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.kisileruygulamasi.data.entity.Kisiler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class KisilerDaoRepository {
    private MutableLiveData<List<Kisiler>> kisilerListesi;
    private DatabaseReference refKisiler;

    public KisilerDaoRepository(DatabaseReference refKisiler) {
        this.refKisiler = refKisiler;
        kisilerListesi = new MutableLiveData();
    }

    public MutableLiveData<List<Kisiler>> kisileriGetir(){
        return kisilerListesi;
    }

    public void kisiKayit(String kisi_ad, String kisi_tel){
        Kisiler yeniKisi = new Kisiler("",kisi_ad,kisi_tel);
        refKisiler.push().setValue(yeniKisi);
    }

    public void kisiGuncelle(String kisi_id, String kisi_ad, String kisi_tel){
        HashMap<String,Object> bilgiler = new HashMap<>();
        bilgiler.put("kisi_ad",kisi_ad);
        bilgiler.put("kisi_tel",kisi_tel);
        refKisiler.child(kisi_id).updateChildren(bilgiler);
    }

    public void kisiAra(String aramaKelimesi){
        refKisiler.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Kisiler> liste = new ArrayList<>();

                for (DataSnapshot d:snapshot.getChildren()) {
                    Kisiler kisi = d.getValue(Kisiler.class);
                    if(kisi != null){
                        if(kisi.getKisi_ad().toLowerCase().contains(aramaKelimesi.toLowerCase())){
                            kisi.setKisi_id(d.getKey());
                            liste.add(kisi);
                        }
                    }
                }
                kisilerListesi.setValue(liste);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    public void kisiSil(String kisi_id){
        refKisiler.child(kisi_id).removeValue();
    }

    public void tumKisileriAl(){
        refKisiler.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Kisiler> liste = new ArrayList<>();

                for (DataSnapshot d:snapshot.getChildren()) {
                    Kisiler kisi = d.getValue(Kisiler.class);
                    if(kisi != null){
                        kisi.setKisi_id(d.getKey());
                        liste.add(kisi);
                    }
                }

                kisilerListesi.setValue(liste);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}

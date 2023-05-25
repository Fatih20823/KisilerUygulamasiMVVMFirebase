package com.example.kisileruygulamasi.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.kisileruygulamasi.data.repo.KisilerDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class KisiKayitViewModel extends ViewModel {
    private KisilerDaoRepository krepo;

    @Inject
    public KisiKayitViewModel(KisilerDaoRepository krepo){
        this.krepo = krepo;
    }

    public void kayit(String kisi_ad, String kisi_tel){
        krepo.kisiKayit(kisi_ad,kisi_tel);
    }
}

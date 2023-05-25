package com.example.kisileruygulamasi.di;

import com.example.kisileruygulamasi.data.repo.KisilerDaoRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public KisilerDaoRepository provideKisilerDaoRepository(DatabaseReference refKisiler){
        return new KisilerDaoRepository(refKisiler);
    }

    @Provides
    @Singleton
    public DatabaseReference provideDatabaseReference(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        return db.getReference("kisiler");
    }
}

package com.ibrahimethemsen.firebasenotification.di


import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.InstallIn
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    @Singleton
    fun provideFirebaseFireStore() : FirebaseFirestore = FirebaseFirestore.getInstance()
}
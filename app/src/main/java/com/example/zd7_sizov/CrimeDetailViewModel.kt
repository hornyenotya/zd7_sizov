package com.example.zd7_sizov

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.UUID

class CrimeDetailViewModel: ViewModel() {
    private val crimeRepository = CrimeRepository.get()
    private val crimeIdLiveData = MutableLiveData<UUID>()
    var crimeLiveData: LiveData<Crime?> = Transformations.switchMap(crimeIdLiveData) { crimeID ->
        crimeRepository.getCrime(crimeID)
    }
    fun loadCrime(crimeId: UUID) {
        crimeIdLiveData.value = crimeId
    }
    fun saveCrime(crime: Crime){
        crimeRepository.updateCrime(crime)
    }
}
package com.infobeans.mydemomvvm.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infobeans.mydemomvvm.R
import com.infobeans.mydemomvvm.repository.MainRepository
import com.infobeans.mydemomvvm.di.BaseApp
import com.infobeans.mydemomvvm.util.ApiState
import com.infobeans.mydemomvvm.util.checkForInternet
import com.infobeans.mydemomvvm.util.showMessage
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import kotlin.text.Typography.dagger

/*@HiltViewModel*/
class MainViewModel
constructor(private val mainRepository: MainRepository) : ViewModel() {

    /*private val postStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val _postStateFlow: StateFlow<ApiState> = postStateFlow*/


    private val _postList = MutableLiveData<ApiState>()
    val postList: LiveData<ApiState> = _postList


    private fun getPost() = viewModelScope.launch {
        /*postStateFlow.value = ApiState.Loading*/
        if (checkForInternet(BaseApp.instance)) {
            _postList.value = ApiState.Loading
            mainRepository.getPost()
                .catch { e ->
                    /*postStateFlow.value = ApiState.Failure(e)*/
                    _postList.value = ApiState.Failure(e)
                }.collect { data ->
                    /*postStateFlow.value = ApiState.Success(data)*/
                    _postList.value = ApiState.Success(data)
                    /* _post.value=_postList.value*/
                }
        } else
            BaseApp.instance.showMessage(BaseApp.instance.getString(R.string.no_internet))



    }


    fun apiPost() {
        getPost()
    }


    /*fun isLoading(): Int {
        Log.d(
            "Click Loading",
            if (_postStateFlow.value == ApiState.Loading) "loading" else "not loading"
        )
        return if (_postStateFlow.value.equals(ApiState.Loading)) View.VISIBLE else View.GONE
    }*/

   /* @BindingAdapter("android:animatedVisibility")
    fun setAnimatedVisibility(target: View, isVisible: Boolean) {
        target.visibility = if (isVisible) View.VISIBLE else View.GONE
    }*/

}
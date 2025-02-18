package com.rsetiapp.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsetiapp.common.model.response.StateDataResponse
import com.rsetiapp.common.model.response.BlockResponse
import com.rsetiapp.common.model.response.DistrictResponse
import com.rsetiapp.common.model.response.VillageResponse
import com.rsetiapp.common.model.response.grampanchayatResponse
import com.rsetiapp.BuildConfig
import com.rsetiapp.common.model.request.EAPInsertRequest
import com.rsetiapp.common.model.request.LoginReq
import com.rsetiapp.common.model.response.EAPInsertResponse
import com.rsetiapp.common.model.response.EapAutoFetchRes
import com.rsetiapp.common.model.response.FormResponse
import com.rsetiapp.common.model.response.LoginRes
import com.rsetiapp.common.model.response.ProgramResponse
import com.rsetiapp.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CommonViewModel @Inject constructor(private val commonRepository: CommonRepository) :
    ViewModel() {


    private  var _getLoginAPI =  MutableStateFlow<Resource<out LoginRes>>(Resource.Loading())
    val getLoginAPI = _getLoginAPI.asStateFlow()


    fun getLoginAPI(loginReq: LoginReq) {
        viewModelScope.launch {
            commonRepository.getLoginAPI(loginReq).collectLatest {
                _getLoginAPI.emit(it)
            }
        }


    }

    private var _getFormAPI = MutableStateFlow<Resource<out FormResponse>>(Resource.Loading())
    val getFormAPI = _getFormAPI.asStateFlow()



    fun getFormAPI(appVersion:String , login:String){
        viewModelScope.launch {
            commonRepository.getFormAPI(appVersion,login).collectLatest {
                _getFormAPI.emit(it)
            }
        }


    }

    private var _stateList =  MutableStateFlow<Resource<out StateDataResponse>>(Resource.Loading())
    val getStateList = _stateList.asStateFlow()


    fun getStateListApi(){
        viewModelScope.launch {
            commonRepository.getStateListApi(BuildConfig.VERSION_NAME).collectLatest {
                _stateList.emit(it)
            }
        }
    }

    private var _districtList =  MutableStateFlow<Resource<out DistrictResponse>>(Resource.Loading())
    val getDistrictList = _districtList.asStateFlow()


    fun getDistrictListApi(state :String){
        viewModelScope.launch {
            commonRepository.getDistrictListApi(state,BuildConfig.VERSION_NAME).collectLatest {
                _districtList.emit(it)
            }
        }
    }


    private var _blockList =  MutableStateFlow<Resource<out BlockResponse>>(Resource.Loading())
    val getBlockList = _blockList.asStateFlow()


    fun getBlockListApi(district :String){
        viewModelScope.launch {
            commonRepository.getBlockListApi(district,BuildConfig.VERSION_NAME).collectLatest {
                _blockList.emit(it)
            }
        }}
    private  var _gpList =  MutableStateFlow<Resource<out grampanchayatResponse>>(Resource.Loading())
    val getGpList = _gpList.asStateFlow()


    fun getGpListApi(block :String) {
        viewModelScope.launch {
            commonRepository.getGPListApi(block, BuildConfig.VERSION_NAME).collectLatest {
                _gpList.emit(it)
            }
        }


    }

    private  var _villageList =  MutableStateFlow<Resource<out VillageResponse>>(Resource.Loading())
    val getVillageList = _villageList.asStateFlow()


    fun getVillageListApi(gp :String) {
        viewModelScope.launch {
            commonRepository.getVillageListApi(gp, BuildConfig.VERSION_NAME).collectLatest {
                _villageList.emit(it)
            }
        }


    }

    private  var _getProgramListAPI =  MutableStateFlow<Resource<out ProgramResponse>>(Resource.Loading())
    val getProgramListAPI = _getProgramListAPI.asStateFlow()


    fun getProgramListAPI() {
        viewModelScope.launch {
            commonRepository.getProgramListAPI( BuildConfig.VERSION_NAME).collectLatest {
                _getProgramListAPI.emit(it)
            }
        }


    }


    private  var _getEapAutoFetchListAPI =  MutableStateFlow<Resource<out EapAutoFetchRes>>(Resource.Loading())
    val getEapAutoFetchListAPI = _getEapAutoFetchListAPI.asStateFlow()


    fun getEapAutoFetchListAPI(login :String, appVersion: String) {
        viewModelScope.launch {
            commonRepository.getEapAutoFetchListAPI( login,  appVersion).collectLatest {
                _getEapAutoFetchListAPI.emit(it)
            }
        }


    }

    private  var _insertEAPAPI =  MutableStateFlow<Resource<out EAPInsertResponse>>(Resource.Loading())
    val insertEAPAPI = _insertEAPAPI.asStateFlow()


    fun insertEAPAPI(eapInsertRequest: EAPInsertRequest) {
        viewModelScope.launch {
            commonRepository.insertEAPAPI(eapInsertRequest).collectLatest {
                _insertEAPAPI.emit(it)
            }
        }


    }


}
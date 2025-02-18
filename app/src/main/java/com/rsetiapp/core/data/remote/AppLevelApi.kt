package com.rsetiapp.core.data.remote

import com.rsetiapp.common.model.response.StateDataResponse
import com.rsetiapp.common.model.request.StateListReq
import com.rsetiapp.common.model.request.BlockReq
import com.rsetiapp.common.model.request.DistrictReq
import com.rsetiapp.common.model.request.EAPInsertRequest
import com.rsetiapp.common.model.request.EapAutofetchReq
import com.rsetiapp.common.model.request.GramPanchayatReq
import com.rsetiapp.common.model.request.VillageReq
import com.rsetiapp.common.model.response.BlockResponse
import com.rsetiapp.common.model.response.DistrictResponse
import com.rsetiapp.common.model.response.VillageResponse
import com.rsetiapp.common.model.response.grampanchayatResponse
import com.rsetiapp.common.model.request.FormRequest
import com.rsetiapp.common.model.request.LoginReq
import com.rsetiapp.common.model.response.EAPInsertResponse
import com.rsetiapp.common.model.response.EapAutoFetchRes
import com.rsetiapp.common.model.response.FormResponse
import com.rsetiapp.common.model.response.LoginRes
import com.rsetiapp.common.model.response.ProgramResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AppLevelApi {


    @POST("login")
    suspend fun getLoginAPI(@Body loginReq: LoginReq): LoginRes

    @POST("forms")
    suspend fun getFormAPI(@Body formRequest: FormRequest): FormResponse


    @POST("stateList")
    suspend fun getStateListAPI(@Body stateListReq: StateListReq): StateDataResponse

    @POST("districtList")
    suspend fun getDistrictListAPI(@Body districtReq: DistrictReq): DistrictResponse

    @POST("blockList")
    suspend fun getBlockListAPI(@Body blockReq: BlockReq): BlockResponse


    @POST("gramPanchayatList")
    suspend fun getGpListAPI(@Body gramPanchayatReq: GramPanchayatReq): grampanchayatResponse


    @POST("villageList")
    suspend fun getVillageListAPI(@Body villageReq: VillageReq): VillageResponse


    @POST("program")
    suspend fun getProgramListAPI(@Body stateListReq: StateListReq): ProgramResponse



    @POST("eapautofetch")
    suspend fun getEapAutoFetchListAPI(@Body eapAutofetchReq: EapAutofetchReq): EapAutoFetchRes

    @POST("insertEap")
    suspend fun insertEAPAPI(@Body eapInsertRequest: EAPInsertRequest): EAPInsertResponse

}
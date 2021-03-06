package com.almas.batuapps.menu.listplace.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ListplaceModel(@SerializedName("nama") @Expose val name:String?,
                          @SerializedName("lokasi") @Expose val location:String?,
                          @SerializedName("kategori") @Expose val category:String?,
                          @SerializedName("deskripsi") @Expose val description:String?,
                          @SerializedName("thumbnail") @Expose val thumbnail:String?,
                          @SerializedName("gambar") @Expose val image:String?) : Parcelable


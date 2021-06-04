package com.indytek.pufsmanagement.objects;

import com.google.gson.annotations.SerializedName;

public enum MetodoDePago{
    @SerializedName(("VISA"))
    VISA,
    @SerializedName("EFECTIVO")
    EFECTIVO//, PAYPAL, BTC

}

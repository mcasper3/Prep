package io.github.mcasper3.prep.recipeviewer.part

import android.os.Parcel
import android.os.Parcelable

class RecipePart : Parcelable {

    constructor() {

    }

    // region Parcelable Implementation
    internal constructor(parcel: Parcel) {
    }

    override fun writeToParcel(out: Parcel, flags: Int) {

    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<RecipePart> {
        override fun createFromParcel(parcel: Parcel): RecipePart = RecipePart(parcel)

        override fun newArray(size: Int): Array<RecipePart?> = arrayOfNulls(size)
    }
    // endregion
}

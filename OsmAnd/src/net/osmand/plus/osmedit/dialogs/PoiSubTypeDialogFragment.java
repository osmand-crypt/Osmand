package net.osmand.plus.osmedit.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import net.osmand.data.Amenity;
import net.osmand.osm.MapPoiTypes;
import net.osmand.osm.PoiType;
import net.osmand.plus.OsmandApplication;
import net.osmand.plus.osmedit.EditPoiFragment;

import java.util.Map;

public class PoiSubTypeDialogFragment extends DialogFragment {
	private static final String KEY_AMENITY = "amenity";

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		MapPoiTypes poiTypes = ((OsmandApplication) getActivity().getApplication()).getPoiTypes();
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		final Amenity a = (Amenity) getArguments().getSerializable(KEY_AMENITY);
		final Map<String, PoiType> allTranslatedNames = poiTypes.getAllTranslatedNames(a.getType(), true);
		// (=^.^=)
		final String[] subCats = allTranslatedNames.keySet().toArray(new String[0]);
		builder.setItems(subCats, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				((EditPoiFragment) getParentFragment()).setSubCategory(subCats[which]);
				dismiss();
			}
		});
		return builder.create();
	}

	public static PoiSubTypeDialogFragment createInstance(Amenity amenity) {
		PoiSubTypeDialogFragment fragment = new PoiSubTypeDialogFragment();
		Bundle args = new Bundle();
		args.putSerializable(KEY_AMENITY, amenity);
		fragment.setArguments(args);
		return fragment;
	}
}
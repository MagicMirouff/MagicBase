package com.magicmirouf.magicbase.utils.pickers;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;

import com.onegravity.contactpicker.contact.ContactDescription;
import com.onegravity.contactpicker.contact.ContactSortOrder;
import com.onegravity.contactpicker.core.ContactPickerActivity;
import com.onegravity.contactpicker.picture.ContactPictureType;

/**
 * Created by MagicMirouf on 26/12/2016.
 */

public class ContactPicker {

    public static int REQUEST_CONTACT = 3007;

    public static void searchContacts(Activity activity){
        Intent intent = new Intent(activity, ContactPickerActivity.class)
                .putExtra(ContactPickerActivity.EXTRA_CONTACT_BADGE_TYPE, ContactPictureType.ROUND.name())
                .putExtra(ContactPickerActivity.EXTRA_SHOW_CHECK_ALL, true)
                .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION, ContactDescription.ADDRESS.name())
                .putExtra(ContactPickerActivity.EXTRA_CONTACT_DESCRIPTION_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                .putExtra(ContactPickerActivity.EXTRA_CONTACT_SORT_ORDER, ContactSortOrder.AUTOMATIC.name())
                .putExtra(ContactPickerActivity.EXTRA_SELECT_CONTACTS_LIMIT , 0)
                .putExtra(ContactPickerActivity.EXTRA_ONLY_CONTACTS_WITH_PHONE , true);
        activity.startActivityForResult(intent, REQUEST_CONTACT);
    }

}

package fr.unice.mbds.maslow.view.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.androidquery.AQuery;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.unice.mbds.maslow.R;
import fr.unice.mbds.maslow.entities.Evenement;

/**
 * Created by Gael on 27/03/2016.
 */
public class DialogAddEvenementAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<DialogAddAliasAdapter> aliasAdapters;
    private EditText txtIdOrchestra;

    private LayoutInflater layoutInflater;

    private List<Evenement> evenements;

    private Map<ViewGroup, List<ViewGroup>> itemsCollection;
    private List<ViewGroup> items;

    public DialogAddEvenementAdapter(Context context) {
        this.context = context;

        items = new ArrayList<>();
        itemsCollection = new LinkedHashMap<>();

        layoutInflater = LayoutInflater.from(context);
    }

    public void addChild(ViewGroup parent) {
        if (itemsCollection.get(parent) == null) {
            itemsCollection.put(parent, new ArrayList<ViewGroup>());
        }

        itemsCollection.get(parent).add((ViewGroup) layoutInflater.inflate(R.layout.dialog_add_alias_expandable_list_item_child, parent, false));
    }

    public void addParent() {

        ViewGroup parent = (ViewGroup) View.inflate(context, R.layout.dialog_add_evenement_expandable_list_item_parent, null);

        items.add(parent);
        itemsCollection.put(parent, new ArrayList<ViewGroup>());
    }

    public Map<ViewGroup, List<ViewGroup>> getData() {
        return itemsCollection;
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemsCollection.get(items.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return itemsCollection.get(items.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return Long.parseLong("" + groupPosition + childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = items.get(groupPosition);
        }

        AQuery aq = new AQuery(context);
        final Button btnIdOrchestra = aq.id(convertView.findViewById(R.id.btn_dialog_add_evenement_new_id_orchestra)).getButton();

        btnIdOrchestra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * from http://stackoverflow.com/questions/10903754/input-text-dialog-android
                 */
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Saisir l'ID");

                // Set up the input
                final EditText input = new EditText(context);

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                final String[] idOrchestra = {null};

                // Set up the buttons
                builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        idOrchestra[0] = input.getText().toString();
                        btnIdOrchestra.setText(idOrchestra[0]);
                    }
                });

                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        final Button btnDelEvenement = aq.id(convertView.findViewById(R.id.btn_dialog_add_evenement_del_evenement)).getButton();

        final ViewGroup finalConvertView = (ViewGroup) convertView;
        btnDelEvenement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsCollection.remove(finalConvertView);
                items.remove(finalConvertView);
                notifyDataSetChanged();
            }
        });

        final Button btnAddAlias = aq.id(convertView.findViewById(R.id.btn_dialog_add_evenement_add_alias)).getButton();

        btnAddAlias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChild(items.get(groupPosition));
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = itemsCollection.get(items.get(groupPosition)).get(childPosition);
        }

        AQuery aq = new AQuery(context);

        Button btnDelAlias = aq.id(convertView.findViewById(R.id.btn_dialog_add_evenement_del_alias)).getButton();

        btnDelAlias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsCollection.get(items.get(groupPosition)).remove(childPosition);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

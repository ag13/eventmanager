package com.app;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.app.db.EventsDataSource;

public class EventManager extends ListActivity implements OnClickListener{

	ListView listView;
	private EventsDataSource eventsDataSource;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		eventsDataSource = new EventsDataSource(this);
		eventsDataSource.open();

		List<String> allEvents = eventsDataSource.getAllEvents();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, allEvents);
		setListAdapter(adapter);
		
		Button button = (Button) findViewById(R.id.addNewEventBtn);

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                System.out.println("In click");
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) getListAdapter();
                EditText newEventTxt = (EditText) findViewById(R.id.newEventTxt);
                System.out.println(newEventTxt.getText().toString());
    			Event newEvent = eventsDataSource.createEvent(newEventTxt.getText().toString());
    			adapter.add(newEvent.getName());
    			adapter.notifyDataSetChanged();
            }
        });
        
        ListView eventsListView = (ListView) findViewById(android.R.layout.simple_list_item_1);
        eventsListView.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view, int position,
        			long id) {
        		Intent eventCategory = new Intent(view.getContext(), EventCategory.class);
        		startActivityForResult(eventCategory, 0);
        	}
		});
	}

	@Override
	protected void onResume() {
		eventsDataSource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		eventsDataSource.close();
		super.onPause();
	}

	@Override
	public void onClick(View view) {
		
		switch(view.getId()){
		case R.id.addNewEventBtn:
			
		}
		
	}
}
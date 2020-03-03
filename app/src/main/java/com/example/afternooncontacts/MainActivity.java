package com.example.afternooncontacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ChoiceAdapter choiceAdapter = null;
    private RecyclerView contactsRV= null;
    private GestureDetectorCompat detector = null;
    EditText addedText;
    Button add,remove;
    private ChoiceModel model;


    //gesture listener
    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        //ontap to list_item remove the item from the list
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = contactsRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = contactsRV.getChildViewHolder(view);
                if (holder instanceof ChoiceAdapter.ContactsViewHolder) {
                    int position = holder.getAdapterPosition();

                    // handling single tap.
                    Log.d("click", "clicked on item " + position);
                    ChoiceModel myModel = ChoiceModel.getSingleton();
                    Toast toast = Toast.makeText(getApplicationContext(),"Clicked on " +myModel.choiceList.get(position).name
                            , Toast.LENGTH_SHORT);

                    toast.show();
                    //outputTV.setText("Clicked on " + myModel.choiceList.get(position).name);
                    myModel.choiceList.remove(position);
                    choiceAdapter.notifyItemRemoved(position);
                    return true;
                }
            }
            return false;
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        choiceAdapter = new ChoiceAdapter();
        contactsRV = findViewById(R.id.ChoiceRV);
        contactsRV.setAdapter(choiceAdapter);
        addedText=findViewById(R.id.editText2);
        add=findViewById(R.id.addBtn);
        model= ChoiceModel.getSingleton();
        remove=findViewById(R.id.button2);


        RecyclerView.LayoutManager myManager = new LinearLayoutManager(this);
        contactsRV.setLayoutManager(myManager);
        // listener for tsps
        detector = new GestureDetectorCompat(this, new RecyclerViewOnGestureListener());

        contactsRV.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return detector.onTouchEvent(e);
            }
        });


        //adding list item to the list
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textAdded=addedText.getText().toString();
                Log.d("String",textAdded);
                model.choiceList.add(new ChoiceModel.Choice(textAdded));
                choiceAdapter.notifyItemInserted(model.choiceList.size()-1);




            }
        });


        //removing all the items from the list and loading the choices
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                model.reset();
                choiceAdapter.notifyDataSetChanged();


            }
        });


    }
}

package com.example.blackjack_play;

import java.util.ArrayList;

import com.example.blackjack_play.Blackjack;
import com.example.blackjack_play.Card;
import com.example.blackjack_play.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
    public static class PlaceholderFragment extends Fragment implements OnClickListener{
    	TextView nameTextView,playerTextView;
    	EditText inputName,inputMoney;
    	Button okButton,moneyButton,betButton,hitButton,stayButton,continueButton,quitButton;
    	ArrayList<ImageView> dealerCards, playerCards;
    	Blackjack game;
    	Animation animFadein;
    	RelativeLayout layout; 
    	int m=0,bm=0;
        public PlaceholderFragment() {
        }

        public int getIdentifierByString(String str){
        	int id = getActivity().getResources().getIdentifier(str, "id", getActivity().getPackageName());
        	return id;
        }
        public int getCardDawablByString(String suit , String face){
        	int id =getActivity().getResources().getIdentifier(suit+"_"+face, "drawable" , getActivity().getPackageName());
        	return id;
        }        
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {  
        	View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        	nameTextView=(TextView)rootView.findViewById(R.id.textView1);
			playerTextView=(TextView)rootView.findViewById(R.id.textView2);
//			conQuitTextView=(TextView)rootView.findViewById(R.id.textView3);
//			m1=(TextView)rootView.findViewById(R.id.textView4);
//			m2=(TextView)rootView.findViewById(R.id.textView5);
			inputName=(EditText)rootView.findViewById(R.id.editText1);
			inputMoney=(EditText)rootView.findViewById(R.id.editText2);
			okButton=(Button)rootView.findViewById(R.id.button1);
			moneyButton=(Button)rootView.findViewById(R.id.button6);
			betButton=(Button)rootView.findViewById(R.id.button7);
			hitButton=(Button)rootView.findViewById(R.id.button2);
			stayButton=(Button)rootView.findViewById(R.id.button3);
			continueButton=(Button)rootView.findViewById(R.id.button4);
			quitButton=(Button)rootView.findViewById(R.id.button5);
			layout =(RelativeLayout)rootView.findViewById(R.id.background);
			animFadein = AnimationUtils.loadAnimation(getActivity(), R.anim.myanim);
			
			layout.setBackgroundColor(0x88BBBBFF);
			dealerCards=new ArrayList<ImageView>();
			playerCards=new ArrayList<ImageView>();
			
			okButton.setOnClickListener(this);
			moneyButton.setOnClickListener(this);
			betButton.setOnClickListener(this);
			hitButton.setOnClickListener(this);
			stayButton.setOnClickListener(this);
			continueButton.setOnClickListener(this);
			quitButton.setOnClickListener(this);
			inputMoney.setVisibility(View.INVISIBLE);
			moneyButton.setVisibility(View.INVISIBLE);
			betButton.setVisibility(View.INVISIBLE);
			hitButton.setVisibility(View.INVISIBLE);
			stayButton.setVisibility(View.INVISIBLE);
			continueButton.setVisibility(View.INVISIBLE);
			quitButton.setVisibility(View.INVISIBLE);
			playerTextView.setVisibility(View.INVISIBLE);
			
        	for(int i=1; i<=10 ; i++){
        		int id1 = getIdentifierByString("imageView" + i);
        		int id2 = getIdentifierByString("imageView0" + i);
        		
        		ImageView v1 = (ImageView) rootView.findViewById(id1);
        		ImageView v2 = (ImageView) rootView.findViewById(id2);
        		v1.setVisibility(View.INVISIBLE);
        		v2.setVisibility(View.INVISIBLE);
        		
        		dealerCards.add(v1);
        		playerCards.add(v2);
        	}
        		
        	return rootView;
        }

		@Override
		public void onClick(View v) {
			
			// TODO Auto-generated method stub
			if(v == okButton){
				InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(inputName.getWindowToken(),0);						
				okButton.setVisibility(View.INVISIBLE);
				game = new Blackjack(inputName.getText().toString());
				animFadein = AnimationUtils.loadAnimation(getActivity(), R.anim.myanim);
				nameTextView.setText("Insert your own money:");
				inputName.setVisibility(View.INVISIBLE);
				inputMoney.setVisibility(View.VISIBLE);
				moneyButton.setVisibility(View.VISIBLE);
			}
			else if(v == moneyButton){
				m = Integer.parseInt(inputMoney.getText().toString());
				moneyButton.setVisibility(View.INVISIBLE);
				betButton.setVisibility(View.VISIBLE);
				nameTextView.setText("Insert money to bet:");
			}
			else if(v == betButton){
				bm = Integer.parseInt(inputMoney.getText().toString());
				betButton.setVisibility(View.INVISIBLE);
				inputMoney.setVisibility(View.INVISIBLE);
				nameTextView.setText("Game Start:");
				hitButton.setVisibility(View.VISIBLE);
				stayButton.setVisibility(View.VISIBLE);
				playerTextView.setVisibility(View.VISIBLE);
				for(int i=0 ; i<2 ; i++){
					dealerCards.get(i).setVisibility(View.VISIBLE);
					
					Card card =game.player.card(i);
			ImageView cardView = playerCards.get(i);
					cardView.setVisibility(View.VISIBLE);
					cardView.setImageResource(getCardDawablByString(card.suit(), card.face()));
				}
				nameTextView.setText("Dealer's cards: ");
				playerTextView.setText("Player's cards:"+ game.player.point() + " points" + game.player.state());
			}
			else if (v == hitButton){
				int i = game.player.cardCount();
	        	if(i >= 10)
	        		return;
	        	ImageView cardView = playerCards.get(i);
	        	game.hit();
	        	Card card = game.player.card(i);
	        	cardView.setImageResource(getCardDawablByString(card.suit(), card.face()));
	        	cardView.setVisibility(View.VISIBLE);
	        	cardView.startAnimation(animFadein);
	        	
	        	if (game.player.point() > 21){
					v=stayButton;
			}
			if(v == stayButton){
				while(game.dealer.point()<17){
					game.dealer.deal(game.dealer);
				}
				for(i=0;i<game.dealer.cardCount();i++){
					 card=game.dealer.card(i);
					 cardView=dealerCards.get(i);
					cardView.setVisibility(View.VISIBLE);
					cardView.startAnimation(animFadein);
					cardView.setImageResource(getCardDrawableByString(card.suit(),card.face()));
				}
				nameTextView.setVisibility(View.VISIBLE);
				nameTextView.setText("Dealer's cards: "+game.dealer.point()+" points "+game.dealer.state());
				if(game.compete()==1){
					m+=bm;
					playerTextView.setText("You win!!!  You have:"+m);
					hitButton.setVisibility(View.INVISIBLE);				
					stayButton.setVisibility(View.INVISIBLE);
					continueButton.setVisibility(View.VISIBLE);
					quitButton.setVisibility(View.VISIBLE);
				}
				else if(game.compete()==-1){
					m-=bm;
					playerTextView.setText("You lose!!!  You have:"+m);
					hitButton.setVisibility(View.INVISIBLE);				
					stayButton.setVisibility(View.INVISIBLE);
					continueButton.setVisibility(View.VISIBLE);
					quitButton.setVisibility(View.VISIBLE);
				}
				else{
					playerTextView.setText("draw!!!");
					hitButton.setVisibility(View.INVISIBLE);				
					stayButton.setVisibility(View.INVISIBLE);
					continueButton.setVisibility(View.VISIBLE);
					quitButton.setVisibility(View.VISIBLE);
				}
			
			}
			if(v==continueButton){
				if(m==0){
					Toast.makeText(getActivity(),"You still have:"+m, Toast.LENGTH_SHORT).show();
					return;
				}
				inputName.setText("");
				nameTextView.setText("Insert money to bet:");
				moneyButton.setVisibility(View.INVISIBLE);
				betButton.setVisibility(View.VISIBLE);			
				for(i=0;i<10;i++){
					dealerCards.get(i).setVisibility(View.INVISIBLE);
					playerCards.get(i).setVisibility(View.INVISIBLE);
				}
				
				playerTextView.setVisibility(View.INVISIBLE);
				nameTextView.setVisibility(View.VISIBLE);
				inputName.setVisibility(View.VISIBLE);
				betButton.setVisibility(View.VISIBLE);
				continueButton.setVisibility(View.INVISIBLE);
				quitButton.setVisibility(View.INVISIBLE);
			}
		}
	}
		private int getCardDrawableByString(String suit, String face) {
			// TODO Auto-generated method stub
			return 0;
		}
    }
 }

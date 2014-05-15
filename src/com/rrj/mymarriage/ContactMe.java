package com.rrj.mymarriage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class ContactMe extends Activity {
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    List<String> listGrpTime;
     HashMap<String, List<String>> listDataChild;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contactme);
		// get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
 
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild,listGrpTime);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
	}
	private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        listGrpTime = new ArrayList<String>();
        // Adding Group data
        listDataHeader.add("Kashi Yathre");
        listDataHeader.add("OONJAL(SWING)");
        listDataHeader.add("Muhurtam");
        listDataHeader.add("Kanya Danam");
        listDataHeader.add("Saptapadi");
        listDataHeader.add("Lunch");
        listDataHeader.add("Reception");
        listGrpTime.add("9:30");
        listGrpTime.add("10:00");
        listGrpTime.add("10:30-12:00");
        listGrpTime.add("12:15");
        listGrpTime.add("12:30");
        listGrpTime.add("13:00");
        listGrpTime.add("18:30");
        // Adding child data
        List<String> kashi = new ArrayList<String>();
        kashi.add("This a very important part of the ceremony. Immediately after his student-life, the young bachelor has two alternatives before him – Married life ( Grihasta ) or asceticism ( Sanyas ). Being by nature escapist, he prefers the ascetic life to the tribulations of married life. He therefore ‘makes his way’ to kasi (VARANASI), complete with slippers, umbrella, bamboo fan etc. On his way, the bride’s father intervenes and advises him of the superiority of married life to ascetic life. He also promises to give him his daughter as companion to face the challenge of life. The umbrella is to remain with groom, to remind him in the future, of this advice.the real tradition.");
        List<String> unchal = new ArrayList<String>();
        unchal.add("Then, the marrying couple is seated on a swing ( oonjal); they rock forth and back, as the ladies around sing – LAALI, songs to praise to the couple. The chains of the swing signify the eternal Karmic link with Almighty above; the to-and-fro motion represent the undulating sea-waves of life; yet, in mind and body they shall move in harmony unperturbed, steady and stable.");
        List<String> muhurtham = new ArrayList<String>();
        muhurtham.add("Next, timed to exact auspicious hour, is the tying of the Mangala Sutra ( Thaali ). The bride seated over a sheaf of grain-laden hay, looking eastward, and the bridegroom facing westward, ties the gold Mangala Sutra around the neck of the bride. As he does so, the Nadaswaram drums are beaten loud and fast, so as to muffle any inauspicious sounds at the critical hour. This is called Getti Melam; as it sounds, the Sumangali ladies sing \"GOURI KALYANAME, VAIBHOGAMAY! \"Three knots are tied – the first one by the bridegroom, the other two knots by his sister to make the bride a parts of the boy’s family. The Vedic hymn recited by the bridegroom when he ties the knot, is: \" Praying the Almighty that I be blessed with a long life, I tie this knot around your neck, oh soubhaygavati, may providence bestow on you a fulfilling life of a ‘Sumangalis’ for a hundred years to come! ");
        List<String> kanyaDanam = new ArrayList<String>();
        kanyaDanam.add("The bride is made to sit on her father’s lap and is given away as gift by him, to the bridegroom.On the girl’s head, a ring made with Kusa, the sacred grass called DARBHA, is placed, and over it, is placed a yoke; the Gold Mangal Sutra ( or THAALI ) is placed right on the aperture of the yoke, and water is poured through the aperture. The Mantras chanted at this time, say:\"Let this gold multiply your wealth! Let this water purify your married life, and may your prosperity increase. Offer yourself to your husband! \"The bride then is given an auspicious ablution, and an exclusive new KOORAI Saree is draped around her – this is done by the sister of the bridegroom.To bride in her new saree, a belt made of reed-grass is tied around the waist. The manthras chant:\"She standeth here, pure before the holy fire, as one blessed with boons of a good mind, a healthy body, life-long companionship of her husband ( Sumangali Bhagyam ) and children with long life. She standeth as one who is avowed to stand by her husband virtuously. Be she tied with this red-grass rope, to the sacrament of marriage!");
        List<String> saptapadi = new ArrayList<String>();
        saptapadi.add("Holding the bride’s hand, the bridegroom walks seven steps with her. This is the most important part of the marriage ceremony, and only when they walk seven steps together ( i.e. perform SAPTHA PADHI ) is the marriage complete legally. The belief is that when one walks 7 steps with another, one becomes the another’s friend. The manthras recited then, mean: \"Ye who have walked seven steps with me, become my companion, where by I acquire your friendship. We shall remain together inseparable. Let us make a vow together; we shall share love, share the same food, and share the strength, the same tastes. We shall be of one mind, we shall observe the vow together. I shall be the SAMA, you the RIG: I shall be the Upper World, you the earth; I shall be the SUKHILAM, you the HOLDER – together we shall live, beget children, and other riches, come thou, O sweet-worded girl!");
        List<String> lunch = new ArrayList<String>();
        lunch.add("Enjoy the wide variety of south Indian Delicacies");
        List<String>reception = new ArrayList<String>();
        reception.add("Wedding reception party is virtually the first public appearance of the couple together, after wedding where they receive the blessing and gifts from the society and relatives. It is just an extension of the wedding day and is characterized by opulence and magnificence. While the Decor of the place makes it looks mesmerizing and majestic, lavish cuisine and excellent dishes spice up the entire ambience of the reception party place");
        
        listDataChild.put(listDataHeader.get(0), kashi); // Header, Child data
        listDataChild.put(listDataHeader.get(1), unchal);
        listDataChild.put(listDataHeader.get(2), muhurtham);
        listDataChild.put(listDataHeader.get(3), kanyaDanam);
        listDataChild.put(listDataHeader.get(4), saptapadi);
        listDataChild.put(listDataHeader.get(5), lunch);
        listDataChild.put(listDataHeader.get(6), reception);
    }
}

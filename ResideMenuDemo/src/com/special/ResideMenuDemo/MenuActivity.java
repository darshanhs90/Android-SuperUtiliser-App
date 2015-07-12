package com.special.ResideMenuDemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MenuActivity extends FragmentActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private MenuActivity mContext;
    private ResideMenuItem      itemHome;
    private ResideMenuItem      itemOfflineHelp;
    private ResideMenuItem      itemDataPlan;
    private ResideMenuItem         itemPillTracker;
    private ResideMenuItem        itemPillIdentifier;
    private ResideMenuItem        itemGlucometer;
    private ResideMenuItem         itemInsulinHelper;
    private ResideMenuItem         itemCalendar;
    private ResideMenuItem         itemAppointmentPhonenumber;
    private ResideMenuItem         itemMedicaid;
    private ResideMenuItem         itemCentralReport;
    private ResideMenuItem         itemReminder;
    private ResideMenuItem        itemNotes;
    private ResideMenuItem         itemHospitalList;
    private ResideMenuItem         itemGeneticInfoSaver;
    private ResideMenuItem        itemCancerInfo;
    private ResideMenuItem        itemNutritionFood;
    private ResideMenuItem         itemQandA;
    private ResideMenuItem        itemMedicalcostOptimisation;
    private ResideMenuItem        itemDrugInfo;
    private ResideMenuItem        itemHealthInsuranceClaims;
    private ResideMenuItem        itemgetFDAHelp;;
    private ResideMenuItem         itemQueryWeb;
    private ResideMenuItem        itemIncomeTracker;
    private ResideMenuItem        itemBMITracker;
    private ResideMenuItem        itemGroceriesTracker;
    private ResideMenuItem        itemSmokingAndDrugTracker;
    private ResideMenuItem         itemTraumaHelper;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;
        setUpMenu();
        if( savedInstanceState == null )
            changeFragment(new HomeFragment());
    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        resideMenu.setScaleValue(0.7f);

        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.icon_home,     "Health Summary");//https://api.humanapi.co/v1/human?access_token=demo
        itemOfflineHelp     = new ResideMenuItem(this, R.drawable.icon_home,     "Offline Help");
        itemDataPlan     = new ResideMenuItem(this, R.drawable.icon_home,     "Health Care Providers");//http://api.bluebuttonconnector.healthit.gov/stage2?state=TX&zip=75150&limit=30&offset=0
        itemPillTracker     = new ResideMenuItem(this, R.drawable.icon_home,     "Pill Tracker");//done
        itemPillIdentifier     = new ResideMenuItem(this, R.drawable.icon_home,     "Get Exercise Info");//https://api.humanapi.co/v1/human/activities/summaries?access_token=demo
        itemGlucometer     = new ResideMenuItem(this, R.drawable.icon_home,     "Glucometer Help");//http://www.myglucohealth.net/forum/default.aspx
        itemInsulinHelper     = new ResideMenuItem(this, R.drawable.icon_home,     "Insulin Helper");//???
        itemCalendar     = new ResideMenuItem(this, R.drawable.icon_home,     "Calendar");//done
        itemAppointmentPhonenumber     = new ResideMenuItem(this, R.drawable.icon_home,     "Appointment Helper");//???
        itemMedicaid     = new ResideMenuItem(this, R.drawable.icon_home,     "MedicAid");//???
        itemCentralReport     = new ResideMenuItem(this, R.drawable.icon_home,     "Data Usage Info");//http://stackoverflow.com/questions/24366016/how-to-get-programmatically-the-data-usage-limit-set-by-user-on-android-os-confi
        itemReminder     = new ResideMenuItem(this, R.drawable.icon_home,     "Get Medication Info");//https://api.humanapi.co/v1/human/medical/medications?access_token=demo
        itemNotes     = new ResideMenuItem(this, R.drawable.icon_home,     "Insurance Claims");//https://platform.pokitdok.com/#/--node
        itemHospitalList     = new ResideMenuItem(this, R.drawable.icon_home,     "Hospital List");////http://api.bluebuttonconnector.healthit.gov/organizations?limit=30&offset=0
        itemGeneticInfoSaver     = new ResideMenuItem(this, R.drawable.icon_home,     "Save Genetic Info");//https://api.23andme.com/docs/reference/--node
        itemCancerInfo     = new ResideMenuItem(this, R.drawable.icon_home,     "Get Cancer Info");//done
        itemNutritionFood     = new ResideMenuItem(this, R.drawable.icon_home,     "Food Info Search");//https://api.edamam.com/search?q=chicken&app_id=8e9d063b&app_key=8657a32df16db3063e61214978826997
        itemQandA     = new ResideMenuItem(this, R.drawable.icon_home,     "My Profile");//https://api.humanapi.co/v1/human/medical/profile?access_token=demo
        itemMedicalcostOptimisation     = new ResideMenuItem(this, R.drawable.icon_home,     "Medical Issues");//https://api.humanapi.co/v1/human/medical/issues?access_token=demo
        itemDrugInfo= new ResideMenuItem(this, R.drawable.icon_home,     "Allergies");//https://api.humanapi.co/v1/human/medical/allergies?access_token=demo
        itemHealthInsuranceClaims= new ResideMenuItem(this, R.drawable.icon_home,     "Test Results");//https://api.humanapi.co/v1/human/medical/test_results?access_token=demo
        itemgetFDAHelp= new ResideMenuItem(this, R.drawable.icon_home,     "Get Meals Info");//https://api.humanapi.co/v1/human/food/meals?access_token=demo
        itemQueryWeb= new ResideMenuItem(this, R.drawable.icon_home,     "Narratives");//https://api.humanapi.co/v1/human/medical/narratives?access_token=demo
        itemIncomeTracker= new ResideMenuItem(this, R.drawable.icon_home,     "Income Tracker");//Inbuilt
        itemBMITracker= new ResideMenuItem(this, R.drawable.icon_home,     "SomeOther Thing");//???
        itemGroceriesTracker= new ResideMenuItem(this, R.drawable.icon_home,     "Groceries Map");//done
        itemSmokingAndDrugTracker= new ResideMenuItem(this, R.drawable.icon_home,     "Habits Tracker");//https://api.humanapi.co/v1/human/activities?access_token=demo
        itemTraumaHelper= new ResideMenuItem(this, R.drawable.icon_home,     "Trauma Helper");//???




        itemHome.setOnClickListener(this);
        itemOfflineHelp.setOnClickListener(this);
        itemPillTracker.setOnClickListener(this);
        itemPillIdentifier.setOnClickListener(this);
        itemGlucometer.setOnClickListener(this);
        itemInsulinHelper.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);
        itemAppointmentPhonenumber.setOnClickListener(this);
        itemMedicaid.setOnClickListener(this);
        itemCentralReport.setOnClickListener(this);
        itemReminder.setOnClickListener(this);
        itemNotes.setOnClickListener(this);
        itemHospitalList.setOnClickListener(this);
        itemGeneticInfoSaver.setOnClickListener(this);
        itemCancerInfo.setOnClickListener(this);
        itemNutritionFood.setOnClickListener(this);
        itemQandA.setOnClickListener(this);
        itemMedicalcostOptimisation.setOnClickListener(this);
        itemDrugInfo.setOnClickListener(this);
        itemHealthInsuranceClaims.setOnClickListener(this);
        itemgetFDAHelp.setOnClickListener(this);
        itemQueryWeb.setOnClickListener(this);
        itemIncomeTracker.setOnClickListener(this);
        itemBMITracker.setOnClickListener(this);
        itemGroceriesTracker.setOnClickListener(this);
        itemSmokingAndDrugTracker.setOnClickListener(this);
        itemTraumaHelper.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemOfflineHelp, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemPillTracker, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemPillIdentifier, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemGlucometer, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemInsulinHelper, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemAppointmentPhonenumber, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemMedicaid, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemCentralReport, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemReminder, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemNotes, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemHospitalList, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemGeneticInfoSaver, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCancerInfo, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemNutritionFood, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemQandA, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemMedicalcostOptimisation, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemDrugInfo, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemHealthInsuranceClaims, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemgetFDAHelp, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemQueryWeb, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemIncomeTracker, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemBMITracker, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemGroceriesTracker, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemSmokingAndDrugTracker, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemTraumaHelper, ResideMenu.DIRECTION_RIGHT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemHome){
            changeFragment(new HomeFragment());
        }else if(view ==itemOfflineHelp){
            changeFragment(new OfflineHelpFragment());
        }
        else if(view ==itemPillTracker){
            changeFragment(new PillTrackerFragment());
        }else if(view ==itemPillIdentifier){
            changeFragment(new PillIdentifierFragment());
        }else if(view ==itemGlucometer){
            changeFragment(new GlucometerFragment());
        }else if(view ==itemInsulinHelper){
            changeFragment(new InsulinHelperFragment());
        }else if(view ==itemCalendar){
            changeFragment(new CalendarFragment());
        }else if(view ==itemAppointmentPhonenumber){
            changeFragment(new AppointmentPhonenumberFragment());
        }else if(view ==itemMedicaid){
            changeFragment(new MedicaidFragment());
        }else if(view ==itemCentralReport){
            changeFragment(new CentralReportFragment());
        }else if(view ==itemReminder){
            changeFragment(new ReminderFragment());
        }else if(view ==itemNotes){
            changeFragment(new NotesFragment());
        }else if(view ==itemHospitalList){
            changeFragment(new HospitalListFragment());
        }else if(view ==itemGeneticInfoSaver){
            changeFragment(new GeneticInfoSaverFragment());
        }else if(view ==itemCancerInfo){
            changeFragment(new CancerInfoFragment());
        }else if(view ==itemNutritionFood){
            changeFragment(new NutritionFoodFragment());
        }else if(view ==itemQandA){
            changeFragment(new QandAFragment());
        }else if(view ==itemMedicalcostOptimisation){
            changeFragment(new MedicalcostOptimisationFragment());
        }else if(view ==itemDrugInfo){
            changeFragment(new DrugInfoFragment());
        }else if(view ==itemHealthInsuranceClaims){
            changeFragment(new HealthInsuranceClaimsFragment());
        }else if(view ==itemgetFDAHelp){
            changeFragment(new FDAHelpFragment());
        }else if(view ==itemQueryWeb){
            changeFragment(new HomeFragment());
        }else if(view ==itemIncomeTracker){
            changeFragment(new IncomeTrackerFragment());
        }else if(view ==itemBMITracker){
            changeFragment(new BMITrackerFragment());
        }else if(view ==itemGroceriesTracker){
            changeFragment(new GroceriesTrackerFragment());
        }else if(view ==itemSmokingAndDrugTracker){
            changeFragment(new SmokingAndDrugTrackerFragment());
        }else if(view ==itemTraumaHelper){
            changeFragment(new TraumaHelperFragment());
        }
        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            //Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            //Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
}

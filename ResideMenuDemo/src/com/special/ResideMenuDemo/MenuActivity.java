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
    private ResideMenuItem         itemTraumaHelper,itemInsuranceProvider,itemSpecialitiesSearch;


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
        itemHome     = new ResideMenuItem(this, R.drawable.icon_home,     "Home");//done
        itemOfflineHelp     = new ResideMenuItem(this, R.drawable.icon_home,     "Health Summary");//done
        itemDataPlan     = new ResideMenuItem(this, R.drawable.icon_home,     "Health Care Providers");//done
        itemPillTracker     = new ResideMenuItem(this, R.drawable.icon_home,     "Pill Tracker");//done
        itemPillIdentifier     = new ResideMenuItem(this, R.drawable.icon_home,     "Get Exercise Info");//done
        itemGlucometer     = new ResideMenuItem(this, R.drawable.icon_home,     "Glucometer Help");//done
        itemInsulinHelper     = new ResideMenuItem(this, R.drawable.icon_home,     "Ask A Doctor");//done
        itemCalendar     = new ResideMenuItem(this, R.drawable.icon_home,     "Calendar");//done
        itemAppointmentPhonenumber     = new ResideMenuItem(this, R.drawable.icon_home,     "Lawyer Help");//done
        itemMedicaid     = new ResideMenuItem(this, R.drawable.icon_home,     "Get Doctor Tips");//done
        itemCentralReport     = new ResideMenuItem(this, R.drawable.icon_home,     "Doctor Search");//done
        itemReminder     = new ResideMenuItem(this, R.drawable.icon_home,     "Get Medication Info");//done
        itemNotes     = new ResideMenuItem(this, R.drawable.icon_home,     "Insurance Plans and Info");//done
        itemHospitalList     = new ResideMenuItem(this, R.drawable.icon_home,     "Hospital List");//done
        itemGeneticInfoSaver     = new ResideMenuItem(this, R.drawable.icon_home,     "Doctors Directory");//done
        itemCancerInfo     = new ResideMenuItem(this, R.drawable.icon_home,     "Get Cancer Info");//done
        itemNutritionFood     = new ResideMenuItem(this, R.drawable.icon_home,     "Food Insight");//done
        itemQandA     = new ResideMenuItem(this, R.drawable.icon_home,     "My Profile");//done
        itemMedicalcostOptimisation     = new ResideMenuItem(this, R.drawable.icon_home,     "Medical Issues");//done
        itemDrugInfo= new ResideMenuItem(this, R.drawable.icon_home,     "Allergies");//done
        itemHealthInsuranceClaims= new ResideMenuItem(this, R.drawable.icon_home,     "Test Results");//done
        itemgetFDAHelp= new ResideMenuItem(this, R.drawable.icon_home,     "Get Meals Info");//done
        itemQueryWeb= new ResideMenuItem(this, R.drawable.icon_home,     "Narratives");//done
        itemIncomeTracker= new ResideMenuItem(this, R.drawable.icon_home,     "Income Tracker");//done
        itemBMITracker= new ResideMenuItem(this, R.drawable.icon_home,     "Data Usage");//done
        itemGroceriesTracker= new ResideMenuItem(this, R.drawable.icon_home,     "Groceries Map");//done
        itemSmokingAndDrugTracker= new ResideMenuItem(this, R.drawable.icon_home,     "Habits Tracker");//done
        itemTraumaHelper= new ResideMenuItem(this, R.drawable.icon_home,     "About Us");//done
        itemInsuranceProvider= new ResideMenuItem(this, R.drawable.icon_home,     "Insurance Provider Search");//done
        itemSpecialitiesSearch= new ResideMenuItem(this, R.drawable.icon_home,     "Specialities Search");//done




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
        itemDataPlan.setOnClickListener(this);
        itemInsuranceProvider.setOnClickListener(this);
        itemSpecialitiesSearch.setOnClickListener(this);

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
        resideMenu.addMenuItem(itemInsuranceProvider, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemDataPlan, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemTraumaHelper, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemSpecialitiesSearch, ResideMenu.DIRECTION_LEFT);

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
            changeFragment(new QueryWebFragment());
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
        }else if(view ==itemDataPlan){
            changeFragment(new DataPlanFragment());
        }else if(view ==itemInsuranceProvider){
            changeFragment(new InsuranceProviderFragment());
        }else if(view ==itemSpecialitiesSearch){
            changeFragment(new SpecialitiesSearchFragment());
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

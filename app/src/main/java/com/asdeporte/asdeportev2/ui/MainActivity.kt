package com.asdeporte.asdeportev2.ui

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.asdeporte.asdeportev2.R
import com.asdeporte.asdeportev2.data.RetrofitHelper
import com.asdeporte.asdeportev2.data.api.UserApi
import com.asdeporte.asdeportev2.data.calls.UserCall
import com.asdeporte.asdeportev2.databinding.ActivityMainBinding
import com.asdeporte.asdeportev2.utils.Constants
import com.asdeporte.asdeportev2.utils.SharedPreferencesAsd
import com.asdeporte.hermes.Hermes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
/*import com.google.firebase.FirebaseApp
import com.sportmaniac.core_copernico.ioc.CCServiceFactory
import com.sportmaniac.core_copernico.ioc.DefaultServiceFactory
import com.sportmaniac.core_proxy.ioc.CPServiceFactory
import com.sportmaniac.core_sportmaniacs.ioc.CSServiceFactory
import com.sportmaniac.core_virtual.ioc.CVServiceFactory
import com.sportmaniac.view_commons.ViewCommonsApp
import com.sportmaniac.view_live.ViewLiveApp
import com.sportmaniac.view_rankings.ViewRankingsApp
import com.sportmaniac.view_virtual.ViewVirtualApp*/
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.util.*

class MainActivity : AppCompatActivity() {

    //Sportmaniacs
    // URL a la API de Copérnico, en principio no hace falta cambiarlo
    private val COPERNICO_API = "https://public-api.copernico.cloud/api/"
    // URL al socket de Copérnico, en principio no hace falta cambiarlo
    private val SOCKET_ENDPOINT = "http://socket.sportmaniacs.com:4321/"
    // URL para la API de Sportmaniacs
    private val SPORTMANIACS_API = "https://api-aws.sportmaniacs.com/api/"
    //    private static final String BASE_SPORTMANIACS = "https://api-tests.sportmaniacs.com/api/";
    //    private static final String BASE_SPORTMANIACS = "https://api-tests.sportmaniacs.com/api/";
    private val VIRTUAL_API = "https://api.copernico.cloud/api/"
    // URL para la consulta de inscripciones de Sportmaniacs (puede ser "")
    private val STORAGE_API = "https://api.copernico.cloud/storage/"
    //
    val RACES_GROUP = "asdeporte"

    /*private var coreVirtualServiceModule: CVServiceFactory? = null
    private var coreCopernicoFactory: CCServiceFactory? = null
    private var coreSportmaniacsFactory: CSServiceFactory? = null
    private var coreProxyFactory: CPServiceFactory? = null

    var viewLiveApp: ViewLiveApp? = null
    var viewRankingsApp: ViewRankingsApp? = null
    private var viewCommonsApp: ViewCommonsApp? = null
    var viewVirtualApp: ViewVirtualApp? = null
    private var analyticsService: AnalyticsEmpty? = null*/

    init {
        myInstance = this
    }
    companion object {

        private var myInstance: MainActivity? = null

        fun getApplicationContext(): Context {
            return myInstance!!.applicationContext
        }
    }


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_tribu, R.id.navigation_more
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener {
            NavigationUI.onNavDestinationSelected(it, navController)
            this.invalidateOptionsMenu()
            return@setOnItemSelectedListener true
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> showNavigationBar()
                R.id.navigation_dashboard -> showNavigationBar()
                R.id.navigation_tribu -> showNavigationBar()
                R.id.navigation_more -> showNavigationBar()
                R.id.navigation_notifications -> hideNavigationBar()

                else -> hideNavigationBar()
            }
        }

        supportActionBar?.elevation = 0F
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.orange_as_light)))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.asd_logo_large)

        binding.toolbar.title = "Asdeporte"
        binding.toolbar.background = ColorDrawable(ContextCompat.getColor(this, R.color.orange_as_light))
        binding.toolbar.visibility = View.GONE

        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)

        setupInits()
        //getUser()

        //initCoresLibraries()
        //initViewLibraries()
    }

    fun setupInits() {
        Hermes.Builder.apiKey = "pub_nLbIjlCWNKAdB_O3qgTaoQ"
        Hermes.Builder.baseUrl = "https://ms.asdeporte.com/v1/"
        Hermes.Builder.appVersion = Build.VERSION.RELEASE
    }

    /*
    Sportmaniacs
     */
    /*private fun initCoresLibraries() {
        coreCopernicoFactory = DefaultServiceFactory(
            this,
            COPERNICO_API,
            SOCKET_ENDPOINT,
            VIRTUAL_API,
            false)

        coreSportmaniacsFactory = com.sportmaniac.core_sportmaniacs.ioc.DefaultServiceFactory(
            this,
            COPERNICO_API,
            SPORTMANIACS_API
        )

        coreVirtualServiceModule = CVServiceFactory(
            this,
            STORAGE_API,
            VIRTUAL_API,
            coreCopernicoFactory,
            analyticsService) // NEW in 1.4.8)

        // NEW in 1.4.8

        // NEW in 1.4.8
        coreProxyFactory = CPServiceFactory(VIRTUAL_API, RACES_GROUP)
    }
    private fun initViewLibraries() {
        //val analyticsService = AnalyticsEmpty()
        FirebaseApp.initializeApp(this)

        viewCommonsApp = ViewCommonsApp.Builder(application)
            .coreCopernicoFactory(coreCopernicoFactory)
            .coreSportmaniacsFactory(coreSportmaniacsFactory)
            .coreProxyFactory(coreProxyFactory) // NEW in 1.4.8
            .analyticsService(analyticsService)
            .copernicoUrl(COPERNICO_API)
            .build()

        viewLiveApp = ViewLiveApp.Builder(application)
            .racesGroup(RACES_GROUP)
            .urlCopernico(COPERNICO_API)
            .coreCopernicoFactory(coreCopernicoFactory)
            .coreSportmaniacsFactory(coreSportmaniacsFactory)
            .analyticsService(analyticsService)
            .enableAutologin(Constants.SPORTMANIACS_USER, Constants.SPORTMANIACS_PSW)
            .tabProfileVisible(false)
            .build()

        viewRankingsApp = ViewRankingsApp.Builder(application)
            .urlSportmaniacs(SPORTMANIACS_API)
            .analyticsService(analyticsService)
            .coreSportmaniacsFactory(coreSportmaniacsFactory)
            .coreSportmaniacsFactory(coreSportmaniacsFactory) // NEW in 1.4.8
            .build()

        viewVirtualApp = ViewVirtualApp.Builder(application)
            .setCvServiceFactory(coreVirtualServiceModule)
            .setCoreCopernicoFactory(coreCopernicoFactory)
            .setCoreSMFactory(coreSportmaniacsFactory)
            .setAnalyticsService(analyticsService)
            .setCoreProxyFactory(coreProxyFactory) // NEW in 1.4.8
            .build()
    }*/

    /*
    Data
     */
    private fun onGetUser(user: UserCall.UserResult) {
        user.user?.let {
            //binding.textDashboard.text = it.first_name
        } ?: run {
            //user.message
            Toast.makeText(this, user.message, Toast.LENGTH_SHORT).show()
        }
    }

    /*
    Services
     */
    @OptIn(DelicateCoroutinesApi::class)
    private fun getUser() {

        //val userid = "bb94d8ef-765b-4fcf-8638-77b2a2f72830"//SharedPreferencesAsd.getUserId(requireContext())
        val userid = SharedPreferencesAsd.getUserId(this)
        println("getUserid: $userid")

        val quotesApi = RetrofitHelper.getInstance().create(UserApi::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = quotesApi.getUser(null, Locale.getDefault().isO3Language, userid ?: "")
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        println("isSuccessful: ${response.code()}")
                        response.body()?.data?.userid?.let {
                            onGetUser(UserCall.UserResult(response.body()?.data, null))
                        } ?: run {
                            onGetUser(UserCall.UserResult(null, "Ooops: Something else went wrong"))
                        }

                    } else {
                        println("Error: ${response.code()}")
                        val error = RetrofitHelper.getMessageException(response.errorBody()?.charStream()?.readText())
                        println("Error message: ${error.message}")
                        onGetUser(UserCall.UserResult(null, error.message))
                    }
                }
            } catch (e: HttpException) {
                println("Exception ${e.message}")
                onGetUser(UserCall.UserResult(null, e.message()))
            } catch (e: Throwable) {
                onGetUser(UserCall.UserResult(null, "Ooops: Something else went wrong"))
                println("Ooops: Something else went wrong")
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tribu_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.navigation_suscription -> {
                startActivity(Intent(this, SuscriptionActivity::class.java))
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                true
            }
            R.id.navigation_tribu -> {
                // start activity
                true
            }
            R.id.navigation_profile -> {
                println("navigation_profile")
                Navigation.findNavController(this, R.id.nav_host_fragment_activity_main).navigate(R.id.profileTribuAction)
                //findNavController().navigate(R.id.detailsTribuAction)
                true
            }
            R.id.navigation_notifications -> {
                println("navigation_profile")
                Navigation.findNavController(this, R.id.nav_host_fragment_activity_main).navigate(R.id.toNotificationsAction)

                //findNavController().navigate(R.id.detailsTribuAction)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

        //Navigation.findNavController(this, R.id.nav_host_fragment_activity_main).navigate(R.id.toNotificationsAction)
    }
    fun showTribuTab() {
        Navigation.findNavController(this, R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_tribu)
    }

    /*
    Action bar
     */
    fun showActionBar() {
        println("showActionBar")
        supportActionBar?.show()
    }
    fun hideActionBar() {
        println("hideActionBar")
        supportActionBar?.hide()
    }

    /*
    Navigation bar
     */
    fun showNavigationBar() {
        println("showNavigationBar")
        binding.navView.visibility = View.VISIBLE
    }
    fun hideNavigationBar() {
        println("hideNavigationBar")
        binding.navView.visibility = View.GONE
    }

    /*
    Loading
    */
    fun showLoading() {
        println("showLoading")
        binding.loadingBar.visibility = View.VISIBLE
    }
    fun hideLoading() {
        println("hideLoading")
        binding.loadingBar.visibility = View.GONE
    }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val navigation = Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment_activity_main)
            val current = Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment_activity_main).currentDestination
            println("current: $current")
            println("currentDestination: ${R.layout.fragment_notifications}")
            //println("navigation: ${navigation.findDestination(R.id.navigation_notifications)}")

            when(current) {
                navigation.findDestination(R.id.navigation_home) -> {
                    showFinishDialog()
                }
                navigation.findDestination(R.id.navigation_notifications) -> {
                    showActionBar()
                }
                navigation.findDestination(R.id.navigation_new_post) -> {
                    showActionBar()
                }
                else -> {
                    showActionBar()
                }
            }
            Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment_activity_main).navigateUp()
        }
    }
    private fun showFinishDialog(){
        MaterialAlertDialogBuilder(this).apply {
            setTitle("are you sure?")
            setMessage("want to close the application ?")
            setPositiveButton("Yes") { _, _ -> finish() }
            setNegativeButton("No", null)
            show()
        }
    }

}
package com.prollery.notetakerapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.prollery.notetakerapp.R
import com.prollery.notetakerapp.common.extensions.navigateSafely
import com.prollery.notetakerapp.databinding.ActivityMainBinding
import com.prollery.notetakerapp.ui.viewmodels.NotesViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val notesViewModel : NotesViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.toolbar.title = getString(R.string.app_name)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
//            val random = Random().nextInt()
//            val note = Note(title = "Note $random"
//                , description = "Desc sdfsdf sf g  $random $random $random"
//                , createdTimestamp = System.currentTimeMillis().toString()
//                , createdTimestampInMillis = System.currentTimeMillis()
//                , modifiedTimestamp = System.currentTimeMillis().toString()
//                , modifiedTimestampInMillis = System.currentTimeMillis()
//            )
//            notesViewModel.createNote(note)

            navController.navigateSafely(R.id.action_notesFragment_to_addNotesFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
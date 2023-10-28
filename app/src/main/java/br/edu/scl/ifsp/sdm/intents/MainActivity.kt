package br.edu.scl.ifsp.sdm.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import br.edu.scl.ifsp.sdm.intents.Extras.PARAMETER_EXTRA
import br.edu.scl.ifsp.sdm.intents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //Usando o lazzy garanto que ele sera inicializado apenas qunado necessario
    //Na aula foi usado o lateinit var e inicializado no onCreate
    private val parameterResultLauncher: ActivityResultLauncher<Intent> by lazy {
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result?.data?.getStringExtra(PARAMETER_EXTRA).also {
                    activityMainBinding.parameterTv.text = it
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)
        setSupportActionBar(activityMainBinding.toolbarIn.toolbar)
        supportActionBar?.subtitle = localClassName

        activityMainBinding.apply {
            parameterBt.setOnClickListener {
                val parameterIntent =
                    Intent(this@MainActivity, ParameterActivity::class.java).apply {
                        putExtra(PARAMETER_EXTRA, parameterTv.text.toString())
                    }
                //Forma depreciada
                //startActivityForResult(parameterIntent, PARAMETER_REQUEST_CODE)
                parameterResultLauncher.launch(parameterIntent)
            }
        }
    }
    // Forma depreciada
    //    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    //        super.onActivityResult(requestCode, resultCode, data)
    //        if (requestCode == 0 && resultCode == RESULT_OK) {
    //            data?.getStringExtra(PARAMETER_EXTRA).also {
    //                activityMainBinding.parameterTv.text = it
    //            }
    //        }
    //    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.openActivityMi -> {
                val parameterIntent = Intent("OPEN_PARAMETER_ACTIVITY").apply {
                    putExtra(PARAMETER_EXTRA, activityMainBinding.parameterTv.text.toString())
                }
                parameterResultLauncher.launch(parameterIntent)
                true
            }

            R.id.viewMi -> {
                true
            }

            R.id.callMi -> {
                true
            }

            R.id.dialMi -> {
                true
            }

            R.id.pickMi -> {
                true
            }

            R.id.chooserMi -> {
                true
            }

            else -> {
                false
            }
        }
    }

    companion object {
        // Forma depreciada
//        private const val PARAMETER_REQUEST_CODE = 0
    }
}
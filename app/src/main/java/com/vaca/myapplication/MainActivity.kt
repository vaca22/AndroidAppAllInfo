package com.vaca.myapplication

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.vaca.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    private fun getPackages() {
        // 获取已经安装的所有应用, PackageInfo　系统类，包含应用信息
        val packages: List<*> = packageManager.getInstalledPackages(0)
        for (i in packages.indices) {
            val packageInfo: PackageInfo = packages[i] as PackageInfo
            if (packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) { //非系统应用


                   val a= packageInfo.applicationInfo.loadLabel(packageManager).toString() //获取应用名称
               val b= packageInfo.packageName //获取应用包名，可用于卸载和启动应用
                val c=packageInfo.versionName //获取应用版本名
                val d=packageInfo.versionCode //获取应用版本号
                val e=packageInfo.applicationInfo.loadIcon(packageManager)
                Log.e("gaga","$a   $b   $c   $d")

            } else { // 系统应用
            }
        }
    }

    fun deleteApp(){
        val uri: Uri = Uri.fromParts("package", "com.jingdong.app.mall", null)
        val intent = Intent(Intent.ACTION_DELETE, uri)
        startActivity(intent)
    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPackages()
        deleteApp()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
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
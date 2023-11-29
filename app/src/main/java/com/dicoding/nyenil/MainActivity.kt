package com.dicoding.nyenil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.nyenil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var rvJajan: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Jajan>()
    private fun showSelectedHero(hero: Jajan) {
        Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ViewJajanDesc::class.java)
        intent.putExtra(ViewJajanDesc.EXTRA_JAJAN, hero)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rvJajan = binding.rvJajan
        rvJajan.setHasFixedSize(true)
        list.addAll(getListJajan())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvJajan.layoutManager = LinearLayoutManager(this)
        val listJajanAdapter = ListJajanAdapter(list)
        rvJajan.adapter = listJajanAdapter
        listJajanAdapter.setOnItemClickCallback(object : ListJajanAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Jajan) {
                showSelectedHero(data)
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toProfile -> {
                // Handle the button click event
                startActivity(Intent(this, ViewProfile::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListJajan(): ArrayList<Jajan> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataHeadline = resources.getStringArray(R.array.data_headline)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataRasa = resources.getStringArray(R.array.data_rasa)
        val dataAsal = resources.getStringArray(R.array.data_asal)
        val dataBahanDasar = resources.getStringArray(R.array.data_bahan_dasar)
        val dataResep = resources.getStringArray(R.array.data_resep)
        val listJajan = ArrayList<Jajan>()
        for (i in dataName.indices) {
            val jajan = Jajan(dataName[i], dataHeadline[i], dataPhoto.getResourceId(i, -1), dataDescription[i], dataAsal[i], dataBahanDasar[i], dataRasa[i], dataResep[i])
            listJajan.add(jajan)
        }
        return listJajan
    }

}
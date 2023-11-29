package com.dicoding.nyenil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.dicoding.nyenil.databinding.ActivityViewJajanDescBinding



class ViewJajanDesc : AppCompatActivity() {
    private lateinit var binding: ActivityViewJajanDescBinding
    companion object {
        const val EXTRA_JAJAN = "extra_jajan"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewJajanDescBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val jajan = intent.getParcelableExtra<Jajan>(EXTRA_JAJAN)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = jajan?.name ?: "Item Details"
        if (jajan != null) {
            binding.imgJajan.setImageResource(jajan.photo ?: R.drawable.cenil)
            binding.tvDetailName.text = jajan.name
            binding.tvDetailDescription.text = jajan.description
            binding.dataAsal.text = jajan.asal
            binding.dataBahanDasar.text = jajan.bahan_dasar
            binding.dataRasa.text=jajan.rasa
            binding.tvResep.text=jajan.resep
        }
        binding.btnShare.setOnClickListener {
            jajan?.let { it1 -> shareJajan(it1) }
        }
    }

    private fun shareJajan(jajan: Jajan) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, jajan.name)
        shareIntent.putExtra(Intent.EXTRA_TEXT, "${jajan.name}\n${jajan.resep}")
        startActivity(Intent.createChooser(shareIntent, "Share using"))
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
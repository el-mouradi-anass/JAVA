package com.example.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etPoids = findViewById<EditText>(R.id.etPoids)
        val etTaille = findViewById<EditText>(R.id.etTaille)
        val rbHomme = findViewById<RadioButton>(R.id.rbHomme)
        val rbFemme = findViewById<RadioButton>(R.id.rbFemme)
        val btnCalculer = findViewById<Button>(R.id.btnCalculer)
        val btnEffacer = findViewById<Button>(R.id.btnEffacer)
        val tvResultat = findViewById<TextView>(R.id.tvResultat)
        val tvAvis = findViewById<TextView>(R.id.tvAvis)

        btnCalculer.setOnClickListener {

            if (etPoids.text.isEmpty() || etTaille.text.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val poids = etPoids.text.toString().toDouble()
            val taille = etTaille.text.toString().toDouble()

            // a = 1 femme, a = 0 homme
            val a = if (rbFemme.isChecked) 1 else 0

            val imc = poids / (taille * taille) + a

            tvResultat.text = "Résultat : %.2f".format(imc)

            // Avis de l’expert
            val avis = when {
                imc < 21 -> "Maigre"
                imc < 23 -> "Normal"
                else -> "Obèse"
            }

            tvAvis.text = "Avis de l’expert : $avis"
        }

        btnEffacer.setOnClickListener {
            etPoids.text.clear()
            etTaille.text.clear()
            rbHomme.isChecked = false
            rbFemme.isChecked = false
            tvResultat.text = "Résultat : "
            tvAvis.text = "Avis de l’expert : "
        }
    }
}
